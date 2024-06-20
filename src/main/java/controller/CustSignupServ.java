package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MyDao;
import dto.Customer;
import dto.Hotel;

@WebServlet("/cust-signup")
public class CustSignupServ extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("cust-signup.html").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		long mobile = Long.parseLong(req.getParameter("mobile"));
		String address = req.getParameter("address");
		String password = req.getParameter("password");

		MyDao dao = new MyDao();

		List<Customer> list = dao.findCustomerByEmail(email);
		if (list.isEmpty()) {
			Customer customer = new Customer();
			customer.setAddress(address);
			customer.setEmail(email);
			customer.setMobile(mobile);
			customer.setName(name);
			customer.setPassword(password);

			dao.saveCustomer(customer);

			resp.getWriter().print("<h1 align='center' style='color:green'>Account Created Success</h1>");
			req.getRequestDispatcher("cust-login.html").include(req, resp);
		} else {
			resp.getWriter().print(
					"<h1 align='center' style='color:red'>Account Already Exists with Email - " + email + "</h1>");
			req.getRequestDispatcher("cust-signup.html").include(req, resp);
		}
	}

}
