package tema09;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name="ProblemaDeConcurrenciaServlet", urlPatterns={"/ProblemaDeConcurrenciaServlet"})
public class ProblemaDeConcurrenciaServlet extends HttpServlet{
   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
public static final int REPETICIONES = 3000000;
   private int valor;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        valor=0;
        int[] numerosAleatorios = new int[REPETICIONES];
        for (int i = 0; i < REPETICIONES; i++) {
            double a = Math.random()*10;
            numerosAleatorios[i] = (int)a;
            valor += numerosAleatorios[i];
        }
        for (int i = 0; i < REPETICIONES; i++) {
            valor -= numerosAleatorios[i];
        }
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Problema de concurrencia</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("El resultado es " + valor + "");
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