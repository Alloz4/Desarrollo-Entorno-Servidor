<?php



function accionDetalles($id)
{
    $usuario = $_SESSION['tuser'][$id];
    $nombre  = $usuario[0];
    $login   = $usuario[1];
    $clave   = $usuario[2];
    $comentario = $usuario[3];
    $orden = "Detalles";
    include_once "layout/formulario.php";
    exit();
}

function accionAlta()
{
    $nombre  = "";
    $login   = "";
    $clave   = "";
    $comentario = "";
    $orden = "Nuevo";
    include_once "layout/formulario.php";
    exit();
}

function accionPostAlta()
{
    limpiarArrayEntrada($_POST); //Evito la posible inyección de código

    $nombre  = $_POST['nombre'];
    $login   = $_POST['login'];
    $clave   = $_POST['clave'];
    $comentario = $_POST['comentario'];

    foreach ($_SESSION['tuser'] as $usuario) {
        if ($usuario[1] == $login) {
            $_SESSION['nombre'] = $nombre;
            $login = "";
            $_SESSION['login'] = $login;
            $_SESSION['clave'] = $clave;
            $_SESSION['comentario'] = $comentario;
            accionAltaErronea();
            exit();
        }
    }
    $usuario = [$nombre, $login, $clave, $comentario];
    $_SESSION['tuser'][] = $usuario;
    $_SESSION['msg'] = "Usuario dado de alta";
}

function accionAltaErronea()
{
    $nombre  = $_SESSION['nombre'];
    $login   = $_SESSION['login'];
    $clave   = $_SESSION['clave'];
    $comentario = $_SESSION['comentario'];
    $orden = "Nuevo";
    $_SESSION['mensaje'] = "El login ya existe";
    include_once "layout/formulario.php";
    exit();
}

function accionTerminar()
{
    volcarDatos($_SESSION['tuser']);
    session_destroy();
    $_SESSION['msg'] = " Se han grabado los datos.";
}

function accionBorrar($id)
{
    $usuario = $_SESSION['tuser'][$id];
    unset($_SESSION['tuser'][$id]);
    $_SESSION['tuser'] = array_values($_SESSION['tuser']);
    $_SESSION['msg'] = "<p style='color:red'>" . " Se ha borrado el usuario " . $usuario[0] . "</p>";
}

function accionModificar($id)
{
    $usuario = $_SESSION['tuser'][$id];
    $nombre  = $usuario[0];
    $login   = $usuario[1];
    $clave   = $usuario[2];
    $comentario = $usuario[3];
    $orden = "Modificar";
    include_once "layout/formulario.php";
    exit();
}

function accionPostModificar()
{
    limpiarArrayEntrada($_POST);

    foreach ($_SESSION['tuser'] as $key => $value) {
        if ($value[1] == $_POST['login']) {
            $nombre = $_POST['nombre'];
            $login = $_POST['login'];
            $clave = $_POST['clave'];
            $comentario = $_POST['comentario'];
            $_SESSION['tuser'][$key] = [$nombre, $login, $clave, $comentario];
        }
        $_SESSION['msg'] = " Se ha modificado el usuario " . $_POST['nombre'];
    }
}
