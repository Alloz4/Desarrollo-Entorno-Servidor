package tema06;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "CookieServlet", urlPatterns = {"/CookieServlet"})
public class CookieServlet extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nombreNuevaCookie = request.getParameter("cookie");
        String valor = request.getParameter("valor");
        int duracion;
        try {
            duracion = Integer.parseInt(request.getParameter("duracion"));
        } catch (NumberFormatException e) {
            duracion = -1;
        }

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet que muestra las cookies</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Las cookies que has recibido de esta web son:</h1>");
            out.println("<ul>");

            if (nombreNuevaCookie != null && valor != null
                    && !nombreNuevaCookie.equals("")) {
                Cookie nuevaCookie = new Cookie(nombreNuevaCookie, valor);
                nuevaCookie.setMaxAge(duracion);
                response.addCookie(nuevaCookie);
                out.println("<li><b>" + nuevaCookie.getName() + ": </b>"
                        + nuevaCookie.getValue() + "; fecha de expiracion: "
                        + nuevaCookie.getMaxAge() + "</li>");
            }

            Cookie[] todasLasCookies = request.getCookies();
            if (todasLasCookies != null) {
                for (Cookie cookie : todasLasCookies) {
                    out.println("<li><b>" + cookie.getName() + ": </b>"
                            + cookie.getValue() + "-, fecha de expiracion: "
                            + cookie.getMaxAge() + "</li>");
                }
            }

            out.println("</ul>");
            out.println("<a href=/cursojavaee/tema6/FormularioCookies.html>"+
                    "Volver a la pagina anterior</a><br/>");
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