/**
 * This class works with a double-linked list
 * 
 *  @author jasonbokinz, ID:112555537, R:03
 *
 */
public class TrainLinkedList {
	
	/**
	 * Below are the parameters for the TrainLinkedList
	 * 
	 * @param head
	 * references the first node of the linked list
	 * @param tail
	 * references the last node of the linked list
	 * @param cursor
	 * references the current node being accessed
	 * @param totalCars
	 * total number of cars on the train
	 * @param totalDangerous
	 * total number of dangerous cars on the train
	 * @param totalLength
	 * total length of the train
	 * @param totalWeight
	 * total weight of the train (TrainCar and ProductLoad)
	 * @param totalValue
	 * total value of the train
	 */
	private TrainCarNode head, tail, cursor;
	private int totalCars, totalDangerous;
	private double totalLength, totalWeight, totalValue;
	
	/**
	 * This is a constructor that creates a default instance of a TrainLinkedList
	 * and sets all the parameters to null or 0
	 */
	public TrainLinkedList() {
		head = null;
		tail = null;
		cursor = null;
		totalCars = 0;
		totalDangerous = 0;
		totalLength = 0;
		totalWeight = 0;
		totalValue = 0;
	}
	
	/**
	 * This method adds the length of the TrainCar to the totalLength
	 * Used when inserting a TrainCar
	 * 
	 * @param length
	 * Double length of TrainCar
	 */
	public void addTotalLength(double length) {
		totalLength += length;
	}
	
	/**
	 * This method subtracts the length of the TrainCar from the totalLength
	 * Used when removing a TrainCar
	 * 
	 * @param length
	 * Double length of TrainCar
	 */
	public void subTotalLength(double length) {
		totalLength -= length;
	}
	
	/**
	 * This method adds the weight of the TrainCar/ProductLoad to the totalWeight
	 * Used when setting a product load or inserting a TrainCar
	 * 
	 * @param weight
	 * Double weight of TrainCar or ProductLoad
	 */
	public void addTotalWeight(double weight) {
		totalWeight += weight;
	}
	
	/**
	 * This method subtracts the weight of the TrainCar/ProductLoad from the totalWeight
	 * Used when setting a product load or removing a TrainCar
	 * 
	 * @param weight
	 * Double weight of TrainCar or ProductLoad
	 */
	public void subTotalWeight(double weight) {
		totalWeight -= weight;
	}
	
	/**
	 * This method adds the value of the ProductLoad to the totalValue
	 * Used when setting a ProductLoad
	 * 
	 * @param value
	 * Double value of ProductLoad
	 */
	public void addTotalValue(double value) {
		totalValue += value;
	}
	
	/**
	 * This method subtracts the value of the ProductLoad from the totalValue
	 * Used when setting a product load or removing a TrainCar
	 * 
	 * @param value
	 * Double value of ProductLoad
	 */
	public void subTotalValue(double value) {
		totalValue -= value;
	}
	
	/**
	 * This method adds 1 to the total number of dangerous cars
	 */
	public void addTotalDangerous() {
		totalDangerous++;
	}
	
	/**
	 * This method subtracts 1 to the total number of dangerous cars
	 */
	public void subTotalDangerous() {
		totalDangerous--;
	}
	
	/**
	 * This method returns the TrainCar of the node that the cursor is currently located at
	 * 
	 * @return
	 * TrainCar of the node that the cursor is pointing at
	 * @throws Exception
	 * when the cursor is null
	 */
	public TrainCar getCursorData() throws Exception {
		if (cursor == null)
			throw new Exception("Cursor is null");
		return cursor.getCar();
	}
	
	/**
	 * This method sets the TrainCar to the node that the cursor is pointing at
	 * 
	 * @param car
	 * The TrainCar that you want to set at the cursor
	 * @throws Exception
	 * when the cursor is null
	 */
	public void setCursorData(TrainCar car) throws Exception {
		if (cursor == null)
			throw new Exception("Cursor is null");
		cursor.setCar(car);
	}
	
	/**
	 * This method attempts to move the cursor forward if possible and prints out a message if it can or not
	 * 
	 * @throws Exception
	 * if the cursor is null or cursor is tail
	 */
	public void cursorForward() throws Exception {
		if (cursor == null)
			throw new Exception("The list is currently empty (cursor is null).");
		else if (cursor == tail)
			throw new Exception("No next car, cannot move cursor forward.");
		else {
			cursor = cursor.getNext();
			System.out.println("\nCursor moved forward.\n");
		}
	}
	
	/**
	 * This method attempts to move the cursor backward if possible and prints out a message if it can or not
	 * 
	 * @throws Exception
	 * if the cursor is null or cursor is head
	 */
	public void cursorBackward() throws Exception {
		if (cursor == null)
			throw new Exception("The list is currently empty (cursor is null).");
		else if (cursor == head)
			throw new Exception("No previous car, cannot move cursor backward.");
		else {
			cursor = cursor.getPrev();
			System.out.println("\nCursor moved backward.\n");
		}
	}
	
