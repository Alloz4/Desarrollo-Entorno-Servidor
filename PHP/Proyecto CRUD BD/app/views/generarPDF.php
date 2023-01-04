<?php

$id = $cli->id;
$first_name = $cli->first_name;
$last_name = $cli->last_name;
$email = $cli->email;
$gender = $cli->gender;
$ip_address = $cli->ip_address;
$telefono = $cli->telefono;


require_once 'vendor/autoload.php';

$mpdf = new \Mpdf\Mpdf();

$html  = '<h1>Cliente</h1>
    <table border="1" cellpadding="10">
        <tr>
            <th>ID</th>
            <th>First_nombre</th>
            <th>Last_name</th>
            <th>Email</th>
            <th>Gender</th>
            <th>IP_address</th>
            <th>Teléfono</th>
        </tr>';

$html .= '<tr>
            <td>' . $id . '</td>
            <td>' . $first_name . '</td>
            <td>' . $last_name . '</td>
            <td>' . $email . '</td>
            <td>' . $gender . '</td>
            <td>' . $ip_address . '</td>
            <td>' . $telefono . '</td>
        </tr>
    </table>';



$mpdf->WriteHTML($html);
$mpdf->Output($first_name . '.pdf', 'D');
