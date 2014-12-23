package test;

import static org.junit.Assert.*;

import org.junit.Test;

import app.Battleship;
import app.Cruiser;
import app.Destroyer;
import app.Ocean;
import app.Ship;
import app.Submarine;

public class ShipTest {

	@Test
	public void testGetShipType() {
		Ship ship = new Ship();
		assertTrue("Wrong answer", ship.getShipType().equals("unset"));
	}

	@Test
	public void testSetAndGetBowRow() {
		Ship ship = new Ship();
		int bowRow = 5;
		ship.setBowRow(bowRow);
		assertEquals("Wrong answer", bowRow, ship.getBowRow());
	}

	@Test
	public void testSetAndGetBowColumn() {
		Ship ship = new Ship();
		int bowColumn = 5;
		ship.setBowColumn(bowColumn);
		assertEquals("Wrong answer", bowColumn, ship.getBowColumn());
	}

	@Test
	public void testSetAndIsHorizontal() {
		Ship ship = new Ship();
		ship.setHorizontal(false);
		assertTrue("Wrong answer", !ship.isHorizontal());
	}

	@Test
	public void testOkToPlaceShipAt() {
		Ocean ocean = new Ocean();
		Ship battleship = new Battleship();
		assertTrue("Wrong answer", battleship.okToPlaceShipAt(5, 5, true, ocean));		
	}

	@Test
	public void testPlaceShipAt() {
		Ocean ocean = new Ocean();
		Ship cruiser = new Cruiser();
		cruiser.placeShipAt(5, 5, false, ocean);
		assertSame("Wrong answer", cruiser, ocean.getShipArray()[6][5]);
	}

	@Test
	public void testShootAtAndIsPlaceHit() {
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
	
}