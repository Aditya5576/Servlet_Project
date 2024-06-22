package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.Hotel;

@WebServlet("/view-menu")
public class ViewMenu extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		Hotel hotel = (Hotel) session.getAttribute("hotel");
		if (hotel != null) {
			req.getRequestDispatcher("hotel-menu.html").forward(req, resp);
		} else {
			resp.getWriter().print("<h1 align='center' style='color:red'>Invalid Session</h1>");
			req.getRequestDispatcher("cust-login.html").include(req, resp);
		}
	}

}
