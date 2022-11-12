<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>CRUD DE USUARIOS</title>
    <link href="web/default.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="web/js/funciones.js"></script>
</head>

<body>
    <div id="container" style="width: 600px;">
        <div id="header">
            <h1>GESTIÓN DE USUARIOS versión 1.0</h1>
        </div>
        <div id="content">
            <?= $msg ?>
            <br><br>
            <?= $contenido ?>
            <br>
            <form>
                <input type="submit" name="orden" value="Nuevo">
                <input type="button" name="orden" value="Terminar" onclick="confirmarCerrar('¿Desea guardar los datos al fichero?')">
            </form>
        </div>
    </div>
</body>