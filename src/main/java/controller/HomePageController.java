package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.User;

import java.io.IOException;


@SuppressWarnings("serial")
@WebServlet(urlPatterns = "/home")
public class HomePageController extends HttpServlet {
    public HomePageController() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		System.out.println("a");
		System.out.println("b");
		System.out.println("-");
		resp.setContentType("text/html");

		boolean ok = false;
		HttpSession session = req.getSession(false);
		if (session != null && session.getAttribute("usermodel") != null) {
			User user = (User) session.getAttribute("usermodel");
			session.setAttribute("nameLogin", user.getFullname());
			session.setAttribute("imageAvatar", user.getImages());
			System.out.println("nameLogin:"+user.getFullname());
			System.out.println("c");
			System.out.println("d");
			System.out.println("e");
			System.out.println("session: " +req.getSession());
				
			ok = true;
		}
		req.getRequestDispatcher("/views/web/home.jsp").forward(req, resp);
		if (!ok) {
			return;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
