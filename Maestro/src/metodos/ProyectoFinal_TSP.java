package metodos;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import main.Logger;
import tsp.AdminRutas;
import tsp.Aproximacion;
import tsp.Ciudad;
import tsp.Poblacion;
import dominio.MetodoPrueba;
import dominio.Semilla;

public class ProyectoFinal_TSP implements MetodoPrueba {
	private static final Logger logger = new Logger(ProyectoFinal_TSP.class);

	public void Cuerpo(Semilla s) {

		@SuppressWarnings("unchecked")
		ArrayList<Ciudad> ciudades = (ArrayList<Ciudad>) s.dato;
		for (Iterator<Ciudad> iterator = ciudades.iterator(); iterator.hasNext();) {
			Ciudad ciudad = (Ciudad) iterator.next();
			AdminRutas.agregaCiudad(ciudad);
		}

		Poblacion pop = new Poblacion(50, true);
		logger.info("Distancia inicial: " + pop.obtenMejor().distanciaTotal());
		pop = Aproximacion.sigPoblacion(pop);
		// 100 mutaciones
		for (int i = 0; i < 100; i++) {
			pop = Aproximacion.sigPoblacion(pop);
		}
		logger.info("Distancia Final  : " + pop.obtenMejor().distanciaTotal());
		logger.info("Ruta:");
		logger.info(pop.obtenMejor());
		AdminRutas.limpiaCiudades();
	}

	@Override
	public List<Semilla> obtenSemillas() {
		List<Semilla> s = new ArrayList<Semilla>();
		// generacion de 50 semillas simples
		int total = 50;
		for (int i = 1; i < total; i++) {
			s.add(generaSemilla(i * 10));
		}
		return s;
	}

	@Override
	public Semilla generaSemilla(int n) {
		// n sera el numero de ciudades
		ArrayList<Ciudad> ciudades = new ArrayList<Ciudad>();

		Random rand = new Random();
		// coordenadas de las ciudades -> mapa de 200 * 200
		int max = 200;
		int min = 1;

		for (int i = 0; i < n; i++) {
			ciudades.add(new Ciudad(rand.nextInt((max - min) + 1) + min, rand.nextInt((max - min) + 1) + min, "Ciudad_"
					+ i));

		}

		return new Semilla(n, ciudades);
	}
}
