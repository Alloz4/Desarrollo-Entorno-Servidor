<?php

include_once 'Producto.php';

class AccesoDatos
{

    private static $modelo;
    private $dbh;
    private $stmt1;
    private $stmt2;


    public static function getModelo()
    {
        if (self::$modelo == null) {
            self::$modelo = new AccesoDatos();
        }
        return self::$modelo;
    }

    private function __construct()
    {

        try {
            $this->dbh = new PDO('mysql:host=localhost;dbname=empresa;charset=utf8', 'root', 'root');
            $this->dbh->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
        } catch (PDOException $e) {
            print "Error!: " . $e->getMessage();
            die();
        }

        //Creo los objetos PDOStatement para las consultas

        $this->stmt1 = $this->dbh->prepare("select * from PRODUCTOS where PRODUCTO_NO NOT IN " . "(SELECT PRODUCTO_NO FROM PEDIDOS) ");
        $this->stmt2 = $this->dbh->prepare("UPDATE PRODUCTOS SET PRECIO_ACTUAL=PRECIO_ACTUAL*0.9 where PRODUCTO_NO = :producto_no");
    }

    // Devuelvo la lista de Productos 
    public function obtenerListaProductos(): array
    {
        $tobjproductos = [];

        // Devuelvo una tabla de objetos 
        $this->stmt1->setFetchMode(PDO::FETCH_CLASS, 'Producto');
        if ($this->stmt1->execute()) {
            while ($objproducto = $this->stmt1->fetch()) {
                $tobjproductos[] = $objproducto;
            }
            return $tobjproductos;
        }
    }

    public function actualizarPrecios($lista): int
    {
        $cont = 0;
        // Devuelvo una tabla de objetos
        foreach ($lista as $producto_no) {
            $this->stmt2->bindValue(':producto_no', $producto_no);
            if ($this->stmt2->execute()) {
                $cont++;
            }
        }
        return $cont;
    }


    function close()
    {
        if (self::$modelo != null) {
            $this->dbh = null;
            $this->stmt1 = null;
        }
    }
}
