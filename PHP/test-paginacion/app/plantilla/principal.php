<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>CRUD DE USUARIOS</title>
    <link href="web/default.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="web/js/funciones.js"></script>
</head>

<body>
    <div id="container">
        <div id="header">
            <h1>Información de Clientes</h1>
        </div>
        <div id="content">
            <?= $contenido ?>
            <form>
                <input type="submit" name="orden" value="Anterior">
                <input type="submit" name="orden" value="Siguiente">
            </form>
        </div>
    </div>
</body>