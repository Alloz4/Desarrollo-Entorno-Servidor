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
    //Defino varias constantes para que el código se vea más limpio
    define("dado1", "&#9856;");
    define("dado2", "&#9857;");
    define("dado3", "&#9858;");
    define("dado4", "&#9859;");
    define("dado5", "&#9860;");
    define("dado6", "&#9861;");
    define('GANA1', "¡Ha ganado el jugador 1!");
    define('GANA2', "¡Ha ganado el jugador 2!");
    define('EMPATE', "¡Empate!");

    //Creo el array de los dados y le asign
    $dados = array(
        1 => dado1,
        2 => dado2,
        3 => dado3,
        4 => dado4,
        5 => dado5,
        6 => dado6
    );
    //Creo la funcion tirar los dados, en ella creo un array auxiliar para meter números aleatorios y asi simular una tirada de dados real.
    function tirada()
    {
        $tirada = array();
        for ($i = 0; $i < 5; $i++) {
            $aleatorio = rand(1, 6);
            $tirada[$i + 1] = $aleatorio;
        }
        return $tirada;
    }
    //Creo la funcion puntuacion para saber el maximo, minimo y total
    function puntuacion($tirada)
    {
        $max = max($tirada);
        $min = min($tirada);
        $sum = array_sum($tirada);

        $puntuacionTotal = $sum - $max - $min;

        return $puntuacionTotal;
    }
    //Creo la funcion mostrar dados para que muestre los emojis definidos arriba en las constantes. Le paso el array aleatorio y los dados para que devuelva los emojis y simule la tirada aleatoria.
    function mostrarDados($jugador, $dados)
    {

        foreach ($jugador as $value) {
            echo $dados[$value];
        }
    }
    //En esta funcion le paso las puntuaciones de cada tirada para ver quien es el ganador
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
    //Asigno las funciones a determinadas variables.
    $jugador1 = tirada();
    $jugador2 = tirada();
    $puntuacion1 = puntuacion($jugador1);
    $puntuacion2 = puntuacion($jugador2);
    ?>
    <!-- Creo una tabla en la que poner los datos para que todo quede orgamizado. -->
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