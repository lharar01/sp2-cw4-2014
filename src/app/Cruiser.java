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
	
	@Override
	public String toString() {
		/* From 4.4: "Returns a single-character String to use in the
Ocean's print method (see below)." */
		return "CHANGE ME!";
	}
}