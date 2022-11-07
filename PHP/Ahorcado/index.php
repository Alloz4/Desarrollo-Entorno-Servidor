<?php
session_start();
define('MAXFALLOS', 6);


$ganadas = 0;
if (isset($_COOKIE['ganadas'])) {
    $ganadas = $_COOKIE['ganadas'];
}

$msg = "";
$finaljuego = false;
// INICIO NO HAY PALABRA ELEGIDA (NUEVA PARTIDA)
if (!isset($_SESSION['palabrasecreta'])) {
    $_SESSION['palabrasecreta'] = elegirPalabra();
    $_SESSION['letrasusuario'] = "";
    $_SESSION['fallos'] = 0;
    $msg .= " NUEVA PARTIDA <br>";
    if ($ganadas > 0) {
        $msg .= " Has ganado $ganadas partidas.<br>";
    }
}



if (isset($_REQUEST['letra'])) {
    $letra =  $_REQUEST['letra'];
    if (comprobarLetra($letra, $_SESSION['palabrasecreta']) == false) {
        $_SESSION['fallos']++;
        if ($_SESSION['fallos'] >= MAXFALLOS) {
            $msg .= " Superado el máximo número de fallos. Has perdido <br> ";
            session_destroy();
            $finaljuego = true;
        }
    } else {
        // Anoto la letra 
        $_SESSION['letrasusuario'] .= $letra;
    }
}

$palabramostrar = generaPalabraconHuecos($_SESSION['letrasusuario'], $_SESSION['palabrasecreta']);
$msg .= " PALABRA :  $palabramostrar </br> ";
// Ha ganado ???
if ($palabramostrar == $_SESSION['palabrasecreta']) {
    $ganadas++;
    $msg .= " Enhorabuena has ganado. Ya son $ganadas partidas ganadas.<br>";
    $finaljuego = true;
    // Actualizo el cookie
    setcookie("ganadas", $ganadas, time() + 2 * 7 * 24 * 3600);
    session_destroy();
} else {
    $msg .= " Has comentido " . $_SESSION['fallos'] . " fallos <br>";
}

function elegirPalabra()
{
    static $tpalabras = ["Madrid", "Sevilla", "Murcia", "Málaga", "Mallorca", "Menorca"];
    // COMPLETAR
    // Devuelve una palabra al azar del array
    return $tpalabras[rand(0, count($tpalabras) - 1)];
}

function comprobarLetra($letra, $cadena)
{
    // COMPLETAR
    // Devuelve true o false si la letra esta en la cadena  
    return strpos($cadena, $letra) !== false;
}


/*
     * Devuelve una cadena donde aparecen las letras de la cadenapalabra en su posición    si cada letra se encuentra en la cadenaletras
     *
     * Ej  generaPalabraconHuecos("aeiou"     ,"hola pepe") -->"-o-a--e-e"
     *     generaPalabraconHuecos("abcdefghi ","hola pepe") -->"h--a -e-e"
     *
     */

function generaPalabraconHuecos($cadenaletras, $cadenapalabra)
{

    // Genero una cadena resultado inicialmente con todas las posiciones con -
    $resu = $cadenapalabra;
    for ($i = 0; $i < strlen($resu); $i++) {
        $resu[$i] = '-';
    }
    // COMPLETAR rellenado la cadena resu

    for ($i = 0; $i < strlen($cadenapalabra); $i++) {
        if (comprobarLetra($cadenapalabra[$i], $cadenaletras)) {
            $resu[$i] = $cadenapalabra[$i];
        }
    }

    return $resu;
}




?>
<html>

<head>
    <meta charset="UTF-8">
    <title>El ahorcado </title>
</head>

<body>
    <div><?= $msg ?> </div>
    <?php if (!$finaljuego) : ?>
        <form>
            Introduzca una letra <input type="text" size="1" maxlength="1" name="letra" autofocus>
        </form>
    <?php else : ?>
        <a href="<?= $_SERVER['PHP_SELF'] ?>"> Otra partida </a>
    <?php endif; ?>
</body>

</html>