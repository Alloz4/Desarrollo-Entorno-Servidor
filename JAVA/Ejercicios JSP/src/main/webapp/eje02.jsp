<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE unspecified PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Ejercicio 2</title>
</head>
<body>
	<center>
		<%
		boolean entrar = false;
		String nombre = request.getParameter("nombre");
		String pass = request.getParameter("pass");
		
		//Si no se han rellenado los campos no se envian en el POST
		
		if(nombre == null || pass == null){
			out.println("<h1> Introduzca un nombre y una clave </h1>");
		} else {
			
		}
		
		%>
	</center>
</body>
</html>