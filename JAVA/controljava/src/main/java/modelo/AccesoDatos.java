package modelo;

import java.util.ArrayList;

//Implemento el modelo con  Patrón Singleton
public class AccesoDatos {

	private ArrayList<Movimiento> listamov;
	private static AccesoDatos modelo;

	public static synchronized AccesoDatos initModelo() {
		if (modelo == null) {
			modelo = new AccesoDatos();
		}
		return modelo;
	}

	// Creo un lista de Movimientos, podría obtenerse de una base de datos
	private AccesoDatos() {
		listamov = new ArrayList<Movimiento>();

		listamov.add(new Movimiento(1001, "ALEX23", "INGRESO A CUENTA", 1100));
		listamov.add(new Movimiento(1002, "NOM344", "INGRESO METÁLICO", 150));
		listamov.add(new Movimiento(1003, "LUIS33", "INGRESO A CUENTA", 120));
		listamov.add(new Movimiento(1004, "ALEX23", "TRANSFERENCIA CC", 50));
		listamov.add(new Movimiento(1005, "LIS943", "INGRESO METÁLICO", 1100));
		listamov.add(new Movimiento(1006, "ALEX23", "IMPORTE NOMINA  ", 1000));
		listamov.add(new Movimiento(1007, "LUIS34", "INGRESO A CUENTA", 50));
		listamov.add(new Movimiento(1008, "ALEX23", "IMPORTE NOMINA  ", 500));
		listamov.add(new Movimiento(1009, "LUIS34", "INGRESO METÁLICO", 150));
		listamov.add(new Movimiento(1010, "ALEX23", "TRANSFERENCIA CC", 100));
		listamov.add(new Movimiento(1011, "NOM344", "TRANSFERENCIA CC", 1450));
		listamov.add(new Movimiento(1012, "EVA100", "INGRESO A CUENTA", 100));
		listamov.add(new Movimiento(1013, "EVA100", "ABONO INTERESES ", 50));
		listamov.add(new Movimiento(1014, "PEPE10", "TRANSFERENCIA CC", 120));
		listamov.add(new Movimiento(1015, "ALEX23", "INGRESO A CUENTA", 150));
		listamov.add(new Movimiento(1016, "PEPE10", "ABONO INTERESES ", 150));
		listamov.add(new Movimiento(1017, "EVA100", "IMPORTE NOMINA  ", 500));
		listamov.add(new Movimiento(1018, "LUIS34", "TRANSFERENCIA CC", 5100));
	}
	/*
	 * DEVUELVE UN NUEVO ARRAYLIST CON LOS MOVIMIENTOS QUE CUMPLEN LA CONDICION
	 * DEVUELVE UN ARRAYLIST VACIO SI NO HAY MOVIMIENTOS PARA ESE CLIENTE
	 * resultado.size() == 0
	 */

	public ArrayList<Movimiento> obtenerListaMovimientos(String cod_cliente, int importmax, int importmin) {
		ArrayList<Movimiento> resultado = new ArrayList<Movimiento>();

		for (Movimiento movimiento : listamov) {
			
			if (movimiento.getCod_cliente().equals(cod_cliente) && movimiento.getImporte() >= importmin
					&& movimiento.getImporte() <= importmax) {
				resultado.add(movimiento);
			}
		}

		return resultado;
	}

	// Devuelve TRUE o FALSE si hay algun Movimiento con ese código de cliente
	public boolean hayMovimientos(String cod_cliente) {
		boolean resu = false;

		for (Movimiento movimiento : listamov) {
			if (movimiento.getCod_cliente().contentEquals(cod_cliente)) {
				resu = true;
				break;
			}
		}

		return resu;

	}

	// Evito que se pueda clonar el objeto.
	@Override
	public AccesoDatos clone() {
		try {
			throw new CloneNotSupportedException();
		} catch (CloneNotSupportedException ex) {
			ex.printStackTrace();
		}
		return null;
	}
}
