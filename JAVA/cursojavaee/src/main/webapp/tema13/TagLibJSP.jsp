<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import= "tema13.*" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="MiLibreriaDeTags" prefix="mistags" %>
<html>
    <head>
        <title>Ejemplo de uso de etiquetas propias</title>
    </head>
    <body>
        <mistags:holamundo texto= "Hola!" repeticiones= "10">Este texto a veces se muestra y a veces no</mistags:holamundo>
        <p>Esto es un parrafo normal</p>
        <mistags:holamundo texto= "Adios!"/>
    </body>
</html>
