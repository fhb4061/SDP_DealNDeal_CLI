import java.util.ArrayList;
import java.util.Collections;

/**
 * This class holds all the functions that shows the display of the game
 *
 */
public class Display {
	
	/************************************************************************************************
	 * The case "board" where cases are displayed having an empty[ ] case when its already opened and	
	 *           cases that are still in play with [1] [2]...
	 * **********************************************************************************************/
	public static void displayCases(ArrayList<Cases> cases){

		int caseCount = 0;	//increments to iterate through all the cases values
		for(int row = 0; row < 5; row++){
			for(int colom = 0; colom < 5; colom++){
				if(cases.get(caseCount) == null){ // which cases that has been opened, and which hasn't
					System.out.print("[ ]\t");
				}
				else{
					System.out.print("["+cases.get(caseCount).getCaseNumber()+"]\t");
				}
				caseCount++;
			}
			System.out.println("");
		}
	}

	// The welcoming text when first opened
	public static void welcomeText(){
		System.out.println("*********************************");
		System.out.println("            Welcome");
		System.out.println("               to");
		System.out.println("          DEAL OR NO DEAL");
		System.out.println("*********************************");
	}

	// Displays the money values that are still left to be played.
	public static void moneyLeftInPlay(ArrayList<Integer> valuesLeftToPlay){
		
		Collections.sort(valuesLeftToPlay); 
		System.out.println("\n\tMONEY LEFT IN PLAY");
		for(int i = 0; i < valuesLeftToPlay.size(); i++){
			System.out.println("$"+valuesLeftToPlay.get(i));
		}
	}
	// Display shown when user accepts the deal
	public static void dealAccepted(int moneyWon){
		
		System.out.println("\tYOU'VE WON");
		System.out.println("\t  $"+moneyWon);
		System.out.println("Do you want to play again?");
		System.out.println("Yes, Press 1:");
		System.out.println("No , Press 2:");
	}
	
	public static void rejectOffer(int personalCaseValue){
		
		System.out.println("You have rejected the offer");
		System.out.println("Your case contained: $"+personalCaseValue);
		System.out.println("\tYou've WON");
		System.out.println("$"+personalCaseValue);
		
	}
}
