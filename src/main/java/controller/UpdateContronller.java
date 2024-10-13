package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.User;
import service.IUserService;
import service.impl.UserService;

import java.io.IOException;


@SuppressWarnings("serial")
@WebServlet(urlPatterns = "/edit")
public class UpdateContronller extends HttpServlet {
    public UpdateContronller() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		if (session != null && session.getAttribute("usermodel") != null) {
			User user = (User) session.getAttribute("usermodel");

			req.setAttribute("imageAvatar", user.getImages());
			System.out.println("Image đã lưu nè: "+ user.getImages());
		}
		System.out.println("Chạy nè");
		req.getRequestDispatcher("edit.jsp").forward(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		resp.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");
		String fullname = req.getParameter("fullname");
		String email = req.getParameter("email");
		String username = "";
		HttpSession session = req.getSession(false);
			if (session != null && session.getAttribute("usermodel") != null) {
				User user = (User) session.getAttribute("usermodel");
				username = user.getUsername();
			}
		IUserService service = new UserService();
		System.out.println("username nè: " + username);
		System.out.println(1);
		User user = service.update(username,fullname, email);
		System.out.println(2);
		if (user != null) {
			System.out.println("Update success");
			//set lại dữ liệu mới
			session.setAttribute("usermodel", user);
		} else {
			System.out.println("Update fail");
			req.setAttribute("type", "Edit"); 
			req.getRequestDispatcher("edit.jsp").forward(req, resp);
		}
	}

}
