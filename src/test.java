import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class test {

	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		Integer source;
		Integer destination;
		int[] sources = {1, 1, 1, 1, 2, 2, 3, 3, 3, 3, 4, 4, 4, 4};
		int[] destinations = {2, 2, 3, 4, 3, 4, 1, 2, 4, 4, 1, 1, 2, 3};
		int[] departureTimes = {1, 3, 2, 4, 7, 3, 1, 2, 1, 7, 1, 6, 2, 5};
		int[] arrivalTimes = {2, 6, 8, 8, 9, 4, 2, 4, 4, 8, 3, 8, 4, 6};
		Graph graph = new Graph(sources, destinations, departureTimes, arrivalTimes);
		Set<Integer> uniqueNodes = new TreeSet<Integer>();
		OptimalRoute route;
		
		for(int i = 0; i < sources.length; i++) {
			uniqueNodes.add((Integer)sources[i]);
		}
		
		System.out.println("The graph contains cities " + uniqueNodes + ".\nEnter an integer source city: ");
		source = keyboard.nextInt();
		if(!uniqueNodes.contains(source)) {
			invalidGraphNode();
		}
		System.out.println("Enter an integer desination city: ");
		destination = keyboard.nextInt();
		if(!uniqueNodes.contains(destination)) {
			invalidGraphNode();
		}
		keyboard.close();
		
		route = graph.findOptimalRoute(source, destination);
		
		System.out.println(route.toString());
	}

	private static void invalidGraphNode() throws Error {
		throw(new Error("You must enter an integer that is a node on the graph."));
	}

}
