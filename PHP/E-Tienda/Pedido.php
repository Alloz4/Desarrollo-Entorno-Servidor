<?php 
class Pedido {

    private $numped;
    private $cod_cliente;
    private $producto;
    private $precio;


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
