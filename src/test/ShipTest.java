package test;

import static org.junit.Assert.*;

import org.junit.Test;

import app.Ocean;
import app.Ship;
import app.EmptySea;
import app.Battleship;
import app.Cruiser;
import app.Destroyer;
import app.Submarine;

public class ShipTest {

	@Test
	public void testGetShipTypeShip() {
		Ship ship = new Ship();
		assertTrue("Wrong answer", ship.getShipType().equals("unset"));
	}
	
	@Test
	public void testGetShipTypeEmptySea() {
		Ship emptySea = new EmptySea();
		assertTrue("Wrong answer", emptySea.getShipType().equals("unset"));
	}
	
	@Test
	public void testGetShipTypeBattleship() {
		Ship battleship = new Battleship();
		assertTrue("Wrong answer", battleship.getShipType().equals("Battleship"));
	}
	
	@Test
	public void testGetShipTypeCruiser() {
		Ship cruiser = new Cruiser();
		assertTrue("Wrong answer", cruiser.getShipType().equals("Cruiser"));
	}
	
	@Test
	public void testGetShipTypeDestroyer() {
		Ship destroyer = new Destroyer();
		assertTrue("Wrong answer", destroyer.getShipType().equals("Destroyer"));
	}
	
	@Test
	public void testGetShipTypeSubmarine() {
		Ship submarine = new Submarine();
		assertTrue("Wrong answer", submarine.getShipType().equals("Submarine"));
	}
	
	@Test
	public void testGetLength() {
		Ship ship = new Ship();
		assertEquals("Wrong answer", 0, ship.getLength());
	}
	
	@Test
	public void testGetLengthEmptySea() {
		Ship emptySea = new EmptySea();
		assertEquals("Wrong answer", 1, emptySea.getLength());
	}
	
	@Test
	public void testGetLengthBattleship() {
		Ship battleship = new Battleship();
		assertEquals("Wrong answer", 4, battleship.getLength());
	}
	
	@Test
	public void testGetLengthCruiser() {
		Ship cruiser = new Cruiser();
		assertEquals("Wrong answer", 3, cruiser.getLength());
	}
	
	@Test
	public void testGetLengthDestroyer() {
		Ship destroyer = new Destroyer();
		assertEquals("Wrong answer", 2, destroyer.getLength());
	}
	
	@Test
	public void testGetLengthSubmarine() {
		Ship submarine = new Submarine();
		assertEquals("Wrong answer", 1, submarine.getLength());
	}

	@Test
	public void testSetAndGetBowRow() {
		Ship ship = new Ship();
		int bowRow = 5;
		ship.setBowRow(bowRow);
		assertEquals("Wrong answer", bowRow, ship.getBowRow());
	}
	
	@Test
	public void testSetBowRowEdgeCase() {
		Ship ship = new Ship();
		int bowRow = -1;
		assertFalse("Wrong answer", ship.setBowRow(bowRow));
	}

	@Test
	public void testSetAndGetBowColumn() {
		Ship ship = new Ship();
		int bowColumn = 5;
		ship.setBowColumn(bowColumn);
		assertEquals("Wrong answer", bowColumn, ship.getBowColumn());
	}
	
	@Test
	public void testSetBowColumnEdgeCase() {
		Ship ship = new Ship();
		int bowColumn = 10;
		assertFalse("Wrong answer", ship.setBowColumn(bowColumn));
	}

	@Test
	public void testSetAndIsHorizontal() {
		Ship ship = new Ship();
		ship.setHorizontal(false);
		assertFalse("Wrong answer", ship.isHorizontal());
	}

	@Test
	public void testOkToPlaceShipAt() {
		Ocean ocean = new Ocean();
		Ship battleship = new Battleship();
		int row = 5, col = 5;
		boolean horizontal = true;
		assertTrue("Wrong answer", battleship.okToPlaceShipAt(row, col, horizontal, ocean));		
	}
	
	@Test
	public void testOkToPlaceShipAtEdgeCase() {
		Ocean ocean = new Ocean();
		Ship battleship = new Battleship();
		Ship cruiser = new Cruiser();
		int row = 5, col = 5;
		boolean horizontal = true;
		cruiser.placeShipAt(row, col, horizontal, ocean);
		assertFalse("Wrong answer", battleship.okToPlaceShipAt(row, col, horizontal, ocean));		
	}

	@Test
	public void testPlaceShipAt() {
		Ocean ocean = new Ocean();
		Ship cruiser = new Cruiser();
		cruiser.placeShipAt(5, 5, false, ocean);
		assertSame("Wrong answer", cruiser, ocean.getShipInLocation(6, 5));
	}
	
	@Test
	public void testPlaceShipAtEdgeCase() {
		Ocean ocean = new Ocean();
		Ship cruiser = new Cruiser();
		cruiser.placeShipAt(5, 5, false, ocean);
		assertNotSame("Wrong answer", cruiser, ocean.getShipInLocation(6, 6));
	}

	@Test
	public void testShootAtAndIsPlaceHit() {
		Ocean ocean = new Ocean();
		Ship destroyer = new Destroyer();
		destroyer.placeShipAt(5, 5, false, ocean);
		assertTrue("Wrong answer", destroyer.shootAt(5,5) && destroyer.isPlaceHit(5,5));
	}
	
	@Test
	public void testShootAtAndIsPlace2() {
		Ocean ocean = new Ocean();
		Ship destroyer = new Destroyer();
		destroyer.placeShipAt(5, 5, false, ocean);
		assertTrue("Wrong answer", destroyer.shootAt(7,5) && destroyer.isPlaceHit(7,5));
	}

	@Test
	public void testIsSunk() {
		Ocean ocean = new Ocean();
		Ship submarine = new Submarine();
		submarine.placeShipAt(5, 5, false, ocean);
		submarine.shootAt(5,5);
		assertTrue("Wrong answer", submarine.isSunk());
	}
	
	@Test
	public void testIsSunkWhenNotSunk() {
		Ocean ocean = new Ocean();
		Ship submarine = new Destroyer();
		submarine.placeShipAt(5, 5, false, ocean);
		submarine.shootAt(5,5);
		assertFalse("Wrong answer", submarine.isSunk());
	}
	
}