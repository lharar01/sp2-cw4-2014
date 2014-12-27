package app;

import java.util.Random;

/**
 * <p>This class represents the ocean in the game Battleship. It is a part of the Battleship game application,
 * which is run by the class <code>BattleshipGame</code>.</p>
 * <p>This class regularly interacts with class <code>Ship</code>.</p>
 * <p>To get started, after instantiation of an object of this class, the instance method
 * <code>placeAllShipsRandomly</code> is invoked in order to place 10 ships in this Ocean: 1 Battleship,
 * 2 Cruisers, 3 Destroyers and 4 Submarines. After that, the Ocean is ready and the game can start.</p>
 * 
 * @author Liran Harary (lharar01, 12837230)
 * @version 1.0
 * @since 17th December 2014
 */
public class Ocean {
	
	/** 2d array for this Ocean's Ships */
	private Ship[][] ships = new Ship[10][10];
	
	/** The total number of shots fired by the user */
	private int shotsFired;
	
	/** The total number of hits amongst the user's shots */
	private int hitCount;
	
	/** The total number of ships sunk */
	private int shipsSunk;
	
	/**
	 * <ul>
	 * <li>Invokes method {@link #initOcean()} to set all Ships in {@link #ships} to <code>EmptySea</code> objects.</li>
	 * <li>Initialises {@link #shotsFired}, {@link #hitCount} and {@link #shipsSunk} to 0.</li>
	 * </ul>
	 */
	public Ocean() {
		initOcean();
		shotsFired = 0;
		hitCount = 0;
		shipsSunk = 0;
	}
	
	/**
	 * <p>Sets all Ships in {@link #ships} to <code>EmptySea</code> objects.</p>
	 * <p>Invoked by this class' constructor.</p>
	 */
	private void initOcean() {
		for(int row=0; row<ships.length; row++) {
			for(int col=0; col<ships[row].length; col++) {
				ships[row][col] = new EmptySea();
			}
		}
	}
	
	/**
	 * Returns the total number of shots fired by the user.
	 * 
	 * @return shotsFired
	 */
	public int getShotsFired() {
		return shotsFired;
	}
	
	/**
	 * <p>Returns the number of hits amongst the user's shots.</p>
	 * <p>All hits are counted, not just the first time a given square is hit.</p>
	 * 
	 * @return hitCount
	 */
	public int getHitCount() {
		return hitCount;
	}
	
	/**
	 * Returns the total number of ships sunk
	 * 
	 * @return shipsSunk
	 */
	public int getShipsSunk() {
		return shipsSunk;
	}
	
	/**
	 * Returns whether or not all the ships have been sunk.
	 * 
	 * @return <code>true</code> if all ships have been sunk, and <code>false</code> otherwise.
	 */
	public boolean isGameOver() {
		// For all elements in the ships array, if any element is occupied by a real ship and it is
		// not sunk, returns false. 
		for(int row=0; row<ships.length; row++) {
			for(int col=0; col<ships[row].length; col++) {
				if(isOccupied(row, col) && !ships[row][col].isSunk()) {
					return false;
				}
			}
		}
		// Otherwise returns true.
		return true;
	}
	
	/**
	 * For unit testing only. Returns the {@link #ships} array.
	 * 
	 * @return ships
	 */
	public Ship[][] getShipArray() {
		return ships;
	}
	
	/**
	 * <p>Returns the <code>Ship</code> in the location sent as arguments (<code>row</code> and <code>column</code>)</p>
	 * <p>If <code>row</code> and <code>column</code> are outside </p> the bounds of the {@link #ships} array,
	 * returns a new <code>Ship</code> to indicate something went wrong.
	 * 
	 * @param row     Required <code>Ship</code>'s row
	 * @param column  Required <code>Ship</code>'s column
	 * @return <code>Ship</code> in required location, or new <code>Ship</code> if location is out of bounds.  
	 */
	public Ship getShipInLocation(int row, int column) {
		if(row >= 0 && row <= 9 && column >= 0 && column <= 9) {
			return ships[row][column];
		}
		return new Ship();
	}
	
