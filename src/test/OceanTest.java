package test;

import static org.junit.Assert.*;

import org.junit.Test;

import app.Ocean;
import app.Ship;

public class OceanTest {

	@Test
	public void testInitOcean() {
		Ocean ocean = new Ocean();
		boolean allEmptySea = true;
		for(int row=0; row<10; row++) {
			for(int col=0; col<10; col++) {
				if(!ocean.getShipInLocation(row, col).getShipType().equals("unset")) {
					allEmptySea = false;
					break;
				}
			}
		}
		assertTrue("Wrong answer", allEmptySea);		
	}
	
	@Test
	public void testGetShotsFired() {
		Ocean ocean = new Ocean();
		ocean.placeAllShipsRandomly(false);
		ocean.shootAt(5, 5);
		ocean.shootAt(5, 4);
		assertEquals("Wrong answer", 2, ocean.getShotsFired());		
	}
	
	@Test
	public void testGetShotsFired2() {
		Ocean ocean = new Ocean();
		ocean.placeAllShipsRandomly(false);
		ocean.shootAt(5, 5);
		ocean.shootAt(5, 4);
		ocean.shootAt(1, 1);
		ocean.shootAt(0, 4);
		ocean.shootAt(2, 2);
		assertEquals("Wrong answer", 5, ocean.getShotsFired());		
	}

	@Test
	public void testGetHitCount() {
		Ocean ocean = new Ocean();
		ocean.placeAllShipsRandomly(false);
		for(int row=0; row<10; row++) {
			for(int col=0; col<10; col++) {
				ocean.shootAt(row, col);
			}
		}
		assertEquals("Wrong answer", 20, ocean.getHitCount());
	}
	
	@Test
	public void testGetHitCount2() {
		Ocean ocean = new Ocean();
		assertEquals("Wrong answer", 0, ocean.getHitCount());
	}

	@Test
	public void testGetShipsSunk() {
		Ocean ocean = new Ocean();
		ocean.placeAllShipsRandomly(false);
		for(int row=0; row<10; row++) {
			for(int col=0; col<10; col++) {
				ocean.shootAt(row, col);
			}
		}
		assertEquals("Wrong answer", 10, ocean.getShipsSunk());
	}
	
	@Test
	public void testGetShipsSunk2() {
		Ocean ocean = new Ocean();
		ocean.placeAllShipsRandomly(false);
		for(int row=0; row<10; row++) {
			for(int col=0; col<10; col++) {
				if(!ocean.getShipInLocation(row, col).getShipType().equals("Battleship")) {
					ocean.shootAt(row, col);
				}
			}
		}
		assertEquals("Wrong answer", 9, ocean.getShipsSunk());
	}

	@Test
	public void testIsGameOver() {
		Ocean ocean = new Ocean();
		ocean.placeAllShipsRandomly(false);
		for(int row=0; row<10; row++) {
			for(int col=0; col<10; col++) {
				ocean.shootAt(row, col);
			}
		}
		assertTrue("Wrong answer", ocean.isGameOver());
	}
	
	@Test
	public void testIsGameOver2() {
		Ocean ocean = new Ocean();
		ocean.placeAllShipsRandomly(false);
		assertFalse("Wrong answer", ocean.isGameOver());
	}

	@Test
	public void testSetAndGetShipInLocation() {
		Ocean ocean = new Ocean();
		Ship ship = new Ship();
		ocean.setShipInLocation(ship, 1, 1);
		assertSame("Wrong answer", ship, ocean.getShipInLocation(1, 1));
	}
	
	@Test
	public void testGetShipArrayAndGetShipInLocation() {
		Ocean ocean = new Ocean();
		ocean.placeAllShipsRandomly(false);
		assertSame("Wrong answer", ocean.getShipInLocation(0, 0), ocean.getShipArray()[0][0]);
	}
	
	@Test
	public void testGetShipArrayAndGetShipInLocation2() {
		Ocean ocean = new Ocean();
		ocean.placeAllShipsRandomly(false);
		assertSame("Wrong answer", ocean.getShipInLocation(5, 3), ocean.getShipArray()[5][3]);
	}

	@Test
	public void testPlaceAllShipsRandomly() {
		Ocean ocean = new Ocean();
		ocean.placeAllShipsRandomly(false);
		int battleship = 0, cruiser = 0, destroyer = 0, submarine = 0;
		for(int row=0; row<10; row++) {
			for(int col=0; col<10; col++) {
				switch(ocean.getShipInLocation(row, col).getShipType()) {
					case "Battleship": battleship++;
						break;
					case "Cruiser": cruiser++;
						break;
					case "Destroyer": destroyer++;
						break;
					case "Submarine": submarine++;
						break;
				}
				
			}
		}
		assertTrue("Wrong answer", battleship == 1*4 && cruiser == 2*3 && destroyer == 3*2 && submarine == 4*1);
	}

	@Test
	public void testShootAt() {
		Ocean ocean = new Ocean();
		ocean.placeAllShipsRandomly(false);
		int shipRow = -1, shipCol = -1;
		for(int row=0; row<10 && shipRow == -1; row++) {
			for(int col=0; col<10 && shipRow == -1; col++) {
				if(ocean.getShipInLocation(row, col).getShipType() != "unset") {
					shipRow = row;
					shipCol = col;
				}
			}
		}
		assertTrue("Wrong answer", ocean.shootAt(shipRow, shipCol));
	}
	
	@Test
	public void testShootAt2() {
		Ocean ocean = new Ocean();
		ocean.placeAllShipsRandomly(false);
		int emptySeaRow = -1, emptySeaCol = -1;
		for(int row=0; row<10 && emptySeaRow == -1; row++) {
			for(int col=0; col<10 && emptySeaRow == -1; col++) {
				if(ocean.getShipInLocation(row, col).getShipType() == "unset") {
					emptySeaRow = row;
					emptySeaCol = col;
				}
			}
		}
		assertFalse("Wrong answer", ocean.shootAt(emptySeaRow, emptySeaCol));
	}

	// Remove later.
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
