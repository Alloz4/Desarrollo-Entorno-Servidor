<jsp:useBean id="firmante" class="tema13.Usuario" scope="session"/>
<jsp:setProperty name="firmante" property="*"/> 
<html>
<head>
</head>
<body>
    <h1> Éstos son tus datos:</h1>
    <p>Nombre: <jsp:getProperty name="firmante" property="nombre" /></p>
    <p>E-mail: <jsp:getProperty name="firmante" property="email" /></p>
    <p>Edad: <jsp:getProperty name="firmante" property="edad" /></p>
<a href="/cursojavaee/tema13/Anhadir.jsp">Confirmar la firma </a> o
<a href="/cursojavaee/tema13/firmarpeticion.html">Volver al formulario</a>
</body>
</html>

