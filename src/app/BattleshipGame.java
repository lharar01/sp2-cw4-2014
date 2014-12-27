package app;

import java.util.Scanner;

/**
 * <p>This class is the app driver of the Battleship game application.</p>
 * <p>To run the game, it utilises class <code>Ocean</code>, which in turn interacts with class <code>Ship</code>
 * and its subclasses.</p>
 * <p>This game has a testing feature which allows the user to see where each ship was placed and in which
 * orientation. To enable this feature, the user needs to enter the word "testing" at game load (when prompted).</p>
 * 
 * @author Liran Harary (lharar01, 12837230)
 * @version 1.0
 * @since 17th December 2014
 */
public class BattleshipGame {
	
	/**
	 * Calls the methods which drive the game.
	 * 
	 * @param args  arguments
	 */
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		boolean playAgain;
		do {
			displayInstructions();
			int shotsFired = runGame(scanner);
			displayGameResults(shotsFired);
			playAgain = isPlayAgain(scanner);
			System.out.println();
		}
		while(playAgain);
		quitGame();
	}
	
	/**
	 * Prints the game's instructions
	 */
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
	
	/**
	 * <p>Runs the game. In charge of the following:</p>
	 * <ul>
	 * <li>Turning testing mode on or off.</li>
	 * <li>Creating a new <code>Ocean</code>.</li>
	 * <li>Doing the following while the game is not over:
	 * 	<ul>
	 *   <li>Displaying the Ocean's current map.</li>
	 * 	 <li>Getting <code>row</code> and <code>column</code> input from the user with the aid of
	 *   {@link #getRowOrColInput(Scanner, String)}.</li>
	 * 	 <li>Invoking <code>Ocean</code>'s <code>shootAt</code> method.</li>
	 * 	 <li>Printing hit and miss messages.</li>
	 * 	</ul>
	 * </li>
	 * <li>Displaying the Ocean's current map one last time.</li>
	 * <li>Returning the number of shots fired in the game.</li>
	 * </ul>
	 * 
	 * @param scanner  An input scanner of type <code>Scanner</code>.
	 * @return The number of shots fired in the game.
	 */
	private static int runGame(Scanner scanner) {
		System.out.print("Press Enter to start playing...\n(or type in \"testing\" and press enter for testing mode) ");
		// Get initial input from the user, and set testing to true if the user's input was "testing", and false otherwise.
		String initialInput = scanner.nextLine();
		boolean testing = initialInput.equals("testing") ? true : false;
		Ocean ocean = new Ocean();
		if(testing) {
			System.out.println();
		}
		// Invoke this method with the testing value. If testing is turned on, this method will print the Ships
		// that have been placed, their locations and their orientation.
		ocean.placeAllShipsRandomly(testing);
		int row;
		int column;
		boolean shipHit;
		
		// While game is not over, does the following in a loop: display the ocean map, gets input and displays results.
		while(!ocean.isGameOver()) {
			// Print the Ocean map
			System.out.println();
			ocean.print();
			System.out.println();
			
			System.out.println("(Enter 'q' to quit)");
			// Use the method getRowOrColInput to get row and column input from the user.
			row = getRowOrColInput(scanner, "row");
			column = getRowOrColInput(scanner, "column");
			
			// Shoot at the Ship the user asked to and store the result in shipHit.
			shipHit = ocean.shootAt(row, column);
			
			/// If the current Ship was hit, display hit message. If it was also sunk, display the relevant messages.
			if(shipHit) {
				System.out.println("\n*hit*");
				if(ocean.getShipInLocation(row, column).isSunk()) {
					System.out.println("**You just sank a " + ocean.getShipInLocation(row, column).getShipType() + "!**");
					System.out.println("Ships left in the enemy's fleet: " + (10 - ocean.getShipsSunk()) );
				}
			}
			// Otherwise, display miss message.
			else {
				System.out.println("\n*miss*");
			}
		} // end while
		
		// Print the Ocean map
		System.out.println();
		ocean.print();
		System.out.println();
		
		return ocean.getShotsFired();
	}
	
	/**
	 * <p>Repeatedly asks for rows or columns input from the user (in a loop), until valid input is received.</p>
	 * <p>Prints error messages for type mismatch (not int) and illegal input (not between 0-9).</p> 
	 * <p>Quits the game (using {@link #quitGame()} if the input is "q".</p>
	 * 
	 * @param scanner    An input scanner of type <code>Scanner</code>.
	 * @param inputName  The name of the input: "row"/"column". This is used for instructional messages and error messages.
	 * @return The user's input
	 */
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
						System.out.println();
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
		} // end do
		while(typeMismatch || illegalInput);
		
		return input;
	}
	
	/**
	 * <p>Displays the game results:</p>
	 * <ul>
	 * <li>The number of shots the user fired.</li>
	 * <li>A winning message according to the number of shots the user needed to fire in order to sink all
	 * the <code>Ships</code>.</li>
	 * </ul>
	 * 
	 * @param shots  The number of shots the user fired.
	 */
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
		} // end else
		System.out.println(winMessage);
		System.out.println("--------------------------------------");
	}
	
	/** Prints thanks message and quits the game. */
	private static void quitGame() {
		System.out.println("-------------------------------");
		System.out.println("Thanks for playing Battleship!");
		System.out.println("-------------------------------");
		System.exit(0);
	}
	
	/**
	 * <p>Asks the user whether or not they would like to play again.</p>
	 * <p>Returns <code>true</code> if the user's answer input is "y" or "yes"; and <code>false</code> if it is
	 * "n" or "q".</p>
	 * <p>This method will keep asking the user for input as long as it is illegal (non - "y"/"yes"/"n"/"q").</p>
	 * 
	 * @param scanner  An input scanner of type <code>Scanner</code>.
	 * @return <code>true</code> if the user wants to play again, and false otherwise.
	 */
	private static boolean isPlayAgain(Scanner scanner) {
		String input = " ";
		boolean illegalInput;
		// The following "do-while" loop does the following:
		// - assumes illegalInput to be true unless proven otherwise.
		// - Keeps asking for input while input is illegal.
		do {
			illegalInput = true;
			System.out.print("\nWould you like to play again? (y/n) ");
			if(scanner.hasNext()) {
				input = scanner.nextLine();
				if(input.equals("y") || input.equals("yes") || input.equals("n") || input.equals("q")) {
					illegalInput = false;
				}
			}
			
			if(illegalInput) {
				System.out.print("\nPlease enter either \"y\" or \"n\" only.");
				System.out.println(" Please try again.");
				//scanner.nextLine(); // needed?
			}
		} // end do
		while(illegalInput);
		
		// Return true if the input was "y" or "yes", and false otherwise
		return input.equals("y") || input.equals("yes");
	}
}