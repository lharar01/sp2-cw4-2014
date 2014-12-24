package app;

import java.util.Scanner;

public class BattleshipGame {
	
	public static void main(String[] args) {
		displayInstructions();
		runGame();
	}
	
	private static void displayInstructions() {
		System.out.println("--== Welcome to Liran's Battleship game! ==--\n");
		System.out.println("Instructions: \nThe computer has randomly placed its fleet of ships in the ocean."
				+ "\nThe fleet is composed of 10 ships:\n"
				+ "- 1 Battleship (4 spaces long)\n"
				+ "- 2 Cruisers   (3 spaces long)\n"
				+ "- 3 destroyers (2 spaces long)\n"
				+ "- 4 submarines (1 space long)\n"
				+ "Your objective is to find and sink all the ships in the fleet.\n");
	}
	
	private static void runGame() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Press any key to start the game...");
		scanner.nextLine();
		Ocean ocean = new Ocean();
		
		// run loop of getting input and displaying results...
		while(!ocean.isGameOver()) {
			System.out.println();
			ocean.print();
			
			int row;
			int col;
			row = getRowOrColInput(scanner, "row");
			col = getRowOrColInput(scanner, "col");
			
			// consider using methods where appropriate...
			// Think if anything belongs outside of this class, as it is the app driver...
		}
		
		scanner.close();
	}
	
	// Gets rows/cols input from user.
	private static int getRowOrColInput(Scanner scanner, String inputName) {
		int input = -1;
		boolean typeMismatch;
		boolean illegalInput;
		
		// The following THREE "do-while" loops do the following:
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
			if(typeMismatch || illegalInput) {
				if(typeMismatch) {
					System.out.print("\nThe " + inputName + " must be an integer.");
				}
				else {
					System.out.print("\nThe " + inputName + " number must be between 0 and 9 (inclusive).");
				}
				System.out.println(" Please try again.");
				scanner.nextLine();
			}
		}
		while(typeMismatch || illegalInput);
		
		return input;
	}
	
}