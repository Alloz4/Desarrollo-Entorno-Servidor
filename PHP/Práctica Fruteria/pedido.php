<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Fruteria S. XXI</title>
</head>

<body>
    <h4>Este es su pedido: </h4>
    <table style="border: 1px solid black">
        <?php
        if (!empty($_SESSION['pedido'])) {
            foreach ($_SESSION['pedido'] as $key => $value) {
                echo "<tr>";
                echo "<td> $key  $value </td>";
                echo "</tr>";
            }
        } else {
            echo "No te llevas nada.";
        }

        ?>
    </table>
</body>

</html>