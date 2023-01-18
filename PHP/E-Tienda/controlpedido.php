<?php
require_once "AccesoDatos.php";
require_once "Cliente.php";
require_once "Pedido.php";


if (empty($_GET['nombre']) || empty($_GET['contraseña'])) {
    $mensaje = "<center><h4 style='color: red;'>Asegurate de que los campos no están vacíos.</h4></center>";
    include "vistaerror.php";
    exit();
}

$nombre = $_GET['nombre'];
$clave = $_GET['contraseña'];

$db = AccesoDatos::getModelo();

$cliente = $db->getCliente($nombre, $clave);

if ($cliente == null) {
    $mensaje = "<center><h4 style='color: red;'>El usuario no existe.</h4></center>";
    include "vistaerror.php";
    exit();
}

//Incremento el número de veces que ha entrado el usuario
$db->setCliente($cliente->cod_cliente);

$pedidos = $db->getPedidos($cliente->cod_cliente);

$db->closeModelo();

include "vistapedidos.php";
