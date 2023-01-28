
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class eje1
 */
@WebServlet("/eje1")
public class eje1 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public eje1() {
		super();
		// TODO Auto-generated constructor stub
		// Realizar un servlet que muestre un mensaje que indique si utilizó o no el
		// navegador Firefox con cultando las cabeceras de la petición enviado por el
		// navegador.
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<html><body>");
		String detallesNavegador = request.getHeader("User-Agent");
		if ( detallesNavegador.indexOf("Firefox") >= 0) {
			out.println("<h1> Usas Firefox</h1>");
		}
		else {
			out.println("<h1>No usas Firefox</h1>");
		}
		out.println("</body></html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
