package dominio;

import java.util.List;

public interface MetodoPrueba {
	String nombre = null;

	List<Semilla> obtenSemillas();

	Semilla generaSemilla(int n);

	void Cuerpo(Semilla s);
}
