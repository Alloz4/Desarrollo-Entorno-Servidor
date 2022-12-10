<?php
session_start();

include_once 'app/funciones.php';
include_once 'app/acciones.php';

define('FPAG', 10);

$midb = AccesoDatos::getModelo();
$totalfilas = $midb->numClientes();
if ($totalfilas % FPAG == 0) {
    $posfin = $totalfilas - FPAG;
} else {
    $posfin = $totalfilas - $totalfilas % FPAG;
}

if (!isset($_SESSION['posini'])) {
    $_SESSION['posini'] = 0;
}
$posAux = $_SESSION['posini'];

if (isset($_GET['nav'])) {

    switch ($_GET['nav']) {
        case "<<":
            $posAux = 0;
            break;
        case ">":
            $posAux += FPAG;
            if ($posAux > $posfin) $posAux = $posfin;
            break;
        case "<":
            $posAux -= FPAG;
            if ($posAux < 0) $posAux = 0;
            break;
        case ">>":
            $posAux = $posfin;
    }
}
$_SESSION['posini'] = $posAux;

// Accedo al Modelo
$tvalores = $midb->getClientes($posAux, FPAG);


// Div con contenido
if ($_SERVER['REQUEST_METHOD'] == "GET") {

    if (isset($_GET['orden'])) {
        switch ($_GET['orden']) {
            case "Nuevo Cliente":
                accionAlta();
                break;
            case "Borrar":
                accionBorrar($_GET['id']);
                break;
            case "Modificar":
                accionModificar($_GET['id']);
                break;
            case "Detalles":
                accionDetalles($_GET['id']);
                break;
        }
    }
}
// POST Formulario de alta o de modificación
else {
    if (isset($_POST['orden'])) {
        switch ($_POST['orden']) {
            case "Nuevo":
                accionPostAlta();
                break;
            case "Modificar":
                accionPostModificar();
                break;
            case "Detalles":; // No hago nada
        }
    }
}
// Muestro la página principal
include_once "app/layout/principal.php";
