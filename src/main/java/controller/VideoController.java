package controller;

import java.io.File;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import models.Video;
import service.IVideoService;
import service.impl.VideoService;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5)
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
			String poster = uploadFileImage(req);			
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
			String poster = uploadFileImage(req);
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
	}
	private String getFileName(Part part) {
		return part.getSubmittedFileName();
	}
	public String uploadFileImage(HttpServletRequest request) {
		System.out.println("đây là số: 1");
		String uploadPath = getServletContext().getRealPath("") + Constants.UPLOAD_DIRECTORY;
		File uploadDir = new File(uploadPath);
		System.out.println("đây là số: 2");

		if (!uploadDir.exists())
			uploadDir.mkdir();
		try {
			String fileName = "";
			System.out.println("đây là số: 3");
			Part part = request.getPart("poster");
			System.out.println("đây là số: 4");

			fileName = getFileName(part);
			System.out.println("đây là filename trong upload:"+fileName);
			if (fileName==null || fileName.length() <= 0)
				return null;
			System.out.println("write " + fileName+"|");
			System.out.println(uploadPath);
			part.write(uploadPath + fileName);
			request.setAttribute("message", "File " + fileName + " has uploaded successfully!");

			return fileName;

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "There was an error: " + e.getMessage());

		}
		return null;
	}
}

			