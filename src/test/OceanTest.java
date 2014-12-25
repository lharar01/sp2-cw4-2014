package test;

import static org.junit.Assert.*;

import org.junit.Test;

import app.Ocean;

public class OceanTest {

	@Test
	public void testOcean() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetShotsFired() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetHitCount() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetShipsSunk() {
		fail("Not yet implemented");
	}

	@Test
	public void testIsGameOver() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetShipArray() {
		fail("Not yet implemented");
	}

	@Test
	public void testPlaceAllShipsRandomly() {
		fail("Not yet implemented");
	}

	@Test
	public void testIsOccupied() {
		fail("Not yet implemented");
	}

	@Test
	public void testShootAt() {
		fail("Not yet implemented");
	}

	// Remove/change later.
	@Test
	public void testPrint() {
		Ocean ocean = new Ocean();
		ocean.placeAllShipsRandomly(true);
		for(int row=0; row<10; row++) {
			for(int col=0; col<10; col++) {
				ocean.getShipArray()[row][col].shootAt(row,col);
			}
		}
		ocean.print();
	}

}
