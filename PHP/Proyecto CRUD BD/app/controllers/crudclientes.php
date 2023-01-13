<?php

function crudBorrar($id)
{
    $db = AccesoDatos::getModelo();
    $tuser = $db->borrarCliente($id);
}

function crudTerminar()
{
    AccesoDatos::closeModelo();
    session_destroy();
}

function crudAlta()
{
    $db = AccesoDatos::getModelo();
    $cli = new Cliente();
    $orden = "Nuevo";
    $cli->id = $db->getUltimoId();
    include_once "app/views/formulario.php";
}

function crudDetalles($id)
{
    $db = AccesoDatos::getModelo();
    $cli = $db->getCliente($id);
    include_once "app/views/detalles.php";
}

//Funciones de navegación en detalles
function crudDetallesSiguiente($id)
{
    $db = AccesoDatos::getModelo();
    $cli = $db->getClienteSiguiente($id);
    if ($cli) {
        include_once "app/views/detalles.php";
    } else {
        crudDetalles($id);
    }
}

function crudDetallesAnterior($id)
{
    $db = AccesoDatos::getModelo();
    $cli = $db->getClienteAnterior($id);
    if ($cli) {
        include_once "app/views/detalles.php";
    } else {
        crudDetalles($id);
    }
}

//Función para imprimir los detalles del cliente

function crudDetallesImprimir($id)
{
    $db = AccesoDatos::getModelo();
    $cli = $db->getCliente($id);
    include_once "app/views/generarPDF.php";
}

//Mostar la foto del cliente

function mostrarFoto($id)
{

    define("DIRECTORIO", 'app\uploads\\');
    $ceros = 0;
    $fichero = str_pad($ceros, 7, "0", STR_PAD_LEFT);
    $nombreFichero = substr($fichero, 0, 8 - strlen($id)) . $id;
    $fichero = 'app/uploads/' . $nombreFichero . '.jpg';


    if (file_exists($fichero)) {
        return "<img src='$fichero' alt='Foto cliente'>";
    } else {
        return "<img src='https://robohash.org/$id' width='20' alt='Foto perfil robot'>";
    }
}

//Mostrar la bandera del pais según la IP

function mostrarBandera($ip)
{

    $url = "http://ip-api.com/json/" . $ip;
    $json = file_get_contents($url);
    $datos = json_decode($json, true);
    if ($datos['status'] == "fail") {
        $bandera = "https://flagpedia.net/data/flags/w580/unknown.webp";
        echo "<img src='https://upload.wikimedia.org/wikipedia/commons/c/c0/Black_flag_waving.png' width='20' alt='No hay bandera'>";
    } else {
        $pais = $datos['country'];
        $bandera = $datos['countryCode'];
        $bandera = strtolower($bandera);
        $bandera = "https://flagpedia.net/data/flags/w580/$bandera.webp";
        echo "<img src='$bandera' alt='$pais' title='$pais' width='30px' height='50px'>";
    }
}

//Mostar el mapa del cliente

function mostrarMapa($ip)
{

    $url = "http://ip-api.com/json/" . $ip;
    $json = file_get_contents($url);
    $datos = json_decode($json, true);

    if ($datos['status'] == "fail") {
        echo "No hay mapa disponible.";
    } else {
        $latitud = $datos['lat'];
        $longitud = $datos['lon'];
        echo "<iframe src='https://maps.google.com/maps?q=$latitud,$longitud&z=15&output=embed' width='250' height='300' frameborder='0' style='border:0' allowfullscreen></iframe>";
    }
}

function crudModificar($id)
{
    $db = AccesoDatos::getModelo();
    $cli = $db->getCliente($id);
    $orden = "Modificar";
    include_once "app/views/formulario.php";
}

//Funciones de navegación en modificación

function crudModificarSiguiente($id)
{
    $db = AccesoDatos::getModelo();
    $cli = $db->getClienteSiguiente($id);
    if ($cli) {
        $orden = "Modificar";
        include_once "app/views/formulario.php";
    } else {
        crudModificar($id);
    }
}

function crudModificarAnterior($id)
{
    $db = AccesoDatos::getModelo();
    $cli = $db->getClienteAnterior($id);
    if ($cli) {
        $orden = "Modificar";
        include_once "app/views/formulario.php";
    } else {
        crudModificar($id);
    }
}

