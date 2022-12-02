<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title> Las frutas </title>
</head>

<body>

    <?php

    //Creo un array con las frutas
    $fruta = [];

    if (isset($_GET['listafrutas'])) {
        //Si existe la lista de frutas, la guardo en la variable fruta
        $fruta = $_GET['listafrutas'];
        //Guardo en la cookie la lista de frutas que he seleccionado separadas por comas y con una duración de 1 semana
        setcookie('listafrutas', implode(",", $fruta), time() + 7 * 24 * 60 * 60);
    } else {
        //Si no esta definida la lista de frutas  y existe la cookie, la guardo en la variable fruta separado por comas
        if (isset($_COOKIE['listafrutas'])) {
            $fruta = explode(",", $_COOKIE['listafrutas']);
        }
    }
    ?>

    <form>
        <fieldset>
            <legend>Sus frutas preferidas </legend>
            <label for="nombre">Lista de frutas:</label><br>
            <select name="listafrutas[]" multiple>
                <!-- Recorro el array fruta y si el valor de la fruta coincide con el valor de la cookie, lo selecciono -->
                <option value="Platano" <?= in_array("Platano", $fruta) ? "selected=\"selected\"" : "" ?>>Platano</option>
                <option value="Fresa" <?= in_array("Fresa", $fruta) ? "selected=\"selected\"" : "" ?>>Fresa</option>
                <option value="Naranja" <?= in_array("Naranja", $fruta) ? "selected=\"selected\"" : "" ?>>Naranja</option>
                <option value="Melón" <?= in_array("Melón", $fruta) ? "selected=\"selected\"" : "" ?>>Melón</option>
                <option value="Manzana" <?= in_array("Manzana", $fruta) ? "selected=\"selected\"" : "" ?>>Manzana</option>
            </select>
            <input type="submit" value=" Cambiar ">
        </fieldset>
    </form>
</body>

</html>