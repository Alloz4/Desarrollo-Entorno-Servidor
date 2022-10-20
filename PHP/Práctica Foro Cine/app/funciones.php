<?php

//Funcion que comprueba si el usuario y la contraseña es correcta.
function usuarioOk($usuario, $contraseña): bool
{
   if (strlen($usuario) >= 8 && $contraseña == strrev($usuario)) {
      return true;
   } else {
      return false;
   }
}

//Funcion para evitar la inyeccion de codigo HTML, utilizo trim para eliminar espacios tanto al inicio como al final y la funcion de cadena htmlspecialchars.
function checkInyeccionHtml($cadena)
{
   $cadenaFree = trim(htmlspecialchars($cadena, ENT_QUOTES, "UTF-8"));

   return $cadenaFree;
}

//Funcion que cuenta el numero de palabras, se utiliza la funcion str_word_count
function numPalabras($comentario)
{

   $contadorPalabras = str_word_count($comentario);

   return $contadorPalabras;
}

//Funcion en la que encontramos cual es la letra mas repetida.
function letraMasRepetida($cadena)
{
   //Creo una cadena con todas las letras del abecedario, concateno esa misma variable con las letras del abecedario en mayusculas
   $letras = "abcdefghijklmnopqrstuvwxyz";
   $letras .= strtoupper($letras);
   $letraRepe = 'a';
   $count = 0;
   $max = 0;

   for ($i = 0; $i < strlen($letras); $i++) {
      $letra = $letras[$i];
      //Se almacenan las veces que se encuentran las letras en el texto.
      $count = substr_count($cadena, $letra);

      if ($count > $max) {
         $max = $count;
         $letraRepe = $letra;
      }
   }
   $resultado = "La letra " . "<b>" . $letraRepe . "</b>" . " se repite " . $max . " veces.";
   return $resultado;
}

//Funcion en la que se busca cual es la palabra mas repetida del texto
function palabraMasRepetida($cadena)
{
   //Convertimos la cadena en un array y almacenamos en una variable nueva un array asociativo del recuento de valores
   $arrayTexto = explode(' ', $cadena);
   $palabrasRepes = array_count_values($arrayTexto);
   $max = 0;

   foreach ($palabrasRepes as $key => $value) {
      if ($value > $max) {
         $max = $value;
         $repetida = $key;
      }
   }
   return $repetida;
}
