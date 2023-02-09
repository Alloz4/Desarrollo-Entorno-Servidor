

/*
 * EJEMPLO BÁSICO DE CONSULTA A UNA BASE DE DATOS 
 * Cada hilo o petición crea su propia conexión y sentencia 
 * 
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * Servlet implementation class ConsultaServlet
 */

@WebServlet(urlPatterns = {"/ConsultaServlet", "/consulta1"})
public class ConsultaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	

	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ConsultaServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// Establecemos el tipo MIME del mensaje de respuesta
		response.setContentType("text/html");
		// Creamos un objeto para poder escribir la respuesta
		PrintWriter out = response.getWriter();
		Connection conn = null;
		Statement stmt = null;
		try {
			//Paso 1: Cargar el driver JDBC.
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			// Paso 2: Conectarse a la Base de Datos utilizando la clase Connection
			String userName="root";
			String password="root";
			//URL de la base de datos(equipo, puerto, base de datos)
			String url="jdbc:mysql://localhost/TiendaLibros";
			conn = DriverManager.getConnection(url, userName, password);
			// Paso 3: Crear sentencias SQL, utilizando objetos de tipo Statement
			stmt = conn.createStatement();

			// Paso 4: Ejecutar las sentencias SQL a trav ́s de los objetos Statement
			String sqlStr = "select * from libros where autor = "
					+ "'" + request.getParameter("autor") + "'";
			// Generar una p ́gina HTML como resultado de la consulta
			
			out.println("<html><head><title>Resultado de la consulta</title></head><body>");
			out.println("<h3>Gracias por tu consulta.</h3>");
			out.println("<p>Tu consulta es: " + sqlStr + "</p>");
			ResultSet rset = stmt.executeQuery(sqlStr);
			// Paso 5: Procesar el conjunto de registros resultante utilizando ResultSet
			int count = 0;
			while(rset.next()) {
				out.println("<p>" + rset.getString("autor")
						+ ", " + rset.getString("titulo")
						+ ", " + rset.getDouble("precio") + "</p>");
				count++;
			}
			out.println("<p>==== " + count + " registros encontrados =====</p>");
			out.println("</body></html>");
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			out.close(); // Cerramos el flujo de escritura
			try {
				// Cerramos el resto de recursos
				if (stmt != null) stmt.close();
				if (conn != null) conn.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
	}


/**
 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
 */
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	// TODO Auto-generated method stub
}

}