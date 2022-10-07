<!DOCTYPE html>
<html lang="en">
<!-- Crear página que simule un calculadora sencilla, mediante un único archivo 02.php que mostrará un formularios con dos campos numéricos y 4 botones con los 4 tipos de operaciones + - * /  posibles. Se incluirá también 3 controles de tipo radio que indicarán como queremos que se muestre el resultado en decimal, binario o hexadecimal.
El programa php debe comprobar que se han recibido los dos valores numéricos y detectará el error de intento de división por cero. Mostrará el resultado calculado según el formato elegido. Por omisión se mostrará en decimal.
 -->

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Ejercicio 02</title>
</head>

<body>
    <center>
        <h1>Mini Calculadora</h1>
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
    </center>
    <?php
    if ($_POST) {
        $n1 = $_POST['n1'];
        $n2 = $_POST['n2'];
        $operador = $_POST['operador'];
        $resultadoSalida = $_POST['salida'];
        $resultado = 0;
    }
    if (isset($n1) || isset($n2)) {
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
        echo "<center>";
        echo "El resultado es: " . $resultado;
        echo "</center>";
    } else {
        echo "<center>";
        echo "No se han enviado datos";
        echo "</center>";
    }

    ?>
</body>

</html>