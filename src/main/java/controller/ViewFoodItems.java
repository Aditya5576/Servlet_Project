package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.MyDao;
import dto.FoodItem;
import dto.Hotel;

@WebServlet("/view-food-item")
public class ViewFoodItems extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		Hotel hotel = (Hotel) session.getAttribute("hotel");
		if (hotel != null) {
			MyDao dao = new MyDao();
			List<FoodItem> foodItems = dao.fetchByFoodByHotel(hotel.getId());
			if (foodItems.isEmpty()) {
				resp.getWriter().print("<h1 align='center' style='color:red'>No Food Items Added Yet</h1>");
				req.getRequestDispatcher("hotel-home.html").include(req, resp);
			} else {
				req.setAttribute("foodItems", foodItems);
				req.getRequestDispatcher("view-food-item.jsp").forward(req, resp);
			}

		} else {
			resp.getWriter().print("<h1 align='center' style='color:red'>Invalid Session</h1>");
			req.getRequestDispatcher("admin-login.html").include(req, resp);
		}
	}
}
