package metodos;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import main.Logger;

public class MetodoN implements MetodoPrueba {
	private static final Logger logger = new Logger(MetodoN.class);

	public void Cuerpo(Semilla s) {

		// promediar los elementos de un arreglo
		logger.info("Promediando . . .");
		int[] e = (int[]) s.dato;
		double suma = 0;
		for (int i = 0; i < e.length; i++) {
			suma += e[i];
		}
		suma = suma / e.length;
		logger.info("Promedio [" + suma + "]");

	}

	@Override
	public List<Semilla> obtenSemillas() {
		List<Semilla> s = new ArrayList<Semilla>();
		// generacion de 20 semillas simples
		int total = 20;
		for (int i = 1; i < total; i++) {
			s.add(generaSemilla(i));
		}
		return s;
	}

	@Override
	public Semilla generaSemilla(int n) {
		// n sera el numero de elementos de un arreglo
		// de elementos aleatoriamente distribuidos de 1- 100
		int[] e = new int[n];

		Random rand = new Random();
		int max = 100;
		int min = 1;
  
		for (int i = 0; i < e.length; i++) {
			e[i] = rand.nextInt((max - min) + 1) + min; 
		} 
		return new Semilla(n, e);
	}
}
