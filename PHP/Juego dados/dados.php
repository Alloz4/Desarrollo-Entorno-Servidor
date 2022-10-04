<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Juego de los Dados</title>
</head>

<body>
    <?php
    define("dado1", "&#9856;");
    define("dado2", "&#9857;");
    define("dado3", "&#9858;");
    define("dado4", "&#9859;");
    define("dado5", "&#9860;");
    define("dado6", "&#9861;");
    define('GANA1', "¡Ha ganado el jugador 1!");
    define('GANA2', "¡Ha ganado el jugador 2!");
    define('EMPATE', "¡Empate!");


    $dados = array(
        1 => dado1,
        2 => dado2,
        3 => dado3,
        4 => dado4,
        5 => dado5,
        6 => dado6
    );

    function tirada()
    {
        $tirada = array();
        for ($i = 0; $i < 5; $i++) {
            $aleatorio = rand(1, 6);
            $tirada[$i + 1] = $aleatorio;
        }
        return $tirada;
    }

    function puntuacion($tirada)
    {
        $max = max($tirada);
        $min = min($tirada);
        $sum = array_sum($tirada);

        $puntuacionTotal = $sum - $max - $min;

        return $puntuacionTotal;
    }
    function mostrarDados($jugador, $dados)
    {

        foreach ($jugador as $value) {
            echo $dados[$value];
        }
    }

    function jugadorGanador($puntuacion1, $puntuacion2)
    {
        if ($puntuacion1 > $puntuacion2) {
            echo GANA1;
        } else if ($puntuacion2 > $puntuacion1) {
            echo GANA2;
        } else echo EMPATE;
    }
    ?>
    <?php
    $jugador1 = tirada();
    $jugador2 = tirada();
    $puntuacion1 = puntuacion($jugador1);
    $puntuacion2 = puntuacion($jugador2);
    ?>
    <center>
        <h1>Cinco dados</h1>
        <p>Actualice la página para mostrar una nueva tirada</p>
        <table>
            <tr>
                <td>
                    <p style="text-align: center;"><strong>Jugador 1</strong></p>
                </td>
                <td style="font-size: 40px; background-color:red"><?php mostrarDados($jugador1, $dados); ?></td>
                <td><strong><?php echo $puntuacion1; ?></strong></td>
            <tr>
                <td>
                    <p style="text-align: center;"><strong>Jugador 2</strong></p>
                </td>
                <td style="font-size: 40px; background-color:deepskyblue"><?php mostrarDados($jugador2, $dados); ?></td>
                <td><strong><?php echo $puntuacion2; ?></strong></td>
            </tr>
        </table>
        <p style="text-align: center;"><strong><?php jugadorGanador($puntuacion1, $puntuacion2); ?></strong></p>
    </center>

</body>

</html>