

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class EjemploParametro
 */
@WebServlet({"/ProcesarParametro", "/pro"})
public class ProcesarParametro extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProcesarParametro() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
    public void doGet(HttpServletRequest request, HttpServletResponse response)
    	    throws IOException, ServletException
    	    {
    	        response.setContentType("text/html");
    	        PrintWriter out = response.getWriter();
    	        out.println("<html>");
    	        out.println("<head>");
    	        out.println("<title>Request Parameters Example</title>");
    	        out.println("</head>");
    	        out.println("<body>");
    	        out.println("<h3> EJEMPLO DE PARAMETROS </h3>");
    	        out.println("Parámetros recibidos:<br>");
    	        String nombre = request.getParameter("nombre");
    	        String apellido = request.getParameter("apellido");
    	        

    	        if (nombre != null || apellido != null) {
    	            out.println("Nombre:");
    	            out.println(" = " + nombre + "<br>");
    	            out.println("Apellido");
    	            out.println(" = " + apellido);
    	        } else {
    	            out.println("Rellene los datos");
    	        }
    	        
    	    }

    	    public void doPost(HttpServletRequest request, HttpServletResponse res)
    	    throws IOException, ServletException
    	    {
    	        doGet(request, res);
    	    }
	
}