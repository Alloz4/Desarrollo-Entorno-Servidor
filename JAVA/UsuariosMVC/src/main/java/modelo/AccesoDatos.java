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
	private static Connection conexion = null;
	private PreparedStatement stmt_usuarios = null;
    private PreparedStatement stmt_usuario  = null;
    private PreparedStatement stmt_boruser  = null;
    private PreparedStatement stmt_moduser  = null;
    private PreparedStatement stmt_creauser = null;
	
	public static synchronized  AccesoDatos initModelo(){
		if (modelo == null){
			modelo = new AccesoDatos();
		}
		return modelo;
	}
	
	// Creo un lista de alimentos, podría obtenerse de una base de datos
	private AccesoDatos (){
		try {
			
			Class.forName("com.mysql.jdbc.Driver");

			conexion = DriverManager.getConnection(
					"jdbc:mysql://localhost/Usuarios", "root", "root");

			
			 this.stmt_usuarios  = conexion.prepareStatement("select * from Usuarios");
		     this.stmt_usuario   = conexion.prepareStatement("select * from Usuarios where login=?");
		     this.stmt_boruser   = conexion.prepareStatement("delete from Usuarios where login =?");
		     this.stmt_moduser   = conexion.prepareStatement("update Usuarios set nombre=?, password=?, comentario=? where login=?");
		     this.stmt_creauser  = conexion.prepareStatement("insert into Usuarios (login,nombre,password,comentario) Values(?,?,?,?)");
			

		} catch (Exception ex) {
			System.err.println(" Error - En la base de datos.");
			ex.printStackTrace();
		}
	}
	
	
	 public static void closeModelo(){
	        if (modelo != null){
	           try {
				conexion.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        }
	    }

    // Devuelvo la lista de Usuarios
    public ArrayList<Usuario> getUsuarios () {
        ArrayList <Usuario> tuser = new ArrayList<Usuario>();
        
        ResultSet rs; 
        try {
			rs =  this.stmt_usuarios.executeQuery();
			  while ( rs.next()) {
		        	Usuario usr = new Usuario();
		        	usr.setLogin(rs.getString("login"));
		        	usr.setNombre(rs.getString("Nombre"));
		        	usr.setPassword(rs.getString("password"));
                    usr.setComentario(rs.getString("Comentario"));
                    tuser.add(usr);
			  }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
      
    
        return tuser;
    }
	
    // Obtengo un usuario
    public Usuario getUsuario(String id) {
    	Usuario usr = null;
    	
        ResultSet rs; 
        try {
        	this.stmt_usuario.setString(1, id);
			rs =  this.stmt_usuario.executeQuery();
			  if  ( rs.next()) {
		        	usr = new Usuario();
		        	usr.setLogin(rs.getString("login"));
		        	usr.setNombre(rs.getString("nombre"));
		        	usr.setPassword(rs.getString("password"));
                    usr.setComentario(rs.getString("Comentario"));
			  }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
          	return usr;
    }
	
    // UPDATE
    public boolean modUsuario(Usuario user){
      
    	boolean resu = false;
        try {
		
			stmt_moduser.setString(1,user.getNombre());
	        stmt_moduser.setString(2,user.getPassword());
	        stmt_moduser.setString(3,user.getComentario());
	    	stmt_moduser.setString(4,user.getLogin());
	        resu = stmt_moduser.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}       
        return resu;
    }
    
    //INSERT
    public boolean addUsuario(Usuario user){
        boolean resu = false;
    	try {
    	stmt_creauser.setString(1,user.getLogin());
		stmt_creauser.setString(2,user.getNombre());
        stmt_creauser.setString(3,user.getPassword());
        stmt_creauser.setString(4,user.getComentario());
        resu = stmt_creauser.execute();
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
        return resu;
    }
    
    
    // DELETE Elimino un usuario
    public boolean borrarUsuario(String login)  {
        boolean resu = false;
    	
        try {
        	this.stmt_boruser.setString (1, login);
			resu = this.stmt_boruser.execute();
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

