package Modelo;
import java.util.ArrayList;


// Implemento el PATRON  SINGLETON singleton para que solo exista una única instancia
// de la clase, aunque existen muchas peticiones simultaneas (JavaEE multihilo)

public class AccesoDatos {

	private ArrayList <Producto> listaProductos;
	private static AccesoDatos miModelo;
	
	//Método que crea o devuelve el objeto ya creado
	// Acceso sincronizado por un servlet es multihilo
	public static final synchronized AccesoDatos initModelo() {
		if (miModelo == null) {
			miModelo = new AccesoDatos();
		}
		return miModelo;
	}
	
	// Accede a los datos y crea la lista de datos
	// Constructor privado para que no sea accesible
	private AccesoDatos(){
		listaProductos = new ArrayList <Producto>();
		listaProductos.add( new Producto(20,"Tornillos",3,10));
		listaProductos.add( new Producto(21,"Martillo",4,12));
		listaProductos.add( new Producto(22,"Grapas",20,5));
	}
	
	// Devuelvo la lista de libros
	public ArrayList <Producto> ObtenerProductos (){		
		return listaProductos;
	}
	
	//Devuelvo un producto concreto a partir de su id
	// o NULL si no existe
	public Producto ObtenerProductoId ( int id) {
		Producto resu = null;
		for (Producto producto : listaProductos) {
			if (producto.getId() == id) {
				resu = producto;
				break;
			}
		}
		return resu;
	}
	
	// Evito que se pueda clonar el objeto.
    @Override
    public AccesoDatos clone(){
            try {
                throw new CloneNotSupportedException();
            } catch (CloneNotSupportedException ex) {
            	ex.printStackTrace();
            }
            return null; 
        }
}
