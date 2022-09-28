<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Ejercicio 03</title>
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

    $periodico = array_rand($periodicos, 1);

    echo "El medio recomendado es: <a href='$periodicos[$periodico]'>$periodico</a>";

    ?>
</body>

</html>