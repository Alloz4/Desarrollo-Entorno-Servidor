

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Control
 */
@WebServlet("/Control")
public class Control extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Control() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String método = request.getParameter("metodo");
		int valor =1;
		if ( método != null){
			try {
			 valor = Integer.parseInt(método);
			}
			catch ( Exception ex){
				valor = 1;
			}
		}
		
		switch ( valor ){
		case 1: request.getRequestDispatcher("/consulta1").forward(request,response);break; // Por URL
		case 2: request.getRequestDispatcher("/consulta2").forward(request,response);break; // Por nombre Servlet2
		case 3: request.getRequestDispatcher("/consulta3").forward(request,response); break;
		case 4: request.getRequestDispatcher("/consultapool").forward(request,response); break;
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
