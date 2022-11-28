<?php


class Coche
{

    private $modelo;
    private $distanciaTotal;
    private $distanciaParcial;
    private $motor;
    private $velocidad;
    private $velocidadMax;


    function __construct($modelo, $velocidadMax)
    {
        $this->modelo = $modelo;
        $this->distanciaTotal = 0;
        $this->distanciaParcial = 0;
        $this->motor = false;
        $this->velocidad = 0;
        $this->velocidadMax = $velocidadMax;
    }



    function __get($name)
    {
        if (property_exists($this, $name)) {
            return $this->$name;
        }
    }

    function __set($name, $value)
    {
        if (property_exists($this, $name)) {
            return $this->$name = $value;
        }
    }

    private function error($mensaje)
    {
        return $mensaje . "<br>";
    }

    function arrancar(): bool
    {
        if ($this->motor) {
            $this->error("Motor arrancado");
            return false;
        } else {
            $this->motor = true;
            return true;
        }
    }

    function parar(): bool
    {
        if (!$this->motor) {
            $this->error("Motor ya parado");
            return false;
        } else {
            $this->motor = false;
            $this->velocidad = 0;
            $this->distanciaParcial = 0;
        }
    }

    function acelera($cantidad)
    {
        if ($this->motor) {
            $this->velocidad = $this->velocidad + $cantidad;
            // Control de la velocidad Máxima
            if ($this->velocidad > $this->velocidadMax) {
                $this->velocidad = $this->velocidadMax;
            }
            return true;
        } else {
            $this->error(" Motor parado. No se puede acelerar");
            return false;
        }
    }

    function frena($cantidad)
    {
        if (!$this->motor) {
            $this->error("No se puede frenar ya que el motor está parado");
            return false;
        } else {
            $this->velocidad -= $cantidad;
            if ($this->velocidad < 0) {
                $this->velocidad = 0;
            }

            return true;
        }
    }


    function recorre()
    {
        if ($this->motor) {
            $this->distanciaParcial += $this->velocidad;
            $this->distanciaTotal += $this->velocidad;
            return true;
        } else {
            $this->error("Motor parado, no se puede avanzar");
            return false;
        }
    }

    function info(): string
    {

        return "Modelo: " . $this->modelo . "<br>" . "Velocidad actual: " . $this->velocidad . "<br>" . "Estado: " . ($this->motor ? "encendido" : "apagado") . "<br>" . "Km parciales: " . $this->distanciaParcial . "km" . "<br>" . "Km totales: " . $this->distanciaTotal . "km" . "<br>";
    }

    function getKilometros()
    {
        return $this->distanciaParcial;
    }
}
