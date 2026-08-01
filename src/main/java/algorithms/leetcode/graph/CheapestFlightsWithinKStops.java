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
        // Bellman ford
        // We don't have to create a graph for Bellman ford

        // which is the cheapest flight with at most 0 stops (1 edge)?
        // which is the cheapest flight with at most 1 stops (2 edges)?
        // which is the cheapest flight with at most 2 stops (3 edges)?


        int stops = 0;
        // Generally to traverse a graph, we use either BFS, or DFS,
        // But when using Bellman ford , we use neither
        // let's create an array that stores the cost of cheapest flight to reach each node(node = array index) for atmost 0 to k stops
        // each index represent the destination node
        int[] previous = new int[n];
        int[] current = new int[n];
        //initially, all flights cost infinity, because we don't know whether there is a flight from source node to that node
        Arrays.fill(previous, Integer.MAX_VALUE);
        Arrays.fill(current, Integer.MAX_VALUE);

        // now we have to fill this array for each stop
        // for the source node, the cost will always be zero
        // max number of edges for n nodes = n-1
        // Remember that max stops (= total edges-1) can be n-2, where n is number of nodes
        // the array starts from 0 edge and then 1 edge and then so on
        // for 0 edge, all costs are infinity
        // if stops are 0, then edges are 1,
        // if stops are 1, then edges are 2
        // so, we need to fill the array till k+1 index
        previous[src] = 0;

        for(int edges =1 ; edges <=k+1 ; edges++) {
            current[src]=0;
            for(int[] flight :flights) {
                int source =flight[0];
                int dest =flight[1];
                int cost =flight[2];
                // Note: the source here is different from the src provided in problem statement
                // Source to destination cost is given
                // Now, how did we reach this source?
                // what was the cost to reach this source previously?, it was previous[source]
                // so the cost to reach the destination is previous[source] + cost
                // if this cost is less than current cost to reach the destination, then we should accept this path and update current[dest]
                if(previous[source] != Integer.MAX_VALUE && previous[source] + cost < current[dest]) {
                    current[dest] = previous[source] + cost;
                }
                //  Recurrence relation: DP[k][v] = min(DP[k][v], DP[k - 1][u] + w(u, v))
            }
            previous = current.clone();
        }
        return current[dst] == Integer.MAX_VALUE? -1 : current[dst];


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
