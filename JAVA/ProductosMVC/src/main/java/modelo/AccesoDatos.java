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
	private PreparedStatement stmt_productos = null;
	private PreparedStatement stmt_producto = null;
	private PreparedStatement stmt_borproducto = null;
	private PreparedStatement stmt_modproducto = null;
	private PreparedStatement stmt_creaproducto = null;

	public static synchronized AccesoDatos initModelo() {
		if (modelo == null) {
			modelo = new AccesoDatos();
		}
		return modelo;
	}

	// Creo un lista de alimentos, podría obtenerse de una base de datos
	private AccesoDatos() {
		try {

			Class.forName("com.mysql.jdbc.Driver");

			conexion = DriverManager.getConnection("jdbc:mysql://localhost/empresa", "root", "root");

			this.stmt_productos = conexion.prepareStatement("select * from productos");
			this.stmt_producto = conexion.prepareStatement("select * from productos where producto_no = ?");
			this.stmt_borproducto = conexion.prepareStatement("delete from productos where producto_no = ?");
			this.stmt_modproducto = conexion.prepareStatement(
					"update productos set descripcion=?, precio_actual=?, stock_disponible=? where producto_no=?");
			this.stmt_creaproducto = conexion.prepareStatement(
					"insert into productos (producto_no,descripcion,precio_actual,stock_disponible) Values(?,?,?,?)");

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

	// Devuelvo la lista de Productos
	public ArrayList<Producto> getProductos() {
		ArrayList<Producto> tproductos = new ArrayList<Producto>();

		ResultSet rs;
		try {
			rs = this.stmt_productos.executeQuery();
			while (rs.next()) {
				Producto productos = new Producto();
				productos.setProducto_no(rs.getInt("producto_no"));
				productos.setDescripcion(rs.getString("descripcion"));
				productos.setPrecio_actual(rs.getFloat("precio_actual"));
				productos.setStock_disponible(rs.getInt("stock_disponible"));
				tproductos.add(productos);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(0);
			
			
			
		}

		return tproductos;
	}

	// Obtengo un producto
	public Producto getProducto(int num) {
		Producto producto = null;

		ResultSet rs;
		try {
			this.stmt_producto.setInt(1, num);
			rs = this.stmt_producto.executeQuery();
			if (rs.next()) {
				producto = new Producto();
				producto.setProducto_no(rs.getInt("producto_no"));
				producto.setDescripcion(rs.getString("descripcion"));
				producto.setPrecio_actual(rs.getFloat("precio_actual"));
				producto.setStock_disponible(rs.getInt("stock_disponible"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return producto;
	}

	// UPDATE
	public boolean modProducto(Producto producto) {

		boolean resu = false;
		try {

			stmt_modproducto.setString(1, producto.getDescripcion());
			stmt_modproducto.setFloat(2, producto.getPrecio_actual());
			stmt_modproducto.setInt(3, producto.getStock_disponible());
			stmt_modproducto.setInt(4, producto.getProducto_no());
			resu = stmt_modproducto.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resu;
	}

	// INSERT
	public boolean addProducto(Producto producto) {
		boolean resu = false;
		try {
			stmt_creaproducto.setInt(1, producto.getProducto_no());
			stmt_creaproducto.setString(2, producto.getDescripcion());
			stmt_creaproducto.setFloat(3, producto.getPrecio_actual());
			stmt_creaproducto.setInt(4, producto.getStock_disponible());
			resu = stmt_creaproducto.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resu;
	}

	// DELETE Elimino un usuario
	public boolean borrarProducto(int producto_no) {
		boolean resu = false;

		try {
			this.stmt_borproducto.setInt(1, producto_no);
			resu = this.stmt_borproducto.execute();
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
