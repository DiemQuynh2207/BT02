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
import models.Category;
import service.ICategoryService;
import service.impl.CategoryService;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5)
@SuppressWarnings("serial")
@WebServlet (urlPatterns = {"/admin/categories", "/admin/category/edit", "/admin/category/update"
		,"/admin/category/insert", "/admin/category/add", "/admin/category/delete", "/admin/category/search"})
public class CategoryController extends HttpServlet{
	public ICategoryService cateService = new CategoryService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		String url = req.getRequestURI();
		if(url.contains("categories")) {
			List<Category> list = cateService.findAll();
			req.setAttribute("listcate", list);
			req.getRequestDispatcher("/views/admin/category-list.jsp").forward(req, resp);
		}
		else if(url.contains("/admin/category/edit")){
			int id = Integer.parseInt(req.getParameter("id"));
			Category category = cateService.findById(id);
			req.setAttribute("cate", category);
			req.getRequestDispatcher("/views/admin/category-edit.jsp").forward(req, resp);
		}
		else if(url.contains("/admin/category/add")){
			req.getRequestDispatcher("/views/admin/category-add.jsp").forward(req, resp);
		}
		else if(url.contains("/admin/category/delete")){
			int id = Integer.parseInt(req.getParameter("id"));
			System.out.println("test id: ");
			System.out.println(id);
			Category category = cateService.findById(id);
			try {
				cateService.delete(category.getCategoryId());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			resp.sendRedirect(req.getContextPath() + "/admin/categories");
		}
		
		
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		String url = req.getRequestURI();
		if(url.contains("/admin/category/update")) {
			int categoryid = Integer.parseInt(req.getParameter("categoryid"));
			String categoryname = req.getParameter("categoryname");
			int status = Integer.parseInt(req.getParameter("status"));
			String images = uploadFileImage(req);
			
			Category category = new Category();
			category.setCategoryId(categoryid);
			category.setCategoryname(categoryname);
			category.setImages(images);
			category.setStatus(status);
			
			cateService.update(category);
			resp.sendRedirect(req.getContextPath() + "/admin/categories");
			
		}
		else if(url.contains("/admin/category/insert")) {
			String categoryname = req.getParameter("categoryname");
			int status = Integer.parseInt(req.getParameter("status"));
			String images = uploadFileImage(req);
			
			Category category = new Category();
			category.setCategoryname(categoryname);
			category.setImages(images);
			category.setStatus(status);
			System.out.println("test 1");
			System.out.println(categoryname);
			System.out.println(images);
			System.out.println(status);
			cateService.insert(category);
			resp.sendRedirect(req.getContextPath() + "/admin/categories");
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
			Part part = request.getPart("images");
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

			