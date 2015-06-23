package metodos;

import helper.FFTbase;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import main.Logger;
import dominio.MetodoPrueba;
import dominio.Semilla;

public class MetodoFFT implements MetodoPrueba {
	private static final Logger logger = new Logger(MetodoFFT.class);

	public void Cuerpo(Semilla s) {

		// promediar los elementos de un arreglo
		logger.info("Transformando Arreglo . . .");
		FFTbase.FFT((double[]) s.dato);
	}

	@Override
	public List<Semilla> obtenSemillas() {
		List<Semilla> s = new ArrayList<Semilla>();
		// generacion de 20 semillas simples
		int total = 400;
		for (int i = 1; i < total; i++) {
			s.add(generaSemilla(i * 900));
		}
		return s;
	}

	@Override
	public Semilla generaSemilla(int n) {
		// n sera el numero de elementos de un arreglo
		// de elementos aleatoriamente distribuidos de 1- 100
		double[] e = new double[n];

		Random rand = new Random();
		int max = 100;
		int min = 1;
		for (int i = 0; i < e.length; i++) {
			e[i] = rand.nextInt((max - min) + 1) + min;
		}
		return new Semilla(n, e);
	}
}
