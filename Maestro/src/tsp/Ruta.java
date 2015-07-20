package tsp;

import java.util.ArrayList;
import java.util.Collections;

public class Ruta {

	private ArrayList<Ciudad> rutas = new ArrayList<Ciudad>();
	private double fitness = 0;
	private int distancia = 0;

	public Ruta() {
		for (int i = 0; i < AdminRutas.ciudades(); i++) {
			rutas.add(null);
		}
	}

	public Ruta(ArrayList<Ciudad> r) {
		this.rutas = r;
	}

	public void generaIndividuo() {
		for (int cityIndex = 0; cityIndex < AdminRutas.ciudades(); cityIndex++) {
			posicionaCiudad(cityIndex, AdminRutas.obtenCiudad(cityIndex));
		}
		Collections.shuffle(rutas);
	}

	public Ciudad obtenCiudad(int pos) {
		return (Ciudad) rutas.get(pos);
	}

	public void posicionaCiudad(int pos, Ciudad ciudad) {
		rutas.set(pos, ciudad);
		fitness = 0;
		distancia = 0;
	}
 
	public double getFitness() {
		if (fitness == 0) {
			fitness = 1 / (double) distanciaTotal();
		}
		return fitness;
	}
 
	public int distanciaTotal() {
		if (distancia == 0) {
			int distanciaT = 0; 
			for (int i = 0; i < totalCiudades(); i++) { 
				Ciudad desde = obtenCiudad(i); 
				Ciudad hasta; 
				if (i + 1 < totalCiudades()) {
					hasta = obtenCiudad(i + 1);
				} else {
					hasta = obtenCiudad(0);
				} 
				distanciaT += desde.distanciaHacia(hasta);
			}
			distancia = distanciaT;
		}
		return distancia;
	}
 
	public int totalCiudades() {
		return rutas.size();
	}
 
	public boolean containsCity(Ciudad city) {
		return rutas.contains(city);
	}

	@Override
	public String toString() {
		String secuencia = " -> ";
		for (int i = 0; i < totalCiudades(); i++) {
			secuencia += obtenCiudad(i) + " -> ";
		}
		return secuencia;
	}
}