package metodos;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import dominio.MetodoPrueba;
import dominio.Semilla;
import main.Logger;

public class Practica1_MetodoN2 implements MetodoPrueba {
	private static final Logger logger = new Logger(Practica1_MetodoN2.class);

	public void Cuerpo(Semilla s) {

		// ordenamiento por burbuja
		logger.info("ordenando . . .");
		int[] arr = (int[]) s.dato;
		boolean swapped = true;
		int j = 0;
		int tmp;
		while (swapped) {
			swapped = false;
			j++;
			for (int i = 0; i < arr.length - j; i++) {
				if (arr[i] > arr[i + 1]) {
					tmp = arr[i];
					arr[i] = arr[i + 1];
					arr[i + 1] = tmp;
					swapped = true;
				}
			}
		}
		// fin del metodo
	}

	@Override
	public List<Semilla> obtenSemillas() {
		List<Semilla> s = new ArrayList<Semilla>();
		// generacion de 30 semillas simples
		int total = 200;
		for (int i = 1; i < total; i++) {
			s.add(generaSemilla(i * 100));
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
