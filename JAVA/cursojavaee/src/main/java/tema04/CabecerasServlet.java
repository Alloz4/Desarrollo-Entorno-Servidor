package tema04;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name="CabecerasServlet", urlPatterns={"/cabeceras"})
public class CabecerasServlet extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void procesaPeticion(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CabecerasServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Cabeceras: </h1>");

            out.println("<ul>");
            Enumeration<String> nombresDeCabeceras = request.getHeaderNames();
            while (nombresDeCabeceras.hasMoreElements()) {
                String cabecera = nombresDeCabeceras.nextElement();
                out.println("<li><b>" + cabecera + ": </b>"
                        + request.getHeader(cabecera) + "</li>");
            }
            
            out.println("</ul>");
            out.println("</body>");
            out.println("</html>");

        } finally {
            out.close();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        procesaPeticion(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        procesaPeticion(request, response);
    }
}