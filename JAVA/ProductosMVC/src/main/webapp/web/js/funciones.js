/**
 * Funciones auxiliares de javascripts 
 */
function confirmarBorrar(nombre, producto_no){
  if (confirm("¿Quieres eliminar el usuario:  "+nombre+"?"))
  {
   document.location.href="?orden=Borrar&producto_no="+producto_no;
  }
}