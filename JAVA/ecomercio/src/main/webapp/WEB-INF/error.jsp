<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Error</title>
</head>
<body>
 <h1> ERROR </h1>
 <%-- Uso la notación mas moderna para visualiar un atributo de peticion de tipo string --%>
 <%-- String mensaje = (String) request.getAttribute("mensaje") 
      out.println("<h1>"+mensaje+"</h1>");
 --%>
 <h1>${mensaje}</h1>
 <% response.setHeader("Refresh", "3; URL=acceso.html"); %>
</body>
</html>