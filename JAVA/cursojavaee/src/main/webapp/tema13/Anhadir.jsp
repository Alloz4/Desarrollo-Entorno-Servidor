<%@ page import= "tema13.*" %>
<jsp:useBean id="firmante" class="tema13.Usuario" scope="session"/>
<jsp:useBean id="libro" class="tema13.LibroDeFirmas" scope="application"/>
<html>
<head>
</head>
<body>
    <% libro.anhadirFirmante(firmante); %>
    <jsp:forward page="/tema13/Libro.jsp" />
</body>
</html>