package controlador;

import modelo.AccesoDatos;
import modelo.Cliente;
import modelo.Vehiculo;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/reservar")
public class Servletreservar extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String codigo = request.getParameter("cod_cli");
		String clave = request.getParameter("clave");
		String localidad = request.getParameter("localidad");
		String mensaje = "";

		// Caso 0: Parámetros incorrecto
		if (codigo == null || codigo.isEmpty() || clave == null || clave.isEmpty() || localidad == null
				|| localidad.isEmpty()) {
			mensaje = "Falta parámetros por introduccir ";
			request.setAttribute("mensaje", mensaje);
			request.getRequestDispatcher("/WEB-INF/layout/error.jsp").forward(request, response);
			return;
		}

		AccesoDatos db = AccesoDatos.initModelo();
		Cliente cliente =   db.getCliente(codigo, clave);
		Vehiculo vehiculo = db.getVehiculo(localidad);

		// Caso 4: EL codigo del cliente y la contraseña no son correctas

		if (cliente == null) {
			mensaje = "Los valores de código de cliente y contraseña no son válidos";
			request.setAttribute("mensaje", mensaje);
			request.getRequestDispatcher("/WEB-INF/layout/error.jsp").forward(request, response);
			return;
		}

		request.setAttribute("cliente", cliente);
		// Caso 3: El codigo del cliente y la contraseña son correctas pero no hay
		// vehiculos disponibles en esa localidad

		if (vehiculo == null) {
			mensaje = "Actualmente no hay vehículos disponibles en  " + localidad;
			request.setAttribute("mensaje", mensaje);
			request.getRequestDispatcher("/WEB-INF/layout/vista.jsp").forward(request, response);
			return;
		}

		// Caso 2: El codigo del cliente y la contraseña son correctas y hay vehiculos
		// disponibles en esa localidad

		if (cliente.getCod_car() != 0) {
			mensaje = "¡Ya tiene reservado el vehículo " + cliente.getCod_car() + " !";
			request.setAttribute("mensaje", mensaje);
			request.getRequestDispatcher("/WEB-INF/layout/vista.jsp").forward(request, response);
			return;
		}

		// Caso 1: El codigo del cliente y la contraseña son correctas y hay vehiculos
		// disponibles en esa localidad

		
		mensaje = "Dispone en " + localidad + " del vehículo " + vehiculo.getCod_car();
		request.setAttribute("mensaje", mensaje);
		db.anotarVehiculoACliente(vehiculo, cliente);
		request.getRequestDispatcher("/WEB-INF/layout/vista.jsp").forward(request, response);
		return;
	}
}
