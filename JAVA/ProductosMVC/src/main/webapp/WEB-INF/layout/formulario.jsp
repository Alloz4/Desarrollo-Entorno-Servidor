<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="modelo.Producto"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CRUD DE PRODUCTOS</title>
<link href="web/default.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<div id="container" style="width: 600px;">
		<div id="header">
			<h1>GESTIÓN DE PRODUCTOS JAVAEE</h1>
		</div>
		<div id="content">
			<hr>
			<form method="POST">
				<table>
					<tr>
						<td>Número Productos</td>
						<td>
							<%
							String orden = request.getAttribute("orden").toString();
							%> <input type="text" name="producto_no"
							value="${producto.producto_no}"
							<%= orden.equals("Detalles")?"readonly":"" %> size="20" autofocus>
						</td>
					</tr>
					<tr>
						<td>Descripción</td>
						<td><input type="text" name="descripcion"
							value="${producto.descripcion}"
							<%= orden.equals("Detalles") || orden.equals("Modificar")?"readonly":"" %>
							size="8"></td>
					</tr>
					<tr>
						<td>Precio</td>
						<td><input type="text" name="precio_actual"
							value="${producto.precio_actual}"
							<%= orden.equals("Detalles")?"readonly":"" %> size=10></td>
					</tr>
					<tr>
						<td>Stock</td>
						<td><input type="text" name="stock_disponible"
							value="${producto.stock_disponible}"
							<%= orden.equals("Detalles")?"readonly":"" %> size=20></td>
					</tr>
				</table>
				<br> <input type="submit" name="orden" value="<%=orden %>">
				<input type="button" name="orden" value="Volver"
					onclick="history.back()">
			</form>
		</div>
	</div>
</body>
</html>
