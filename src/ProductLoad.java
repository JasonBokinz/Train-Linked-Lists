/**
 * This class is used when you need to create a new ProductLoad or obtain the information from the parameters
 * 
 *  @author jasonbokinz, ID:112555537, R:03
 *
 */
public class ProductLoad {
	
	/**
	 * Below are the parameters for the ProductLoad
	 * 
	 * @param weight
	 * Double weight of the ProductLoad
	 * @param value
	 * Double value of the ProductLoad
	 * @param name
	 * String name of the ProductLoad
	 * @param isDangerous
	 * Boolean whether of not the ProductLoad is dangerous
	 */
	private double weight, value;
	private String name;
	private boolean isDangerous;
	
	
	/**
	 * This is a constructor that creates an instance of ProductLoad with the given parameters
	 * 
	 * @param name
	 * String name of the ProductLoad
	 * @param weight
	 * Double weight of the ProductLoad
	 * @param value
	 * Double value of the ProductLoad
	 * @param isDangerous
	 * Boolean whether or not the ProductLoad is dangerous
	 */
	public ProductLoad(String name, double weight, double value, boolean isDangerous) {
		this.name = name;
		this.weight = weight;
		this.value = value;
		this.isDangerous = isDangerous;
	}
	
	/**
	 * This method returns the weight of the ProductLoad since weight is a private parameter
	 * 
	 * @return
	 * Double weight of the ProductLoad
	 */
	public double getWeight() {
		return weight;
	}
	
	/**
	 * This method sets weight to the new parameter newWeight
	 * 
	 * @param newWeight
	 * Double being set to weight
	 * @throws IllegalArgumentException
	 * if the new weight is negative 
	 */
	public void setWeight(double newWeight) throws IllegalArgumentException {
		if (newWeight < 0)
			throw new IllegalArgumentException("Weight entered is negative!");
		weight = newWeight;
	}
	
	/**
	 * This method returns the value of the ProductLoad since value is a private parameter
	 * 
	 * @return
	 * Double value of the ProductLoad
	 */
	public double getValue() {
		return value;
	}
	
	/**
	 * This method sets value to the new parameter newValue
	 * 
	 * @param newValue
	 * Double being set to value
	 * @throws IllegalArgumentException
	 * if the new value is negative 
	 */
	public void setValue(double newValue) throws IllegalArgumentException {
		if (newValue < 0)
			throw new IllegalArgumentException("Value entered is negative!");
		value = newValue;
	}
	
	/**
	 * This method returns the name of the ProductLoad since name is a private parameter
	 * 
	 * @return
	 * String name of the ProductLoad
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * This method sets name to the new parameter newName
	 * 
	 * @param newName
	 * String being set to name
	 * @throws IllegalArgumentException
	 * if the string is a double by itself (not if the name includes a number/double)
	 */
	public void setName(String newName) throws IllegalArgumentException {
		int counter = 0;
		for (int i = 0; i < newName.length();i++) {
			if (Character.isLetter(newName.charAt(i)))
				counter++;
		}
		if (counter == 0)
			throw new IllegalArgumentException("Enter a name, not a double!");
		name = newName;
	}
	
	/**
	 * This method is used to see if the ProductLoad is dangerous or not
	 * 
	 * @return
	 * True if it's dangerous, false if not
	 */
	public boolean getIsDangerous() {
		return isDangerous;
	}
	
	/**
	 * This method sets if the ProductLoad is dangerous or not
	 * 
	 * @param newIsDangerous
	 * Boolean being set to isDangerous
	 */
	public void setIsDangerous(boolean newIsDangerous) {
		isDangerous = newIsDangerous;
	}
}
