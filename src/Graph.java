import java.util.ArrayList;

public class Graph {
	
	private ArrayList<Path> paths = new ArrayList<Path>();
	private ArrayList<Path> route = new ArrayList<Path>();
	private int currentPosition;
	private int currentTime = 0;
	
	public Graph(int[] sources, int[] destinations, int[] departureTimes, int[] arrivalTimes) {
		for(int i = 0; i < sources.length; i++) {
			this.paths.add(new Path(sources[i], destinations[i], departureTimes[i], arrivalTimes[i]));
		}
	}
	
	public OptimalRoute findOptimalRoute(int source, int destination) {
		Path bestPath;
		this.currentPosition = source;
 		ArrayList<Path> possiblePaths = this.generatePossiblePaths();
 		
		while(this.currentPosition != destination) {
			sortPossiblePaths(destination, possiblePaths);
			bestPath = findBestPath(possiblePaths);
			this.route.add(bestPath);	
			this.currentPosition = bestPath.getDestination();
			this.currentTime = bestPath.getArrivalTime();
			updatePossiblePaths(bestPath, possiblePaths);
		}
		
		return new OptimalRoute(route);
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
		if(!this.route.isEmpty()) {
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
		return this.currentPosition == path.getSource() && this.currentTime < path.getDepartureTime();
	}
}
