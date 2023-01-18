<?php
class Cliente
{

    private $cod_cliente;
    private $nombre;
    private $clave;
    private $veces;



    function __set($name, $value)
    {
        if (property_exists($this, $name)) {
            $this->$name = $value;
        }
    }

    function &__get($name)
    {
        if (property_exists($this, $name)) {
            return $this->$name;
        }
    }
}
