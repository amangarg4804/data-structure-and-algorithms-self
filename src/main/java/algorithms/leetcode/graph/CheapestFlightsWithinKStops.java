package algorithms.leetcode.graph;

// There are n cities connected by some number of flights.
// You are given an array flights where flights[i] = [fromi, toi, pricei]
// indicates that there is a flight from city fromi to city toi with cost pricei.
//
// You are also given three integers src, dst, and k, return the cheapest price from src to dst with at most k stops.
// If there is no such route, return -1.

//Input: n = 4, flights = [[0,1,100],[1,2,100],[2,0,100],[1,3,600],[2,3,200]], src = 0, dst = 3, k = 1
//Output: 700
//Explanation:
//The optimal path with at most 1 stop from city 0 to 3 is marked in red and has cost 100 + 600 = 700.
//Note that the path through cities [0,1,2,3] is cheaper but is invalid because it uses 2 stops.


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

// 2 <= n <= 100
//0 <= flights.length <= (n * (n - 1) / 2)
//flights[i].length == 3
//0 <= fromi, toi < n
//fromi != toi
//1 <= pricei <= 104
//There will not be any multiple flights between two cities.
//0 <= src, dst, k < n
//src != dst
public class CheapestFlightsWithinKStops {

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        // Bellman Ford Algo
        // At most k stops mean at most k+1 edges
        int atMostEdges = k+1;
        // 1. First we need a distance array which stores the minimum cost to reach a node i
        // by using atMostEdges
        // We can use either 2-D array (requires k*n space)
        // Or we can use 2 1-D arrays - first one to store the min distance by using k edges- outer for loop
        // and the 2nd one would be used as a temp array in inner for loop
        int[] minCost = new int[n]; // 0 <= from i, to i < n
        Arrays.fill(minCost, Integer.MAX_VALUE);
        minCost[src] =0;
        // 2. We traverse atMostEdges times to find a route from source to distanation by using at most given number of edges
        // When the first pass of for loop is over, it means we have found all routes from source to destination using at most 1 edge
        // When the second pass of for loop is over; it means we have found all routes from source to destination
        // using at most 2 edges

