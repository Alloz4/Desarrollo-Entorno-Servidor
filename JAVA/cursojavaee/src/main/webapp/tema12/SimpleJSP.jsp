<!--SimpleJSP.jsp-->
<%!int ultimo = 0;%>

<%!public String hora() {
		return (new java.util.Date()).toString();
	}%>

<html>
<head>
<title>Simple KSP</title>
</head>
<body>
	<h1>Una bonita tabla:</h1>
	<table BORDER=2>
		<%
			for (int i = ultimo; i < ultimo + 10; i++) {
		%>   
		<tr>
			<td>Numero</td>        
			<td><%=i%></td>      
		</tr>
		<%
			}
			ultimo += 10;
		%>
	</table>
	<p>
		La hora actual es:
		<%=hora()%></p>
</body>
</html>
