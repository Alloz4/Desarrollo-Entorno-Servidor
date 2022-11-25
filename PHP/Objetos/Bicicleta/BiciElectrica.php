<?php

class BiciElectrica
{
    public $id; // Identificador de la bicicleta (entero)
    public $coordx; // Coordenada X (entero)
    public $coordy; // Coordenada Y (entero)
    public $bateria; // Carga de la batería en tanto por ciento (entero)
    public $operativa; // Estado de la bicleta ( true operativa- false no disponible)

    function ___get($propiedad)
    {
        if (property_exists($this, $propiedad)) {
            return $this->$propiedad;
        }
    }

    function ___set($propiedad, $valor)
    {
        if (property_exists($this, $propiedad)) {
            $this->$propiedad = $valor;
        }
    }

    function __toString()
    {
        return "BiciElectrica: " . $this->id . " Batería: " . $this->bateria . "% ";
    }

    function distancia($x, $y)
    {
        return sqrt(pow($this->coordx - $x, 2) + pow($this->coordy - $y, 2));
    }
}
