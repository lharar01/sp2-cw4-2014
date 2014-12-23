package app;

public class Cruiser extends Ship {

	public Cruiser() {
		length = 3;
		for(int i=0; i<hit.length; i++) {
			hit[i] = false;
		}
	}
	
	@Override
	public String getShipType() {
		return "cruiser";
	}
	
}