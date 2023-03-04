import modelo.AccesoDatos;
import modelo.Cliente;
import modelo.Vehiculo;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "Servletreservar", value = "/reservar")
public class Servletreservar extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        String codigo = request.getParameter("cod_cli");
        String clave = request.getParameter("clave");
        String localidad = request.getParameter("localidad");
        String mensaje = "";

        AccesoDatos db = AccesoDatos.initModelo();
        Cliente cliente = db.getCliente(codigo, clave);
        Vehiculo vehiculo = db.getVehiculo(localidad);

        //Caso 4: EL codigo del cliente y la contraseña no son correctas

        if (cliente == null){
            mensaje = "Los valores de código de cliente y contraseña no son válidos";
            request.setAttribute("mensaje", mensaje);
            request.getRequestDispatcher("/WEB-INF/layout/error.jsp").forward(request, response);
        }

        //Caso 3: El codigo del cliente y la contraseña son correctas pero no hay vehiculos disponibles en esa localidad

        if (cliente!= null && vehiculo == null){
            request.setAttribute("cliente", cliente);
            mensaje = "Actualmente no hay vehículos disponibles en  "+ localidad;
            request.setAttribute("mensaje", mensaje);
            request.getRequestDispatcher("/WEB-INF/layout/vista.jsp").forward(request, response);
            return;
        }

        //Caso 2: El codigo del cliente y la contraseña son correctas y hay vehiculos disponibles en esa localidad

        if(cliente.getCod_car() != 0) {
            request.setAttribute("cliente", cliente);
            mensaje = "Ya tiene reservado el vehículo "+cliente.getCod_car();
            request.setAttribute("mensaje", mensaje);
            request.getRequestDispatcher("/WEB-INF/layout/vista.jsp").forward(request, response);
            return;
        }

        //Caso 1: El codigo del cliente y la contraseña son correctas y hay vehiculos disponibles en esa localidad

        request.setAttribute("cliente", cliente);
        mensaje = "Dispone en " +localidad+ " del vehículo "+vehiculo.getCod_car();
        request.setAttribute("mensaje", mensaje);
        db.alquilerVehiculo(vehiculo, cliente);
        request.getRequestDispatcher("/WEB-INF/layout/vista.jsp").forward(request, response);
        return;
    }
}
