<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="miscompras.profesor.modelo.Socio" %>
    <%@ page import="miscompras.profesor.modelo.Producto" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>RESULTADO DE COMPRA</title>
</head>
<body>
 
  BIENVENIDO/A  ${socio.nombre} <br>
  ${exito ? "Se ha efectuado " : " <b>NO</b> se ha podido efectuar "}
  la compra del producto ${producto.nombre_pro} por ${producto.precio} Euros.<br>
  Su límite actual de compra es de ${socio.cantidad_max} Euros.
</body>
</html>