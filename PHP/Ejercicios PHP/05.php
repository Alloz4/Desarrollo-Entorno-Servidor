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
    $num1 = random_int(1, 10);
    $num2 = random_int(1, 10);

    echo "1º Número: " . $num1 . "<br>";
    echo "2º Número: " . $num2 . "<br><br>";
    ?>
    <table>
        <tr class="cabecera">
            <td>Operación</td>
            <td>Resultado</td>
        </tr>
        <tr>
            <td class="blanco"><?php echo $num1 . " + " . $num2 ?></td>
            <td class="blanco"><?php echo $num1 + $num2 ?></td>
        </tr>
        <tr>
            <td class="azul"><?php echo $num1 . " - " . $num2 ?></td>
            <td class="azul"><?php echo $num1 - $num2 ?></td>
        </tr>
        <tr>
            <td class="blanco"><?php echo $num1 . " * " . $num2 ?></td>
            <td class="blanco"><?php echo $num1 * $num2 ?></td>
        </tr>
        <tr>
            <td class="azul"><?php echo $num1 . " / " . $num2 ?></td>
            <td class="azul"><?php echo $num1 / $num2 ?></td>
        </tr>
        <tr>
            <td class="blanco"><?php echo $num1 . " % " . $num2 ?></td>
            <td class="blanco"><?php echo $num1 % $num2 ?></td>
        </tr>
        <tr>
            <td class="azul"><?php echo $num1 . " ^ " . $num2 ?></td>
            <td class="azul"><?php echo $num1 ** $num2 ?></td>
        </tr>
    </table>
    <style>
        table {
            border: 1px solid black;
            border-collapse: collapse;
        }

        tr {
            border: 1px solid black;
            text-align: right;
        }

        td {
            border: 1px solid black;
            border-collapse: collapse;
            padding: 5px;
        }

        .azul {
            background-color: #BAC7E7;
        }

        .blanco {
            background-color: white;
        }

        .cabecera {
            color: blue;
            background-color: grey;
        }
    </style>
</body>

</html>