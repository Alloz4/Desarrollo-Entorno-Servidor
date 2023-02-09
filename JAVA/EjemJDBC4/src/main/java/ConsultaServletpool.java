/*
 * EJEMPLO DE ACCESO A CONCURRENTE A UNA MISMA CONEXIÓN
 * Más eficiente pero el acceso debe ser sincronizado
 * Se establece la conexión con el método init usando todos
 * los hilos la misma conexión
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.naming.*;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;


/**
 * Servlet implementation class ConsultaServlet2
 */
@WebServlet("/consultapool")
public class ConsultaServletpool extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private DataSource pool;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ConsultaServletpool() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		try {
			// Crea un contexto para poder luego buscar el recurso DataSource
			InitialContext ctx = new InitialContext();
			// Busca el recurso DataSource en el contexto
			pool = (DataSource) ctx.lookup("java:comp/env/jdbc/mysql_tiendalibros");
			if (pool == null)
				throw new ServletException( "DataSource desconocida ’mysql_tiendalibros’");
		} catch (NamingException ex) {
			System.err.println(" Error - Conexion con el POOL base de datos.");
			ex.printStackTrace();
		}
	}

	@Override
	public void destroy() {
		
		
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		Statement statment = null;
		Connection conexion = null;
		
		
		// Establecemos el tipo MIME del mensaje de respuesta
		response.setContentType("text/html");
		// Creamos un objeto para poder escribir la respuesta
		PrintWriter out = response.getWriter();

		String sqlStr = "select * from libros where autor = " + "'"
				+ request.getParameter("autor") + "'";
		// Generar una p ́gina HTML como resultado de la consulta

		
		out.println("<html><head><title>Resultado de la consulta</title></head><body>");
		out.println("<h3>Gracias por tu consulta.</h3>");
		out.println("<p>Tu consulta (4) es: " + sqlStr + "</p>");
		ResultSet rset = null;
		
		try {
			conexion = pool.getConnection(); // Obtiene la conexión del pool
			statment = conexion.createStatement();
			rset = statment.executeQuery(sqlStr);
			
			// Paso 5: Procesar el conjunto de registros resultante utilizando
			// ResultSet
			int count = 0;
			while (rset.next()) {
				out.println("<p>" + rset.getString("autor") + ", "
						+ rset.getString("titulo") + ", "
						+ rset.getDouble("precio") + "</p>");
				count++;
				out.println("<p>==== " + count
						+ " registros encontrados =====</p>");
				out.println("</body></html>");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
