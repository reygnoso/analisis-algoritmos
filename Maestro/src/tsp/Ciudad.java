package tsp;

public class Ciudad {
	String nombre;
	int x;
	int y;
 
	public Ciudad(int x, int y, String name) {
		this.nombre = name;
		this.x = x;
		this.y = y;
	}
 
	public int getX() {
		return this.x;
	}
 
	public int getY() {
		return this.y;
	}
 
	public double distanciaHacia(Ciudad ciudad) {
		int xDistance = Math.abs(getX() - ciudad.getX());
		int yDistance = Math.abs(getY() - ciudad.getY());
		double distance = Math.sqrt((xDistance * xDistance) + (yDistance * yDistance)); 
		return distance;
	}

	@Override
	public String toString() {
		return nombre + " [" + getX() + ", " + getY() + "]";
	}
}