<?php

/*
 * Acceso a datos con BD y Patrón Singleton
 */
class AccesoDatos
{

    private static $modelo = null;
    private $dbh = null;
    private $stmt = null;

    // Auxiliar para genera el formulario
    private static $select0 = " SELECT CLIENTE_NO, NOMBRE FROM CLIENTES";

    //Mostrar productos con precio superior un valor ordenado por descripción.
    private static $select1 = "select * from PRODUCTOS where PRECIO_ACTUAL >= ? ORDER BY DESCRIPCION ";

    // Mostrar Total de pedidos y unidades pedidas por producto 
    private static $select2 = "";
    // Mostrar el departamento con mayor número de empleados.
    private static $select3 = "";
    // Mostrar Código y apellido de TODOS los empleados y ciudad donde trabajan.
    private static $select4 = "";

    // Mostrar productos no pedidos por un cliente determinado
    private static $select5 = "";



    public static function initModelo()
    {
        if (self::$modelo == null) {
            self::$modelo = new AccesoDatos();
        }
        return self::$modelo;
    }

    private function __construct()
    {

        try {
            $dsn = "mysql:host=localhost;dbname=Empresa;charset=utf8";
            $this->dbh = new PDO($dsn, "root", "root");
            $this->dbh->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
        } catch (PDOException $e) {
            echo "Error de conexión " . $e->getMessage();
            exit();
        }
    }



    public function consulta0(): array
    {
        $resu = [];
        $stmt = $this->dbh->prepare(self::$select0);
        // Devuelvo una tabla asociativa
        $stmt->setFetchMode(PDO::FETCH_ASSOC);
        if ($stmt->execute()) {
            while ($fila = $stmt->fetch()) {
                $resu[] = $fila;
            }
        }
        return $resu;
    }



    public function consulta1(int $precio): array
    {
        $resu = [];
        $stmt = $this->dbh->prepare(self::$select1);
        $stmt->bindValue(1, $precio);
        // Devuelvo una tabla asociativa
        $stmt->setFetchMode(PDO::FETCH_ASSOC);
        if ($stmt->execute()) {
            while ($fila = $stmt->fetch()) {
                $resu[] = $fila;
            }
        }
        return $resu;
    }

    public function consulta2(): array
    {
        $resu = [];
        return resu;
    }

    public function consulta3(): array
    {
        $resu = [];
        return resu;
    }
    public function consulta4(): array
    {
        $resu = [];
        return resu;
    }
    public function consulta5(int $cliente_num): array
    {
        $resu = [];
        return resu;
    }

    // Evito que se pueda clonar el objeto.
    public function __clone()
    {
        trigger_error('La clonación no permitida', E_USER_ERROR);
    }
}
