package controladores;
import java.util.ArrayList;

import modelo.AccesoDatos;
import modelo.Usuario;

public class Funciones {
   
	
	static String  mostrarDatos (){
	    
		String msg ="";
	    String titulos []= { "Nombre","login","Password","Comentario"};
	    msg += "<table>\n";
	     // Identificador de la tabla
	    msg += "<tr>";
	    for (int j=0; j < titulos.length; j++){
	        msg += "<th>"+titulos[j]+"</th>";
	    }  
	    msg += "</tr>";
	    AccesoDatos db = AccesoDatos.initModelo();
	    ArrayList <Usuario> tuser = db.getUsuarios();
	    for (Usuario user: tuser) {
	        msg += "<tr>";
	        msg += "<td>"+user.getNombre()  +"</td>";
	        msg += "<td>"+user.getLogin()   +"</td>";
	        msg += "<td>"+user.getPassword()+"</td>";
	        msg += "<td>"+user.getComentario()+"</td>";
	        msg +="<td><a href='#' onclick=\"confirmarBorrar('"+user.getNombre()+"','"+user.getLogin()+"');\" >Borrar</a></td>\n";
	        msg +="<td><a href='?orden=Modificar&id="+ user.getLogin() +"'>Modificar</a></td>\n";
	        msg +="<td><a href='?orden=Detalles&id=" + user.getLogin() +"' >Detalles</a></td>\n";
	        msg +="</tr>\n";
	        
	    }
	    msg += "</table>";
	   
	    return msg;    
	}
}
