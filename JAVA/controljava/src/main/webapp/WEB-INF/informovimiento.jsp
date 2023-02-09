<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%-- Importo las clases necesarias --%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="modelo.Movimiento"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Consulta de movimientos</title>
</head>
<body>
	<!--  UTILIZANDO INSTRUCCIONES DE JAVA  -->
	<%
	ArrayList<Movimiento> lista = (ArrayList <Movimiento>) request.getAttribute("listmovimientos");
	if (lista == null) {
		out.println("ERROR: Me han enviado una lista == NULL");
	} else {
	%>
	<H1>Consulta de Movimientos</H1>
	<table border="1">
		<tr>
			<th>Nº de mov</th>
			<th>cod_cliente</th>
			<th>concepto</th>
			<th>importe</th>
		</tr>
		<%
		for (Movimiento mov : lista) {
		%><tr>
			<td><%=mov.getNum_mov()%></td>
			<td><%=mov.getCod_cliente()%></td>
			<td><%=mov.getConcepto()%></td>
			<td><%=mov.getImporte()%></td>
		</tr>
		<%
}
%>
	</table>
	<p>
		Se han encontrados un total de
		<%=lista.size()%>
		movimientos
		<%
	}
	%>
	
</body>
</html>