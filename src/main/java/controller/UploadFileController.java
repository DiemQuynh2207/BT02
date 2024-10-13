package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import models.User;
import service.IUserService;
import service.impl.UserService;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = "/uploadFile")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 *2, maxFileSize = 1024 * 1024 *10, maxRequestSize = 1024 * 1024 * 50)
public class UploadFileController extends HttpServlet {
	private String getFileName(Part part) {
		 for (String content : part.getHeader("content-disposition").split(";")) {
			 if (content.trim().startsWith("filename"))
				 return content.substring(content.indexOf("=") + 2, content.length() - 1);
		 }
		 return Constants.DEFAULT_FILENAME;
	 }

	 protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 String uploadPath = Constants.UPLOAD_DIRECTORY; //upload vào thư mục bất kỳ
		 //String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIRECTORY; //upload vào thư mục project
		 File uploadDir = new File(uploadPath);
		 if (!uploadDir.exists())
			 uploadDir.mkdir();
		 try {
			 String fileName = "";
			 for (Part part : request.getParts()) {
				 fileName = getFileName(part);
				 String username = "";
				 HttpSession session = request.getSession(false);
				 System.out.println("h");
				 System.out.println("g");
				 if (session != null && session.getAttribute("usermodel") != null) {
					System.out.println("session not null");
					User user = (User) session.getAttribute("usermodel");
					username = user.getUsername();
					System.out.println("e");
					System.out.println("f");
				 }
					IUserService service = new UserService();
					String resultImage = uploadPath + File.separator + fileName;
					System.out.println("this is the username: " + username);
					System.out.println("this is the filename: " + resultImage);
					String finalResult =encodeImage(resultImage);
					request.setAttribute("imageAvatar", finalResult);
					System.out.println("-Success-");
					System.out.println("this is the finalresult: " + finalResult);
					System.out.println("--");
					User user = service.updateImage(username,finalResult);
					session.setAttribute("imageAvatar", finalResult);
					
			 	if (user != null) {
					System.out.println("Update success");
					//set lại dữ liệu mới
					session.setAttribute("usermodel", user);
			 	}
			 	part.write(uploadPath + File.separator + fileName);
			 	request.setAttribute("message", "File " + fileName + " has uploaded successfully!");
			 }
		 } catch (FileNotFoundException fne) {
			 request.setAttribute("message", "There was an error: " + fne.getMessage());
		 }
		 	request.getRequestDispatcher("edit.jsp").forward(request, response);
 }


		


	public static String encodeImage(String path) {
		byte[] fileContent;
		try {
			fileContent = Files.readAllBytes(new File(path).toPath());
			return Base64.getEncoder().encodeToString(fileContent);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
