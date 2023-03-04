<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="modelo.Cliente" %>
<%@ page import="modelo.Vehiculo" %>
<%
Vehiculo vehiculo = (Vehiculo) request.getAttribute("vehiculo");
Cliente cliente = (Cliente) request.getAttribute("cliente");
%>
<html>
<head>
    <title>ELECTRONCAR</title>
</head>
<body>
<center>
    <h1>BIENVENIDO/A <%= cliente.getNombre()%> </h1>
    <p>

        <%=request.getAttribute("mensaje")%>

    </p>
</center>
</body>
</html>
