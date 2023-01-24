package tema06;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "SessionServlet", urlPatterns = {"/SessionServlet"})
public class SessionServlet extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nuevoAtributo = request.getParameter("parametro");
        String valor = request.getParameter("valor");
        String action = request.getParameter("accion");

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet que muestra el contenido de la sesion</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>El contenido de tu sesion es:</h1>");

            HttpSession s = request.getSession();
            if (action.equals("invalidar")) {
                s.invalidate();
                out.println("<h1>Sesion invalidada:</h1>");
            } else {
                s.setAttribute(nuevoAtributo, valor);
                out.println("<ul>");
                // Fijo en 10 segundo el intervalo de inactividad para anular la sesion
                s.setMaxInactiveInterval(10);
                Enumeration<String> nombresDeAtributos = s.getAttributeNames();
                while (nombresDeAtributos.hasMoreElements()) {
                    String atributo = nombresDeAtributos.nextElement();
                    out.println("<li><b>" + atributo + ": </b>"
                            + s.getAttribute(atributo) + "</li>");
                }

                out.println("</ul>");
            }


            out.println("<a href=/cursojavaee/tema6/FormularioSesion.html>"+
                    "Volver a la página anterior</a><br/>");
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
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