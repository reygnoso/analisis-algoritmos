package metodos;

import java.util.List;

interface MetodoPrueba {
	String nombre = null;

	List<Semilla> obtenSemillas();

	Semilla generaSemilla(int n);

	void Cuerpo(Semilla s);
}
