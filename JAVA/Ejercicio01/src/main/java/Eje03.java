

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Eje03
 */
@WebServlet("/Eje03")
public class Eje03 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Eje03() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		String usuario  = request.getParameter("usuario");
		String clave    = request.getParameter("clave");
		PrintWriter out = response.getWriter();
        try {
        	out.println("<html><body>");
        	if ( usuario != null && clave != null &&
        		 usuario.contentEquals("alumno") &&
        		   clave.contentEquals("alumno") ) {
        		out.println ("<h1> Bienvenido al sistema </h1>");
        	}
        	else {
        		out.println("<body><h1> ACCESO NO AUTORIZADO </h1><BR>"
        		        + " Introduzca un usuario y contraseña correctos </body></html>");
        		// Redirección con Header
        		response.setHeader("Refresh", "3; URL=acceso.html");
        		
        	}
        	out.println("</body></html>");
     
	    } finally { 
        out.close();
    }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
