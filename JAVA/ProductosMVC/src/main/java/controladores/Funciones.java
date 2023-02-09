package controladores;

import java.util.ArrayList;

import modelo.AccesoDatos;
import modelo.Producto;

public class Funciones {

	static String mostrarDatos() {

		String msg = "";
		String titulos[] = { "Numero Producto", "Descripción", "Precio", "Stock" };
		msg += "<table>\n";
		// Identificador de la tabla
		msg += "<tr>";
		for (int j = 0; j < titulos.length; j++) {
			msg += "<th>" + titulos[j] + "</th>";
		}
		msg += "</tr>";
		AccesoDatos db = AccesoDatos.initModelo();
		ArrayList<Producto> tproductos = db.getProductos();
		for (Producto producto : tproductos) {
			msg += "<tr>";
			msg += "<td>" + producto.getProducto_no() + "</td>";
			msg += "<td>" + producto.getDescripcion() + "</td>";
			msg += "<td>" + producto.getPrecio_actual() + "</td>";
			msg += "<td>" + producto.getStock_disponible() + "</td>";
			msg += "<td><a href='#' onclick=\"confirmarBorrar('" + producto.getDescripcion() + "','" + producto.getProducto_no()
					+ "');\" >Borrar</a></td>\n";
			msg += "<td><a href='?orden=Modificar&producto_no=" + producto.getProducto_no() + "'>Modificar</a></td>\n";
			msg += "<td><a href='?orden=Detalles&producto_no=" + producto.getProducto_no() + "' >Detalles</a></td>\n";
			msg += "</tr>\n";

		}
		msg += "</table>";

		return msg;
	}
}
