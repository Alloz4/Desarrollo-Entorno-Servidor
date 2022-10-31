<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Fruteria S. XXI</title>
</head>

<body>
    <?php
    $nombre = $_SESSION['nombre'];
    ?>

    <h3>REALICE SU COMPRA <?php echo  strtoupper($nombre) ?></h3>

    <form action="lafruteria.php" method="POST">
        <label>Seleccione la fruta:
            <select name="fruta" required>
                <option value="Naranjas">Naranjas</option>
                <option value="Limones">Limones</option>
                <option value="Platanos">Platanos</option>
                <option value="Manzanas">Manzanas</option>
            </select>
        </label>
        <label>Cantidad: <input type="number" name="cantidad" required></label>
        <input type="submit" value="Anotar" name="boton">
        <input type="submit" value="Terminar" name="boton">
    </form>
</body>

</html>