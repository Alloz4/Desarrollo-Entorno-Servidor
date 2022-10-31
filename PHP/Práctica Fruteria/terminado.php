<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Fruteria S. XXI</title>
</head>

<body>
    <br>
    <h4>Muchas gracias por su pedido.</h4>
    <input type="button" value=" NUEVO CLIENTE " onclick="location.href='<?= $_SERVER['PHP_SELF']; ?>'">
    <?php
    session_destroy();
    ?>

</body>

</html>