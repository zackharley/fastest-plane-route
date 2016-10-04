/**
 * This class is used to import all of the different flight data from the file, as well
 * as act as an interface to the flight Graph. To use any other method on this class, you
 * must first run the initialize() method to load in the data from the file.
 * 
 * @author Zack Harley
 */

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class FlightManager {
	
	ArrayList<Integer> departureCities;
	ArrayList<Integer> arrivalCities;
	ArrayList<Integer> departureTimes;
	ArrayList<Integer> arrivalTimes;
	int numberOfCities;
	Graph flightGraph;
	
	/**
	 * Constructor for the FlightManager class
	 */
	public FlightManager() {
		departureCities = new ArrayList<Integer>();
		arrivalCities = new ArrayList<Integer>();
		departureTimes = new ArrayList<Integer>();
		arrivalTimes = new ArrayList<Integer>();
		numberOfCities = 0;
	}
	
	/**
	 * This method is used to load in the flight data from the local file, after which
	 * a new instance of Graph is assigned to this.flightGraph.
	 */
	public void initialize() {
		String filename = "flights.txt";
		Pattern firstLineRegExp = Pattern.compile("^(\\d+)$", Pattern.MULTILINE);
		Pattern flightRegExp = Pattern.compile("^(\\d+)\\t(\\d+)\\t(\\d+)\\t(\\d+)$", Pattern.MULTILINE);
		
		try (Stream<String> stream = Files.lines(Paths.get(filename))) {
			stream.forEach((line) -> {
				Matcher firstLineMatcher = firstLineRegExp.matcher(line);
				Matcher flightMatcher = flightRegExp.matcher(line);
				
				if(firstLineMatcher.find()) {
					this.numberOfCities = Integer.parseInt(line);
				}
				
				if(flightMatcher.find()) {
					addFlightDetails(flightMatcher);
				}
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		updateFlightGraph();
	}
	
	/**
	 * This method is used to find the OptimalRoute between a source and a destination
	 * on the flight Graph when no inputs are specified. The user will be prompted to
	 * enter a valid source and a valid destination, after which the OptimalRoute will
	 * be returned.
	 * @return The optimal route between the source and destination cities
	 */
	public OptimalRoute findOptimalRoute() {
		int source;
		int destination;
		Scanner keyboard = new Scanner(System.in);
		
		System.out.print("\nPlease enter a source city: ");
		source = keyboard.nextInt();
		System.out.print("\nPlease enter a destination city: ");
		destination = keyboard.nextInt();
		
		keyboard.close();
		
		return findOptimalRoute(source, destination);
	}
	
	/**
	 * This method acts as an wrapper for the flight Graph's findOptimalRoute method.
	 * 
	 * @param source - The initial node on the graph.
	 * @param destination - The final node on the graph.
	 * @return The optimal route between the source and destination cities
	 */
	public OptimalRoute findOptimalRoute(int source, int destination) {
		OptimalRoute route = this.flightGraph.findOptimalRoute(source, destination);
		return route;
	}

	private void updateFlightGraph() {
		this.flightGraph = new Graph(
			this.departureCities,
			this.arrivalCities,
			this.departureTimes,
			this.arrivalTimes
		);
	}

	private void addFlightDetails(Matcher flightMatcher) {
		departureCities.add(Integer.parseInt(flightMatcher.group(1)));
		arrivalCities.add(Integer.parseInt(flightMatcher.group(2)));
		departureTimes.add(Integer.parseInt(flightMatcher.group(3)));
		arrivalTimes.add(Integer.parseInt(flightMatcher.group(4)));
	}
}
