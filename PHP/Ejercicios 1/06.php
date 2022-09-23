<!-- 6.- Generar la tabla de multiplicar de un número elegido al azar entre 1 y 10 con la siguiente apariencia -->

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>

<body>
    <h1 style="background-color: blue; color: white; text-align: center;">TABLA DE MULTIPLICAR</h1>
    <table>
        <?php
        $num = random_int(1, 10);
        ?>
        <tr>
            <td>Tabla del <?php echo $num ?></td>
            <td><?php echo " "; ?></td>
        </tr>
        <?php
        for ($i = 0; $i <= 10; $i++) {
            echo "<tr>";
            echo "<td>$num x $i = </td>";
            echo "<td>" . $num * $i . "</td>";
            echo "</tr>";
        }
        ?>
    </table>
    <style>
        table {
            border: 1px solid black;
            border-collapse: collapse;
            width: 50%;
            margin: auto;
        }

        td {
            border: 1px solid black;
            border-collapse: collapse;
            text-align: center;
        }
    </style>
</body>

</html>