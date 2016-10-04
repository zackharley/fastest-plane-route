/**
 * This class is used to store the Optimal Route information for a route between two 
 * cities.
 * 
 * @author Zack Harley
 */

import java.util.ArrayList;

public class OptimalRoute {
	
	private ArrayList<Integer> cities = new ArrayList<Integer>();
	private int source;
	private int destination;
	private int timeRequired;
	
	/**
	 * The constructor for the OptimalRoute class when an optimal route between two cities
	 * exists.
	 * 
	 * @param paths - The paths in the optimal route
	 */
	public OptimalRoute(ArrayList<Path> paths) {
		this.source = paths.get(0).getSource();
		this.destination = paths.get(paths.size() - 1).getDestination();
		int startingNode;
		int endingNode;
		
		for(int i = 0; i < paths.size(); i++) {
			startingNode = paths.get(i).getSource();
			endingNode = paths.get(i).getDestination();
			
			if(this.isFirstPath(i)) {
				this.cities.add(startingNode);
			}
			
			this.cities.add(endingNode);
		}
		
		this.timeRequired = paths.get(paths.size() - 1).getArrivalTime();
	}
	
	/**
	 * The constructor for the OptimalRoute class when an optimal route between two cities
	 * does not exist.
	 * 
	 * @param source - The city that we depart from
	 * @param destination - The city we intended to arrive at
	 */
	public OptimalRoute(int source, int destination) {
		this.source = source;
		this.destination = destination;
		this.timeRequired = (int)Integer.MAX_VALUE;
		this.cities = new ArrayList<Integer>();
	}
	
	private Boolean isFirstPath(int index) {
		return index == 0;
	}
	
	/** 
	 * @return The string representation of the OptimalRoute class
	 */
	public String toString() {
		if(this.timeRequired != Integer.MAX_VALUE) {
			return "The optimal route from " + 
				this.source + 
				" to " + 
				this.destination + 
				" passes through cities " + 
				this.cities.toString() + 
				" and arrives at time " +
				this.timeRequired;
		} else {
			return "There is no possible route from " +
				this.source +
				" to " +
				this.destination;
		}
		
	}
	
}
