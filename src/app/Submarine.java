package app;

/**
 * <p>Subclass of class Ship. Represents a Ship 1-space long.</p>
 * <p>It is a part of the Battleship game application, which is run by the class <code>BattleshipGame</code>.</p>
 * <p>Its toString method returns "Submarine".</p>
 * 
 * @author Liran_and_Di
 * @version 1.0
 * @since 17th December 2014
 */
public class Submarine extends Ship {
	
	public Submarine() {
		length = 1;
		for(int i=0; i<hit.length; i++) {
			hit[i] = false;
		}
	}
	
	@Override
	public String getShipType() {
		return "Submarine";
	}
	
}