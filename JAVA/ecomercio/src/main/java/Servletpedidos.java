
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.AccesoDatos;
import modelo.Cliente;
import modelo.Pedido;

/**
 * Servlet implementation class Servletpedidos
 */
@WebServlet({ "/Servletpedidos", "/verpedidos" })
public class Servletpedidos extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Servletpedidos() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		AccesoDatos ac = AccesoDatos.initModelo();
		String nombre = request.getParameter("nombre");
		String clave = request.getParameter("clave");

		if (nombre == null || clave == null || nombre.trim().contentEquals("") || clave.trim().contentEquals("")) {
			String mensaje = "Introduzca un valor de usuario y contraseña";
			request.setAttribute("mensaje", mensaje);
			request.getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
			return;
		}
		Cliente cli = ac.ckequearUsuario(nombre, clave);
		if (cli == null) {
			String mensaje = "Error nombre de usuarios y contraseña incorrecto.";
			request.setAttribute("mensaje", mensaje);
			request.getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
			return;
		}
		// Obtengo los datos del modelo
		ArrayList<Pedido> lista = ac.obtenerListaPedidos(cli.getCod_cliente());
		// Incremento el número de veces
		ac.IncrementarVeces(cli.getNombre());
		// Creo atributos para que los visualice el JSP
		request.setAttribute("cliente", cli);
		request.setAttribute("lista", lista);
		// Se lo envio a la vista
		request.getRequestDispatcher("/WEB-INF/listarPedidos.jsp").forward(request, response);
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
