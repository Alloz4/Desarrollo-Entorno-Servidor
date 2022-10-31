<?php
session_start();

if (!isset($_SESSION['numeroOculto'])) {
    $_SESSION['numeroOculto'] = random_int(1, 20);
    echo "<h1>¡Bienvenido!</h1>";
} else {
    if (isset($_REQUEST['numeroOculto'])) {
        echo "El número es: " . $_SESSION['numeroOculto'] . "<br>";
        if ($_SESSION['numeroOculto'] == $_REQUEST['numeroOculto']) {
            echo "Enhorabuena, has acertado! <br>";
            session_destroy();
            header("Refresh: 3");
        } else {
            echo "Has fallado!";
        }
    }
}
?>
<form method="get">
    Introduce un número: <input type="text" name="numeroOculto">
</form>