<?php
include_once "cliente.php";

function accionBorrar($login)
{
    $db = AccesoDatos::getModelo();
    $tuser = $db->borrarUsuario($login);
}

function accionAlta()
{
    $user = new Cliente();
    $user->id  = "";
    $user->first_name  = "";
    $user->email   = "";
    $user->gender = "";
    $user->ip_address = "";
    $user->telefono = "";
    $orden = "Nuevo";
    include_once "layout/formulario.php";
}

function accionDetalles($login)
{
    $db = AccesoDatos::getModelo();
    $user = $db->getCliente($login);
    $orden = "Detalles";
    include_once "layout/formulario.php";
}


function accionModificar($login)
{
    $db = AccesoDatos::getModelo();
    $user = $db->getCliente($login);
    $orden = "Modificar";
    include_once "layout/formulario.php";
}

function accionPostAlta()
{
    limpiarArrayEntrada($_POST); //Evito la posible inyección de código
    $user = new Cliente();
    $user->id  = $_POST['id'];
    $user->first_name  = $_POST['first_name'];
    $user->email   = $_POST['email'];
    $user->gender = $_POST['gender'];
    $user->ip_address = $_POST['ip_address'];
    $user->telefono = $_POST['telefono'];
    header("Location: index.php");
    $db = AccesoDatos::getModelo();
    $db->addUsuario($user);
}

function accionPostModificar()
{
    limpiarArrayEntrada($_POST); //Evito la posible inyección de código
    $user = new Cliente();
    $user->id  = $user->id  = $_POST['id'];
    $user->first_name  = $_POST['first_name'];
    $user->email   = $_POST['email'];
    $user->gender = $_POST['gender'];
    $user->ip_address = $_POST['ip_address'];
    $user->telefono = $_POST['telefono'];
    header("Location: index.php");
    $db = AccesoDatos::getModelo();
    $db->modUsuario($user);
}
