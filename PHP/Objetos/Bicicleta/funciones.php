<?php

include_once('BiciElectrica.php');

//Leer el fichero csv y devolver una tabla de bicicletas

function cargarbicis()
{
    $fichero = fopen("bicis.csv", "r");
    $bicis = array();
    while (($datos = fgetcsv($fichero, 1000, ",")) !== FALSE) {
        $bici = new BiciElectrica();
        $bici->id = $datos[0];
        $bici->coordx = $datos[1];
        $bici->coordy = $datos[2];
        $bici->bateria = $datos[3];
        $bici->operativa = $datos[4];
        $bicis[] = $bici;
    }

    fclose($fichero);
    return $bicis;
}


function mostrartablabicis($tabla): string
{
    $cadena = "<table><tr><th>ID</th><th>Coordena X</th><th>Coordenada Y</th><th>Bateria</th>";

    foreach ($tabla as $bici) {
        $cadena .= "<tr>";
        $cadena .= "<td>" . $bici->id . "</td>";
        $cadena .= "<td>" . $bici->coordx . "</td>";
        $cadena .= "<td>" . $bici->coordy . "</td>";
        $cadena .= "<td>" . $bici->bateria . "</td>";
        $cadena .= "</tr>";
    }
    return $cadena;
}

//Devuelve la bici con menor distancia a las coordenadas del usuario.

function bicimascercana($tabla, $x, $y): mixed
{
    $bicimascercana = null;
    $distanciaminima = PHP_INT_MAX;
    foreach ($tabla as $bici) {
        $distancia = $bici->distancia($x, $y);
        if ($distanciaminima == 0 || $distancia < $distanciaminima) {
            $distanciaminima = $distancia;
            $bicimascercana = $bici;
        }
    }
    return $bicimascercana;
}
