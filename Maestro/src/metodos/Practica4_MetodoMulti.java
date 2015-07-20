package metodos;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import main.Logger;
import dominio.MetodoPrueba;
import dominio.Semilla;

public class Practica4_MetodoMulti implements MetodoPrueba {
	private static final Logger logger = new Logger(Practica4_MetodoMulti.class);

	public void Cuerpo(Semilla s) {

		// promediar los elementos de un arreglo
		String A = ((String[]) s.dato)[0];
		String B = ((String[]) s.dato)[1];
		logger.info("Multiplicando [" + A + "] * [" + B + "]");

		logger.info("Resultado [" + multi(A, B) + "]");

	}

	private String multi(String a, String b) {
		
	 return new BigInteger(a).multiply(new BigInteger(b)).toString();
	 
		// String Al= a.substring(a.length()/2);
		// String Bl= b.substring(b.length()/2);
		// String Ar= a.substring(a.length()/2,a.length());
		// String Br= b.substring(b.length()/2,a.length());
		//
		// String X1 = multi(Al,Bl);
		// String X2 = multi(Ar,Br);
		// String X3 = sub(Al,Ar);
		/*
		 * 
		 * a(right ) a(left) b(r) b (l) if (n = 1) then return a(1)*b(1)
		 * 
		 * 
		 * x1 = multi ( al, bl) x2 = multi (ar,br) x3 = multi (sum(al,ar), sum
		 * (bl,br)) x3 = sub(sub(x3,x1),x2) x1 = x1 << n x3 = x3 << n/2 c = sum
		 * (sum(x1,x3),x2)
		 * 
		 * return c
		 */

	 
	}

	@Override
	public List<Semilla> obtenSemillas() {
		List<Semilla> s = new ArrayList<Semilla>();
		// generacion de 20 semillas simples
		int total = 500;
		for (int i = 1; i < total; i++) {
			s.add(generaSemilla(i * 40));
		}
		return s;
	}

	@Override
	public Semilla generaSemilla(int n) {
		// n sera el tamaño de los enteros
		// seran dos arreglos de strings A y B de tamaño N
		Random rand = new Random();
		int max = 9;
		int min = 0;
		StringBuilder A = new StringBuilder();
		StringBuilder B = new StringBuilder();
		for (int i = 0; i < n; i++) {
			A.append(rand.nextInt((max - min) + 1) + min);
			B.append(rand.nextInt((max - min) + 1) + min);
		}
		return new Semilla(n, new String[] { A.toString(), B.toString() });
	}
}