	/**
	 * This method inserts the TrainCar after the cursor and alters total amount counters
	 * Also changes the setNext() and setPrev() to maintain the doubly linked list
	 * 
	 * @param newCar
	 * The TrainCar you want to insert
	 * @throws IllegalArgumentException
	 * If the TrainCar you want to insert is null
	 */
	public void insertAfterCursor(TrainCar newCar) throws IllegalArgumentException {
		if (newCar == null)
			throw new IllegalArgumentException("The train car is null!");
		
		TrainCarNode newNode = new TrainCarNode(newCar);
		totalLength += newCar.getCarLength();
		totalWeight += newCar.getCarWeight();
		totalCars++;
		ProductLoad load = newCar.getProductLoad();
		/**
		 * For this assignment this if statement is a dead code since during every insertion the load is null
		 * 
		 * Kept just in case test cases have the inserted TrainCar with a ProductLoad
		 */
		if (load != null) {
			totalValue += load.getValue();
			totalWeight += load.getWeight();
			if (load.getIsDangerous())
				totalDangerous++;
		}
		if (cursor == null) {
			newNode.setNext(null);
			newNode.setPrev(null);
			cursor = newNode;
			head = newNode;
			tail = newNode;
		}
		else if (cursor == tail) {
			newNode.setNext(null);
			newNode.setPrev(cursor);
			cursor.setNext(newNode);
			tail = newNode;
			cursor = tail;
		}
		else {
			newNode.setNext(cursor.getNext());
			newNode.setPrev(cursor);
			cursor.setNext(newNode);
			newNode.getNext().setPrev(newNode);
			cursor = newNode;
		}
	}
	
	/**
	 * This method removes the TrainCar at the cursor and alters total amount counters while returning the moved car
	 * Also changes the setNext() and setPrev() to maintain the doubly linked list
	 * 
	 * @return
	 * The removed TrainCar
	 */
	public TrainCar removeCursor() throws Exception {
		if (cursor == null)
			throw new Exception("The list is currently empty (cursor is null).");
		TrainCar removed = null;
		removed = cursor.getCar();
		if ((cursor == head) && (cursor == tail)) {
			cursor = null;
			head = null;
			tail = null;
		}
		else if (cursor == head) {
			cursor = cursor.getNext();
			cursor.setPrev(null);
			head = cursor;
		}
		else if (cursor == tail) {
			cursor = cursor.getPrev();
			cursor.setNext(null);
			tail = cursor;
		}
		else {
			cursor.getPrev().setNext(cursor.getNext());;
			cursor.getNext().setPrev(cursor.getPrev());
			cursor = cursor.getNext();
		}
		totalCars--;
		totalLength -= removed.getCarLength();
		totalWeight -= removed.getCarWeight();
		ProductLoad load = removed.getProductLoad();
		if (load != null) {
			if (load.getIsDangerous())
				totalDangerous--;
			totalWeight -= load.getWeight();
			totalValue -= load.getValue();
		}
		return removed;
	}
	
	/**
	 * This method simply returns the totalCars counter
	 * This counter is altered if cars are inserted or removed
	 * 
	 * @return
	 * number of cars on train
	 */
	public int size() {
		return totalCars;
	}
	
	/**
	 * This method simply returns the totalLength counter
	 * This counter is altered if the cars are inserted or removed
	 * 
	 * @return
	 * Double totalLength of train
	 */
	public double getLength() {
		return totalLength;
	}
	
	/**
	 * This method simply returns the totalWeight counter
	 * This counter is altered if the cars are inserted or removed
	 * 
	 * @return
	 * Double totalWeight of train (TrainCar and ProductLoad)
	 */
	public double getWeight() {
		return totalWeight;
	}
	
	/**
	 * This method simply returns the totalValue counter
	 * This counter is altered if the cars are inserted or removed
	 * 
	 * @return
	 * Double totalValue of train
	 */
	public double getValue() {
		return totalValue;
	}
	
	/**
	 * This method simply returns if totalDangerous counter is greater than or equals to 1
	 * This counter is altered if the cars are inserted or removed
	 * 
	 * @return
	 * True if totalDangerous is greater than or equal to 1, and false if not
	 */
	public boolean isDangerous() {
		return totalDangerous >= 1;
	}
	
