package metodos;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import main.Logger;
import dominio.MetodoPrueba;
import dominio.Semilla;

public class Practica6_MetodoLCS implements MetodoPrueba {
	private static final Logger logger = new Logger(Practica6_MetodoLCS.class);

	public void Cuerpo(Semilla s) {
		String[] p = (String[]) s.dato;
		logger.info(lcs(p[0], p[1]));
	}

	public static String lcs(String a, String b) {
		int[][] lengths = new int[a.length() + 1][b.length() + 1];
		for (int i = 0; i < a.length(); i++)
			for (int j = 0; j < b.length(); j++)
				if (a.charAt(i) == b.charAt(j))
					lengths[i + 1][j + 1] = lengths[i][j] + 1;
				else
					lengths[i + 1][j + 1] = Math.max(lengths[i + 1][j], lengths[i][j + 1]);

		StringBuffer sb = new StringBuffer();
		for (int x = a.length(), y = b.length(); x != 0 && y != 0;) {
			if (lengths[x][y] == lengths[x - 1][y])
				x--;
			else if (lengths[x][y] == lengths[x][y - 1])
				y--;
			else {
				assert a.charAt(x - 1) == b.charAt(y - 1);
				sb.append(a.charAt(x - 1));
				x--;
				y--;
			}
		}
		return sb.reverse().toString();
	}

	@Override
	public List<Semilla> obtenSemillas() {
		List<Semilla> s = new ArrayList<Semilla>();
		int total = 3000;
		for (int i = 1; i < total; i++) {
			s.add(generaSemilla(i * 2));
		}
		return s;
	}

	@Override
	public Semilla generaSemilla(int n) {
		String[] e = new String[2];
		for (int i = 0; i < e.length; i++) {
			e[0] = gen(n);
			e[1] = gen(n);
		}
		return new Semilla(n, e);
	}

	public static String gen(int length) {
		Random rng = new Random();
		String characters = "abcdefghijklmnñopqrstuvwxyz";
		char[] text = new char[length];
		for (int i = 0; i < length; i++) {
			text[i] = characters.charAt(rng.nextInt(characters.length()));
		}
		return new String(text);
	}

}
