package app;

public class Destroyer extends Ship {
	
	public Destroyer() {
		length = 2;
		for(int i=0; i<hit.length; i++) {
			hit[i] = false;
		}
	}
	
	@Override
	public String getShipType() {
		return "destroyer";
	}
	
}