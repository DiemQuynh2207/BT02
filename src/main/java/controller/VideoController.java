package controller;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Video;
import service.IVideoService;
import service.impl.VideoService;

@SuppressWarnings("serial")
@WebServlet (urlPatterns = {"/admin/videos", "/admin/video/edit", "/admin/video/update"
		,"/admin/video/insert", "/admin/video/add", "/admin/video/delete", "/admin/video/search"})
public class VideoController extends HttpServlet{
	public IVideoService videoService = new VideoService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		String url = req.getRequestURI();
		if(url.contains("videos")) {
			List<Video> list = videoService.findAll();
			req.setAttribute("listvideo", list);
			req.getRequestDispatcher("/views/admin/video-list.jsp").forward(req, resp);
		}
		else if(url.contains("/admin/video/edit")){
			String id = req.getParameter("id");
			Video video = videoService.findById(id);
			req.setAttribute("cate", video);
			req.getRequestDispatcher("/views/admin/video-edit.jsp").forward(req, resp);
		}
		else if(url.contains("/admin/video/add")){
			req.getRequestDispatcher("/views/admin/video-add.jsp").forward(req, resp);
		}
		else if(url.contains("/admin/video/delete")){
			String id = req.getParameter("id");
			System.out.println("test id: ");
			System.out.println(id);
			Video video = videoService.findById(id);
			try {
				videoService.delete(video.getVideoId());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			resp.sendRedirect(req.getContextPath() + "/admin/videos");
		}
		
		
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		String url = req.getRequestURI();
		if(url.contains("/admin/video/update")) {
			String videoid = req.getParameter("videoid");
			String description = req.getParameter("description");
			String active = req.getParameter("active");
			String poster = "https://stcv4.hnammobile.com/downloads/2/goi-y-top-30-bo-phim-tinh-cam-han-quoc-khong-the-bo-qua-41675351263.jpg";
			String title = req.getParameter("title");
			String views = req.getParameter("views");

			
			Video video = new Video();
			video.setVideoId(videoid);
			video.setActive(active);
			video.setDescription(description);
			video.setPoster(poster);
			video.setTitle(title);
			video.setViews(views);
			
			videoService.update(video);
			resp.sendRedirect(req.getContextPath() + "/admin/videos");
			
		}
		else if(url.contains("/admin/video/insert")) {
			String videoid = req.getParameter("videoid");
			String description = req.getParameter("description");
			String active = req.getParameter("active");
			String poster = "https://stcv4.hnammobile.com/downloads/2/goi-y-top-30-bo-phim-tinh-cam-han-quoc-khong-the-bo-qua-41675351263.jpg";
			String title = req.getParameter("title");
			String views = req.getParameter("views");
			
			Video video = new Video();
			video.setVideoId(videoid);
			video.setActive(active);
			video.setDescription(description);
			video.setPoster(poster);
			video.setTitle(title);
			video.setViews(views);
			videoService.insert(video);
			resp.sendRedirect(req.getContextPath() + "/admin/videos");
		}
		/*
		 * else if(url.contains("/admin/category/search")) {
		 * System.out.println("test find: "); String name =
		 * req.getParameter("catesearch"); System.out.println("test catename: ");
		 * System.out.println(name); List<Category> listResult =
		 * videoService.findByCategoryname(name); req.setAttribute("listResult",
		 * listResult);
		 * req.getRequestDispatcher("/views/admin/category-find.jsp").forward(req,resp);
		 * }
		 */
	}
}

			