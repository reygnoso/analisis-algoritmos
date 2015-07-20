package tsp;

public class Aproximacion { 
    private static final double indiceMutacion = 0.015; //mas grande == mayor mutacion
    private static final int tamT = 5;
    private static final boolean elitism = true;
 
    public static Poblacion sigPoblacion(Poblacion pop) {
        Poblacion nuevaPoblacion = new Poblacion(pop.populationSize(), false);
 
        int elitismOffset = 0;
        if (elitism) {
            nuevaPoblacion.guardaRuta(0, pop.obtenMejor());
            elitismOffset = 1;
        }
 
        for (int i = elitismOffset; i < nuevaPoblacion.populationSize(); i++) { 
            Ruta parent1 = mejorCandidato(pop);
            Ruta parent2 = mejorCandidato(pop); 
            Ruta child = cruza(parent1, parent2); 
            nuevaPoblacion.guardaRuta(i, child);
        }
 
        for (int i = elitismOffset; i < nuevaPoblacion.populationSize(); i++) {
            mutar(nuevaPoblacion.obtenRuta(i));
        }

        return nuevaPoblacion;
    }
 
    public static Ruta cruza(Ruta ruta1, Ruta ruta2) { 
        Ruta nuevaRuta = new Ruta(); 
        int startPos = (int) (Math.random() * ruta1.totalCiudades());
        int endPos = (int) (Math.random() * ruta1.totalCiudades());
 
        for (int i = 0; i < nuevaRuta.totalCiudades(); i++) { 
            if (startPos < endPos && i > startPos && i < endPos) {
                nuevaRuta.posicionaCiudad(i, ruta1.obtenCiudad(i));
            }  
            else if (startPos > endPos) {
                if (!(i < startPos && i > endPos)) {
                    nuevaRuta.posicionaCiudad(i, ruta1.obtenCiudad(i));
                }
            }
        }
 
        for (int i = 0; i < ruta2.totalCiudades(); i++) { 
            if (!nuevaRuta.containsCity(ruta2.obtenCiudad(i))) { 
                for (int ii = 0; ii < nuevaRuta.totalCiudades(); ii++) { 
                    if (nuevaRuta.obtenCiudad(ii) == null) {
                        nuevaRuta.posicionaCiudad(ii, ruta2.obtenCiudad(i));
                        break;
                    }
                }
            }
        }
        return nuevaRuta;
    }
 
    private static void mutar(Ruta tour) { 
        for(int tourPos1=0; tourPos1 < tour.totalCiudades(); tourPos1++){ 
            if(Math.random() < indiceMutacion){ 
                int tourPos2 = (int) (tour.totalCiudades() * Math.random());  
                Ciudad city1 = tour.obtenCiudad(tourPos1);
                Ciudad city2 = tour.obtenCiudad(tourPos2);
 
                tour.posicionaCiudad(tourPos2, city1);
                tour.posicionaCiudad(tourPos1, city2);
            }
        }
    }
 
    private static Ruta mejorCandidato(Poblacion pop) { 
        Poblacion prueba = new Poblacion(tamT, false); 
        for (int i = 0; i < tamT; i++) {
            int randomId = (int) (Math.random() * pop.populationSize());
            prueba.guardaRuta(i, pop.obtenRuta(randomId));
        } 
        Ruta mejor = prueba.obtenMejor();
        return mejor;
    }
}