function crudPostAlta()
{
    $db = AccesoDatos::getModelo();
    limpiarArrayEntrada($_POST); //Evito la posible inyección de código
    $cli = new Cliente();
    $cli->id            = $_POST['id'];
    $cli->first_name    = $_POST['first_name'];
    $cli->last_name     = $_POST['last_name'];
    $cli->email         = $_POST['email'];
    $cli->gender        = $_POST['gender'];
    $cli->ip_address    = $_POST['ip_address'];
    $cli->telefono      = $_POST['telefono'];



    $correcto = true;

    if (emailRepetido($cli->email)) {
        print "<p style='color: red;'>El email ya existe en la base de datos.</p>";
        $cli->email = "";
        $correcto = false;
    }
    if (ipRepetida($cli->ip_address)) {
        print "<p style='color: red;'>La IP ya existe en la base de datos.</p>";
        $cli->ip_address = "";
        $correcto = false;
    }
    if (!ipCorrecta($cli->ip_address)) {
        print "<p style='color: red;'>La IP no tiene el formato correcto.</p>";
        $cli->ip_address = "";
        $correcto = false;
    }
    if (!telefonoCorrecto($cli->telefono)) {
        print "<p style='color: red;'>El teléfono no tiene el formato correcto.</p>";
        $cli->telefono = "";
        $correcto = false;
    }

    if ($correcto) {
        $db->addCliente($cli);
        subirFoto($cli->id);
    } else {
        echo "<script>alert('No se ha podido añadir el cliente.');</script>";
        $orden = "Nuevo";
        include_once "app/views/formulario.php";
    }
}

function crudPostModificar()
{
    limpiarArrayEntrada($_POST); //Evito la posible inyección de código
    $cli = new Cliente();

    $cli->id            = $_POST['id'];
    $cli->first_name    = $_POST['first_name'];
    $cli->last_name     = $_POST['last_name'];
    $cli->email         = $_POST['email'];
    $cli->gender        = $_POST['gender'];
    $cli->ip_address    = $_POST['ip_address'];
    $cli->telefono      = $_POST['telefono'];
    $db = AccesoDatos::getModelo();


    $correcto = true;

    if (emailRepetido($cli->email)) {
        print "<p style='color: red;'>El email ya existe en la base de datos.</p>";
        $cli->email = "";
        $correcto = false;
    }

    if (!ipCorrecta($cli->ip_address)) {
        print "<p style='color: red;'>La IP no tiene el formato correcto.</p>";
        $cli->ip_address = "";
        $correcto = false;
    }
    if (!telefonoCorrecto($cli->telefono)) {
        print "<p style='color: red;'>El teléfono no tiene el formato correcto.</p>";
        $cli->telefono = "";
        $correcto = false;
    }

    if ($correcto) {
        subirFoto($cli->id);
        $db->modCliente($cli);
    } else {
        echo "<script>alert('No se ha podido modificar el cliente.');</script>";
        $orden = "Modificar";
        include_once "app/views/formulario.php";
    }
}

//Funcion para ver si la ip tiene el formato correcto (999.999.999.999)

function ipCorrecta($ip)
{
    $formato = true;
    $patron = "/^[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}$/";

    if (preg_match($patron, $ip)) {
        $formato = true;
    } else {
        $formato = false;
    }

    return $formato;
}

//Funcion para ver si la ip esta repetido

function ipRepetida($ip)
{
    $db = AccesoDatos::getModelo();
    $cli = $db->getClienteIp($ip);
    if ($cli) {
        return true;
        echo "La ip ya existe";
    } else {
        return false;
    }
}

//Funcion para ver si el email esta repetido

function emailRepetido($email)
{
    $db = AccesoDatos::getModelo();
    $cli = $db->getClienteEmail($email);
    if ($cli) {
        return true;
        echo "El email ya existe";
    } else {
        return false;
    }
}

//Funcion para ver si el telefono tiene el formato correcto (999-999-999)

function telefonoCorrecto($telefono)
{
    $formato = true;
    $patron = "/^[0-9]{3}-[0-9]{3}-[0-9]{4}$/";

    if (preg_match($patron, $telefono)) {
        $formato = true;
    } else {
        $formato = false;
    }

    return $formato;
}

//Funcion para subir la foto o modificarla, la foto se guarda con el nombre del id del cliente y la extension jpg

function subirFoto($id)
{
    $ceros = 0;
    $fichero = str_pad($ceros, 7, "0", STR_PAD_LEFT);
    $fichero = substr($fichero, 0, 8 - strlen($id)) . $id;
    $ruta = 'app/uploads/' . $fichero . '.jpg';

    $nombre = $_FILES['foto']['name'];
    $tipo = $_FILES['foto']['type'];
    $tamano = $_FILES['foto']['size'];

    if ($nombre != "") {
        if ($tipo == "image/jpeg" || $tipo == "image/jpg") {
            if ($tamano <= 1000000) {
                move_uploaded_file($_FILES['foto']['tmp_name'], $ruta);
            } else {
                echo "El tamaño es demasiado grande";
            }
        } else {
            echo "El formato no es el correcto";
        }
    }
}
