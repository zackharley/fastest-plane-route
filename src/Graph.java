/**
 * This is a class that is used as a graph containing all of the possible flight. It
 * keeps track of all possible flight paths and its main purpose is to find the optimal
 * route from a source city to a destination city using Dijkstra's Algorithm.
 * 
 * @author Zack Harley
 */

import java.util.ArrayList;
import java.util.Collections;

public class Graph {
	
	private ArrayList<Path> paths = new ArrayList<Path>();
	private ArrayList<Path> flightsTaken = new ArrayList<Path>();
	private ArrayList<Path> route = new ArrayList<Path>();
	private int currentPosition;
	private int currentTime = 0;
	
	/**
	 * This method is the constructor for the Graph class. This takes ArrayList<Integer>
	 * inputs, where the indexes on each correspond to each other (i.e. sources[i],
	 * destinations[i], departureTimes[i], and arrivalTimes[i] all belong to the same
	 * flight.
	 * 
	 * @param sources - The list of source cities, labeled as integers.
	 * @param destinations - The list of destination cities, labeled as integers.
	 * @param departureTimes - The departure time of flights.
	 * @param arrivalTimes - The arrival time of flights.
	 */
	public Graph(ArrayList<Integer> sources, ArrayList<Integer> destinations,
			ArrayList<Integer> departureTimes, ArrayList<Integer> arrivalTimes) {
		for(int i = 0; i < sources.size(); i++) {
			this.paths.add(
				new Path(
					sources.get(i), 
					destinations.get(i), 
					departureTimes.get(i), 
					arrivalTimes.get(i)
				)
			);
		}
	}
	
	/**
	 * This method is used to find the optimal route between a source city and a
	 * destination city. Either way, and OptimalRoute instance will be returned, but
	 * if there is not possible route from the source to the destination, the instance
	 * will contain that information.
	 * 
	 * @param source - The initial node in the route.
	 * @param destination - The final node in the route.
	 * @return The OptimalRoute instance containing the Path objects that make up the
	 * best route from the source to the destination
	 */
	public OptimalRoute findOptimalRoute(int source, int destination) {
		Path bestPath;
		ArrayList<Path> possiblePaths;
		this.currentPosition = source;
		this.currentTime = 0;
		flightsTaken = new ArrayList<Path>();
		int parentSource;
		route = new ArrayList<Path>();
		
		try {
			possiblePaths = this.generatePossiblePaths();
			while(this.currentPosition != destination) {
				sortPossiblePaths(destination, possiblePaths);
				bestPath = findBestPath(possiblePaths);
				this.flightsTaken.add(bestPath);	
				this.currentPosition = bestPath.getDestination();
				this.currentTime = bestPath.getArrivalTime();
				updatePossiblePaths(bestPath, possiblePaths);
			}
			
			this.currentPosition = source;
			
			parentSource = flightsTaken.get(flightsTaken.size() - 1).getSource();
			route.add(flightsTaken.get(flightsTaken.size() - 1));
			
			while(parentSource != source) {
				for(Path flight : flightsTaken) {
					if(flight.getDestination() == parentSource) {
						parentSource = flight.getSource();
						route.add(flight);
						break;
					}
				}
			}
			
			Collections.reverse(route);
			
			return new OptimalRoute(route);
		} catch (Exception e) {
			return new OptimalRoute(source, destination);
		}
 		
		
	}
	
	private void sortPossiblePaths(int destination, ArrayList<Path> possiblePaths) {
		possiblePaths.sort((path1, path2) -> {
			if(path1.getDestination() == destination && path2.getDestination() == destination) {
				return 0;
			} else if(path1.getDestination() == destination) {
				return -1;
			} else if(path2.getDestination() == destination) {
				return 1;
			} else {
				return 0;
			}
		});
	}

	private Path findBestPath(ArrayList<Path> possiblePaths) {
		Path bestPath;
		int currentArrivalTime;
		int bestArrivalTime;
		bestPath = possiblePaths.get(0); // use first path as a default
		
		for(int i = 1; i < possiblePaths.size(); i++) {
			currentArrivalTime = possiblePaths.get(i).getArrivalTime();
			bestArrivalTime = bestPath.getArrivalTime();
			if(currentArrivalTime < bestArrivalTime) {
				bestPath = possiblePaths.get(i);
			}
		}
		return bestPath;
	}

	private void updatePossiblePaths(Path bestPath, ArrayList<Path> possiblePaths) {
		ArrayList<Path> pathsToAdd;
		if(!this.flightsTaken.isEmpty()) {
			for(int i = 0; i < possiblePaths.size(); i++) {
				if(possiblePaths.get(i).equals(bestPath)) {
					possiblePaths.remove(i);
				}
			}
			pathsToAdd = generatePossiblePaths(bestPath);
			for(Path pathToAdd : pathsToAdd) {
				possiblePaths.add(pathToAdd);
			}
		}
	}
	
	private ArrayList<Path> generatePossiblePaths() {
		Path currentPath;
		ArrayList<Path> possiblePaths = new ArrayList<Path>();
		
		for(int i =  0; i < this.paths.size(); i++) {
			currentPath = this.paths.get(i);
			if(isPathPossible(currentPath)) {
				possiblePaths.add(currentPath);
			} 
		}
		
		return possiblePaths;	
	}
	
	private ArrayList<Path> generatePossiblePaths(Path currentPath) {
		ArrayList<Path> possiblePaths = new ArrayList<Path>();
		
		for(int i =  0; i < this.paths.size(); i++) {
			currentPath = this.paths.get(i);
			
			if(isPathPossible(currentPath)) {
				possiblePaths.add(currentPath);
			}
		}
		
		return possiblePaths;	
	}
	
	private Boolean isPathPossible(Path path) {
		return this.currentPosition == path.getSource() && 
				this.currentTime < path.getDepartureTime();
	}
}
