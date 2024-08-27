/**
 * This class contains the main program which is used to take in methods from public supported classes
 * also imports Java's Scanner feature to take inputs from the user
 *  @author jasonbokinz, ID:112555537, R:03
 *
 */
import java.util.Scanner;
public class TrainManager {
	
	/**
	 * This method prints out the menu at the beginning of the while loop
	 */
	public static void printMenu() {
		System.out.println("(F) Cursor Forward");
		System.out.println("(B) Cursor Backward");
		System.out.println("(I) Insert Car After Cursor");
		System.out.println("(R) Remove Car At Cursor");
		System.out.println("(L) Set Product Load");
		System.out.println("(S) Search For Product");
		System.out.println("(T) Display Train");
		System.out.println("(M) Display Manifest");
		System.out.println("(D) Remove Dangerous Cars");
		System.out.println("(Q) Quit\n");
	}
	
	/**
	 * This method is called when you want to communicate with the user and you want them to input a Double
	 * 
	 * @param str
	 * Instructions of what you want the user to enter that gets printed in the console
	 * @return
	 * Double you want to store
	 * @throws IllegalArgumentException
	 * if the double entered is negative or not a double at all
	 */
	public static double promptDouble(String str) throws IllegalArgumentException {
		System.out.println(str);
		Scanner doubleInput = new Scanner(System.in);
		String inputStr = doubleInput.nextLine();
		for (int i = 0; i < inputStr.length();i++) {
			if (!Character.isDigit(inputStr.charAt(i))) {
				if (inputStr.charAt(i) == '.')
					continue;
				else if (inputStr.charAt(i) == '-')
					throw new IllegalArgumentException("Double is a negative value!");
				throw new IllegalArgumentException("Invalid double entered!");
			}
		}
		Double inputDoub = Double.parseDouble(inputStr);
		return inputDoub;
	}
	
	/**
	 * This method is called when you want to communicate with the user and you want them to input a String
	 * 
	 * @param str
	 * Instructions of what you want the user to enter that gets printed in the console
	 * @return
	 * String you want to store
	 * @throws IllegalArgumentException
	 * if the string entered is a double by itself (not if the name includes a number/double)
	 */
	public static String promptString(String str) throws IllegalArgumentException {
		int counter = 0;
		System.out.println(str);
		Scanner strInput = new Scanner(System.in);
		String inputStr = strInput.nextLine();
		for (int i = 0; i < inputStr.length();i++) {
			if (Character.isLetter(inputStr.charAt(i)))
				counter++;
		}
		if (counter == 0)
			throw new IllegalArgumentException("Enter a string, not a double!");
		return inputStr;
	}
	
