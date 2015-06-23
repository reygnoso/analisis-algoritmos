package metodos;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import main.Logger;
import dominio.MetodoPrueba;
import dominio.Semilla;

public class MetodoPermutar implements MetodoPrueba {
	private static final Logger logger = new Logger(MetodoPermutar.class);

	public void Cuerpo(Semilla s) {
		Random rand = new Random();
		// permuta los elementos de un arreglo
		logger.info("permutando!");
		int[] e = (int[]) s.dato;
		int nl = e.length;
		logger.info("permutando! {"+nl+"}");
		BigInteger n = new BigInteger("" + nl);
		BigInteger per = n.pow(3);
		BigInteger upperBound;
		do {
			upperBound = new BigInteger(n.bitLength(), rand);
		} while (upperBound.compareTo(n) >= 0);

		BigInteger pos = (upperBound.multiply(new BigInteger(new Date().getTime() + ""))).modPow(upperBound, per);
		BigInteger[] c = new BigInteger[nl];

		for (int i = 0; i < nl; i++) {
			BigInteger fac = factorial(new BigInteger("" + (nl - i)));
			// logger.info("fact! [" + nl + "][" + i + "] " + fac);
			c[i] = pos.divide(fac);
		}

		int[] x = new int[nl];
		for (int i = 0; i < nl; i++) {
			x[i] = i;
		}

		int[] b = new int[nl];
		for (int i = 0; i < nl; i++) {
			if (e[i] == x[i]) {
				b[i] = e[i];
			}
		}

		logger.info("hecho!");

	}

	BigInteger factorial(BigInteger n) {
		if (n.compareTo(BigInteger.ONE) <= 0) {
			return BigInteger.ONE;
		} else

			return n.multiply(factorial(n.subtract(BigInteger.ONE)));
	}

	@Override
	public List<Semilla> obtenSemillas() {
		List<Semilla> s = new ArrayList<Semilla>();
		// generacion de 20 semillas simples
		int total = 1000;
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
		int max = 50;
		int min = 1;

		for (int i = 0; i < e.length; i++) {
			e[i] = rand.nextInt((max - min) + 1) + min;
		}
		return new Semilla(n, e);
	}
}
