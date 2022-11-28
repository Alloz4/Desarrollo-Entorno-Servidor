<?php

session_start();

if (!isset($_SESSION['saldo'])) {
    $_SESSION['saldo'] = 0;
}
$msg = "";

if (isset($_POST['Orden'])) {
    $importe = $_POST['importe'];
    $orden = $_POST['Orden'];
    switch ($orden) {
        case 'Ingreso':
            if (!is_numeric($importe) || $importe < 0) {
                $msg = "Importe incorrecto o importe menor de 0 <br>";
            } else {
                ingreso($importe);
                $msg = "Ingreso realizado <br>";
            }
            break;
        case 'Reintegro':
            if (!is_numeric($importe) || $importe > $_SESSION['saldo'] || $importe < 0) {
                $msg = "Importe erróneo o importe mayor al saldo. <br>";
            } else {
                reintegro($importe);
                $msg = "Reintegro realizado <br>";
            }
            break;
        case 'Ver saldo':
            $msg = verSaldo();
            break;
        case 'Terminar':
            session_destroy();
            break;
    }
    header("Location: minibanco.php?msg=" . urlencode($msg));
}

function ingreso($importe)
{
    return $_SESSION['saldo'] += $importe;
}

function reintegro($importe)
{
    $importe = intval($_POST['importe']);
    return $_SESSION['saldo'] -= $importe;
}
function verSaldo()
{
    return "Su saldo actual es " . $_SESSION['saldo'] . " Euros. <br>";
}
