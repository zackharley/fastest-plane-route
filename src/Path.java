
public class Path {
	
	private int source;
	private int destination;
	private int departureTime;
	private int arrivalTime;
	
	public Path(int source, int destination, int departureTime, int arrivalTime) {
		this.source = source;
		this.destination = destination;
		this.departureTime = departureTime;
		this.arrivalTime = arrivalTime;
	}

	public int getSource() {
		return this.source;
	}

	public int getDestination() {
		return this.destination;
	}

	public int getDepartureTime() {
		return this.departureTime;
	}

	public int getArrivalTime() {
		return this.arrivalTime;
	}
	
	public int getPathTime() {
		return this.arrivalTime - this.departureTime;
	}
	
}
