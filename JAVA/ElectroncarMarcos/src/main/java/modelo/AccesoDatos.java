package modelo;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

//Implemento el modelo con  Patrón Singleton es casi equivalente a crear a conexión
//en el método init de Servlet

public class AccesoDatos {

    private static AccesoDatos modelo;
    private static Connection conexion = null;

    private PreparedStatement stmt_vehiculos = null;
    private PreparedStatement stmt_clientes = null;
    private PreparedStatement stmt_updateCliente = null;
    private PreparedStatement stmt_updateVehiculo = null;
    public static synchronized AccesoDatos initModelo() {
        if (modelo == null) {
            modelo = new AccesoDatos();
        }
        return modelo;
    }

    private AccesoDatos() {
        try {

            Class.forName("com.mysql.jdbc.Driver");

            conexion = DriverManager.getConnection("jdbc:mysql://localhost/mycardb", "root", "root");

            this.stmt_clientes = conexion.prepareStatement("select * from clientes where cod_cli = ? AND clave = ?");
            this.stmt_vehiculos = conexion.prepareStatement("SELECT * from vehiculos WHERE localidad = ?  AND bateria > 0 and estado = 0 ORDER BY bateria DESC LIMIT 1");
            this.stmt_updateCliente = conexion.prepareStatement("UPDATE clientes SET cod_car = ? WHERE cod_cli = ?");
            this.stmt_updateVehiculo = conexion.prepareStatement("UPDATE vehiculos SET estado = 1 WHERE cod_car = ?");

        } catch (Exception ex) {
            System.err.println(" Error - En la base de datos.");
            ex.printStackTrace();
        }
    }

    public static void closeModelo() {
        if (modelo != null) {
            try {
                conexion.close();
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    //Método para obtener el cliente
    public Cliente getCliente(String codigo, String clave) {
        try {
            this.stmt_clientes.setString(1, codigo);
            this.stmt_clientes.setString(2, clave);
            ResultSet rs = this.stmt_clientes.executeQuery();
            if (rs.next()) {
                return new Cliente(rs.getString("cod_cli"), rs.getString("nombre"), rs.getString("clave"), rs.getInt("cod_car"));
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    //Método para obtener el vehículo
    public Vehiculo getVehiculo(String localidad) {
        try {
            this.stmt_vehiculos.setString(1, localidad);
            ResultSet rs = this.stmt_vehiculos.executeQuery();
            if (rs.next()) {
                return new Vehiculo(rs.getInt("cod_car"), rs.getString("localidad"), rs.getInt("estado"), rs.getInt("bateria"));
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    //Método para actualizar el estado del vehículo y el cliente
    public void alquilerVehiculo (Vehiculo vehiculo, Cliente cliente) {

        try {
            this.stmt_updateVehiculo.setInt(1, vehiculo.getCod_car());
            this.stmt_updateVehiculo.execute();
            this.stmt_updateCliente.setInt(1,vehiculo.getCod_car());
            this.stmt_updateCliente.setString(2,cliente.getCod_cli());
            stmt_updateCliente.execute();
        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    public AccesoDatos clone() {
        try {
            throw new CloneNotSupportedException();
        } catch (CloneNotSupportedException ex) {
            ex.printStackTrace();
        }
        return null;
    }

}