        for(int pass = 1 ; pass <= atMostEdges; pass++) {
            // Now we need to iterate over all edges
            // the edges in this problem are stored in flights array
            // Temp array is never updated in the loop
            int [] temp = Arrays.copyOf(minCost, minCost.length);
            for(int[] flight : flights) {
                int currentSource = flight[0];
                int currentDest = flight[1];
                int currentPrice = flight[2];
                // If the currentSource hasn't been visited yet at all, we won't updare the price of currentDest
                if (temp[currentSource] == Integer.MAX_VALUE) {
                    continue;
                }
                //                 // The new price to reach currentDest node is the price paid to reach currentSource + the price from currentSource to CurrentDest

                int newPrice =  temp[currentSource] + currentPrice;
                if(minCost[currentDest] > newPrice) {
                    // Since the minCost is getting updated inside the loop we have to check  minCost[currentDest] and not tmp[currentDest]
                    minCost[currentDest] = newPrice;
                }
            }

        }
        return minCost[dst] == Integer.MAX_VALUE? -1: minCost[dst];


    }
    public int findCheapestPriceDijkstra(int n, int[][] flights, int src, int dst, int k) {
            // Dijkstra
            // 1. First let's create the adjacency list
            // Should we use list for the outer part?
            // We can because in the constraint it is mentioned 0 <= src, dst, k < n
            List<List<int[]>> graph = new ArrayList<>();

            // how many arraylist do we need ? N - one for each node
            for(int i=0; i< n; i++) {
                graph.add(new ArrayList<>());
            }
            for(int[] flight : flights) {
                int source = flight[0];
                int destination = flight [1];
                int price = flight [2];
                graph.get(source).add(new int[]{destination, price});
            }
            // 2. Second thing we need in Dijkstra is a distance array
            // The problem here is that we can't use standard Dijkstra here. There is a tradeoff between price and stops
            // If we use standard Dijkstra with Price array, We will never choose an expensive "intermediate" path over a cheap "intermediate" path
            // Check this example;
            // Input
            // n =4
            // flights =[[0,1,1],[0,2,5],[1,2,1],[2,3,1]]
            // src =0
            // dst =3
            // k =1
            // Expected Answer 6
            // Stop condition is rigid here, we cant go beyond the given stops constraint
            // Price is flexible
            // Here distance is Stops
            int[] minStops = new int[n];
            Arrays.fill(minStops, Integer.MAX_VALUE);
            minStops[src] = 0;

            // 3. We neeed priority queue
            // Normally, the priority que contains an array of arrays which stores just the node and distance
            // But here we will also store the number of stops
            // We can't keep the stops outside the loop
            // Each node needs to know inside the loop, how many stops it took to reach it
            PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> a[1] -b[1]); // 1 is the price
            // Notice that although the distance array contains stops,
            // the pq is stil sorted based on price. So, lowest priced flights are processed first
            pq.offer(new int[]{src, 0, -1});

            while(!pq.isEmpty()) {
                int[] polled = pq.poll();
                int currentNode = polled[0];
                int currentPrice = polled[1];
                int stops = polled[2];
                // Since we are processing lowest priced flights before expensive flight, if we found the destination
                // it means it is the cheapest path



                if (currentNode == dst) {
                    return currentPrice;
                }
                //  Now check the number of stops.
                // If the stops are already stops =k, the next loop will be adding 1 to that and
                // we can't allow k+1 stops to find a flight



                // Explanation of condition stops +1 > k
                // Since the currentnode is not the destination node at this point
                // we are now trying to find whether the neighbour nodes are within k stops and are destination nodes or leads to a path to destination node
                // Now each neighbour from currentNode will require stops + 1 stops (where stops are the stops we have already taken to reach the currentNode)
                // Now, if stops + 1 is already > k, there is no point of visiting neighbour nodes as choosing these paths(edges), violate the max stops constraint


                // Explanation of condition  stops >=minStops[currentNode]. Two points here.
                // 1. if the stops for currentNode are more than or equal to minStops[currentNode],
                // it means that currentNode was reached earlier with stops less than the stops we currently have reached
                // Remember that minStops[currentNode] was initiliazed with Integer.MAX_VALUE and stops >=minStops[currentNode] means
                // it is not Integer.MAX_VALUE  anymore and the node was definitely reached earlier.
                // 2. In addition to this node was previously already visited with less number of stops,
                // it is also guaranteed that it was visited with lesser price. Because the priority queue visits always the lesser price nodes first
                // Gemini: the first time you ever popped currentNode out of the queue, it was guaranteed to have the lowest possible price for that node (or for that stop tier).
                // If a second path arrives at currentNode later with more or equal stops, and because of the PQ sorting order, it must also have an equal or higher price, that path is completely redundant (worse price, worse or equal stops). Skipping it prevents exponential path explosion and infinite loops!

                // Given these two points, there is no benefit of visiting the neighbours of this current Node.



                // This condition can be put above the condition [if (currentNode == dst) ] but then we have to check
                // stops > k instead of stops + 1> k
                if(stops+1 > k || stops >=minStops[currentNode]) {
                    continue;
                }
                minStops[currentNode] = stops;
                for(int[] neighbour: graph.get(currentNode)) {
                    int nNode = neighbour[0];
                    int nPrice = currentPrice + neighbour[1];
                    // This is again a deviation from standard dijkstra
                    // In standard dijkstra, we check that the nPrice is less that the nPrice in the distance array
                    // and only push the node to pq if that nPrice is less than the value stored in the distance array

                    pq.offer(new int[]{nNode, nPrice, stops+1});
                }
            }

            return -1;

        }


}
