package app;

/**
 * <p>Subclass of class Ship. Represents a place in the ocean in which there is no Ship.</p>
 * <p>It is a part of the Battleship game application, which is run by the class <code>BattleshipGame</code>.</p>
 * <p>The length of this "Ship" is 1, it returns false when shot at and when inquired if it is sunk, and
 * its toString method returns "-" if it has been shot at and "." if it hasn't.</p>
 * 
 * @author Liran_and_Di
 * @version 1.0
 * @since 17th December 2014
 */
public class EmptySea extends Ship {

	public EmptySea() {
		length = 1;
		for(int i=0; i<hit.length; i++) {
			hit[i] = false;
		}
	}
	
	/* This method overrides shootAt(int row, int column) that is inherited from Ship, and always returns false to indicate that nothing was hit. */
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
	
	/*public boolean isHit() {
		return hit[0];
	}*/
	
//	@Override
//	public String getShipType() {
//		return "battleship";
//	}
	
	@Override
	public String toString() {
		/* From 4.4: "Returns a single-character String to use in the
Ocean's print method (see below)." */
		return hit[0] ? "-" : ".";
		//return "E";
	}
	
}