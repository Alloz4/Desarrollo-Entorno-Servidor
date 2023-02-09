<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="Modelo.Producto" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Consulta de Producto </title>
</head>
<body>
<%
Producto  p =  (Producto) request.getAttribute("productoItem");
if ( p == null){
	out.println(" <h1> Producto no encontrado.</h1>");
}
else {
%>
<table border="1">
<tr>
<th>id</th>
<th>Nombre</th>
<th>Stock</th>
<th>precio</th>
<tr>
<td><%= p.getId() %></td>
<td><%= p.getNombre() %></td>
<td><%= p.getStock() %></td>
<%-- Forma mas directa usuando cualidades de los JavaBean --%>
<td>${productoItem.precio}</td>
</tr>
</table>
<% } %>
</body>
</html>