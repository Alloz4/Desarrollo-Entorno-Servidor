<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
<?php 

$numero = random_int(1,9);
$espacios = $numero - 1;
echo "<code>";
//
for ($i=1; $i <= $numero; $i++) { 
    for ($j=$espacios; $j > 0 ; $j--) { 
        echo "&nbsp;";
    }
    $espacios--;
    for ($k=0; $k <=2*$i-1; $k++) { 
        echo "*";
    }
    echo "<br>";
}

echo "</code>";

?>
</body>
</html>