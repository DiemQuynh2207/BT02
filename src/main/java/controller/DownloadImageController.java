package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.io.IOUtils;

@WebServlet(urlPatterns = "/image") 
public class DownloadImageController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String fileName = req.getParameter("fname");
		System.out.println(fileName);
		if (fileName==null || fileName.length()<=0)
			return;
		File file = new File(getServletContext().getRealPath("") + Constants.UPLOAD_DIRECTORY + fileName);
		resp.setContentType("image/jpeg");
		System.out.println(file);
		if (file.exists()) {
			IOUtils.copy(new FileInputStream(file), resp.getOutputStream());
		}
	}

}