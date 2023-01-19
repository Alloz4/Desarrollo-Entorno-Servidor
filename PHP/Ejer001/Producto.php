<?php

class Producto
{

    private $producto_no;
    private $descripcion;
    private $precio_actual;
    private $stock_disponible;


    public function __set($name, $value)
    {
        $name = strtolower($name);
        if (property_exists($this, $name)) {
            $this->$name = $value;
        }
    }

    public function &__get($name)
    {
        $name = strtolower($name);
        if (property_exists($this, $name)) {
            return $this->$name;
        }
    }
}
