<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Palabra Oculta</title>
</head>

<body>
    <?php

    function elegirPalabra()
    {
        static $tpalabras = ["Madrid", "Sevilla", "Murcia", "Málaga", "Mallorca", "Menorca"];
        $res = array_rand($tpalabras, 1);
        return $tpalabras[$res];
    }

    function comprobarLetra($letra, $cadena)
    {
        if (strpos($cadena, $letra) !== false) {
            return true;
        } else return false;
    }

    function generaPalabraconHuecos($cadenaletras, $cadenapalabra)
    {

        // Genero una cadena resultado inicialmente con todas las posiciones con -
        $resu = $cadenapalabra;
        for ($i = 0; $i < strlen($resu); $i++) {
            $resu[$i] = '-';
        }
        // COMPLETAR rellenado la cadena resu

        for ($i = 0; $i < strlen($cadenaletras); $i++) {
            $letra = $cadenaletras[$i];
            $indice = 0;
            while (($pos = strpos($cadenapalabra, $letra, $indice)) !== FALSE) {
                $indice   = $pos + 1;
                $resu[$pos] = $letra;
            }
        }
        return $resu;
    }

    function ganar()
    {
        if (generaPalabraconHuecos($_SESSION['letrasusuario'], $_SESSION['palabrasecreta']) == $_SESSION['palabrasecreta']) {
            session_destroy();
            print("Palabra " . $_SESSION['palabrasecreta'] . "<br> Enhorabuena has acertado <br> <a href='index.php?ganas=si'>Otra partida</a>");
        }
    }

    function limpia($str)
    {
        $str = strtolower($str);
    }


    // CONTROL
    session_start();
    if (empty($_GET['letra'])) {
        $_SESSION['palabrasecreta'] = elegirPalabra();
        $_SESSION['letrasusuario'] = ""; // Inicialmente no tiene ninguna letra  
        $_SESSION['fallos'] = 0; // Inicialmente no hay ningún fallo
        include 'formulario.php';
    } else {
        if (!empty($_GET['letra'])) {
            if (comprobarLetra($_GET['letra'], $_SESSION['palabrasecreta'])) {
                $_SESSION['letrasusuario'] .= $_GET['letra'];
                include 'formulario.php';
                ganar();
            } else {
                $_SESSION['fallos']++;
                if ($_SESSION['fallos'] > 5) {
                    session_destroy();
                    print("Superado el el maximo numero de fallos. Has perdido <br> <a href='index.php'>Otra partida</a>");
                } else {
                    include 'formulario.php';
                }
            }
        }
    }
    ?>
</body>

</html>