<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>

<body>
    <h1>JUEGO AHORCADO</h1>
    <label>PALABRA : </label> <?= generaPalabraconHuecos($_SESSION['letrasusuario'], $_SESSION['palabrasecreta']); ?> <br>
    Has cometido <?= $_SESSION['fallos'] ?> fallos <br>
    <form action="index.php" method="GET">
        Introduzca una letra: <input type="text" name="letra">
    </form>
</body>

</html>