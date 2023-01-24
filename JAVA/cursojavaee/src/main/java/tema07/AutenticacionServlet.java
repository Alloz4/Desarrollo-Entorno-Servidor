package tema07;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name="AutenticacionServlet", urlPatterns={"/AutenticacionServlet"})
public class AutenticacionServlet extends HttpServlet {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String passwordAdministrador;


    @Override
   public void init(ServletConfig config){
        String ficheroUsuarios = config.getInitParameter("ficheroUsuarios");
        ServletContext contexto = config.getServletContext();
        InputStream is = contexto.getResourceAsStream(ficheroUsuarios);
        Properties ficheroPropiedades = new Properties ();
        try {
            ficheroPropiedades.load(is);
            passwordAdministrador = ficheroPropiedades.getProperty("administradorPassword");
        } catch (Exception ex) {
            Logger.getLogger(AutenticacionServlet.class.getName()).log(Level.SEVERE,
                    "No se pudo cargar el fichero con los password", ex);
        }
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {

        String usuario = request.getParameter("usuario");
        String password = request.getParameter("password");
        String articulo = request.getParameter("articulo");
        ServletContext contexto = request.getServletContext();

        if (usuario!= null && usuario.equals("administrador")
                           && password.equals(passwordAdministrador)) {
            request.setAttribute("autenticado", true);
            request.setAttribute("articulo", articulo);
            RequestDispatcher anhadirServlet =
                    contexto.getNamedDispatcher("AnhadiArticuloServlet");
            anhadirServlet.forward(request, response);
        } else {
             RequestDispatcher paginaError =
                     contexto.getRequestDispatcher("/tema7/error.html");
             paginaError.forward(request, response);
        }
    } 

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    } 

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }
}