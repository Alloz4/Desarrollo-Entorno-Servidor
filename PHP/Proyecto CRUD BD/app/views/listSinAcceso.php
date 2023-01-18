<form>
    <button type="submit" name="orden" value="Nuevo"> Cliente Nuevo </button><br>
</form>
<br>

<table>
    <tr>
        <th><a href="?orden=Ordenar&valor=id">ID</a><?= mostrarflecha("id") ?></th>
        <th><a href="?orden=Ordenar&valor=first_name">First_name</a><?= mostrarflecha("first_name") ?></th>
        <th><a href="?orden=Ordenar&valor=email">Email</a><?= mostrarflecha("email") ?></th>
        <th><a href="?orden=Ordenar&valor=gender">Gender</a><?= mostrarflecha("gender") ?></th>
        <th><a href="?orden=Ordenar&valor=ip_address">IP_address</a><?= mostrarflecha("ip_address") ?></th>
        <th><a href="?orden=Ordenar&valor=telefono">Teléfono</a><?= mostrarflecha("telefono") ?></th>
    </tr>
    <?php foreach ($tvalores as $valor) : ?>
        <tr>
            <td><?= $valor->id ?> </td>
            <td><?= $valor->first_name ?> </td>
            <td><?= $valor->email ?> </td>
            <td><?= $valor->gender ?> </td>
            <td><?= $valor->ip_address ?> </td>
            <td><?= $valor->telefono ?> </td>
            <td><a href="?orden=Detalles&id=<?= $valor->id ?>">Detalles</a></td>

        <tr>
        <?php endforeach ?>
</table>

<form>
    <br>
    <button type="submit" name="nav" value="Primero">
        << </button>
            <button type="submit" name="nav" value="Anterior">
                < </button>
                    <button type="submit" name="nav" value="Siguiente"> > </button>
                    <button type="submit" name="nav" value="Ultimo"> >> </button>
</form>