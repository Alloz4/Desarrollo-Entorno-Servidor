<?php
include_once "cliente.php";
include_once "config.php";

/*
 * Acceso a datos con BD Usuarios : 
 * Usando la librería mysqli
 * Uso el Patrón Singleton :Un único objeto para la clase
 * Constructor privado, y métodos estáticos 
 */
class AccesoDatos
{

    private static $modelo = null;
    private $dbh = null;
    private $stmt_numclientes = null;
    private $stmt_clientes = null;
    private $stmt_cliente = null;
    private $stmt_modificar = null;
    private $stmt_añadir = null;
    private $stmt_borrar = null;

    public static function getModelo()
    {
        if (self::$modelo == null) {
            self::$modelo = new AccesoDatos();
        }
        return self::$modelo;
    }



    // Constructor privado  Patron singleton

    private function __construct()
    {
        try {
            $dsn = "mysql:host=" . DB_SERVER . ";dbname=" . DATABASE . ";charset=utf8";
            $this->dbh = new PDO($dsn, DB_USER, DB_PASSWD);
            $this->dbh->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
        } catch (PDOException $e) {
            echo "Error de conexión " . $e->getMessage();
            exit();
        }
        //Creo los objetos PDOStatement para las consultas
        $this->stmt_numclientes = $this->dbh->prepare("SELECT id FROM Clientes");
        $this->stmt_clientes = $this->dbh->prepare("SELECT * FROM Clientes limit :primero,:cuantos");
        $this->stmt_cliente = $this->dbh->prepare("SELECT * FROM Clientes where id=:id");
        $this->stmt_modificar = $this->dbh->prepare("UPDATE Clientes set first_name=:first_name, email=:email,gender=:gender, ip_address=:ip_address, telefono=:telefono where id=:id");
        $this->stmt_añadir = $this->dbh->prepare("INSERT INTO Clientes (id,first_name,email,gender,ip_address,telefono) VALUES (:id,:first_name,:email,:gender,:ip_address,:telefono)");
        $this->stmt_borrar = $this->dbh->prepare("DELETE FROM Clientes where id=:id");
    }

    // Cierro la conexión anulando todos los objectos relacioanado con la conexión PDO (stmt)
    public static function closeModelo()
    {
        if (self::$modelo != null) {
            $obj = self::$modelo;
            $obj->stmt_numclientes->null;
            $obj->stmt_clientes->null;
            $obj->stmt_cliente->null;
            $obj->stmt_modificar->null;
            $obj->stmt_añadir->null;
            $obj->stmt_borrar->null;
            $obj->dbh->null;
            self::$modelo = null; // Borro el objeto.
        }
    }

    public function numClientes(): int
    {
        if ($this->stmt_numclientes->execute()) {
            $num = $this->stmt_numclientes->rowCount();
        }
        return $num;
    }

    // SELECT Devuelvo la lista de Usuarios
    public function getClientes($primero, $cuantos): array
    {
        $tuser = [];
        $this->stmt_clientes->bindParam(':primero', $primero, PDO::PARAM_INT);
        $this->stmt_clientes->bindParam(':cuantos', $cuantos, PDO::PARAM_INT);
        $this->stmt_clientes->setFetchMode(PDO::FETCH_CLASS, 'cliente');
        if ($this->stmt_clientes->execute()) {
            while ($user = $this->stmt_clientes->fetch()) {
                $tuser[] = $user;
            }
        }
        return $tuser;
    }

    // SELECT Devuelvo un usuario o false
    public function getCliente(int $login)
    {
        $user = false;

        $this->stmt_cliente->setFetchMode(PDO::FETCH_CLASS, 'cliente');
        $this->stmt_cliente->bindParam(':id', $login, PDO::PARAM_INT);
        if ($this->stmt_cliente->execute()) {
            $user = $this->stmt_cliente->fetch();
        }
        return $user;
    }

    // UPDATE
    public function modUsuario($user): bool
    {

        $this->stmt_modificar->bindParam(':id', $user->id, PDO::PARAM_INT);
        $this->stmt_modificar->bindParam(':first_name', $user->first_name, PDO::PARAM_STR);
        $this->stmt_modificar->bindParam(':email', $user->email, PDO::PARAM_STR);
        $this->stmt_modificar->bindParam(':gender', $user->gender, PDO::PARAM_STR);
        $this->stmt_modificar->bindParam(':ip_address', $user->ip_address, PDO::PARAM_STR);
        $this->stmt_modificar->bindParam(':telefono', $user->telefono, PDO::PARAM_STR);
        $this->stmt_modificar->execute();
        $resu = ($this->stmt_modificar->rowCount() == 1);
        return $resu;
    }

    //INSERT
    public function addUsuario($user): bool
    {
        $this->stmt_añadir->bindParam(':id', $user->id, PDO::PARAM_INT);
        $this->stmt_añadir->bindParam(':first_name', $user->first_name, PDO::PARAM_STR);
        $this->stmt_añadir->bindParam(':email', $user->email, PDO::PARAM_STR);
        $this->stmt_añadir->bindParam(':gender', $user->gender, PDO::PARAM_STR);
        $this->stmt_añadir->bindParam(':ip_address', $user->ip_address, PDO::PARAM_STR);
        $this->stmt_añadir->bindParam(':telefono', $user->telefono, PDO::PARAM_STR);
        $this->stmt_añadir->execute();
        $resu = ($this->stmt_añadir->rowCount() == 1);
        return $resu;
    }

    //DELETE
    public function borrarUsuario(int $login): bool
    {
        $this->stmt_borrar->bindParam(':id', $login, PDO::PARAM_INT);
        $this->stmt_borrar->execute();
        $resu = ($this->stmt_borrar->rowCount() == 1);
        return $resu;
    }

    // Evito que se pueda clonar el objeto. (SINGLETON)
    public function __clone()
    {
        trigger_error('La clonación no permitida', E_USER_ERROR);
    }
}
