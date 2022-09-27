<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Juego de Piedra, Papel o Tijera</title>
</head>

<body>

    <?php

    define('PIEDRA1',  "&#x1F91C;");
    define('PIEDRA2',  "&#x1F91B;");
    define('TIJERAS',  "&#x1F596;");
    define('PAPEL',    "&#x1F91A;");
    define('GANA1',     " Ha ganado el jugador 1");
    define('GANA2',   " Ha ganado el jugador 2");
    define('EMPATE',   "¡Empate!");


    $jugador1 = rand(1, 3);
    $jugador2 = rand(1, 3);


    function jugador1($jugador1)
    {
        if ($jugador1 == 1) {
            echo PIEDRA1;
        }
        if ($jugador1 == 2) {
            echo PAPEL;
        }
        if ($jugador1 == 3) {
            echo TIJERAS;
        }
    }

    function jugador2($jugador2)
    {
        if ($jugador2 == 1) {
            echo PIEDRA2;
        }
        if ($jugador2 == 2) {
            echo PAPEL;
        }
        if ($jugador2 == 3) {
            echo TIJERAS;
        }
    }

    function jugar($jugador1, $jugador2)
    {
        if ($jugador1 == $jugador2) {
            echo EMPATE;
        }
        if ($jugador1 == 1 && $jugador2 == 2) {
            echo GANA2;
        }
        if ($jugador1 == 1 && $jugador2 == 3) {
            echo GANA1;
        }
        if ($jugador1 == 2 && $jugador2 == 1) {
            echo GANA1;
        }
        if ($jugador1 == 2 && $jugador2 == 3) {
            echo GANA2;
        }
        if ($jugador1 == 3 && $jugador2 == 1) {
            echo GANA2;
        }
        if ($jugador1 == 3 && $jugador2 == 2) {
            echo GANA1;
        }
    }

    ?>
    <center>
        <h1>¡Piedra, Papel, Tijera!</h1>
        <p>Actualice la página para mostrar otra partida.</p>
        <table>
            <tr>
                <td>
                    <p style="text-align: center;"><strong>Jugador 1</strong></p>
                </td>
                <td>
                    <p style="text-align: center;"><strong>Jugador 2</strong></p>
                </td>
            </tr>
            <tr>
                <td>
                    <p style="font-size: 100px;  margin:0px; "><?php echo jugador1($jugador1); ?></p>
                </td>
                <td>
                    <p style="font-size: 100px; margin:0px;"><?php echo jugador2($jugador2); ?></p>
                </td>
            </tr>
        </table>
        <p style="text-align: center;"><strong><?php echo jugar($jugador1, $jugador2); ?></strong></p>
    </center>

</body>

</html>