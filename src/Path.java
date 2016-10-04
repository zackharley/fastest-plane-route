/**
 * A class that contains information regarding a specific flight path.
 * 
 * @author Zack Harley
 */

public class Path {
	
	private int source;
	private int destination;
	private int departureTime;
	private int arrivalTime;
	
	/**
	 * Constructor for the Path class
	 * 
	 * @param source - The city to depart from
	 * @param destination - The city to arrive at
	 * @param departureTime - The time to depart at
	 * @param arrivalTime - The time to arrive at
	 */
	public Path(int source, int destination, int departureTime, int arrivalTime) {
		this.source = source;
		this.destination = destination;
		this.departureTime = departureTime;
		this.arrivalTime = arrivalTime;
	}

	/**
	 * Getter for the source attribute
	 * 
	 * @return The city to depart from
	 */
	public int getSource() {
		return this.source;
	}

	/**
	 * Getter for the destination attribute
	 * 
	 * @return The city to arrive at
	 */
	public int getDestination() {
		return this.destination;
	}

	/**
	 * Getter for the departureTime attribute
	 * 
	 * @return The time to depart from
	 */
	public int getDepartureTime() {
		return this.departureTime;
	}

	/**
	 * Getter for the arrivalTime attribute
	 * 
	 * @return The time to arrive at
	 */
	public int getArrivalTime() {
		return this.arrivalTime;
	}
	
	/**
	 * Getter for the time spent traveling along the path
	 * 
	 * @return The city to depart from
	 */
	public int getPathTime() {
		return this.arrivalTime - this.departureTime;
	}
	
}
