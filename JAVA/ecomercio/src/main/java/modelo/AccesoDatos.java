package modelo;

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
	private Connection conexion = null;
	private PreparedStatement sentenciapreparada1 = null;
	private PreparedStatement sentenciapreparada2 = null;
	private PreparedStatement sentenciapreparada3 = null;

	public static synchronized AccesoDatos initModelo() {
		if (modelo == null) {
			modelo = new AccesoDatos();
		}
		return modelo;
	}

	// Creo un lista de alimentos, podría obtenerse de una base de datos
	private AccesoDatos() {
		try {
			// Class.forName("org.apache.derby.jdbc.ClientDriver");
			Class.forName("com.mysql.jdbc.Driver");

			conexion = DriverManager.getConnection("jdbc:mysql://192.168.5.199/etienda", "administrador", "master");

			sentenciapreparada1 = conexion.prepareStatement("select * from clientes where nombre = ? and clave = ?");
			sentenciapreparada2 = conexion.prepareStatement("select * from pedidos where cod_cliente = ?");
			sentenciapreparada3 = conexion.prepareStatement("update clientes SET veces=veces+1 where nombre = ?");

		} catch (Exception ex) {
			System.err.println(" Error - En la base de datos.");
			ex.printStackTrace();
		}
	}

	// Conprueba si el usuario es correcto
	public Cliente ckequearUsuario(String usuario, String clave) {

		Cliente resu = null;
		ResultSet rs;
		try {
			// Acceso sincronizado la sentencia y la conexión se comparten
			synchronized (sentenciapreparada1) {
				sentenciapreparada1.setString(1, usuario);
				sentenciapreparada1.setString(2, clave);
				rs = sentenciapreparada1.executeQuery();
				// Si es correcto existe
				if (rs.next()) {
					resu = new Cliente();
					resu.setCod_cliente(rs.getInt("cod_cliente"));
					resu.setVeces(rs.getInt("veces"));
					resu.setNombre(rs.getString("nombre"));
					
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resu;
	}

	// Devuelvo la lista de libros
	public ArrayList<Pedido> obtenerListaPedidos(int cod_cliente) {
		ArrayList<Pedido> listaresu = new ArrayList<Pedido>();
		ResultSet rs;
		try {
			// Acceso sincronizado la sentencia y la conexión se comparten
			synchronized (sentenciapreparada2) {
				sentenciapreparada2.setInt(1, cod_cliente);
				rs = sentenciapreparada2.executeQuery();

				// Vuelco el resultado de ResultSet al ArrayList
				while (rs.next()) {
					Pedido nueva = new Pedido();
					// En este ejercicio no es necesario rellenar todos los atributos
					nueva.setCod_cliente(rs.getInt("cod_cliente"));
					nueva.setProducto(rs.getString("producto"));
					nueva.setPrecio(rs.getInt("precio"));
					listaresu.add(nueva);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listaresu;
	}

	public int IncrementarVeces(String nombre) {

		int resu =0;
		ResultSet rs;
		try {
			// Acceso sincronizado la sentencia y la conexión se comparten
			synchronized (sentenciapreparada3) {
			  // Devuelve el número de filas modificada en este caso debe ser 1
				sentenciapreparada3.setString(1,nombre);
			  resu =	sentenciapreparada3.executeUpdate();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resu;
	}
		
	// Evito que se pueda clonar el objeto.
	@Override
	public AccesoDatos clone() {
		try {
			throw new CloneNotSupportedException();
		} catch (CloneNotSupportedException ex) {
			ex.printStackTrace();
		}
		return null;
	}
}