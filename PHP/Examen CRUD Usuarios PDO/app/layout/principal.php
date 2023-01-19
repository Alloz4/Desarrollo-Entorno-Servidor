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
            <h1>GESTIÓN DE USUARIOS versión 1.1 + BD</h1>
        </div>
        <div id="content">
            <form>
                <?= $contenido ?>
                <br>
                <button name="orden" value="Nuevo"> Nuevo </button>
                <button name="orden" value="Terminar"> Terminar </button>
                <button name="orden" value="IncrementarSaldo">Incrementar Saldo</button>
                <button name="orden" value="Bloqueo">Cambiar Bloqueos</button>
            </form>
        </div>
    </div>
</body>