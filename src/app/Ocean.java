package app;

import java.util.Random;

/**
 * <p>This class represents the ocean in the game Battleship. It is a part of the Battleship game application,
 * which is run by the class <code>BattleshipGame</code>.</p>
 * <p>This class regularly interacts with class <code>Ship</code>.</p>
 * <p>To get started, after instantiation of an object of this class, the instance method
 * <code>placeAllShipsRandomly</code></p> is invoked in order to place 10 ships in this Ocean: 1 Battleship,
 * 2 Cruisers, 3 Destroyers and 4 Submarines. After that, the Ocean is ready and the game can start.</p>
 * 
 * @author Liran_and_Di
 * @version 1.0
 * @since 17th December 2014
 */
public class Ocean {

	private Ship[][] ships = new Ship[10][10];
	private int shotsFired;
	private int hitCount;
	private int shipsSunk;
	
	public Ocean() {
		initOcean();
		shotsFired = 0;
		hitCount = 0;
		shipsSunk = 0;
	}
	
	private void initOcean() {
		for(int row=0; row<ships.length; row++) {
			for(int col=0; col<ships[row].length; col++) {
				ships[row][col] = new EmptySea();
			}
		}
	}
	
	/* Returns the number of shots fired (in this game). */
	public int getShotsFired() {
		return shotsFired;
	}
	
	/* Returns the number of hits recorded (in this game). All hits are counted, not just the first time a given square is hit. */
	public int getHitCount() {
		return hitCount;
	}
	
	/* Returns the number of ships sunk (in this game). */
	public int getShipsSunk() {
		return shipsSunk;
	}
	
	/* Returns true if all ships have been sunk, otherwise false. */
	public boolean isGameOver() {
		for(int row=0; row<ships.length; row++) {
			for(int col=0; col<ships[row].length; col++) {
				if(isOccupied(row, col) && !ships[row][col].isSunk()) {
					return false;
				}
			}
		}
		return true;
	}
	
	/* For unit testing only */
	public Ship[][] getShipArray() {
		return ships;
	}
	
	public Ship getShipInLocation(int row, int column) {
		if(row >= 0 && row <= 9 && column >= 0 && column <= 9) {
			return ships[row][column];
		}
		return new Ship();
	}
	
	public boolean setShipInLocation(Ship ship, int row, int column) {
		if(row >= 0 && row <= 9 && column >= 0 && column <= 9) {
			ships[row][column] = ship;
			return true;
		}
		return false;
	}
	
	/* Place all ten ships randomly on the (initially empty)
	ocean. Place larger ships before smaller ones, or you may end up with no legal
	place to put a large ship. You will want to use the Random class in the java.util
	package, so look that up in the Java API. */
	public void placeAllShipsRandomly(boolean testing) {
		Random rand;
		int counter;
		int row;
		int col;
		boolean horizontal;
		Ship[] shipArray = generateShipFleetArray();
		
		for(int i=0; i<shipArray.length; i++) {
			rand = new Random();
			counter = 0;
			
			do {
				counter++;
				row = rand.nextInt(10);
				col = rand.nextInt(10);
				horizontal = rand.nextInt(2) == 0 ? false : true;
			}
			while(!shipArray[i].okToPlaceShipAt(row, col, horizontal, this) && counter <= 1000);

			// This prevents an infinite loop. (Although after through testing this method doesn't seem to take longer
			// than 50 loops to find a legal random place for any ship)
			if(counter == 1000 && !shipArray[i].okToPlaceShipAt(row, col, horizontal, this)) {
				System.out.println("Error placing ship in ocean. Please restart the game.");
				System.exit(1);
			}
			else {
				shipArray[i].placeShipAt(row, col, horizontal, this);
				if(testing) {
					System.out.println("For testing: " + shipArray[i].getShipType() + " placed " + (shipArray[i].isHorizontal() ? "horizontally" : "vertically") + " in " + shipArray[i].getBowRow() + ", " + shipArray[i].getBowColumn());
				}
			}
		}
	}
	
	private Ship[] generateShipFleetArray() {
		Ship[] shipArray = new Ship[10];
		shipArray[0] = new Battleship();
		shipArray[1] = new Cruiser();
		shipArray[2] = new Cruiser();
		for(int i=3; i<6; i++) {
			shipArray[i] = new Destroyer();
		}
		for(int i=6; i<10; i++) {
			shipArray[i] = new Submarine();
		}
		return shipArray;
	}
	
	/* Returns true if the given location contains a ship, false if it does not. */
	public boolean isOccupied(int row, int column) {
		return !ships[row][column].getShipType().equals("unset");
	}
	
	/* Returns true if the given location contains a real ship, still afloat, (not an EmptySea), false if it does not.
	 * In addition, this method updates the number of shots that have been fired, and the number of hits. */
	// I made this method call the relevant ship's shootAt method, because:
	// - this Ocean's method needs to afterwards increment the "shipsSunk" counter
	// - It doesn't make much sense for the BattleshipGame class to interact with the Ship class, nor does it make sense for it to
	// have to call 2 different shootAt methods.
	public boolean shootAt(int row, int column) {
		shotsFired++;
		if(isOccupied(row, column) && !ships[row][column].isSunk()) {
			hitCount++;
			//return true;
		}
		boolean shootResult = ships[row][column].shootAt(row, column);
		if(ships[row][column].isSunk()) {
			shipsSunk++;
		}
		return shootResult;
	}
	
	/* Prints the ocean. To aid the user, row numbers should be displayed along
	the left edge of the array, and column numbers should be displayed along the top.
	Numbers should be 0 to 9, not 1 to 10. The top left corner square should be 0,
	0. 
	
	Use:
	'S' to indicate a location that you have fired upon and hit a (real) ship,
	'-' to indicate a location that you have fired upon and found nothing there,
	'x' to indication location containing a sunken ship, and
	'.' to indicate a location that you have never fired upon.
	
	This is the only method in the Ocean class that does any input/output, and it is
	never called from within the Ocean class (except possibly during debugging), only
	from the BattleshipGame class. */
	public void print() {
		System.out.println("The ocean:");
		System.out.print(" ");
		for(int i=0; i<ships[0].length; i++) {
			System.out.print(" " + i);
		}
		System.out.println("");
		String symbol = " ";
		for(int row=0; row<ships.length; row++) {
			System.out.print(row);
			for(int col=0; col<ships[row].length; col++) {
				if(ships[row][col].toString().equals("S")) {
					if(ships[row][col].isSunk()) {
						symbol = "x";
					}
					else {
						if(ships[row][col].isPlaceHit(row, col)) {
							symbol = "S";
						}
						else {
							symbol = ".";
						}
					}
				}
				else {
					symbol = ships[row][col].toString();
				}
				System.out.print(" " + symbol);
			}
			System.out.println("");
		}
		System.out.println();
		System.out.println("Legend: ");
		System.out.println("'S' indicates a location that you have fired upon and hit a ship");
		System.out.println("'-' indicates a location that you have fired upon and found nothing there");
		System.out.println("'x' indicates location containing a sunken ship");
		System.out.println("'.' indicates a location that you have never fired upon");
		
	}
	
}