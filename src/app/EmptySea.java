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
	
	/**
	 * <p>Sets the <code>length</code> to 1.</p>
	 * <p>Sets all elements in the <code>hit</code> array to <code>false</code>.</p>
	 */
	public EmptySea() {
		length = 1;
		for(int i=0; i<hit.length; i++) {
			hit[i] = false;
		}
	}
	
	/** <p>This method overrides <code>Ship</code>'s <code>shootAt</code> method, and always returns <code>false</code>
	 * to indicate that nothing was hit, as this is location contains empty sea.</p>
	 * <p>Additionally it marks <code>hit[0]</code> as <code>true</code>, to indicate that this location has been
	 * shot at.</p>
	 * 
	 * @return <code>false</code>
	 */
	@Override
	public boolean shootAt(int row, int column) {
		hit[0] = true;
		return false;
	}
	
	/** This method overrides <code>Ship</code>'s <code>isSunk</code> method, and always returns <code>false</code>
	 * to indicate that no (real) Ships have been sunk.
	 * 
	 * @return <code>false</code>
	*/
	@Override
	public boolean isSunk() {
		return false;
	}
	
	/**
	 * <p>Returns "-" if this <code>Ship</code> has been fired upon, and "." if it has not.</p>
	 * <p>This symbol is then used in the <code>Ocean</code>'s <code>print</code> method to denote the state of the
	 * <code>Ship</code>.</p>
	 * 
	 * @return hit[0]
	 */
	@Override
	public String toString() {
		/* From 4.4: "Returns a single-character String to use in the
Ocean's print method (see below)." */
		return hit[0] ? "-" : ".";
		//return "E";
	}
	
}