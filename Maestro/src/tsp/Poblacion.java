package tsp;

public class Poblacion {
 
    Ruta[] rutas;
 
    public Poblacion(int tam, boolean init) {
        rutas = new Ruta[tam]; 
        if (init) { 
            for (int i = 0; i < populationSize(); i++) {
                Ruta nuevaRuta = new Ruta();
                nuevaRuta.generaIndividuo();
                guardaRuta(i, nuevaRuta);
            }
        }
    }
     
    public void guardaRuta(int index, Ruta tour) {
        rutas[index] = tour;
    }
     
    public Ruta obtenRuta(int index) {
        return rutas[index];
    }
 
    public Ruta obtenMejor() {
        Ruta fittest = rutas[0]; 
        for (int i = 1; i < populationSize(); i++) {
            if (fittest.getFitness() <= obtenRuta(i).getFitness()) {
                fittest = obtenRuta(i);
            }
        }
        return fittest;
    }
 
    public int populationSize() {
        return rutas.length;
    }
}