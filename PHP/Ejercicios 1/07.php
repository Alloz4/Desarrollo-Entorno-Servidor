<!--7. Elegir tres valores entre 100 y 500 y pintar tres barras de color rojo, verde y azul del tamaño indicado.
Pista: Utilizar  3 tablas con una fila del tamaño generado.
 -->
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="refresh" content="2">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>

<body>
    <?php

    $num1 = random_int(100, 500);
    $num2 = random_int(100, 500);
    $num3 = random_int(100, 500);

    echo ('<table width=' . $num1 . '>
      <tr>
         <td bgcolor="red">Rojo ( ' . $num1 . ' )</td>
      </tr>
      </table>
      <table width=' . $num2 . '>
      <tr>
      <td bgcolor="green">Verde ( ' . $num2 . ' )</td>
      </tr>
      </table>
      <table width=' . $num3 . '>
      <tr>
      <td bgcolor="blue">Azul ( ' . $num3 . ' )</td>
      </tr>
      </table>
    </table>');
    ?>
</body>

</html>