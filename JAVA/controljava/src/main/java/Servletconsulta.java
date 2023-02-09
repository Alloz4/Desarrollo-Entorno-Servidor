
import java.io.IOException;
import java.security.PublicKey;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.plaf.basic.BasicSplitPaneUI.BasicHorizontalLayoutManager;

import modelo.AccesoDatos;
import modelo.Movimiento;

/**
 * Servlet implementation class Servletconsulta
 */
@WebServlet({ "/Servletconsulta", "/procesarconsulta" })
public class Servletconsulta extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Servletconsulta() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String cod_cliente = request.getParameter("cod_cliente");

		String minimo = request.getParameter("minimo");

		String maximo = request.getParameter("maximo");



		int importe_max = -1;
		int importe_min = 1;
		boolean error = false;

		try {
			importe_max = Integer.parseInt(maximo);
			importe_min = Integer.parseInt(minimo);
		} catch (NumberFormatException ex) {
			error = true;
		}

		AccesoDatos mimodelo = AccesoDatos.initModelo();
		String msg;

		if (error || importe_max < importe_min) {
			msg = "Los importes introducidos no son correctos " + error;
			request.setAttribute("msg", msg);
			request.getRequestDispatcher("/WEB-INF/infoerror.jsp").forward(request, response);
			return;
		}
		

		if (!mimodelo.hayMovimientos(cod_cliente)) {
			msg = "El código de cliente " + cod_cliente + " no se encuentra en la base de datos ";
			request.setAttribute("msg", msg);
			request.getRequestDispatcher("/WEB-INF/infoerror.jsp").forward(request, response);
			return;
		}

		ArrayList<Movimiento> lmov = mimodelo.obtenerListaMovimientos(cod_cliente, importe_max, importe_min);

		if (lmov.size() == 0) {
			msg = "No se han encontrado movimientos del cliente " + cod_cliente + " con los importes seleccionados";
			request.setAttribute("msg", msg);
			request.getRequestDispatcher("/WEB-INF/infoerror.jsp").forward(request, response);
			return;
		}

		request.setAttribute("listmovimientos", lmov);
		request.getRequestDispatcher("/WEB-INF/informovimiento.jsp").forward(request, response);
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
