import java.util.ArrayList;

public class Graph {
	
	private ArrayList<Path> paths = new ArrayList<Path>(0);
	private ArrayList<Path> route = new ArrayList<Path>(0);
	private int currentPosition;
	private int currentTime = 0;
	
	public Graph(int[] sources, int[] destinations, int[] departureTimes, int[] arrivalTimes) {
		
		for(int i = 0; i < sources.length; i++) {
			this.paths.add(new Path(sources[i], destinations[i], departureTimes[i], arrivalTimes[i]));
		}
		
	}
	
	public OptimalRoute findOptimalRoute(int source, int destination) {
		
		ArrayList<Path> possiblePaths = new ArrayList<Path>(0);
		Path bestPath;
		int pathTime = 0;
 		this.currentPosition = source;
		
		while(this.currentPosition != destination) {
			possiblePaths = this.generatePossiblePaths();
			bestPath = possiblePaths.get(0);
			
			for(int i = 1; i < possiblePaths.size(); i++) {
				pathTime = possiblePaths.get(i).getPathTime();
				
				if(pathTime < bestPath.getPathTime()) {
					bestPath = possiblePaths.get(i);
				}
			}
			
			this.route.add(bestPath);
			
			this.currentPosition = bestPath.getDestination();
		}
		
		return new OptimalRoute(route);
	}
	
	private ArrayList<Path> generatePossiblePaths() {
		
		Path currentPath;
		ArrayList<Path> possiblePaths = new ArrayList<Path>(0);
		
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
