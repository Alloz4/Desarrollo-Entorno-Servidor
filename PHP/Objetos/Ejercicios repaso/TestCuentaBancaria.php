<?php
/*
 * Prueba de CuentaBancaria
 */
include_once 'CuentaBancaria.php';
echo "<h2> PROBANDO </H2>";

$c1 = new CuentaBancaria(100);
$c2 = new CuentaBancaria(1900);
$c3 = new CuentaBancaria();

$c1->abono(20);
$c1->ingreso(10);
$c1->anotarGastos();
echo "<br> Cuenta c1 = " . $c1->consultarEstado();

$c2->ingreso(100);
$c2->anotarGastos(); // No se aplican pues su saldo es mayor que 1000
$c2->anotarIntereses(5); // 5% de interes
$c2->transferencia(100, $c3);
echo "<br> Cuenta c2 = " . $c2->consultarEstado();

$c3->abono(75);
$c3->abono(75);  // Se queda con saldo negativo
echo "<br> Cuenta c3 = " . $c3->consultarEstado();
