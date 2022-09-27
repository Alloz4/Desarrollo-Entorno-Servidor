<!-- Crear un array que almacene 5 cadenas con el nombre de periódicos y sus enlaces para acceder. El array será asociativo con el nombre del periódico como clave y su URL como valor. -->
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Ejercicio 02</title>
</head>

<body>

    <?php
    $periodicos = [
        "El País" => "https://elpais.com/",
        "El Mundo" => "https://www.elmundo.es/",
        "ABC" => "https://www.abc.es/",
        "El Periódico" => "https://www.elperiodico.com/es/",
        "La Vanguardia" => "https://www.lavanguardia.com/"
    ];

    echo "<h4>Listado de periódicos: </h4>";

    foreach ($periodicos as $key => $value) {
        echo "<li><a href='$value'>$key</a></li>";
    }

    ?>

</body>

</html>