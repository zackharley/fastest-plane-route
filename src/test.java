
public class test {

	public static void main(String[] args) {
		int[] sources = {1, 1, 1, 1, 2, 2, 3, 3, 3, 3, 4, 4, 4, 4};
		int[] destinations = {2, 2, 3, 4, 3, 4, 1, 2, 4, 4, 1, 1, 2, 3};
		int[] departureTimes = {1, 3, 2, 4, 7, 3, 1, 2, 1, 7, 1, 6, 2, 5};
		int[] arrivalTimes = {2, 6, 8, 8, 9, 4, 2, 4, 4, 8, 3, 8, 4, 6};
		Graph graph = new Graph(sources, destinations, departureTimes, arrivalTimes);
		OptimalRoute route;
		
		route = graph.findOptimalRoute(2, 3);
		
		System.out.println(route.toString());
	}

}
