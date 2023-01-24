package tema08;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "GestorDeExcepcionesServlet", urlPatterns = {"/GestorDeExcepcionesServlet"})
public class GestorDeExcepcionesServlet extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Exception exception =
                (Exception) request.getAttribute("javax.servlet.error.exception");
        String urlFuenteDelError =
                (String) request.getAttribute("javax.servlet.error.request_uri");

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet GestorDeExcepcionesServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Se ha producido el siguiente error:</h1>");
            out.println("<pre>");
            exception.printStackTrace(out);
            out.println("</pre>");
            out.println("<br/>Al intentar acceder al siguiente recurso:<br/>");
            out.println("<pre>");
            out.println(urlFuenteDelError);
            out.println("</pre>");
            out.println("</body>");
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