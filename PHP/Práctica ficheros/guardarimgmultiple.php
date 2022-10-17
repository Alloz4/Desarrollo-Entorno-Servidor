<?php
//Defino las variable archivo y directorio para no engrosar tanto el codigo
$archivo = $_FILES['archivos'];
$directorio = "./imgusers/";

//Compruebo si hay archivos para subir
if ($archivo['name'][0] == "") {
    echo ("No hay ningún archivo para subir.");
    echo '<input type="button" onclick="history.back()" value="Volver atrás">';
    exit;
}
//Compruebo el tamaño de todos los archivos 
if (tamañoTotal($archivo) > 300000) {
    echo ("El tamaño total de los archivos es superior a 300 Kbytes");
    exit;
}
//Llamada de funciones
tamañoCadaArchivo($archivo);
checkYsubidaArchivos($archivo, $directorio);


//Funcion en la que compruebo el tamaño de los archivos y los sumo.
function tamañoTotal($archivo)
{
    $suma = 0;

    foreach ($archivo['size'] as $key => $value) {
        $suma += $value;
    }
    return $suma;
}

//Funcion en la que compruebo el tamaño de cada archivo.
function tamañoCadaArchivo($archivo)
{
    foreach ($archivo['size'] as $key => $value) {
        if ($value > 200000) {
            exit("El tamaño del archivo " . $archivo . " supera el limite establecido en 200 Kbytes");
        }
    }
}

//Funcion en la que compruebo si la extension es correcta, si el archivo no esta repetido en el directorio, si esto es asi, subo el archivo al directorio
function checkYsubidaArchivos($archivo, $directorio)
{
    foreach ($archivo['tmp_name'] as $key => $value) {
        $extension = pathinfo(strtoupper($archivo['name'][$key]), PATHINFO_EXTENSION);

        if ($extension == "JPEG" || $extension == "PNG" || $extension == "JPG") {
            if (!file_exists($directorio . $archivo['name'][$key])) {
                if (move_uploaded_file($archivo['tmp_name'][$key], ($directorio . $archivo['name'][$key]))) {
                    echo ("El archivo " . $archivo['name'][$key] . " es válido y se cargó correctamente.<br><br>");
                } else {
                    echo ("El archivo no se cargo correctamente. <br><br>");
                }
            } else {
                echo ("El archivo " . $archivo['name'][$key] . " ya se encuentra en la carpeta. <br>");
            }
        } else {
            echo ("La extension del archivo <b>" . $archivo['name'][$key] . "</b> no es correcta. <br>");
        }
    }
    echo '<input type="button" onclick="history.back()" value="Volver atrás">';
}
