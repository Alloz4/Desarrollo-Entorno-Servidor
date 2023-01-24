package tema05;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name="ConfigurableServlet", urlPatterns={"/ConfigurableServlet"},
initParams={@WebInitParam(name="parametro1", value="Valor1"),
            @WebInitParam(name="parametro2", value="Valor2")})
public class ConfigurableServlet extends HttpServlet {
    /**
	 * 
	 */

	private static final long serialVersionUID = 1L;
	private Map<String,String> mapaDeParametrosDeConfiguracion =
            new ConcurrentHashMap<String,String>();

    @Override
   public void init(ServletConfig config){
        Enumeration<String> nombresParametros = config.getInitParameterNames();
            while (nombresParametros.hasMoreElements()) {
                String nombreParametro = nombresParametros.nextElement();
                mapaDeParametrosDeConfiguracion.put(nombreParametro,
                        config.getInitParameter(nombreParametro));
            }

    }
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
 response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet que toma parametros de configuracion</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Parametros de configuracion:</h1>");
            out.println("<ul>");
            Set<String> s= mapaDeParametrosDeConfiguracion.keySet();
            for (String h : s) {
                out.println("<li>" + h + ": "+
                        mapaDeParametrosDeConfiguracion.get(h) + "</li>");
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
        processRequest(request, response);
    } 

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

}