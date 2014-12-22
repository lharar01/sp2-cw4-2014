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
		return "battleship";
	}
	
	@Override
	public String toString() {
		/* From 4.4: "Returns a single-character String to use in the
Ocean's print method (see below)." */
		if(isSunk()) {
			return "x";
		}
		return "S";
	}
}