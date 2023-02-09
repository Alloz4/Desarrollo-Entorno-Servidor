package controladores;

import modelo.AccesoDatos;
import modelo.Producto;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Acciones {
	
	HttpServletRequest request;
	HttpServletResponse response;
	
	Acciones (HttpServletRequest request, HttpServletResponse response ){
		this.request = request;
		this.response = response;
	}
	
	void accionAlta() throws ServletException, IOException {
		 Producto producto = new Producto();
		 request.setAttribute("orden", "Nuevo");
		 request.setAttribute("producto", producto);
		 request.getRequestDispatcher("/WEB-INF/layout/formulario.jsp").forward(request, response);
	}
	 void accionBorrar ( int producto_no ){
		 AccesoDatos db = AccesoDatos.initModelo();
		 db.borrarProducto(producto_no);	
	}
	void accionModificar (int producto_no) throws ServletException, IOException {
		 AccesoDatos db = AccesoDatos.initModelo();
		    Producto producto = db.getProducto(producto_no);
		    request.setAttribute("orden", "Modificar");
		    request.setAttribute("producto", producto);
			request.getRequestDispatcher("/WEB-INF/layout/formulario.jsp").forward(request, response);

	}
	 void accionDetalles  (int producto_no ) throws ServletException, IOException {
		    AccesoDatos db = AccesoDatos.initModelo();
		    Producto producto = db.getProducto(producto_no);
		    request.setAttribute("orden", "Detalles");
		    request.setAttribute("producto", producto);
			request.getRequestDispatcher("/WEB-INF/layout/formulario.jsp").forward(request, response);
	}
    void accionTerminar() {
    	System.out.println("->>> accionTerminar   \n");
    }
    
    void accionPostAlta() {
      // Habría que controlar los datos de recibido <<<<<<<
      Producto producto = new Producto();
   	  producto.setProducto_no(Integer.parseInt(request.getParameter("producto_no")));
   	  producto.setDescripcion(request.getParameter("descripcion"));
   	  producto.setPrecio_actual(Float.parseFloat(request.getParameter("precio_actual")));
   	  producto.setStock_disponible(Integer.parseInt(request.getParameter("stock_disponible")));
   	  AccesoDatos db = AccesoDatos.initModelo();
   	  db.addProducto(producto);
    }
    void accionPostModificar() {
    	// Habría que controlar los datos de recibido <<<<<<<
        Producto producto = new Producto();
     	  producto.setProducto_no(Integer.parseInt(request.getParameter("producto_no")));
     	  producto.setDescripcion(request.getParameter("descripcion"));
     	  producto.setPrecio_actual(Float.parseFloat(request.getParameter("precio_actual")));
     	  producto.setStock_disponible(Integer.parseInt(request.getParameter("stock_disponible")));
     	  AccesoDatos db = AccesoDatos.initModelo();
    	  db.modProducto(producto);
 	
    }
}
