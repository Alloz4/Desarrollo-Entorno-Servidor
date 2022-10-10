<?php

$datos = $_REQUEST;
print_r($datos);
echo "<br><br>";
var_dump($datos);
echo "<br><br>";

foreach ($datos as $key => $value) {
    echo $key . " => " . $value;
    echo "<br>";
}
