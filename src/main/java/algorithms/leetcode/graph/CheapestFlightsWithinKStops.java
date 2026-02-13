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
        // First let's create the graph
        List<List<int[]>> graph = new ArrayList<>(); // weighted graph, so have to use int[] instead of just Integer
        for(int i=0; i< n ; i++) {
            graph.add(new ArrayList<>());
        }

        for(int[] flight : flights) {
            // 0 is source, 1 is destination, 2 is price
            graph.get(flight[0]).add(new int[]{flight[1], flight[2]});
            // it's a directed graph so we add only in one direction
        }
        // which is the cheapest flight with at most 0 stops (1 edge)?
        // which is the cheapest flight with at most 1 stops (2 edges)?
        // which is the cheapest flight with at most 2 stops (3 edges)?


        int stops = 0;
        // to traverse the graph, either we will have to use BFS, or DFS,
        // But here we use Dijkstra- looks similar to BFS but we don't use visited array
        // As soon as we have reached the destination, we are done.
        // int[] stores the node as well the cost to reach this node from src
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> Integer.compare(a[1], b[1]));
        // start from source, cost to reach source is 0
        pq.offer(new int[]{src, 0});
        while (!pq.isEmpty() && stops<=k) {
            int[] nodeAndCost= pq.poll();
            int node = nodeAndCost[0];
            int costToReachThisNode = nodeAndCost[1];
            if(node == dst) {
                return costToReachThisNode;
            }
            for(int[] neighbour : graph.get(node)) {
                int neighbourNode  = neighbour[0];
                int costToReachNeighbour = neighbour[1];
                pq.offer(new int[]{neighbourNode, costToReachThisNode + costToReachNeighbour});
            }
        }
        return -1;

    }
}
