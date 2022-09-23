<!-- Generar números al azar entre 1 y 10 hasta que se generen 3 veces el valor 6 de forma consecutiva en ese caso se mostrará cuantos número se han generado. -->
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>

<body>
    <?php
    $contador6 = 0;
    $contador = 0;
    $tiempo = microtime(true);

    while ($contador6 != 3) {
        $num = random_int(1, 10);
        $contador++;
        if ($num == 6) {
            $contador6++;
        }
    }

    $tiempoFinal = microtime(true);

    $microseg = $tiempoFinal - $tiempo;

    echo "Han salido tres 6 seguidos tras generar " . $contador . " números en " . ($microseg * 1000) . " milisegundos.";
    ?>
</body>

</html>