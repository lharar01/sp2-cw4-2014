package app;

import java.util.Scanner;

/**
 * <p>This class is the app driver of the Battleship game application.</p>
 * <p>To run the game, it utilises class <code>Ocean</code>, which in turn interacts with class <code>Ship</code>
 * and its subclasses</p>.
 * <p>This game has a testing feature which allows the user to see where each ship was placed and in which
 * orientation. To enable this feature, the user needs to enter the word "testing" at game load (when prompted).</p>
 * 
 * @author Liran_and_Di
 * @version 1.0
 * @since 17th December 2014
 */
public class BattleshipGame {
	
	public static void main(String[] args) {
		displayInstructions();
		int shotsFired = runGame();
		displayGameResults(shotsFired);
		quitGame();
	}
	
	private static void displayInstructions() {
		System.out.println("--== Welcome to Liran's Battleship game! ==--\n");
		System.out.println("Instructions: \nThe computer has randomly placed its fleet of ships in the ocean."
				+ "\nThe fleet is composed of 10 ships:\n"
				+ "- 1 Battleship (4 spaces long)\n"
				+ "- 2 Cruisers   (3 spaces long)\n"
				+ "- 3 Destroyers (2 spaces long)\n"
				+ "- 4 Submarines (1 space long)\n"
				+ "Your objective is to find and sink all the ships in the fleet.\n\n"
				+ "Tip: No ship is placed in the immediate surrounding of another ship.\nThus if you sank a ship, don't bother "
				+ "looking for other ships around it!\nGood luck!\n");
	}
	
	private static int runGame() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Press Enter to start playing...\n(or type in \"testing\" and press enter for testing mode) ");
		String initialInput = scanner.nextLine();
		boolean debugging = initialInput.equals("testing") ? true : false;
		Ocean ocean = new Ocean();
		if(debugging) {
			System.out.println();
		}
		ocean.placeAllShipsRandomly(debugging);
		int row;
		int column;
		boolean shipHit;
		
		// run loop of getting input and displaying results...
		while(!ocean.isGameOver()) {
			System.out.println();
			ocean.print();
			System.out.println();
			
			System.out.println("(Enter 'q' to quit)");
			row = getRowOrColInput(scanner, "row");
			column = getRowOrColInput(scanner, "column");
			
			shipHit = ocean.shootAt(row, column);
			//shipHit = ocean.getShipInLocation(row, column).shootAt(row, column);
			
			if(shipHit) {
				System.out.println("\n*hit*");
				if(ocean.getShipInLocation(row, column).isSunk()) {
					System.out.println("**You just sank a " + ocean.getShipInLocation(row, column).getShipType() + "!**");
					System.out.println("Ships left in the enemy's fleet: " + (10 - ocean.getShipsSunk()) );
				}
			}
			else {
				System.out.println("\n*miss*");
			}
		}
		scanner.close();
		
		System.out.println();
		ocean.print();
		System.out.println();
		
		return ocean.getShotsFired();
	}
	
	// Gets rows/columns input from user.
	private static int getRowOrColInput(Scanner scanner, String inputName) {
		int input = -1;
		boolean typeMismatch;
		boolean illegalInput;
		
		// The following "do-while" loop does the following:
		// - assumes typeMismatch and illegalInput to be true unless proven otherwise.
		// - Keeps asking for input while there are errors (wrong type or illegal input).
		
		do {
			typeMismatch = true;
			illegalInput = true;
			System.out.print("Which " + inputName + " would you like to shoot at? ");
			if(scanner.hasNextInt()) {
				input = scanner.nextInt();
				typeMismatch = false;
				if(input >= 0 && input <= 9) {
					illegalInput = false;
				}
			}
			else {
				if(scanner.hasNext()) {
					if(scanner.nextLine().equals("q")) {
						quitGame();
					}
				}
			}
			if(typeMismatch || illegalInput) {
				if(typeMismatch) {
					System.out.print("\nThe " + inputName + " must be an integer.");
				}
				else {
					System.out.print("\nThe " + inputName + " number must be between 0 and 9 (inclusive).");
				}
				System.out.println(" Please try again.");
				//scanner.nextLine(); // This is no longer needed, as the nextLine() is handled above when checking for "q".
			}
		}
		while(typeMismatch || illegalInput);
		
		return input;
	}
	
	private static void displayGameResults(int shots) {
		System.out.println("\n--------------------------------------");
		System.out.println("You sank all the ships with " + shots + " shots.");
		String winMessage;
		if(shots <= 35) {
			winMessage = "You're a legend!";
		}
		else {
			if(shots <= 50) {
				winMessage = "You're amazing!";
			}
			else {
				if(shots <= 65) {
					winMessage = "You're good!";
				}
				else {
					if(shots <= 75) {
						winMessage = "Not bad!";
					}
					else {
						if(shots <= 85) {
							winMessage = "Good effort!";
						}
						else {
							winMessage = "Better luck next time!";
						}
					}
				}
			}
		}
		System.out.println(winMessage);
		System.out.println("--------------------------------------");
	}
	
	private static void quitGame() {
		System.out.println("\n-------------------------------");
		System.out.println("Thanks for playing Battleship!");
		System.out.println("-------------------------------");
		System.exit(0);
	}
}