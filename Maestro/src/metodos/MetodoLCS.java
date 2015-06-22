package metodos;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import dominio.MetodoPrueba;
import dominio.Semilla;
import main.Logger;

public class MetodoLCS implements MetodoPrueba {
	private static final Logger logger = new Logger(
			MetodoLCS.class);

	int[][] m;
	int[][] s;

	public void Cuerpo(Semilla s) {
		// promediar los elementos de un arreglo
		StringBuilder res = new StringBuilder();
		int[] p = (int[]) s.dato;
		for (int i = 0; i < p.length; i++) {
			res.append(" " + p[i] + ",");
		}

		logger.info("Obteniendo menor numero de operaciones para [" + res + "]");

		// regresa m,s
		matrixChainOrder(p);
		//saltando impresion de resultados para un tiempo mas cercano
		//logger.info("Resultado:");

		//printOptimalParenthesizations();
	}

	public void matrixChainOrder(int[] p) {
		int n = p.length - 1;
		m = new int[n][n];
		s = new int[n][n];
		for (int ii = 1; ii < n; ii++) {
			for (int i = 0; i < n - ii; i++) {
				int j = i + ii;
				m[i][j] = Integer.MAX_VALUE; // infinito
				for (int k = i; k < j; k++) {
					int q = m[i][k] + m[k + 1][j] + p[i] * p[k + 1] * p[j + 1];
					if (q < m[i][j]) {
						m[i][j] = q;
						s[i][j] = k;
					}
				}
			}
		}
	}

	public void printOptimalParenthesizations() {
		boolean[] inAResult = new boolean[s.length];
		printOptimalParenthesizations(s, 0, s.length - 1, inAResult);
	}

	void printOptimalParenthesizations(int[][] s, int i, int j,
			boolean[] inAResult) {
		if (i != j) {
			printOptimalParenthesizations(s, i, s[i][j], inAResult);
			printOptimalParenthesizations(s, s[i][j] + 1, j, inAResult);
			String istr = inAResult[i] ? "_resultado " : " ";
			String jstr = inAResult[j] ? "_resultado  " : " ";
			logger.info(" A_" + i + istr + "* A_" + j + jstr);
			inAResult[i] = true;
			inAResult[j] = true;
		}
	}

	@Override
	public List<Semilla> obtenSemillas() {
		List<Semilla> s = new ArrayList<Semilla>();
		// generacion de   semillas simples
		int total = 300;
		for (int i = 1; i < total; i++) {
			s.add(generaSemilla(i * 2));
		}
		return s;
	}

	@Override
	public Semilla generaSemilla(int n) {
		// n sera el numero de elementos de un arreglo
		// de elementos aleatoriamente distribuidos de min - max
		int[] e = new int[n];

		Random rand = new Random();
		int max = 50;
		int min = 1;

		for (int i = 0; i < e.length; i++) {
			e[i] = rand.nextInt((max - min) + 1) + min;
		}
		return new Semilla(n, e);
	}
}
