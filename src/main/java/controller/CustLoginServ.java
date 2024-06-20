package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MyDao;
import dto.Hotel;
import dto.Customer;

@WebServlet("/cust-login")
public class CustLoginServ extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("cust-login.html").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");
		String password = req.getParameter("password");

		MyDao dao = new MyDao();

		List<Customer> list = dao.findCustomerByEmail(email);

		if (list.isEmpty()) {
			resp.getWriter().print("<h1 align='center' style='color:red'>Invalid Email</h1>");
			req.getRequestDispatcher("cust-login.html").include(req, resp);
		} else if (password.equals(list.get(0).getPassword())) {
			resp.getWriter().print("<h1 align='center' style='color:green'>Login Success</h1>");
			req.getRequestDispatcher("Home.html").include(req, resp);
		} else {
			resp.getWriter().print("<h1 align='center' style='color:red'>Invalid Password</h1>");
			req.getRequestDispatcher("cust-login.html").include(req, resp);
		}
	}
}
