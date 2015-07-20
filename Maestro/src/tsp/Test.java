package tsp;

import java.io.IOException;

public class Test {

	public static void main(String[] args) throws IOException {

		// test
		Ciudad city = new Ciudad(60, 200, "Ciudad_00");
		AdminRutas.agregaCiudad(city);
		Ciudad city2 = new Ciudad(180, 200, "Ciudad_01");
		AdminRutas.agregaCiudad(city2);
		Ciudad city3 = new Ciudad(80, 180, "Ciudad_02");
		AdminRutas.agregaCiudad(city3);
		Ciudad city4 = new Ciudad(140, 180, "Ciudad_03");
		AdminRutas.agregaCiudad(city4);
		Ciudad city5 = new Ciudad(20, 160, "Ciudad_04");
		AdminRutas.agregaCiudad(city5);
		Ciudad city6 = new Ciudad(100, 160, "Ciudad_05");
		AdminRutas.agregaCiudad(city6);
		Ciudad city7 = new Ciudad(200, 160, "Ciudad_06");
		AdminRutas.agregaCiudad(city7);
		Ciudad city8 = new Ciudad(140, 140, "Ciudad_07");
		AdminRutas.agregaCiudad(city8);
		Ciudad city9 = new Ciudad(40, 120, "Ciudad_08");
		AdminRutas.agregaCiudad(city9);
		Ciudad city10 = new Ciudad(100, 120, "Ciudad_09");
		 AdminRutas.agregaCiudad(city10);
		 Ciudad city11 = new Ciudad(180, 100,"Ciudad_10");
		 AdminRutas.agregaCiudad(city11);
		 Ciudad city12 = new Ciudad(60, 80,"Ciudad_11");
		 AdminRutas.agregaCiudad(city12);
		 Ciudad city13 = new Ciudad(120, 80,"Ciudad_12");
		 AdminRutas.agregaCiudad(city13);
		 Ciudad city14 = new Ciudad(180, 60,"Ciudad_13");
		 AdminRutas.agregaCiudad(city14);
		 Ciudad city15 = new Ciudad(20, 40,"Ciudad_14");
		 AdminRutas.agregaCiudad(city15);
		 Ciudad city16 = new Ciudad(100, 40,"Ciudad_15");
		 AdminRutas.agregaCiudad(city16);
		 Ciudad city17 = new Ciudad(200, 40,"Ciudad_16");
		 AdminRutas.agregaCiudad(city17);
		 Ciudad city18 = new Ciudad(20, 20,"Ciudad_17");
		 AdminRutas.agregaCiudad(city18);
		 Ciudad city19 = new Ciudad(60, 20,"Ciudad_18");
		 AdminRutas.agregaCiudad(city19);
		 Ciudad city20 = new Ciudad(160, 20,"Ciudad_19");
		 AdminRutas.agregaCiudad(city20);
 
		Poblacion pop = new Poblacion(50, true);
		System.out.println("Distancia inicial: " + pop.obtenMejor().distanciaTotal());  
		pop = Aproximacion.sigPoblacion(pop);
		//100 mutaciones
		for (int i = 0; i < 100; i++) {
			pop = Aproximacion.sigPoblacion(pop);
		}  
		System.out.println("Distancia Final  : " + pop.obtenMejor().distanciaTotal()); 
		System.out.println("Ruta:");
		System.out.println(pop.obtenMejor());
	}
}