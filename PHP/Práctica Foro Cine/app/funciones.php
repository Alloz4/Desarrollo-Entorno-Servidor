<?php
function usuarioOk($usuario, $contraseña): bool
{
   if (strlen($usuario) >= 8 && $contraseña == strrev($usuario)) {
      return true;
   } else {
      return false;
   }
}

function checkInyeccionHtml($cadena)
{
   $cadena = trim(htmlspecialchars($cadena));

   return $cadena;
}
