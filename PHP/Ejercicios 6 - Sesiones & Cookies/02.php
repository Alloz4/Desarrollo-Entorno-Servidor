<?php
session_start();
define('INTENTOS_MAX', 5);

if (!isset($_SESSION['numeroOculto'])) {
    $_SESSION['numeroOculto'] = random_int(1, 20);
    $_SESSION['intentos'] = 0;
    echo "<h1>¡Bienvenido!</h1>";
} else {
    if (isset($_REQUEST['numeroOculto'])) {
        if ($_SESSION['numeroOculto'] == $_REQUEST['numeroOculto']) {
            echo "Enhorabuena, has acertado! <br>";
            session_destroy();
            header("Refresh: 3");
            exit();
        } else if ($_SESSION['numeroOculto'] != $_REQUEST['numeroOculto']) {
            echo "¡Has fallado! <br>";
            $_SESSION['intentos']++;
            echo "Te quedan " . (INTENTOS_MAX - $_SESSION['intentos']) . "<br>";
            if ($_SESSION['numeroOculto'] > $_REQUEST['numeroOculto']) {
                echo "El número que intentas adivinar es mayor. <br>";
            } else {
                echo "El número que intentas adivinar es menor. <br>";
            }
        }
        if ($_SESSION['intentos'] === INTENTOS_MAX) {
            echo "Has agotado todos los intentos. <br>";
            session_destroy();
            header("Refresh: 3");
            exit();
        }
    }
}
?>
<form method="get" action="02.php">
    Introduce un número: <input type="text" name="numeroOculto">
</form>