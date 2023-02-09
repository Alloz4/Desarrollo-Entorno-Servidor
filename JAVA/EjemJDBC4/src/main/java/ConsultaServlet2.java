
/*
 * EJEMPLO DE ACCESO A CONCURRENTE A UNA MISMA CONEXIÓN
 * Más eficiente pero el acceso debe ser sincronizado
 * Se establece la conexión con el método init usando todos
 * los hilos la misma conexión
 */


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ConsultaServlet2
 */
@WebServlet("/consulta2")
public class ConsultaServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Statement statment = null;
	private Connection conexion = null;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ConsultaServlet2() {
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
			statment = conexion.createStatement();
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

		String sqlStr = "select * from libros where autor = " + "'"
				+ request.getParameter("autor") + "'";
		// Generar una p ́gina HTML como resultado de la consulta

		out.println("<html><head><title>Resultado de la consulta</title></head><body>");
		out.println("<h3>Gracias por tu consulta.</h3>");
		out.println("<p>Tu consulta (2) es: " + sqlStr + "</p>");
		ResultSet rset = null;
		try {
			// Acceso simultáneo sincronizado
			synchronized (statment) {
				rset = statment.executeQuery(sqlStr);
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
