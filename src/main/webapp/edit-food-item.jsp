<%@page import="dto.FoodItem"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%FoodItem item =(FoodItem) request.getAttribute("item"); %>

<div align="center" class="main">
			<h1>Enter Updated Food Details</h1><br><br>
			<form action="edit-food-item" method="post"
				enctype="multipart/form-data">
				<input type="text">
				<input type="text" value="<%=item.getName()%>" name="name" class="inp" placeholder="Enter Food Item Name"
					id="email"
					required><br><br> <input type="number" name="price" class="inp"
					placeholder="Enter Price" value="<%=item.getPrice()%>" required><br><br> <input type="radio"
					name="type" class="radio" value="veg">VEG <input type="radio" name="type"
					value="non-veg" class="radio" >NON-VEG<br> <br><input type="number" class="inp"
					name="stock" placeholder="Stock"><br><br> <br><input type="file"
					name="image" class="file"  placeholder="Picture"><br><br>
				<button class="addbut">Update</button>
			</form>
		</div>
</body>
</html>