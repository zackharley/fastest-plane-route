/**
 * The class that runs the test cases specified by the assignment.
 * 
 * @author Zack Harley
 */

class test {

	public static void main(String[] args) {
		FlightManager manager = new FlightManager();
		manager.initialize();
		System.out.println(manager.findOptimalRoute(50, 144).toString());
		System.out.println(manager.findOptimalRoute(140, 92).toString());
		System.out.println(manager.findOptimalRoute(99, 117).toString());
		System.out.println(manager.findOptimalRoute(108, 28).toString());
		System.out.println(manager.findOptimalRoute(192, 86).toString());
	}
}
