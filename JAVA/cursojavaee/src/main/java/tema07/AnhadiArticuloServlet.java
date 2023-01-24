package tema07;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "AnhadiArticuloServlet", urlPatterns = {"/AnhadiArticuloServlet"})
public class AnhadiArticuloServlet extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ServletContext contexto = request.getServletContext();

        Boolean autenticado = (Boolean) request.getAttribute("autenticado");
        if ( autenticado == null){
        	RequestDispatcher paginaError =
                    contexto.getRequestDispatcher("/tema7/error.html");
            paginaError.forward(request, response);
        }
        else
        if (!autenticado) {
            RequestDispatcher paginaError =
                    contexto.getRequestDispatcher("/tema7/error.html");
            paginaError.forward(request, response);
        } 
        else {

            List<String> lista = (List<String>) contexto.getAttribute("lista");
            if (lista == null) {
                lista = new LinkedList<String>();
                contexto.setAttribute("lista", lista);
            }

            lista.add((String) request.getAttribute("articulo"));
            RequestDispatcher listarArticulosServlet =
                    contexto.getNamedDispatcher("ListarArticulosServlet");
            listarArticulosServlet.forward(request, response);
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