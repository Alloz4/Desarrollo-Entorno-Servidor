<?php
require_once('dat/datos.php');
/**
 *  Devuelve true si el código del usuario y contraseña se 
 *  encuentra en la tabla de usuarios
 *  @param $login : Código de usuario
 *  @param $clave : Clave del usuario
 *  @return true o false
 */
function userOk($login, $clave): bool
{
    global $usuarios;
    $acceso = false;
    if (array_key_exists($login, $usuarios)) {
        if ($usuarios[$login][1] == $clave) {
            $acceso = true;
        }
    }
    return $acceso;
}

/**
 *  Devuelve el rol asociado al usuario
 *  @param $login: código de usuario
 *  @return ROL_ALUMNO o ROL_PROFESOR
 */
function getUserRol($login)
{

    global $usuarios;
    return $usuarios[$login][2];
}

/**
 *  Muestra las notas del alumno indicado.
 *  @param $codigo: Código del usuario
 *  @return $devuelve una cadena con una tabla html con los resultados 
 */
function verNotasAlumno($codigo): String
{
    $msg = "";
    global $nombreModulos;
    global $notas;
    global $usuarios;

    $msg .= " Bienvenido/a alumno/a: " . $usuarios[$codigo][0] . "<br>";
    $msg .= "<hr>";
    $msg .= "<table>";
    $msg .= "<tr><td>Módulos</td><td>Nota</td></tr>";
    foreach ($nombreModulos as $key => $value) {
        $msg .= "<tr><td>" . $value . "</td><td>" . $notas[$codigo][$key] . "</td></tr>";
    }
    $msg .= "</table>";
    return $msg;
}

/**
 *  Muestra las notas de todos alumnos. 
 *  @param $codigo: Código del profesor
 *  @return $devuelve una cadena con una tabla html con los resultados 
 */
function verNotaTodas($codigo): String
{
    $msg = "";
    global $nombreModulos;
    global $notas;
    global $usuarios;
    $msg .= " Bienvenido Profesor: " . $usuarios[$codigo][0] . "<br>";
    $msg .= "<table>";
    $msg .= "<tr><td>Nombre</td>";
    $msg .= "<hr>";
    foreach ($nombreModulos as $value) {
        $msg .= "<td>" . $value . "</td>";
    }
    $msg .= "</tr>";
    foreach ($notas as $key => $value) {
        $msg .= "<tr><td>" . $usuarios[$key][0] . "</td>";
        foreach ($value as $valor) {
            $msg .= "<td>" . $valor . "</td>";
        }
        $msg .= "</tr>";
    }
    $msg .= "</table>";

    return $msg;
}
