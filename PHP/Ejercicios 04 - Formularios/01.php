<?php

$valores = array(
    'Marcos' => '1234',
    'Adriana' => 'hola',
    'Alberto' => '50',
);
$nombre = $_POST["nombre"];
$clave = $_POST["clave"];

if (empty($nombre) || empty($clave)) {
    if (empty($nombre)) {
        echo "No has introducido nada en el campo ";
    }
    echo "No has introducido nada en algún campo <br><br>";
    echo '<input type="button" onclick="history.back()" value="Volver atrás">';
}
if (!empty($nombre) && !empty($clave)) {
    if (key_exists($nombre, $valores)) {
        if ($valores[$nombre] == $clave) {
            echo "Buenos días " . $nombre . "<br><br>";
            echo '<input type="button" onclick="history.back()" value="Volver atrás">';
        } else {
            echo "Login incorrecto. <br><br>";
            echo '<input type="button" onclick="history.back()" value="Volver atrás">';
        }
    } else {
        echo "Login incorrecto. <br><br>";
        echo '<input type="button" onclick="history.back()" value="Volver atrás">';
    }
}