	/**
	 * This method traverses through the doubly linked list and determines if 
	 * each car's ProductLoad's name is equal to the name the user is looking for using an ignore case (If data is just copied)
	 * and totals all of there weight, value, and determines if it is dangerous of not
	 * 
	 * @param name
	 * The name of the ProductLoad searching for
	 */
	public void findProduct(String name) {
		TrainCarNode traverser = new TrainCarNode();
		traverser = head;
		int counter = 0;
		double findWeight = 0, findValue = 0;
		boolean findDanger = false, first = false;
		String strDanger = "NO";
		
		while (traverser != null) {
			ProductLoad currentLoad = traverser.getCar().getProductLoad();
			if (currentLoad == null)
				break;
			if (currentLoad.getName().trim().equalsIgnoreCase(name.trim())) {
				if (!first) {
					findDanger = currentLoad.getIsDangerous();
					first = true;
				}
				counter++;
				findWeight += currentLoad.getWeight();
				findValue += currentLoad.getValue();
			}
			traverser = traverser.getNext();
		}
		if (findDanger)
			strDanger = "YES";
		if (counter == 0) {
			System.out.println("\nNo record of " + name + " on board train.\n");
		}
		else {
			if (counter == 1)
				System.out.println("\nThe following products were found on 1 car:\n");
			else
				System.out.println("\nThe following products were found on " + counter + " cars:\n");
			System.out.println(String.format("%8s%16s%14s%12s", "Name", "Weight (t)", "Value ($)", "Dangerous"));
			System.out.println("===================================================");
			System.out.println(String.format("%10s%,14.1f%,14.2f%12s\n", name, findWeight, findValue, strDanger));
		}
	}
	
	/**
	 * This method helps printManifest to help not repeat code
	 * 
	 * @param traverser
	 * temporary cursor's position
	 * @param i
	 * number car in the train
	 */
	public void printManifestHelper(TrainCarNode traverser, int i) {
		if (traverser == cursor)
			System.out.print("-> ");
		else
			System.out.print("   ");
	
			System.out.print(String.format("%4d", i));
			System.out.println(traverser);
	}
	
	/**
	 * This method prints out the whole train information neatly using the TrainCarNode toString()
	 * Also uses an arrow to reference the cursor's location
	 */
	public void printManifest() {
		int i = 1;
		TrainCarNode traverser = new TrainCarNode();
		traverser = head;
		if (head != null) {
			System.out.print("   ");
			System.out.println(String.format("%s%36s", "CAR:", "LOAD:"));
			System.out.print("   ");
			System.out.print(String.format("%5s%13s%14s", "Num", "Length (m)", "Weight (t)"));
			System.out.print("  |");
			System.out.println(String.format("%8s%16s%14s%12s", "Name", "Weight (t)", "Value ($)", "Dangerous"));
			System.out.print("   ");
			System.out.println("==================================+===================================================");
			while (traverser.getNext() != null) {
				printManifestHelper(traverser, i++);
				traverser = traverser.getNext();
			}
			printManifestHelper(traverser, i++);
			}
		else 
			System.out.println("\nThere are no train cars!\n");
	}
	
	/**
	 * This method traverses through the train in search of cars with a dangerous ProductLoad
	 * If the ProductLoad is dangerous, the car is passed to the removeCursor() method since the cursor is currently at that car
	 * 
	 * @throws Exception 
	 * if cursor is null (from removeCursor())
	 */
	public void removeDangerousCars() throws Exception {
		TrainCarNode temp = null;
		temp = head;
		TrainCar dangerousCar = null;
		ProductLoad load = null;
		while (temp != null) {
			load = temp.getCar().getProductLoad();
			if (load != null) {
				if (load.getIsDangerous()) {
					cursor = temp;
					dangerousCar = removeCursor();
				}
			}
			if (temp == tail)
				break;
			temp = temp.getNext();
		}
		if (dangerousCar == null)
			System.out.println("\nNo dangerous cars exist in the train!\n");
		else 
			System.out.println("\nDangerous cars successfully removed from the train.\n");
	}
	
	/**
	 * This method is used when you want to display the total amounts of each parameter in the train
	 * User enters 'T'
	 * 
	 * @overrides toString
	 * overrides the object class's toString() method
	 * @return
	 * Neat version of the TrainLinkedList
	 */
	public String toString() {
				String danger = "not dangerous";
				if (isDangerous())
					danger = "DANGEROUS";
				if (size() > 1)
					return ("\nTrain: " + size() + " cars, " + (String.format("%,.1f", getLength())) + " meters, " + (String.format("%,.1f", getWeight())) + " tons, $" + 
							(String.format("%,.2f", getValue())) + " value, " + danger + ".\n");
				else if (size() == 1)
					return ("\nTrain: 1 car, " + (String.format("%,.1f", getLength())) + " meters, " + (String.format("%,.1f", getWeight())) + " tons, $" + 
							(String.format("%,.2f", getValue())) + " value, " + danger +".\n");
				else
					return ("\nThere are no cars on the train!\n");
	}
}