	/**
	 * Main method of the whole Train program, which calls multiple methods and communicates with the user
	 * @param args
	 * Array of Strings?
	 */
	public static void main(String[] args) {
		TrainLinkedList train = new TrainLinkedList();
		String selection = "";
		Scanner input = new Scanner(System.in);
		
		/**
		 * This while loop continues until the user enters 'Q' to quit the loop
		 */
		while (!selection.equalsIgnoreCase("Q")) {
			
			printMenu();
			
			try {
			
			System.out.println("Enter a selection: ");
			selection = input.nextLine().toUpperCase();
			
			switch(selection) {
			
			/**
			 * User enters 'F' to move the cursor forward
			 * 
			 * Specs say if cursor is tail or null, throw Exception (That's why there is a different print statement)
			 */
			case "F": 
				
				train.cursorForward();
				
				break;
				
			/**
			 * User enters 'B' to move cursor backward
			 * 
			 * Specs say if cursor is head or null, throw Exception (That's why there is a different print statement)
			 */
			case "B":
				
				train.cursorBackward();
				
				break;
			
			/**
			 * User enters 'I' to insert a TrainCar after the cursor
			 */
			case "I":
				
				double length = promptDouble("Enter car length in meters: ");
				double weight = promptDouble("Enter car weight in tons: ");
				TrainCar insert = new TrainCar(weight, length, null);
				train.insertAfterCursor(insert);
				System.out.println("\nNew train car " + String.format("%,.1f", length) + " meters " + String.format("%,.1f", weight) + " tons inserted into train.\n");
				
				break;
			
			/**
			 * User enters 'R' to remove the TrainCar at the cursor
			 */
			case "R":
						TrainCar removed = train.removeCursor();
						ProductLoad load = removed.getProductLoad();
						String strDanger = "NO";
						if (load != null) {
							if (load.getIsDangerous())
							strDanger = "YES";
							System.out.println("\nCar successfully unlinked. The following load has been removed from the train:\n");
							System.out.println(String.format("%8s%16s%14s%12s", "Name", "Weight (t)", "Value ($)", "Dangerous"));
							System.out.println("===================================================");
							System.out.println(String.format("%10s%,14.1f%,14.2f%12s\n", load.getName(), load.getWeight(), load.getValue(), strDanger));
						}
						else {
							System.out.println(String.format("%8s%16s%14s%12s", "Name", "Weight (t)", "Value ($)", "Dangerous"));
							System.out.println("===================================================");
							System.out.println(String.format("%10s%14.1f%,14.2f%12s\n", "Empty", 0.0, 0.00, "NO"));
						}
						
				break;
				
			/**
			 * User enters 'L' to set the cursor's TrainCar's ProductLoad	
			 */
			case "L":
				
				if (train.size() == 0) 
					System.out.println("\nThere are no train cars to set a product load to!\n");
				else {
					ProductLoad currentLoad;
					String loadName = promptString("Enter product name: ");
					double loadWeight = promptDouble("Enter product weight in tons: ");
					double loadValue = promptDouble("Enter product value in dollars: ");
					String testDanger = promptString("Enter is product dangerous? (y/n): ").toUpperCase();
				
					switch(testDanger) {
				
					/**
					 * If the user enters 'y' for if the load is dangerous or not
					 */
					case "Y": 
						/**
						 * Below is used for if you want to re-set a product load
						 */
						currentLoad = train.getCursorData().getProductLoad();
						if (currentLoad != null) {
							train.subTotalWeight(currentLoad.getWeight());
							train.subTotalValue(currentLoad.getValue());
							if (currentLoad.getIsDangerous())
								train.subTotalDangerous();
						}
						train.addTotalDangerous();
						train.addTotalWeight(loadWeight);
						train.addTotalValue(loadValue);
						ProductLoad loadYes = new ProductLoad(loadName, loadWeight, loadValue, true);
						train.getCursorData().setProductLoad(loadYes);
						System.out.println("\n" + String.format("%,.1f", loadWeight) + " tons of " + loadName + " added to the current car.\n");
						break;
					
						/**
						 * If the user enters 'n' for if the load is dangerous or not
						 */	
					case "N":
						currentLoad = train.getCursorData().getProductLoad();
						if (currentLoad != null) {
							train.subTotalWeight(currentLoad.getWeight());
							train.subTotalValue(currentLoad.getValue());
							if (currentLoad.getIsDangerous())
								train.subTotalDangerous();
						}
						train.addTotalWeight(loadWeight);
						train.addTotalValue(loadValue);
						ProductLoad loadNo = new ProductLoad(loadName, loadWeight, loadValue, false);
						train.getCursorData().setProductLoad(loadNo);
						System.out.println("\n" + String.format("%,.1f", loadWeight) + " tons of " + loadName + " added to the current car.\n");
						break;
				
						/**
						 * If the user enters an invalid answer for if the load is dangerous or not
						 */
					default:
						System.out.println("\nInvalid input for product danger! Returning to menu...\n");
						break;
					}
				}
				
					break;
				
			/**
			 * User enters 'S' to search for a certain products name and retrieve total stats of the products with the name if they exist
			 */
			case "S":
				
				if (train.size() == 0) 
					System.out.println("\nThere are no train cars to search!\n");
				else {
					String name = promptString("Enter product name:");
					train.findProduct(name);
				}
				
				break;
				
			/**
			 * User enters 'T' to display the total stats of the whole train	
			 */
			case "T":
				
				System.out.println(train);
				
				break;
			
			/**
			 * User enters 'M' to print out all of the information of each car in the train	
			 */
			case "M":
				
				train.printManifest();
				
				break;
				
			/**
			 * User enters 'D' to remove all of the dangerous cars in the train	
			 */
			case "D":
				
				if (train.size() == 0) 
					System.out.println("\nThere are no train cars to remove!\n");
				else 
					train.removeDangerousCars();
				
				break;
				
			}
			
			/**
			 * Below catches any Exception in the code, which is going to be an input Exception
			 * Specs say once a user inputs an invalid response, return to the menu
			 */
			} catch (IllegalArgumentException ex) {
				System.out.println("\n" + ex + " Returning to menu...\n");
			} catch (Exception ex) {
				System.out.println("\n" + ex + " Returning to menu...\n");
			}
		}
		
		/**
		 * User enters 'Q' to terminate the code
		 */
		System.out.println();
		System.out.println("Program terminating successfully...");
	}
}