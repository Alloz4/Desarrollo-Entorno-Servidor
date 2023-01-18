<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Lista Pedidos</title>
</head>

<body>

    <?php
    require_once 'Cliente.php';
    require_once 'Pedido.php';
    ?>

    <h1>Lista de pedidos</h1>
    <h2>Bienvenido <?= $cliente->nombre ?>. Has entrado <?= $cliente->veces ?> veces en nuestra web.</h2>
    <h3>Esta es tu lista de pedidos de cliente con código <?= $cliente->cod_cliente ?>:</h3>
    <?php
    $total = 0;
    if (count($pedidos) > 0) {
    ?>
        <table border="1">
            <?php foreach ($pedidos as $pedido) { ?>
                <tr>
                    <td><?= $pedido->producto ?></td>
                    <td><?= $pedido->precio ?></td>
                </tr>
            <?php
                $total += $pedido->precio;
            }
            ?>
            <tr>
                <td>Total pedidos</td>
                <td><?= $total ?></td>
            </tr>
        </table>
    <?php } else {
        echo "<h4>No tienes pedidos.</h4>";
    } ?>
    <br>
    <a href="acceso.html">
        <input type="submit" value="Volver">
    </a>
</body>

</html>