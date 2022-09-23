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
    for ($i=0; $i <= $numero; $i++) { 
        for ($j=0; $j < $i ; $j++) { 
            if ($i % 2 == 0){
                echo "<span style='color: blue;'>".$i."</span>";
            } else {
                echo "<span style='color: red'>".$i."</span>";
            }
        }
        echo "<br>";
    }   
    
    ?>

</body>
</html>