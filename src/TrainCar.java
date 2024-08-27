/**
 * This class is used when you need to create a new TrainCar or obtain the information from the parameters
 * 
 * @author jasonbokinz, ID:112555537, R:03
 *
 */
public class TrainCar {
	
	/**
	 * Below are the parameters of a TrainCar itself
	 * 
	 * @param carLength
	 * length of the TrainCar
	 * @param carWeight
	 * weight of the TrainCar
	 * @param load
	 * ProductLoad load of the TrainCar
	 */
	private double carLength, carWeight;
	private ProductLoad load;
	
	/**
	 * This is a constructor that creates an instance of TrainCar with the given parameters.
	 * 
	 * @param carWeight
	 * Double weight of the TrainCar
	 * @param carLength
	 * Double length of the TrainCar
	 * @param load
	 * ProductLoad load of the TrainCar
	 */
	public TrainCar(double carWeight, double carLength, ProductLoad load) {
		this.carLength = carLength;
		this.carWeight = carWeight;
		this.load = load;
	}
	
	/**
	 * This method returns the weight of the TrainCar since carWeight is a private parameter
	 * 
	 * @return
	 * Double CarWeight of the TrainCar
	 */
	public double getCarWeight() {
		return carWeight;
	}
	
	/**
	 * This method returns the length of the TrainCar since carLength is a private parameter
	 * 
	 * @return
	 * Double carLength of the TrainCar
	 */
	public double getCarLength() {
		return carLength;
	}
	
	/**
	 * This method returns the load of the TrainCar since load is a private parameter
	 * 
	 * @return
	 * ProductLoad load of the TrainCar
	 */
	public ProductLoad getProductLoad() {
		return load;
	}
	
	/**
	 * This method sets load to the new parameter load
	 * 
	 * @param load
	 * ProductLoad load being set to load
	 */
	public void setProductLoad(ProductLoad load) {
		this.load = load;
	}
	
	/**
	 * This method is used to see if the TrainCar is empty (has no reference)
	 * 
	 * @return
	 * True if it's empty, false if it's not
	 */
	public boolean isEmpty() {
		return this.getProductLoad().equals(null);
	}
}
