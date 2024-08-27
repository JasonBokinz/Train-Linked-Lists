/**
 * This class acts as a node wrapper around a TrainCar object
 * 
 * @author jasonbokinz, ID:112555537, R:03
 *
 */
public class TrainCarNode {
	
	/**
	 * Below are the parameters for the TrainCarNode
	 * 
	 * @param prev
	 * points to the car before the cursor if one exists
	 * @param next
	 * point to the car after the cursor if one exists
	 * @param car
	 * The TrainCar being used
	 */
	private TrainCarNode prev, next;
	private TrainCar car;
	
	/**
	 * This is a constructor that creates a default instance of a TrainCarNode
	 */
	public TrainCarNode() {
		prev = null;
		next = null;
		car = null;
	}
	
	/**
	 * This is a constructor that creates an instance of TrainCarNode with the given parameter
	 * 
	 * @param car
	 * The TrainCar being used
	 */
	public TrainCarNode(TrainCar car) {
		this.prev = null;
		this.next = null;
		this.car = car;
	}
	
	/**
	 * This method returns the previous TrainCarNode since prev is a private parameter
	 * 
	 * @return
	 * TrainCarNode prev
	 */
	public TrainCarNode getPrev() {
		return prev;
	}
	
	/**
	 * This method sets prev to the new parameter newPrev
	 * 
	 * @param newPrev
	 * TrainCarNode being set to prev
	 */
	public void setPrev(TrainCarNode newPrev) {
		prev = newPrev;
	}
	
	/**
	 * This method returns the next TrainCarNode since next is a private parameter
	 * 
	 * @return
	 * TrainCarNode next
	 */
	public TrainCarNode getNext() {
		return next;
	}
	
	/**
	 * This method sets next to the new parameter newNext
	 * 
	 * @param newNext
	 * TrainCarNode being set to next
	 */
	public void setNext(TrainCarNode newNext) {
		next = newNext;
	}
	
	/**
	 * This method returns the car TrainCar since car is a private parameter
	 * 
	 * @return
	 * TrainCar car
	 */
	public TrainCar getCar() {
		return car;
	}
	
	/**
	 * This method sets car to the new parameter newCar
	 * 
	 * @param newCar
	 * TrainCar being set to car
	 */
	public void setCar(TrainCar newCar) {
		car = newCar;
	}
	
	/**
	 * This method neatly format the TrainCarNode for when the time comes to print the node
	 * Used when printManifest() is called
	 * 
	 * Can't include the numbering here, so that part is done in printManifest() itself
	 * 
	 * @overrides toString
	 * overrides the object class's toString() method
	 * @return
	 * Neat version of the TrainCarNode in a table format
	 */
	
	public String toString() {
		String strDanger;
	
		if (this.getCar().getProductLoad() != null) {
			if (this.getCar().getProductLoad().getIsDangerous())
				strDanger = "YES";
			else
				strDanger = "NO";
			return String.format("%,14.1f%,14.1f%3s%10s%,14.1f%,14.2f%12s", this.getCar().getCarLength(), this.getCar().getCarWeight(), "|", this.getCar().getProductLoad().getName(), 
					this.getCar().getProductLoad().getWeight(), this.getCar().getProductLoad().getValue(), strDanger);
		}
		else 
			return String.format("%,14.1f%,14.1f%3s%10s%,14.1f%,14.2f%12s", this.getCar().getCarLength(), this.getCar().getCarWeight(), "|", "Empty", 0.0, 0.0, "NO");
	}
}
