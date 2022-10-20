<!-- Preguntar por que la longitud y el num de palabras es mayor al evitar la inyeccion de codigo html -->
<div>
    <b> Detalles:</b><br>
    <table>
        <tr>
            <td>Longitud: </td>
            <td><?= strlen(checkInyeccionHtml($_REQUEST['comentario'])) ?></td>
        </tr>
        <tr>
            <td>Nº de palabras: </td>
            <td><?= numPalabras(checkInyeccionHtml($_REQUEST['comentario'])) ?></td>
        </tr>
        <tr>
            <td>Letra + repetida: </td>
            <td><?= (!empty($_REQUEST['comentario'])) ? letraRepetida(checkInyeccionHtml($_REQUEST['comentario'])) : '' ?></td>
        </tr>
        <tr>
            <td>Palabra + repetida:</td>
            <td><?= (!empty($_REQUEST['comentario'])) ? palabraRepetida(checkInyeccionHtml($_REQUEST['comentario'])) : '' ?></td>
        </tr>
    </table>
</div>