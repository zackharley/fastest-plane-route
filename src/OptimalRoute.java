import java.util.ArrayList;

public class OptimalRoute {
	
	private ArrayList<Integer> nodes = new ArrayList<Integer>();
	private int source;
	private int destination;
	private int timeRequired;
	
	public OptimalRoute(ArrayList<Path> paths) {
		this.source = paths.get(0).getSource();
		this.destination = paths.get(paths.size() - 1).getDestination();
		int startingNode;
		int endingNode;
		
		for(int i = 0; i < paths.size(); i++) {
			startingNode = paths.get(i).getSource();
			endingNode = paths.get(i).getDestination();
			
			if(this.isFirstPath(i)) {
				this.nodes.add(startingNode);
			}
			
			this.nodes.add(endingNode);
		}
		
		this.timeRequired = paths.get(paths.size() - 1).getArrivalTime();
	}
	
	private Boolean isFirstPath(int index) {
		return index == 0;
	}
	
	public String toString() {
		return "The optimal route from " + 
				this.source + 
				" to " + 
				this.destination + 
				" passes through nodes " + 
				this.nodes.toString() + 
				" and arrives at time " +
				this.timeRequired;
	}
	
}