	/**
	 * <p>Sets the <code>Ship</code> sent as an argument in the <code>row</code> and <code>column</code>
	 * sent as arguments; only if the row and column are not outside the bounds of the {@link #ships} array.</p>
	 * <p>Returns whether or not the operation was legal and performed.</p>
	 * 
	 * @param ship    <code>Ship</code> to set in location.
	 * @param row     <code>row</code> to set the <code>Ship</code> in.
	 * @param column  <code>column</code> to set the <code>Ship</code> in.
	 * @return whether or not the operation was legal and performed.
	 */
	public boolean setShipInLocation(Ship ship, int row, int column) {
		if(row >= 0 && row <= 9 && column >= 0 && column <= 9) {
			ships[row][column] = ship;
			return true;
		}
		return false;
	}
	
	/**
	 * Randomly places the following <code>Ships</code> (in the following order) in the
	 * ocean (in the {@link #ships} array):
	 * <ul>
	 * <li>1 <code> Battleship</code></li>
	 * <li>2 <code>Cruiser</code>s</li>
	 * <li>3 <code>Destroyer</code>s</li>
	 * <li>4 <code>Submarine</code>s</li>
	 * </ul>
	 * 
	 * @param testing  Whether or not to turn on testing mode. Testing mode prints the <code>Ships</code>
	 * that have been placed, their locations and their orientation.
	 */
	public void placeAllShipsRandomly(boolean testing) {
		Random rand;
		int counter;
		int row;
		int col;
		boolean horizontal;
		// Places 10 Ships into the shipArray: 1 Battleship, 2 Cruisers, 3 Destroyers and 4 Submarines.
		Ship[] shipArray = generateShipFleetArray();
		
		// For all Ships in the shipArray
		for(int i=0; i<shipArray.length; i++) {
			rand = new Random();
			counter = 0;
			
			// Generates random row, column and orientation - while these are illegal according to Ship's
			// method okToPlaceShipAt.
			do {
				counter++;
				row = rand.nextInt(10);
				col = rand.nextInt(10);
				horizontal = rand.nextInt(2) == 0 ? false : true;
			}
			while(!shipArray[i].okToPlaceShipAt(row, col, horizontal, this) && counter <= 1000);

			// If counter has reached 1000 tries of finding a legal location and orientation for the current Ship
			// and the 1000th try is still illegal:
			// (This prevents an infinite loop. [Although after through testing this method it doesn't seem
			// to take longer than 50 loops to find a legal random place for any Ship])
			if(counter == 1000 && !shipArray[i].okToPlaceShipAt(row, col, horizontal, this)) {
				System.out.println("Error placing ship in ocean. Please restart the game.");
				System.exit(1);
			}
			// Otherwise, uses Ship's placeShipAt method to place the current Ship in the found legal location
			// and orientation. If testing is turned on, displays the Ship that have been placed, its location
			// and its orientation.
			else {
				shipArray[i].placeShipAt(row, col, horizontal, this);
				if(testing) {
					System.out.println("For testing: " + shipArray[i].getShipType() + " placed " + (shipArray[i].isHorizontal() ? "horizontally" : "vertically") + " in " + shipArray[i].getBowRow() + ", " + shipArray[i].getBowColumn());
				}
			}
		} // end for
	}
	
	/**
	 * <p>Places 10 <code>Ships</code> into the <code>shipArray</code>: 1 <code>Battleship</code>, 2 <code>Cruisers</code>,
	 * 3 <code>Destroyers</code> and 4 <code>Submarines</code>.</p>
	 * <p>Then returns the array.</p>
	 * <p>Used by {@link #placeAllShipsRandomly}</p>
	 * 
	 * @return shipArray
	 */
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
	
	/**
	 * Returns whether or not the location received as arguments contains a <code>Ship</code>.
	 * 
	 * @param row     Row to check if is occupied
	 * @param column  Column to check if is occupied
	 * @return <code>true</code> if the given location contains a ship, <code>false</code> if it does not.
	 */
	public boolean isOccupied(int row, int column) {
		// Returns if the ship type at the given row and column is "unset", which indicates that it is either a general
		// Ship (not a subclass of it) or an EmptySea (since this subclass does not override Ship's getShipType method.
		return !ships[row][column].getShipType().equals("unset");
	}
	
