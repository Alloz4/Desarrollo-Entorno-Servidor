<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Forma de pago</title>
</head>

<body>
    <?php
    session_start();

    if (isset($_GET['nuevatarjeta'])) {
        $tarjetaactual = $_GET['nuevatarjeta'];
        $_SESSION['tarjeta'] = $tarjetaactual;
        echo "<center>";
        echo "<br><h1> Cambiando su tipo de tarjeta...</h1> ";
        echo '<iframe src="https://giphy.com/embed/88EvfARM1YaCQ" width="480" height="317" frameBorder="0" class="giphy-embed" allowFullScreen></iframe><p><a href="https://giphy.com/gifs/little-rascals-our-gang-spanky-88EvfARM1YaCQ">via GIPHY</a></p>';
        echo "</center>";
        header("Refresh:3; url=\"" . $_SERVER['PHP_SELF'] . "\"");
        echo "<body></html>";
        exit();
    } else {
        if (isset($_SESSION['tarjeta'])) {
            $tarjetaactual = $_SESSION['tarjeta'];
        }
    }

    if (isset($tarjetaactual)) {
        echo "<center>";
        echo " <H2> SU FORMA DE PAGO ACTUAL ES</H2> </br> ";
        echo " <img src='imagenes/$tarjetaactual.gif' /></a>";
        echo "</center>";
    } else {
        echo "<center>";
        echo " <H2> NO TIENE FORMA  DE PAGO ASIGNADA ES</H2> </br> ";
        echo "<br><br>";
        echo "</center>";
    }
    session_destroy();

    ?>
    <center>
        <h2>SELECCIONE UNA NUEVA TARJETA DE CREDITO </h2><br>
        <a href='?nuevatarjeta=cashu'><img src='imagenes/cashu.gif' /></a>&ensp;
        <a href='?nuevatarjeta=cirrus1'><img src='imagenes/cirrus1.gif' /></a>&ensp;
        <a href='?nuevatarjeta=dinersclub'><img src='imagenes/dinersclub.gif' /></a>&ensp;
        <a href='?nuevatarjeta=mastercard1'><img src='imagenes/mastercard1.gif' /></a>&ensp;
        <a href='?nuevatarjeta=paypal'><img src='imagenes/paypal.gif' /></a>&ensp;
        <a href='?nuevatarjeta=visa1'><img src='imagenes/visa1.gif' /></a>&ensp;
        <a href='?nuevatarjeta=visa_electron'><img src='imagenes/visa_electron.gif' /></a>

    </center>
    ?>
</body>

</html>