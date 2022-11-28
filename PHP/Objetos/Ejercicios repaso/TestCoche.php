/*
* CLASE DE PRUEBA: Realiza una simulación sencilla de una carrera de coches
*/

<?php

include_once "Coche.php";

const META = 500;         // 500 kilómetros
$parrilla = [];            // Array de coches con 5 coches

// Creo 5 coches
$parrilla[0] = new Coche("Ferrari", 300);
$parrilla[1] = new Coche("600", 100);
$parrilla[2] = new Coche("BMW", 220);
$parrilla[3] = new Coche("Seat", 150);
$parrilla[4] = new Coche("La Cabra", 50);

// Test de pruebas todos deben generar errores

$c1 = $parrilla[0];
$c2 = $parrilla[1];
$c1->acelera(10);
$c2->frena(20);
$c1->parar();
$c2->recorre();

// Arranquen los motores
for ($i = 0; $i < count($parrilla); $i++) {
    $parrilla[$i]->arrancar();
}

// Comienza la carrera !!!!
do {
    for ($i = 0; $i < count($parrilla); $i++) {
        $parrilla[$i]->acelera(random_int(0, 20));
        $parrilla[$i]->recorre();
        $parrilla[$i]->frena(random_int(0, 10));
        echo " <br> " . $parrilla[$i]->info();
    }
} while (!alcanzarMeta($parrilla, META));


// Ordena la tabla para mostrar la clasificación
ordenarClasificacion($parrilla);

// Muestra la clasificación
for ($i = 0; $i < count($parrilla); $i++) {
    echo ($i + 1) . "º Clasificado " . $parrilla[$i]->info() . "<br>";
}

// MÉTODOS AUXILIARES
// Devuelve verdadero si hay algun coche que haya recorrido la distancia indicada.

function alcanzarMeta(array $tcoches, int $distancia): bool
{

    return true;
}

// Ordeno la tabla de objetos por los kilometros recorridos
function ordenarClasificacion(&$tcoches): void
{
    usort($tcoches, function ($a, $b) {
        return $a->getKilometros() - $b->getKilometros();
    });
}
