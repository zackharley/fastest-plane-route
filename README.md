# Connecting Flights {


Given a set of cities and flights between them, each flight with a departure time and arrival time, findthe route that starts in specified city A and ends in specified city B, and has the earliest possible arrivaltime in B. The flight plan may contain any number of flights but each flight in the route must have adeparture time **strictly greater** than the arrival time of the previous flight in the route.

For example, suppose the set of cities is {1,2,3} and the set of flights is given by this table

| Source | Destination | Departure Time | Arrival Time |
|:-:|:-:|:-:|:-:|
| 1 | 2 | 2 | 6 |
| 2 | 3 | 6 | 7 |
| 2 | 3 | 8 | 9 |
| 3 | 1 | 8 | 10 |
If the task is to fly from City 1 to City 3, the route is obviously 1 – 2 – 3, and the arrival time at City 3is 9. We cannot take the earlier flight from City 2 to City 3 because its departure time is not greaterthan the arrival time of the flight from City 1 to City 2.If the task is to fly from City 2 to City 1, the route is 2 – 3 – 1, and the arrival time at City 1 is 10.If the task is to fly from City 3 to City 2, there is no solution.Your program should be based on a modified version of Dijkstra's Algorithm.Output from your program should list the cities on the flight route (if one exists), and give the arrivaltime in the target city. If there is no route from the specified start city to the specified end city, yourprogram should print a message to that effect.

The data set is in a file named flights.txt. **The format of this file is:**

first line: a single integer n, giving the number of cities on the map. The cities are identified by the integers 1 ... n

subsequent lines: each subsequent line contains 4 integers, separated by tabs. Each linerepresents a scheduled flight by means of four integers, in this order:

- departure city
- arrival city
- departure time
- arrival time

All times are based on a universal clock.


# }
