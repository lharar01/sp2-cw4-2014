package app;

public class EmptySea extends Ship {
	public EmptySea() {
		length = 4;
		for(int i=0; i<hit.length; i++) {
			hit[i] = false;
		}
	}
	
	/* This method overrides shootAt(int
	row, int column) that is inherited from Ship, and always returns false to indicate that nothing was hit. */
	@Override
	public boolean shootAt(int row, int column) {
		hit[0] = true;
		return false;
	}
	
	/* This method overrides isSunk() that is inherited from
	Ship, and always returns false to indicate that you didn't sink anything. */
	@Override
	public boolean isSunk() {
		return false;
	}
	
//	@Override
//	public String getShipType() {
//		return "battleship";
//	}
	
	@Override
	public String toString() {
		/* From 4.4: "Returns a single-character String to use in the
Ocean's print method (see below)." */
		return hit[0] ? "-" : ".";
	}
}