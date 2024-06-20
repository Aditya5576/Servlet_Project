package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import dao.MyDao;
import dto.FoodItem;
import dto.Hotel;

@WebServlet("/add-food-item")
@MultipartConfig
public class AddFoodItem extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (req.getSession().getAttribute("hotel") != null) {
			req.getRequestDispatcher("add-food-item.html").forward(req, resp);
		} else {
			resp.getWriter().print("<h1>Invalid Session</h1>");
			req.getRequestDispatcher("admin-login").include(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("name");
		double price = Double.parseDouble(req.getParameter("price"));
		String type = req.getParameter("type");
		int stock = Integer.parseInt(req.getParameter("stock"));

		Part image = req.getPart("image");
		byte[] picture = new byte[image.getInputStream().available()];
		image.getInputStream().read(picture);
		
		
		Hotel hotel = (Hotel) req.getSession().getAttribute("hotel");
		
		///create object food item
		
		FoodItem food = new FoodItem();
		food.setName(name);
		food.setPrice(price);
		food.setStock(stock);
		food.setType(type);
		food.setHotel(hotel);
		food.setPicture(picture);
		
		MyDao dao = new MyDao();
		
		dao.saveFoodItem(food);
		resp.getWriter().print("<h1 align='center' style='color:green'>Item Added	</h1>");
		req.getRequestDispatcher("hotel-home.html").include(req, resp);
		
		

	}
	

}
