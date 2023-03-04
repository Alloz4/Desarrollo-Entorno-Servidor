<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.net.URLEncoder"%>
<%@ page import="java.net.URLDecoder"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>COMPRA DE SOCIOS</title>
</head>
<body>
<body>
	<div align="center">
		<img src="tienda-online.png" width="200" height="100">
		<h1>INTRODUZCA LOS DATOS DE SU COMPRA</h1>
		<%
		// Actualizo el cookie
		String fechaactual = new java.util.Date().toString();
		fechaactual = URLEncoder.encode(fechaactual, "UTF-8");
		Cookie nuevaCookie = new Cookie("fecha", fechaactual);
		nuevaCookie.setMaxAge(365 * 24 * 60 * 60);
		response.addCookie(nuevaCookie);

		// Buscar el valor del cookie
		String valoractual = "";
		boolean nuevo = true;
		Cookie[] todasLasCookies = request.getCookies();
		if (todasLasCookies != null) {
			for (Cookie cookie : todasLasCookies) {
				if (cookie.getName().equals("fecha")) {
				valoractual = cookie.getValue();
				valoractual = URLDecoder.decode(valoractual, "UTF-8");
				nuevo = false;
				break;
				}

			}
		}

		if (nuevo) {
			out.println(" Bienvenido por primera vez<br>");
		} else {
			out.println(" Ultimo acceso :" + valoractual + "<br>");
		}
		%>
	</div>
	<div align="center">
		<fieldset>
			<legend> Datos de usuario </legend>
			<form name="formulario" method="post" action="realizarcompra">
				<table>
					<tr>
						<td>Código de Socio:</td>
						<td><input type="text" name="cod_socio" size="10"
							maxlength="10"></td>
					</tr>
					<tr>
						<td>Contraseña:</td>
						<td><input type="password" name="clave" size="10"
							maxlength="10"></td>
					</tr>
					<tr>
						<td>Código de producto:</td>
						<td><input type="text" name="cod_pro" size="12"
							maxlength="10"></td>
					</tr>

					<tr>
						<td colspan="2" align="center" class="formulario"><input
							type="submit" name="enviar" value="Enviar"></td>
					</tr>

				</table>
			</form>
		</fieldset>
	</div>
</body>
</html>