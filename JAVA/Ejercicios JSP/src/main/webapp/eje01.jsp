<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<h1>Navegas:</h1>
<%
			if (request.getHeader("user-agent").indexOf("Firefox") != -1) {
				out.println("Con firefox <br>");
			} else {
				out.println("Sin firefox <br>");
			}
	
%>
</body>
</html>