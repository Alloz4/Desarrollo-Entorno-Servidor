<!-- Preguntar por que la longitud y el num de palabras es mayor al evitar la inyeccion de codigo html -->
<div>
    <b> Detalles:</b><br>
    <table>
        <tr>
            <td>Longitud: </td>
            <td><?= strlen(($_REQUEST['comentario'])) ?></td>
        </tr>
        <tr>
            <td>Nº de palabras: </td>
            <td><?= str_word_count(checkInyeccionHtml($_REQUEST['comentario'])) ?></td>
        </tr>
        <tr>
            <td>Letra + repetida: </td>
            <td>a</td>
        </tr>
        <tr>
            <td>Palabra + repetida:</td>
            <td>Hola</td>
        </tr>
    </table>
</div>