<!-- Definir dos variables asignándoles un valor entero aleatorio entre 1 y 10 y mostrar su suma, su resta, su división, su multiplicación, módulo y potencia (ciclo for) -->
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

    $numero1 = random_int(1, 10);
    $numero2 = random_int(1, 10);

    echo "1º Número: " . $numero1 . "<br>";
    echo "2º Número: " . $numero2 . "<br>";

    echo "La suma de los numeros es: " . ($numero1 + $numero2) . "<br>";
    echo "La resta de los numeros es: " . ($numero1 - $numero2) . "<br>";
    echo "La multiplicacion de los numeros es: " . ($numero1 * $numero2) . "<br>";
    echo "La division de los numeros es: " . ($numero1 / $numero2) . "<br>";
    echo "El modulo de los numeros es: " . ($numero1 % $numero2) . "<br>";

    $potencia = 1;
    for ($i = 1; $i <= $numero2; $i++) {
        $potencia = $potencia * $numero1;
    }
    echo "La potencia de los numeros es: " . ($potencia) . "<br>";

    ?>
</body>

</html>