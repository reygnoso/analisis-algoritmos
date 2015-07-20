package tsp;

import java.util.ArrayList;

public class AdminRutas {

	private static ArrayList<Ciudad> ciudadesDestino = new ArrayList<Ciudad>();

	public static void agregaCiudad(Ciudad city) {
		ciudadesDestino.add(city);
	}

	public static Ciudad obtenCiudad(int index) {
		return (Ciudad) ciudadesDestino.get(index);
	}

	public static int ciudades() {
		return ciudadesDestino.size();
	}

	public static void limpiaCiudades() {
		ciudadesDestino.clear();
	}

}