package tema07;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name="ListarArticulosServlet", urlPatterns={"/ListarArticulosServlet"})
public class ListarArticulosServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
         ServletContext contexto = request.getServletContext();
         List<String> lista = (List<String>) contexto.getAttribute("lista");

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ListarArticulosServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Lista:</h1>");

            out.println("<ul>");
            if (lista != null) {
                for (String articulo : lista) {
                    out.println("<li>" +  articulo +  "</li>");
                }
            }
            out.println("</ul>");
            out.println("<a href=/cursojavaee/tema7/FormularioLogin.html>"+
                    "Ir al formulario para añadir articulos</a><br/>");
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
