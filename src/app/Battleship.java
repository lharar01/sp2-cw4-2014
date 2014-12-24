package app;

public class Battleship extends Ship {
	
	public Battleship() {
		length = 4;
		for(int i=0; i<hit.length; i++) {
			hit[i] = false;
		}
	}
	
	@Override
	public String getShipType() {
		return "Battleship";
	}
	
}