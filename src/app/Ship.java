package app;

public class Ship {

	protected int bowRow = -1;
	protected int bowColumn = -1;
	protected int length = 0;
	protected boolean horizontal = true;
	protected boolean[] hit = new boolean[4];
	
	// Getters and Setters START
	public String getShipType() {
		return "unset";
	}
	
	public int getBowRow() {
		return bowRow;
	}
	
	public void setBowRow(int bowRow) {
		this.bowRow = bowRow;
	}
	
	public int getBowColumn() {
		return bowColumn;
	}
	
	public void setBowColumn(int bowColumn) {
		this.bowColumn = bowColumn;
	}
	
	public int getLength() {
		return length;
	}
	
	public boolean isHorizontal() {
		return horizontal;
	}
	
	public void setHorizontal(boolean horizontal) {
		this.horizontal = horizontal;
	}
	// Getters and Setters END
	
	/* Returns true if it is okay to put a ship of this length with its bow in this location,
	with the given orientation, and returns false otherwise. The ship must not overlap
	another ship, or touch another ship (vertically, horizontally, or diagonally), and it
	must not stick out beyond the array. Does not actually change either the ship or
	the Ocean, just says whether it is legal to do so. */
	
	// * Consider breaking this method down to smaller chunks *
	public boolean okToPlaceShipAt(int row, int column, boolean horizontal, Ocean ocean) {
		if(horizontal) {
			// See if ship fits horizontally
			if(row >= 0 && row <= 9 && column >= 0 && column+this.length <= 9) {
				// Check for ships in row above and below the proposed ship location
				for(int rowCheck=row-1; rowCheck<=row+1; rowCheck++) {
					if(rowCheck >= 0 && rowCheck <= 9) {
						for(int colCheck=column-1; colCheck<=column+length; colCheck++) {
							if(colCheck >= 0 && colCheck <= 9) {
								if(!ocean.getShipInLocation(rowCheck, colCheck).getShipType().equals("unset")) {
									return false;
								}
							}
						}
					}
				}
//				// Check for ships left and right of the proposed ship location.
//				if(column-1 >= 0) {
//					if(!ocean.getShipInLocation(row, column-1).getShipType().equals("unset")) {
//						return false;
//					}
//				}
//				if(column+1 <= 9) {
//					if(!ocean.getShipInLocation(row, column+1).getShipType().equals("unset")) {
//						return false;
//					}
//				}
				
				return true;
			}
		}
		else { // Not horizontal
			// See if ship fits horizontally
			if(column >= 0 && column <= 9 && row >= 0 && row+this.length <= 9) {
				// Check for ships in column left and right of the proposed ship location
				for(int colCheck=column-1; colCheck<=column+1; colCheck++) {
					if(colCheck >= 0 && colCheck <= 9) {
						for(int rowCheck=row-1; rowCheck<=row+length; rowCheck++) {
							if(rowCheck >= 0 && rowCheck <= 9) {
								if(!ocean.getShipInLocation(rowCheck, colCheck).getShipType().equals("unset")) {
									return false;
								}
							}
						}
					}
				}
//				// Check for ships left and right of the proposed ship location.
//				if(row-1 >= 0) {
//					if(!ocean.getShipInLocation(row-1, column).getShipType().equals("unset")) {
//						return false;
//					}
//				}
//				if(row+1 <= 9) {
//					if(!ocean.getShipInLocation(row+1, column).getShipType().equals("unset")) {
//						return false;
//					}
//				}
				
				return true;
			}
		}
		return false;
	}
	
	/* Puts the ship in the ocean. This involves giving values to the bowRow, bowColumn, and
	horizontal instance variables in the ship, and it also involves putting a reference
	to the ship in each of 1 or more locations (up to 4) in the ships array in the Ocean
	object. (Note: This will be as many as four identical references; you can't refer to
	a part of a ship, only to the whole ship.) */
	public void placeShipAt(int row, int column, boolean horizontal, Ocean ocean) {
		this.bowRow = row;
		this.bowColumn = column;
		this.horizontal = horizontal;
		
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
	
	/* If a part of the ship occupies the given row	and column, and the ship hasn't been sunk, mark that
	 * part of the ship as hit (in the hit array, 0 indicates the bow) and return true, otherwise return false. */
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
		}
		return false;
	}
	
	/* Return true if every part of the ship has been hit, false otherwise. (Copied verbatim from instructions) */
	public boolean isSunk() {
		for(int i=0; i<length; i++) {
			if(hit[i] == false) {
				return false;
			}
		}
		return true;
	}
	
	/*public boolean isPlaceOverlappingWithShip(int row, int column) {
		if(horizontal) {
			if(bowRow == row && bowColumn <= column && bowColumn+length >= column) {
				return true;
			}
		}
		else {
			if(bowColumn == column && bowRow <= row && bowRow+length >= row) {
				return true;
			}
		}
		return false;
	}*/
	
	/* Checks if this place is in the ship and is hit */
	public boolean isPlaceHit(int row, int column) {
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
		return false;
	}
	
	@Override
	public String toString() {
		return "S";
		/*if(isSunk()) {
			return "x";
		}*/
		// if()
		// But how does the toString method know which location to check if it has been hit?
		// If I am able to use this way, comment out all Ship's subclasses' toString() methods.
	}
	
}