package miscompras.profesor.modelo;

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
	private PreparedStatement consultasocios = null;
	private PreparedStatement consultaproductos = null;
	private PreparedStatement updatesocios = null;
	
	public static synchronized  AccesoDatos initModelo(){
		if (modelo == null){
			modelo = new AccesoDatos();
		}
		return modelo;
	}
	
	// Creo un lista de alimentos, podría obtenerse de una base de datos
	private AccesoDatos (){
		try {
			
			//Class.forName("com.mysql.jdbc.Driver");
			Class.forName("com.mysql.cj.jdbc.Driver");

			conexion = DriverManager.getConnection(
					"jdbc:mysql://localhost/comprasdb", "root", "root");

		   consultasocios = conexion
					.prepareStatement("select * from socios where socios.cod_socio = ? and socios.clave = ?" );
			
	    	consultaproductos = conexion
					.prepareStatement("select * from productos where cod_pro = ?");
	    	updatesocios = conexion
	    			.prepareStatement("update socios set cantidad_max=cantidad_max-? where  cod_socio = ?");
			

		} catch (Exception ex) {
			System.err.println(" Error - En la base de datos.");
			ex.printStackTrace();
		}
	}
	
	
	// Devuelvo la lista de Clientes
		public Socio obtenerSocio (String codigo, String clave){		
			Socio resu = null;
			
			ResultSet rs;
			try {
				 // Acceso sincronizado la sentencia y la conexión se comparten
			     synchronized (consultasocios) {
			    	consultasocios.setString(1, codigo);
			    	consultasocios.setString(2, clave);
					rs = consultasocios.executeQuery();
				
			   if ( rs.next()) {
				    resu = new Socio();
				    // En este ejercicio no es necesario rellenar todos los atributos
				    resu.setCod_socio   ( rs.getString("cod_socio"));
				    resu.setNombre	    ( rs.getString("nombre"));
				    resu.setCantidad_max( rs.getInt   ("cantidad_max"));
				    resu.setClave       ( rs.getString("clave"));
			   }
			  } 
			} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
			}
		return resu;	
		}
	
		public Producto obtenerProducto (String cod_pro){		
			Producto resu = null;
			
			ResultSet rs;
			try {
				 // Acceso sincronizado la sentencia y la conexión se comparten
			     synchronized (consultaproductos) {
			    	consultaproductos.setString(1, cod_pro);
			    	rs = consultaproductos.executeQuery();
			     }
				
			   if ( rs.next()) {
				    resu = new Producto();
				    // En este ejercicio no es necesario rellenar todos los atributos
				    resu.setCod_pro   ( rs.getString("cod_pro"));
				    resu.setNombre_pro( rs.getString("nombre_pro"));
				    resu.setPrecio    ( rs.getInt   ("precio"));
			  } 
			} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
			}
		return resu;	
		}
	
		public boolean actualizarSocio ( String cod_socio, int importe) {
	        boolean resu = false;
			try {
				 // Acceso sincronizado la sentencia y la conexión se comparten
			     synchronized (updatesocios) {
			    	updatesocios.setInt(1, importe);
			    	updatesocios.setString(2, cod_socio);
			    	if ( updatesocios.executeUpdate() == 1) {
			    		resu = true;
			    	}
			     } 
			} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
			}
		return resu;
		}
			
	
	// Evito que se pueda clonar el objeto.
 @Override
 public AccesoDatos clone(){
         try {
             throw new CloneNotSupportedException();
         } catch (CloneNotSupportedException ex) {
         	ex.printStackTrace();
         }
         return null; 
     }    
}

