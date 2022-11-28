<?php
define('FILEUSER', 'incidencias.txt');


$nombre = limpiarEntrada($_POST['nombre']);
$resumen = limpiarEntrada($_POST['resumen']);
$prioridad = limpiarEntrada($_POST['prioridad']);

function limpiarEntrada($cadena)
{
    return trim(htmlspecialchars($cadena, ENT_QUOTES, "UTF-8"));
}

function crearIncidencia($nombre, $resumen, $prioridad)
{
    $fecha = date("d/m/Y h:i:s");
    $ip = $_SERVER['REMOTE_ADDR'];

    $incidencia = [$fecha, $nombre, $resumen, $prioridad, $ip];

    return $incidencia;
}

function escribirIncidencia($incidencia)
{
    $fichero = fopen(FILEUSER, 'a');
    fwrite($fichero, implode(',', $incidencia) . "\n");
    fclose($fichero);
}


if (!empty($_POST)) {
    $incidencia = crearIncidencia($nombre, $resumen, $prioridad);
    escribirIncidencia($incidencia);
    $numIncidencias = 0;

    if (isset($_COOKIE['numIncidencias'])) {
        $numIncidencias = $_COOKIE['numIncidencias'];
        if ($numIncidencias >= 3) {
            echo "Has superado el número de incidencias";
            exit;
        }
    }
    $numIncidencias++;
    setcookie('numIncidencias', $numIncidencias, time() + 120);
    header("Refresh: 3; url=incidenciasform.html");
    echo "Incidencia registrada";
} else {
    echo "No se ha podido anotar la incidencia, lo sentimos.";
}
