package app;

/**
 * <p>This class represents a ship in the game Battleship. It is a part of the Battleship game application,
 * which is run by the class <code>BattleshipGame</code>.</p>
 * <p>This class regularly interacts with class <code>Ocean</code>.</p>
 * <p>The subclasses of this class are: <code>EmptySea</code>, <code>Battleship</code>,
 * <code>Cruiser</code>, <code>Destroyer</code> and <code>Submarine</code>.</p>
 * 
 * @author Liran_and_Di
 * @version 1.0
 * @since 17th December 2014
 */
public class Ship {

	/**	This Ship's bow (ship front) row */
	protected int bowRow = -1;
	
	/**	This Ship's bow (ship front) column */
	protected int bowColumn = -1;
	
	/**	This Ship's length */
	protected int length = 0;
	
	/**	This Ship's orientation */
	protected boolean horizontal = true;
	
	/**	This array denotes where this Ship has been hit. Array elements with a <code>true</code> value
	 * correspond to a hit part of this Ship. */
	protected boolean[] hit = new boolean[4];
	
	// Getters and Setters START
	/**
	 * <p>Returns this Ship's type. This method is overridden in all Ship subclasses apart from
	 * <code>EmptySea</code>.</p>
	 * 
	 * @return this Ship's type.
	 */
	public String getShipType() {
		return "unset";
	}
	
	/**
	 * Returns this Ship's bow row.
	 * 
	 * @return bowRow
	 */
	public int getBowRow() {
		return bowRow;
	}
	
	/**
	 * Sets this Ship's {@link #bowRow} only if the <code>bowRow</code> parameter is between 0 and 9.
	 * Returns whether or not the operation was successful. 
	 * 
	 * @param bowRow  This Ship's bow (ship front) row 
	 * @return <code>true</code> if the operation was successful (i.e. the bowRow parameter was legal).
	 */
	public boolean setBowRow(int bowRow) {
		if(bowRow >= 0 && bowRow <= 9) {
			this.bowRow = bowRow;
			return true;
		}
		return false;
	}
	
	/**
	 * Returns this Ship's bow column.
	 * 
	 * @return bowColumn
	 */
	public int getBowColumn() {
		return bowColumn;
	}
	
	/**
	 * Sets this Ship's {@link #bowColumn} only if the <code>bowColumn</code> parameter is between 0 and 9.
	 * Returns whether or not the operation was successful. 
	 * 
	 * @param bowColumn  This Ship's bow (ship front) column 
	 * @return <code>true</code> if the operation was successful (i.e. the bowColumn parameter was legal).
	 */
	public boolean setBowColumn(int bowColumn) {
		if(bowColumn >= 0 && bowColumn <= 9) {
			this.bowColumn = bowColumn;
			return true;
		}
		return false;
	}
	
	/**
	 * Returns this Ship's length.
	 * 
	 * @return length
	 */
	public int getLength() {
		return length;
	}
	
	/**
	 * Returns whether or not this Ship is horizontal.
	 * 
	 * @return horizontal
	 */
	public boolean isHorizontal() {
		return horizontal;
	}
	
	/**
	 * Sets this Ship's {@link #horizontal}.
	 * 
	 * @param horizontal
	 */
	public void setHorizontal(boolean horizontal) {
		this.horizontal = horizontal;
	}
	// Getters and Setters END
	
	/**
	 * <p>Returns whether or not it is okay to place this Ship with the given orientation and with its bow in the given 
	 * <code>row</code> and <code>column</code> of the given <code>Ocean</code> object - all received as arguments.</p>
	 * <p>Performs the following checks:</p>
	 * <ul>
	 * <li>The proposed row and column do not make this Ship exceed the boundaries of the <code>Ocean</code> object.</li>
	 * <li>There are no other Ships on or all around this Ship's proposed location.</li> 
	 * </ul>
	 * <p>It should be noted that this method does not modify this Ship or the <code>Ocean</code> object. Rather it
	 * merely says whether or not it is legal to do so.</p>
	 * 
	 * @param row         Proposed Ship bow row.
	 * @param column      Proposed Ship bow column
	 * @param horizontal  Proposed Ship orientation
	 * @param ocean       Ocean object to place this Ship in 
	 * @return Whether or not it is legal to place this Ship in the <code>Ocean</code> object
	 */
	public boolean okToPlaceShipAt(int row, int column, boolean horizontal, Ocean ocean) {
		if(horizontal) {
			// Checks if this Ship fits horizontally
			if(row >= 0 && row <= 9 && column >= 0 && column+this.length <= 9) {
				// Checks for Ships on and all around this Ship's proposed location. Only checks places which are
				// within the boundaries of the given Ocean.
				for(int rowCheck=row-1; rowCheck<=row+1; rowCheck++) {
					if(rowCheck >= 0 && rowCheck <= 9) {
						for(int colCheck=column-1; colCheck<=column+length; colCheck++) {
							if(colCheck >= 0 && colCheck <= 9) {
								if(!ocean.getShipInLocation(rowCheck, colCheck).getShipType().equals("unset")) {
									return false;
								}
							}
						} // end for
					} // end if
				} // end for
				// Would only get to this stage if this Ship's proposed location and orientation in the given Ocean
				// was legal. Returns true, since the Ship also does not exceed the Ocean's boundaries.
				return true;
			}
		}
		else { // Not horizontal
			// Checks if this Ship fits vertically
			if(column >= 0 && column <= 9 && row >= 0 && row+this.length <= 9) {
				// Checks for Ships on and all around this Ship's proposed location. Only checks places which are
				// within the boundaries of the given Ocean.
				for(int colCheck=column-1; colCheck<=column+1; colCheck++) {
					if(colCheck >= 0 && colCheck <= 9) {
						for(int rowCheck=row-1; rowCheck<=row+length; rowCheck++) {
							if(rowCheck >= 0 && rowCheck <= 9) {
								if(!ocean.getShipInLocation(rowCheck, colCheck).getShipType().equals("unset")) {
									return false;
								}
							}
						} // end for
					} // end if
				} // end for
				// Would only get to this stage if this Ship's proposed location and orientation in the given Ocean
				// was legal. Returns true, since the Ship also does not exceed the Ocean's boundaries.
				return true;
			}
		}
		// Returns false at this stage, as the Ship would be out of the Ocean's boundaries.
		return false;
	}
	
