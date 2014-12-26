package app;

/**
 * <p>Subclass of class Ship. Represents a Ship 4-spaces long.</p>
 * <p>It is a part of the Battleship game application, which is run by the class <code>BattleshipGame</code>.</p>
 * <p>Its toString method returns "Battleship".</p>
 * 
 * @author Liran_and_Di
 * @version 1.0
 * @since 17th December 2014
 */
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