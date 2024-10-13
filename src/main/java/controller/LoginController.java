package controller;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.User;
import service.IUserService;

import service.impl.UserService;

@SuppressWarnings("serial")
@WebServlet (urlPatterns = {"/login"})
public class LoginController extends HttpServlet{
	public IUserService userService = new UserService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		HttpSession session = req.getSession(false);
		if (session != null && session.getAttribute("usermodel") != null) {
			User user = (User) session.getAttribute("usermodel");
			return;
		}
		req.getRequestDispatcher("login.jsp").forward(req, resp);
	}
	private void saveRemeber(HttpServletRequest req, User usermodel) {
		HttpSession session = req.getSession();
		session.setAttribute("usermodel", usermodel);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		resp.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");
		String username = req.getParameter("username");
		String password = req.getParameter("pword");
		boolean isRememberMe = false;
		String remember = req.getParameter("remember");
		if ("on".equals(remember)) {
			isRememberMe = true;
		}
		if (username == null || password == null) {
			req.setAttribute("type", "Login"); 
			req.getRequestDispatcher("login.jsp").forward(req, resp);
			return;
		}
		IUserService service = new UserService();

		System.out.println(1);
		User user = service.login(username, password);

		System.out.println(2);
		if (user != null) {
			HttpSession session = req.getSession(true);
			session.setAttribute("usermodel", user);
			saveRemeber(req, user);
			System.out.println("User "+username+" login to web");
			
			if (user.isAdmin()==true)
				resp.sendRedirect(req.getContextPath() + "/home");
			else if (user.isAdmin()==false)
				resp.sendRedirect(req.getContextPath() + "/home");
		} else {
			System.out.println("login fail for user: "+username);
			req.setAttribute("type", "Login"); 
			req.getRequestDispatcher("login.jsp").forward(req, resp);
			
		}
			
		
		
	}
}

			