<?php
include_once('funciones.php');

//Crear un formulario para calcular la bici más cercana a unas coordenadas.
//El formulario debe tener dos campos de texto para las coordenadas y un botón para enviar el formulario.
//El formulario debe enviar los datos a la misma página.
//La página debe mostrar la bici más cercana a las coordenadas introducidas.

//Si el formulario no ha sido enviado, mostrar el formulario.
//Si el formulario ha sido enviado, mostrar la bici más cercana a las coordenadas introducidas.

//Si el formulario no ha sido enviado, mostrar el formulario.



echo mostrartablabicis(cargarbicis());

if (!isset($_POST['coordx'])) {
    echo "<form action='consultabicis.php' method='post'>";
    echo "<label for='coordx'>Coordenada X</label>";
    echo "<input type='text' name='coordx' id='coordx'>";
    echo "<label for='coordy'>Coordenada Y</label>";
    echo "<input type='text' name='coordy' id='coordy'>";
    echo "<input type='submit' value='Enviar'>";
    echo "</form>";
} else {
    //Si el formulario ha sido enviado, mostrar la bici más cercana a las coordenadas introducidas.
    $bicis = cargarbicis();
    $bicimascercana = bicimascercana($bicis, $_POST['coordx'], $_POST['coordy']);
    echo "<p>La bici más cercana es la bici con ID " . $bicimascercana->id . "</p>";
}
