package tema10;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

@WebServlet(name = "ConectionPoolServlet", urlPatterns = {"/ConectionPoolServlet"})
public class ConectionPoolServlet extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	DataSource pool;

    @Override
    public void init(ServletConfig config) throws ServletException {
        Context env = null;
        try {
            env = (Context) new InitialContext().lookup("java:comp/env");
            pool = (DataSource) env.lookup("jdbc/HolaMundoPool");
            if (pool == null) {
                throw new ServletException("No se encontró el DataSource");
            }
        } catch (NamingException ne) {
            throw new ServletException(ne.getMessage());
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Listar Personas</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Lista de las personas:</h1>");
            out.println("<ul>");


            String query = "select * from PERSONAS";
            Connection conexion = null;
            ResultSet resultSet = null;
            try {
                conexion = pool.getConnection();
                Statement statment = conexion.createStatement();
                resultSet = statment.executeQuery(query);
                while (resultSet.next()) {
                    out.println("<li>" + resultSet.getString("NOMBRE")
                            + " edad: " + resultSet.getInt("EDAD") + "</li>");
                }
            } catch (SQLException ex) {
                gestionarErrorEnConsultaSQL(ex, request, response);
            } finally {

                try {
                    conexion.close();
                    if (resultSet != null) {
                        resultSet.close();
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(JDBCServlet.class.getName()).log(Level.SEVERE,
                            "No se pudo cerrar el Resulset", ex);
                }

            }
            out.println("</ul>");
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nombre = request.getParameter("nombre");
        int edad;
        try {
            edad = Integer.parseInt(request.getParameter("edad").trim());
        } catch (NumberFormatException e) {
            edad = -1;
        }
        ServletContext contexto = request.getServletContext();
        String query = null;

        try {
            Connection conexion = pool.getConnection();
            Statement statment = conexion.createStatement();
            
            query = "insert into PERSONAS values(" + 
                    "'"+ nombre + "'," + edad + ")";
            statment.executeUpdate(query);
            statment.close();
            conexion.close();
        } catch (SQLException ex) {
            gestionarErrorEnConsultaSQL(ex, request, response);
        }

        RequestDispatcher paginaAltas =
                contexto.getRequestDispatcher("/tema10/crearpersona.html");
        paginaAltas.forward(request, response);

    }

    private void gestionarErrorEnConsultaSQL(SQLException ex, HttpServletRequest request,
            HttpServletResponse response) throws IOException, ServletException {
        ServletContext contexto = request.getServletContext();
        Logger.getLogger(JDBCServlet.class.getName()).log(Level.SEVERE, "No se pudo ejecutar la consulta contra la base de datos", ex);
        RequestDispatcher paginaError = contexto.getRequestDispatcher("/tema10/crearpersona.html");
        paginaError.forward(request, response);
    }
}