<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Fruteria S. XXI</title>
</head>

<body>
    <h1>La Frutería del siglo XXI</h1>
    <?php

    session_start();

    if ($_SERVER['REQUEST_METHOD'] == "GET" && empty($_GET['nombre'])) {
        include 'registro.php';
    }
    if (($_SERVER['REQUEST_METHOD'] == "GET" && !empty($_GET['nombre']))) {
        $_SESSION['nombre'] = $_GET['nombre'];
        include 'formulario.php';
    }
    if ($_SERVER['REQUEST_METHOD'] == "POST") {
        if ($_POST['boton'] == 'Anotar') {
            if (empty($_SESSION['pedido'][$_POST['fruta']])) {
                $_SESSION['pedido'][$_POST['fruta']] = $_POST['cantidad'];
            } else {
                $_SESSION['pedido'][$_POST['fruta']] += $_POST['cantidad'];
            }
            include 'pedido.php';
            include 'formulario.php';
        } else {
            include 'pedido.php';
            include 'terminado.php';
        }
    }
    ?>
</body>

</html>