package app;

/**
 * <p>Subclass of class Ship. Represents a Ship 3-spaces long.</p>
 * <p>It is a part of the Battleship game application, which is run by the class <code>BattleshipGame</code>.</p>
 * <p>Its toString method returns "Cruiser".</p>
 * 
 * @author Liran Harary (lharar01, 12837230)
 * @version 1.0
 * @since 17th December 2014
 */
public class Cruiser extends Ship {
	
	/**
	 * <p>Sets the <code>length</code> to 3.</p>
	 * <p>Sets all elements in the <code>hit</code> array to <code>false</code>.</p>
	 */
	public Cruiser() {
		length = 3;
		for(int i=0; i<hit.length; i++) {
			hit[i] = false;
		}
	}
	
	/**
	 * Returns this <code>Ship's</code> type.
	 * 
	 * @return "Cruiser"
	 */
	@Override
	public String getShipType() {
		return "Cruiser";
	}
	
}