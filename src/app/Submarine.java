package app;

public class Submarine extends Ship {
	public Submarine() {
		length = 4;
		for(int i=0; i<hit.length; i++) {
			hit[i] = false;
		}
	}
	
	@Override
	public String getShipType() {
		return "submarine";
	}
	
	@Override
	public String toString() {
		/* From 4.4: "Returns a single-character String to use in the
Ocean's print method (see below)." */
		return "CHANGE ME!";
	}
}