	/**
	 * <p>Places this Ship in the <code>Ocean</code> object received as an argument.</p>
	 * <p>This involves the following actions:</p>
	 * <ul>
	 * </li>Setting {@link #bowRow} to <code>row</code>'s value.</li>
	 * </li>Setting {@link #bowColumn} to <code>column</code>'s value.</li>
	 * </li>Setting {@link #horizontal} to <code>horizontal</code>'s value.</li>
	 * </li>Using <code>Ocean</code>'s <code>setShipInLocation</code> method to place this Ship in the
	 * <code>Ocean</code>.</li>
	 * </ul>
	 * <p>It should be noted that a precondition to using this method is invoking the method
	 * <code>okToPlaceShipAt</code> with the same parameters. This is to ensure that it is legal to place
	 * this Ship in the given Ocean.</p>
	 * 
	 * @param row         Requested bow row for this Ship.
	 * @param column      Requested bow column for this Ship.
	 * @param horizontal  Requested orientation for this Ship.
	 * @param ocean       <code>Ocean</code> to place this Ship in.
	 */
	public void placeShipAt(int row, int column, boolean horizontal, Ocean ocean) {
		this.bowRow = row;
		this.bowColumn = column;
		this.horizontal = horizontal;
		
		// Places this Ship in the given Ocean, in all places that it occupies (according to its orientation).
		if(horizontal) {
			for(int i=0; i<this.length; i++) {
				ocean.setShipInLocation(this, row, column+i);
			}
		}
		else {
			for(int i=0; i<this.length; i++) {
				ocean.setShipInLocation(this, row+i, column);
			}
		}
	}
	
	/**
	 * <p>shoots at this Ship.</p>
	 * <p>If this Ship is not sunk and the shot row and column are part of it, this method:</p>
	 * <ul>
	 * <li>Marks the corresponding part of this Ship's {@link #hit} array as <code>true</code>.</li>
	 * <li>Returns <code>true</code>.</li>
	 * </ul>
	 * <p>Otherwise, it returns false.</p>
	 * 
	 * @param row     shot row.
	 * @param column  shot column.
	 * @return <code>true</code> if the shot hit a part of this Ship AND this Ship is not sunk.
	 */
	public boolean shootAt(int row, int column) {
		if(!isSunk()) {
			if(horizontal) {
				if(bowRow == row && bowColumn <= column && bowColumn+length >= column) {
					hit[column - bowColumn] = true;
					return true;
				}
			}
			else {
				if(bowColumn == column && bowRow <= row && bowRow+length >= row) {
					hit[row - bowRow] = true;
					return true;
				}
			}
		} // end if
		return false;
	}
	
	/**
	 * Returns whether or not this Ship has been sunk.
	 * 
	 * @return <code>true</code> if this Ship has been sunk and false otherwise.
	 */
	public boolean isSunk() {
		// Goes through the hit array for the length of this particular Ship.
		// If any part of this ship is not hit, returns false.
		for(int i=0; i<length; i++) {
			if(hit[i] == false) {
				return false;
			}
		}
		// Otherwise, returns true.
		return true;
	}
	
	/**
	 * Returns whether or not the given place (row and column received as parameters) is part of this Ship and
	 * has been hit.
	 * 
	 * @param row     row to check if hit.
	 * @param column  column to check if hit.
	 * @return <code>true</code> if place is hit; <code>false</code> otherwise.
	 */
	public boolean isPlaceHit(int row, int column) {
		// If place is part of this Ship and is hit (corresponding hit element has the value true), returns true.
		if(horizontal) {
			if(bowRow == row && bowColumn <= column && bowColumn+length >= column && hit[column - bowColumn]) {
				return true;
			}
		}
		else {
			if(bowColumn == column && bowRow <= row && bowRow+length >= row && hit[row - bowRow]) {
				return true;
			}
		}
		// Otherwise, returns false.
		return false;
	}
	
	/**
	 * <p>Returns "S" to denote a (non-EmptySea) Ship.</p>
	 * <p>This symbol is then used in the <code>Ocean</code>'s <code>print</code> method to determine if it is
	 * a real <code>Ship</code>, and if so determine its state by invoking various methods.</p>
	 * 
	 * @return "S"
	 */
	@Override
	public String toString() {
		return "S";
	}
	
}