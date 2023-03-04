<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  
<%-- Importo las clases necesarias --%>
<%@ page  import="java.util.ArrayList" %>
<%@ page import="modelo.*" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Listado de Películas </title>
</head>
<body>
<!--  UTILIZANDO INSTRUCCIONES DE JAVA  -->

<%
ArrayList <Pedido> lista = (ArrayList <Pedido>) request.getAttribute("lista");
Cliente  cli =                       (Cliente ) request.getAttribute("cliente");
if (lista == null){
    	out.println( " Me han enviado una lista == NULL");
    	
}
else {
 	  // Si hay alguna pelicula
   	  if ( lista.size() != 0 ) {
	  %>
	<h1>Bienvenido usuario:<%=cli.getNombre() %>   </h1> 
	 Has entrado <%=cli.getVeces() %> veces en nuestra web <br>
	 <b> Esta es su lista de pedidos del cliente con <%= cli.getCod_cliente() %></b><br>
	<table border="1">
	<%
	int total =0;
	for (Pedido l : lista) {%>
	<tr>
	<td><%= l.getProducto() %></td>
	<td><%= l.getPrecio() %></td>
	</tr>
	<% total +=l.getPrecio(); 
	} %>
	<tr>
	<td> TOTAL PEDIDOS </td><td><%=total %></td>
	</tr>
	</table>
 	
	<% } else { %>
     <p> No existen pedidos para este cliente.</p>

<%  }
}
%> 	

</body>
</html>