	/**
	 * <p>Shoots at a given row and column in the ocean. This entails:</p>
	 * <ul>
	 * <li>Incrementing {@link #shotsFired} by 1.</li>
	 * <li>Incrementing {@link #hitCount} by 1 if the given location {@link #isOccupied(int, int)} and the
	 * <code>Ship</code> at the given location is not sunk.</li>
	 * <li>Invoking the method shootAt of the <code>Ship</code> at the given location and storing the result in
	 * <code>shootResult</code>.</li>
	 * <li>Incrementing {@link #shipsSunk} by 1 if the <code>Ship</code> at the given location is not sunk after
	 * shooting it.</li>
	 * <li>Returning the <code>shootResult</code>.</li>
	 * </ul>
	 * 
	 * <p>This method has been made to call the relevant <code>Ship</code>'s <code>shootAt</code> method, because:</p>
	 * <ul>
	 * <li>For this Ocean's {@link #shootAt(int, int)} method to know if to increment the {@link #shipsSunk} or not,
	 * it needs to check if the relevant <code>Ship</code> is sunk <strong>after</strong> its <code>shootAt</code>
	 *  method has been invoked.</li>
	 * <li>It doesn't make much sense for the BattleshipGame class to interact with the Ship class, nor does it make sense
	 * for it to have to call 2 different shootAt methods to achieve the one goal of shooting at a <code>Ship</code></li>
	 * </ul>
	 * 
	 * @param row     Row to shoot at in the ocean. 
	 * @param column  Column  to shoot at in the ocean.
	 * @return <code>true</code> if the shot hit a part of the relevant Ship AND the Ship is not sunk.
	 */
	public boolean shootAt(int row, int column) {
		shotsFired++;
		if(isOccupied(row, column) && !ships[row][column].isSunk()) {
			hitCount++;
		}
		boolean shootResult = ships[row][column].shootAt(row, column);
		if(ships[row][column].isSunk()) {
			shipsSunk++;
		}
		return shootResult;
	}
	
	/**
	 * <p>Prints the ocean's map with row and column numbers to aid the user.</p>
	 * <p>The legend of symbols for it is as follows:</p>
	 * <ul>
	 * <li>'S' indicates a location that has been fired upon and contains a hit but not sunk <code>Ship</code>.</li>
	 * <li>'-' indicates a location that has been fired upon and does not contain a real <code>Ship</code>
	 * (i.e. is EmptySea).</li>
	 * <li>'x' indicates location containing a sunken <code>Ship</code>.</li>
	 * <li>'.' indicates a location that has not been fired upon.</li>
	 * </ul>
	 * <p>Utilises {@link #printLegend()} to print the legend of symbols for the ocean's map.</p>
	 */
	public void print() {
		System.out.println("The ocean:");
		System.out.print(" ");
		// Print column numbers (0-9)
		for(int colNumber=0; colNumber<ships[0].length; colNumber++) {
			System.out.print(" " + colNumber);
		}
		System.out.println("");
		// Initialise the symbol to be printed
		String symbol = " ";
		// For all Ships in the ships array:
		for(int row=0; row<ships.length; row++) {
			// Print the row number at the start of the row
			System.out.print(row);
			for(int col=0; col<ships[row].length; col++) {
				// If the current Ship's toString method returns S, it is a real Ship (not EmptySea); so determine
				// if it is: sunk, just hit or never shot at. Store the correct symbol in the symbol variable.
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
				} // end if
				// Otherwise, the Ship is EmptySea, so just use its toString's return value as the symbol, as that
				// method returns "-" if it has been shot at, and "." if it has not.
				else {
					symbol = ships[row][col].toString();
				}
				System.out.print(" " + symbol);
			} // end for
			System.out.println("");
		}// end for
		// Print the legend.
		printLegend();
	}
	
	/**
	 * <p>Print the legend of symbols for the ocean's map.</p>
	 * <p>This method is currently only used by the {@link #print()} method.</p>
	 */
	public void printLegend() {
		System.out.println("\nLegend: ");
		System.out.println("'S' indicates a location that you have fired upon and hit a ship");
		System.out.println("'-' indicates a location that you have fired upon and found nothing there");
		System.out.println("'x' indicates location containing a sunken ship");
		System.out.println("'.' indicates a location that you have never fired upon");
	}
}