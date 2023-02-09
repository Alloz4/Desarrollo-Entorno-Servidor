<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%-- Importo las clases necesarias --%>
<%@ page  import="java.util.ArrayList" %>
<%@ page import="Modelo.Producto" %>

<%-- Utilizlo una librería adicional de marcar tag --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Listado de productos </title>
</head>
<body>


<!--  UTILIZANDO INSTRUCCIONES DE JAVA  -->
<%
	ArrayList <Producto> lista = (ArrayList) request.getAttribute("listaProductos");
    if (lista == null){
    	out.println( " Me han enviado una lista == NULL");
    	
    }
    else 
    {

%>


<table border="1">
<tr>
<th>id</th>
<th>Nombre</th>
<th>Stock</th>
<th>precio</th>
<%
for (Producto p : lista) {
%><tr>
<td><%= p.getId() %></td>
<td><%= p.getNombre() %></td>
<td><%= p.getStock() %></td>
<td><%= p.getPrecio() %></td>
</tr>
<%
}
%>
</table>

<br>
<hr>

<!--  UTILIZANDO ACCESO A BEAN Y  LIBRERIA DE TAG DE JSP- JSTL  -->

<table border="1">
<tr>
<th>id</th>
<th>Nombre</th>
<th>Stock</th>
<th>precio</th>
</tr>

<c:forEach items="${listaProductos}" var="producto">
                    <tr>
                       <td>${producto.id}</td>
                       <td>${producto.nombre}</td>
                       <td>${producto.stock}</td>
                       <td>${producto.precio}</td>
              
                    </tr>
 </c:forEach> 

</table>
<% } %>
</body>
</html>