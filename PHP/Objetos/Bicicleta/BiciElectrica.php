<?php

class BiciElectrica
{
    public $id; // Identificador de la bicicleta (entero)
    public $coordx; // Coordenada X (entero)
    public $coordy; // Coordenada Y (entero)
    public $bateria; // Carga de la batería en tanto por ciento (entero)
    public $operativa; // Estado de la bicleta ( true operativa- false no disponible)


    // Constructor
    function __construct($id, $coordx, $coordy, $bateria, $operativa)
    {
        $this->id = $id;
        $this->coordx = $coordx;
        $this->coordy = $coordy;
        $this->bateria = $bateria;
        $this->operativa = $operativa;
    }

    //Función __get para acceder a los atributos de la clase
    function __get($propiedad)
    {
        if (property_exists($this, $propiedad)) {
            return $this->$propiedad;
        }
    }

    //Función __set para modificar los atributos de la clase
    function __set($propiedad, $valor)
    {
        if (property_exists($this, $propiedad)) {
            $this->$propiedad = $valor;
        }
    }

    //Función para mostrar los datos de la bicicleta
    function __toString()
    {
        return "BiciElectrica: " . $this->id . " Batería: " . $this->bateria . "% ";
    }

    //Función para sacar la distancia entre dos puntos
    function distancia($x, $y)
    {
        return sqrt(pow($this->coordx - $x, 2) + pow($this->coordy - $y, 2));
    }
}
