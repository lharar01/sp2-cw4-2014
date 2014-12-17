package app;

public class Ship {
	private String type = "unset";
	private int bowRow;
	private int bowColumn;
	private int length;
	private boolean horizontal;
	boolean[] hit = new boolean[4];
	
	// Getters and Setters START
	public String getType() {
		return type;
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
	
	boolean okToPlaceShipAt(int row, int column, boolean horizontal, Ocean ocean) {
		/* Returns true if it is okay to put a ship of this length with its bow in this location,
with the given orientation, and returns false otherwise. The ship must not overlap
another ship, or touch another ship (vertically, horizontally, or diagonally), and it
must not stick out beyond the array. Does not actually change either the ship or
the Ocean, just says whether it is legal to do so. */
	}
	
	void placeShipAt(int row, int column, boolean horizontal, Ocean ocean) {
		/* Puts the ship in the ocean. This involves giving values to the bowRow, bowColumn, and
	horizontal instance variables in the ship, and it also involves putting a reference
	to the ship in each of 1 or more locations (up to 4) in the ships array in the Ocean
	object. (Note: This will be as many as four identical references; you can't refer to
	a part of a ship, only to the whole ship.) */
	}
	
	boolean shootAt(int row, int column) {
		/* If a part of the ship occupies the given row	and column, and the ship hasn't been sunk, mark that part of the ship as hit (in
	the hit array, 0 indicates the bow) and return true, otherwise return false. */
		
	}
	
	/* Return true if every part of the ship has been hit, false otherwise. (Copied verbatim from instructions) */
	boolean isSunk() {
		for(int i=0; i<hit.length; i++) {
			if(hit[i] == false) {
				return false;
			}
		}
		return true;
	}
}