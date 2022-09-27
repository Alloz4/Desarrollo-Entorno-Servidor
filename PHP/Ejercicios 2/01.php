<?php 

/* 1. Realizar y probar una función en PHP  llamada elMayor  que reciba tres números: 
A,B y C. La función almacenará en C el valor que sea mayor A o B. 
En el caso sean iguales almacenará el valor 0 en C 
¿Qué parámetros se deberían pasar por valor o copia y cuales por referencia?*/

function elMayor($a,$b,&$c){
    echo "Los números son ".$a." y ".$b;
    ?>
    </br>
    <hr>
    <?php
    if($a>$b){
        $c=$a;
        echo "El número resultante en es ".$c;
    }else if($b>$a){
        $c=$b;
        echo "El número resultante en es ".$c;
    }else{
        $c=0;
        echo $a." y ".$b." son iguales con lo cual c es igual a ".$c;
        
    }
}

elMayor(random_int(1,10),random_int(1,10),$resu);

?>