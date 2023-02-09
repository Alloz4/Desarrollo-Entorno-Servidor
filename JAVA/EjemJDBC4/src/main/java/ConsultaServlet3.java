/*
 *  EJEMPLO DE CONEXIÓN MÁS SEGURA QUE IMPIDE LA
 *  INYECCIÓN DEL CÓDIGO y REALIZA CONSULTAS MÁS EFICIENTES
 *  Se utiliza la consultas precompiladas
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * Servlet implementation class ConsultaServlet
 */

@WebServlet(urlPatterns = {"/ConsultaServlet3", "/consulta3"})
public class ConsultaServlet3 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Statement statment = null;
	private Connection conexion = null;
	private PreparedStatement sentenciapreparada = null;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ConsultaServlet3() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public void init(ServletConfig config) {
		try {
			// Class.forName("org.apache.derby.jdbc.ClientDriver");
			Class.forName("com.mysql.jdbc.Driver");

			conexion = DriverManager.getConnection(
					"jdbc:mysql://localhost/TiendaLibros", "root", "root");
			
			sentenciapreparada = conexion.prepareStatement("select * from libros where autor = ?");
			
		} catch (Exception ex) {
			System.err.println(" Error - Conexion con la base de datos.");
			ex.printStackTrace();
		}
	}

	@Override
	public void destroy() {
		try {
			statment.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			try {
				conexion.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// Establecemos el tipo MIME del mensaje de respuesta
		response.setContentType("text/html");
		// Creamos un objeto para poder escribir la respuesta
		PrintWriter out = response.getWriter();
		
		String autorpar = request.getParameter("autor");
		// Generar una p ́gina HTML como resultado de la consulta

		out.println("<html><head><title>Resultado de la consulta</title></head><body>");
		out.println("<h3>Gracias por tu consulta.</h3>");
		ResultSet rset = null;
		try {
			// Acceso simultáneo sincronizado
			synchronized (sentenciapreparada) {
				sentenciapreparada.setString(1, autorpar);
				rset = sentenciapreparada.executeQuery();
			}

			// Paso 5: Procesar el conjunto de registros resultante utilizando
			// ResultSet
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
