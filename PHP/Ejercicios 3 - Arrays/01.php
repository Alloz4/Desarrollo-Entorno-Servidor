<!-- Rellenar un array con 20 números aleatorios entre 1 y 10 y mostrar el contenido del array  mediante una tabla de una fila en HMTL. Mostrar a continuación el valor máximo, el mínimo y el  valor que mas veces se repite.  -->
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Ejercicio 01</title>
</head>

<body>
    <?php
    $array1 = [];
    function rellenarArray(&$array1, $tamaño)
    {
        for ($i = 0; $i < $tamaño; $i++) {
            $aleatorio = random_int(1, 10);
            $array1[] = $aleatorio;
        }
    }

    rellenarArray($array1, 20);

    ?>
    <p>El contenido del array es el siguiente: </p>
    <table style="border: 1px solid black ;">
        <tr>
            <?php
            for ($i = 0; $i < 20; $i++) {
            ?>
                <td style="border: 1px solid black;">
                    <?php print_r($array1[$i]); ?>
                </td>
            <?php } ?>
        </tr>
    </table>
    <?php
    echo "<br>";
    echo "El valor máximo del array es: ";
    echo max($array1) . "<br>";
    echo "El valor mínimo del array es: ";
    echo min($array1) . "<br>";

    $array2 = array_count_values($array1);
    $maximo = max($array2);
    $valor = array_search($maximo, $array2);
    echo "El valor que mas veces se repite es: " . $valor . " y se repite " . $maximo . " veces";

    ?>
</body>

</html>