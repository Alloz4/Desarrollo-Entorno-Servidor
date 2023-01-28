<?php

/*
 *  Funciones para limpiar la entrada de posibles inyecciones
 */

function limpiarEntrada(string $entrada): string
{
    $salida = trim($entrada); // Elimina espacios antes y después de los datos
    $salida = strip_tags($salida); // Elimina marcas
    return $salida;
}
// Función para limpiar todos elementos de un array
function limpiarArrayEntrada(array &$entrada)
{

    foreach ($entrada as $key => $value) {
        $entrada[$key] = limpiarEntrada($value);
    }
}

// Función para mostrar la flecha de ordenación en la cabecera de la tabla
function mostrarFlecha($campo)
{
    if ($_SESSION['ordenCampo'] == $campo) {
        if ($_SESSION['ordenAscDesc'] == "ASC") {
            return "<a style='color: blue;'>&nbsp;&nbsp;↑</a>";
        } else {
            return "<a style='color: blue;'>&nbsp;&nbsp;↓</a>";
        }
    } else {
        return "";
    }
}
