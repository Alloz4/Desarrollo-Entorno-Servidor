<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>

<body>
    <form action="02.php" method="post">
        <p>Número 1: <input type="number" name="n1"></p>
        <p>Número 2: <input type="number" name="n2"></p>
        <p>Suma: <input type="submit" name="operador" value="+"></p>
        <p>Resta: <input type="submit" name="operador" value="-"></p>
        <p>Multiplicación: <input type="submit" name="operador" value="*"></p>
        <p>División: <input type="submit" name="operador" value="/"></p>
        <p>Resultado:
            <input type="radio" name="salida" value="decimal" checked> Decimal
            <input type="radio" name="salida" value="binario"> Binario
            <input type="radio" name="salida" value="hexadecimal"> Hexadecimal
        </p>
    </form>
    <?php
    if ($_POST) {
        $n1 = $_POST['n1'];
        $n2 = $_POST['n2'];
        $operador = $_POST['operador'];
        $resultadoSalida = $_POST['salida'];
        $resultado = 0;
    }

    if (empty($n1) || empty($n2)) {
        echo "No se han enviado datos";
    } else {
        switch ($operador) {
            case '+':
                $resultado = $n1 + $n2;
                break;
            case '-';
                $resultado = $n1 - $n2;
                break;
            case '*':
                $resultado = $n1 * $n2;
                break;
            case '/':
                if ($n2 == 0) {
                    echo "No se puede dividir por 0.";
                    exit;
                } else {
                    $resultado = $n1 / $n2;
                }
                break;
        }
        switch ($resultadoSalida) {
            case 'decimal':
                $resultado = $resultado;
                break;
            case 'binario':
                $resultado = decbin($resultado);
                break;
            case 'hexadecimal':
                $resultado = dechex($resultado);
                break;
        }
        echo "El resultado es: " . $resultado;
    }

    ?>
</body>

</html>