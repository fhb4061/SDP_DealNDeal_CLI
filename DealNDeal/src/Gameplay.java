import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * @author Jonathan Lopeti
 */
public class Gameplay {

	/**************************************************************************************
	 *>>valuesLeftToPlay: Contains the money, and removed one by one when the money value has
	 * already been played.
	 *>>alreadyPlayedValues: values that are already viewed, also used keep track of how many
	 *cases has been opened.
	 *>>cases: are the cases being displayed and being played in the Game
	 *>>gameOver: made "true" when does not want to continue playing
	 *>>personalCase: holds the players case
	 *>>userInput: saves the values inserted by the user, for multiple use in methods
	 **************************************************************************************/
	static ArrayList<Integer> alreadyPlayedValues; 
	static ArrayList<Integer> valuesLeftToPlay;
	static ArrayList<Cases> cases;
	static Scanner in = new Scanner(System.in);
	static Cases playerCase;
	static Banker bankers;
	static boolean gameOver = false;
	static int userInput = 0;


	/********************************************************************************
	 * Makes new instance every time it is called, aimed at the purpose of making a
	 * NEW GAME when it is called. Only if player wants to play again.
	 ********************************************************************************/
	public static void setCases(){
		alreadyPlayedValues =  new ArrayList<Integer>(Arrays.asList(1,5,10,25,50,75,100,200,300,400,500,750,1000,5000,10000,25000,50000,75000,100000,200000,300000,400000,500000,750000,1000000));
		cases = new ArrayList<Cases>();
		valuesLeftToPlay = new ArrayList<Integer>();
		Collections.shuffle(alreadyPlayedValues);
		
		for(int i = 0; i < 25; i++){
			cases.add(new Cases((i+1),alreadyPlayedValues.get(0)));
			valuesLeftToPlay.add(alreadyPlayedValues.get(0));
			alreadyPlayedValues.remove(0);
		}
	}

	public static void removeCase(int userInput){
		alreadyPlayedValues.add((cases.get(userInput - 1).getMoney())); // Already played money values
		valuesLeftToPlay.remove(valuesLeftToPlay.indexOf(cases.get(userInput - 1).getMoney())); // Removing already played values
		cases.set(userInput - 1, null); //setting case chosen to null, so it doesn't change the index of the cases
	}
	
	/**************************************************************************************
	 * @param oneOrLastCase: String that would say if it is the LAST case to open,or
	 * just open "one" for the last 3 rounds.
	 * Only accept user input of either 1 or 2, where 1 = deal & 2 = No Deal.
	 * Deal = user wins the bankers offer and asked if user wants to play a New Game.
	 * No Deal = if number of cases opened is 23 then game will end and show the players
	 * personal case
	 ****************************************************************************************/
	public static void dealOrNoDeal(String oneOrLastCase){
		
		Display.displayCases(cases);
		System.out.print("Pick "+oneOrLastCase+" case:");
		userInput = in.nextInt(); // Pick the "LAST" or just 1 case when 20 case has already been opened
		System.out.println("Case: "+userInput+" held: $"+cases.get(userInput - 1).getMoney());
		removeCase(userInput);
		bankers = new Banker(Collections.max(valuesLeftToPlay), Collections.min(valuesLeftToPlay));	// Make new offer every time its called.
		bankers.calculateOffer(); //Calculating the bankers offer
		
		Display.moneyLeftInPlay(valuesLeftToPlay);
		System.out.println(bankers);
		System.out.println("To Deal, press 1: \nNo Deal, press 2:");
		userInput = in.nextInt();
		
		if(userInput == 1){	//when player Deals and accepts the offer
			Display.dealAccepted(bankers.getOffer()); // will show what player wins, and asked if want to play again.
			userInput = in.nextInt();
			
			if(userInput == 1){	//when player wants to play another game
				setCases(); 	// Sets up a New Game
				Display.displayCases(cases);
			}
			else 
				gameOver = true;
		}
		else if(userInput == 2){	//when player wants rejects the offer (No Deal)
			
			if(valuesLeftToPlay.size() == 2){	// if only one more case left to open then Game will end
				Display.rejectOffer(playerCase.getMoney());
				valuesLeftToPlay.remove(valuesLeftToPlay.indexOf(playerCase.getMoney()));
				System.out.println("The Last case held: $"+valuesLeftToPlay.get(0));
				gameOver = true;
			}
		}
		else
			throw new InputMismatchException();
		
	}
	
	/**
	 * gamePlay: is the actual flow of the game where it restricts the number of cases opened in each round. Also
	 * restrict number of cases opened to 1 only once number of cases opened is greater than 20
	 */
	public static void gamePlay(){
		do{
			try{
				if(alreadyPlayedValues.size() == 6 || alreadyPlayedValues.size() == 11 || alreadyPlayedValues.size() == 15 ||
						alreadyPlayedValues.size() == 18 || alreadyPlayedValues.size() == 20)	//prompt for the last case of EACH round
				{
					dealOrNoDeal("your LAST");
				}
				else if(alreadyPlayedValues.size() == 0){	//prompt for the personal case of the user
					System.out.print("Pick your personal case:");
					userInput = in.nextInt();
					playerCase = new Cases(userInput,cases.get(userInput - 1).getMoney());
					alreadyPlayedValues.add(cases.get(userInput - 1).getMoney());
					cases.set(userInput - 1, null);
				}
				else if(alreadyPlayedValues.size() > 20){	//prompt for the rounds that requires to open only 1 case
					dealOrNoDeal("1");
				}
				else{ //prompt for the multiple case opened during each rounds
					Display.displayCases(cases);
					System.out.print("Pick a case please: ");
					userInput = in.nextInt();
					System.out.println("Case: "+userInput+" held: $"+cases.get(userInput - 1).getMoney());
					removeCase(userInput);			
				}
			}
			catch(InputMismatchException e){
				System.err.println("Please input valid value:");
				in.next();
			}
			catch(IndexOutOfBoundsException e){
				System.err.println("That case does not exist");
			}
			catch(NullPointerException e){
				System.err.println("That case has already been opened");
			}
		}while(!gameOver);
	}

	
	public static void main(String[] args){
		
		setCases();
		Display.welcomeText();
		Display.displayCases(cases);
		gamePlay();
	}


}
