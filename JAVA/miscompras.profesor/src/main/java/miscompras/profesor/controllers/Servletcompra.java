package miscompras.profesor.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import miscompras.profesor.modelo.*;


/**
 * Servlet implementation class Servletcompra
 */
@WebServlet({ "/Servletcompra", "/realizarcompra","/cosa" })
public class Servletcompra extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Servletcompra() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		String cod_socio  = request.getParameter("cod_socio");
		String clave      = request.getParameter("clave");
		String cod_pro    = request.getParameter("cod_pro");
		
		if ( cod_socio == null || clave == null || cod_pro == null ||
			cod_socio.isEmpty() || clave.isEmpty() || cod_pro.isEmpty()) {
			request.setAttribute("msg"," Parámetros incorrectos");
			request.getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
			return;
		}

		AccesoDatos ac =  AccesoDatos.initModelo();

		Socio socio = ac.obtenerSocio(cod_socio, clave);
		if ( socio == null) {
			request.setAttribute("msg","Los valores de código de cliente y contraseña no son válidos");
			request.getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
			return;	
		}
		
		Producto producto = ac.obtenerProducto(cod_pro);
		
		if ( producto == null) {
			request.setAttribute("msg","No se encuentra ningún producto con el código "+cod_pro +" en nuestro catálogo");
			request.getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
			return;	
		}

	   request.setAttribute("producto", producto);
   	   request.setAttribute("socio",socio);
       if ( socio.getCantidad_max() >= producto.getPrecio()) {
    	   // Redundacia: posible incoherecia El objeto y la entidad no están sincronizados
    	   ac.actualizarSocio(socio.getCod_socio(), producto.getPrecio());
    	   socio.setCantidad_max( socio.getCantidad_max()-producto.getPrecio());      
    	   request.setAttribute("exito", true);
       } else {
    	   request.setAttribute("exito", false);
       }
		request.getRequestDispatcher("/WEB-INF/vista.jsp").forward(request, response);
       
		
	}

}
