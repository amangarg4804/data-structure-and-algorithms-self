package algorithms.leetcode.graph;

//Dijkstra's Algo
//You are given a network of n nodes, labeled from 1 to n. You are also given times,
// a list of travel times as directed edges times[i] = (ui, vi, wi), where ui is the source node,
// vi is the target node, and wi is the time it takes for a signal to travel from source to target.
//
//We will send a signal from a given node k. Return the minimum time it takes for all the n nodes to receive the signal.
// If it is impossible for all the n nodes to receive the signal, return -1.

//Input: times = [[2,1,1],[2,3,1],[3,4,1]], n = 4, k = 2
//Output: 2

//Example 2:
//Input: times = [[1,2,1]], n = 2, k = 1
//Output: 1

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

//Example 3:
//Input: times = [[1,2,1]], n = 2, k = 2
//Output: -1
public class NetworkDelayTime {
    public int networkDelayTimeBFS(int[][] times, int n, int k) {
        // this is a weighted graph, so for each node, we not only need its target nodes but also the weight
        // that's why we used int[] instead of Integer
        List<List<int[]>> graph = new ArrayList<>();
        // 1. we could use hashmap too for the outer list,
        // 2. we could use Pair class  instead of int[] array
        for (int i = 0; i <= n; i++) { // have used <= instead of < to avoid doing -1 in all subsequent code
            graph.add(new ArrayList<>());
        }
        for (int[] time : times) {
            graph.get(time[0]).add(new int[]{time[1], time[2]});
            // this is a directed graph, so we don't add in both directions
        }
        int[] time = new int[n + 1]; // dist[i] will store the shortest path from source node to node i
        // initially, we assume that the shortest path to each node is infinity
        for (int i = 1; i <= n; i++) {
            time[i] = Integer.MAX_VALUE;
        }
        // we also need the previous node of each node that leads to the shortest path
        // Then for each node, we will find its shortest path from the source node (k)
        // the sum of all the shortest paths is our answer- WRONG. The answer is the maximum of all shortest paths from the source node
        // if, for any node, we are not able to find a path to source node, we will return -1,
        // but how do we know that there is no path from that node to source node?
        // in that case, the distance array will contain Integer.MAX_VALUE for that node
        Queue<Integer> q = new LinkedList<>();
        q.offer(k);
        time[k] = 0;
        // Answer is wrong when using visited array and not visiting the node twice if it was already visited
        // {1,2,1}, {1, 3, 4}, {2, 1, 2}, {2,3, 7}
        // The answer when using visited array is 7, but it should be 6. Min time to reach node 3 from 2 is 6.
        // when not using the visited array, we get TLE
        while (!q.isEmpty()) {
            int currentNode = q.poll();
            if (graph.get(currentNode).isEmpty()) {
                continue;
            }

            for (int[] neighbor : graph.get(currentNode)) {
                // we have to do two things for neighbor nodes
                // Both things have to done only If the time take to this node via currentNode is less than existing time taken stored in the time[] array,
                // 1. we have to update its time in the time array
                // 2. We have to push it to queue

                int neighbourTimeFromSource = time[currentNode] + neighbor[1];
                // If there exists a path that improves time[x], then x will eventually be enqueued.
                if (neighbourTimeFromSource < time[neighbor[0]]) {
                    // if a node's path is improving, then all the paths going through it might also improve,
                    // so we have to put the node again into queue
                    time[neighbor[0]] = neighbourTimeFromSource;
                    q.offer(neighbor[0]);
                }
            }
        }
        // after all nodes are visited
        // We have to Return the minimum time it takes for all the n nodes to receive the signal
        // minimum time to reach all nodes is the max value in time array.
        // if any array index has value = INTEGER.MAX_VALUE, it means we couldn't reach that node
        // we return -1, in that case
        int ans = Integer.MIN_VALUE;
        for (int i : time) {
            if (i == Integer.MAX_VALUE) {
                return -1;
            }
            ans = Math.max(ans, i);
        }
        return ans;

    }

    public int networkDelayTimeDijkstra(int[][] times, int n, int k) {
        // first prepare the adjacency list
        List<List<int[]>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) { // used <= because nodes are 1 indexed, we will ignore the 0th index
            graph.add(new ArrayList<>());
        }
        for (int[] edge : times) {
            graph.get(edge[0]).add(new int[]{edge[1], edge[2]});
        }
        // create a time array to keep track of minimum time take for signal to reach each node
        int[] time = new int[n + 1]; // n+1 because nodes start from 1
        for(int i =1; i< time.length; i++) { // we shouldn't set index 0 to max value if using the foreach loop later at the end
            time[i] = Integer.MAX_VALUE;
        }
        time[k] = 0;// we will visit the neighbours/edges of kth node first, initialize its time to 0
        // priority queue's integer array contains the node at index 0, and its minimum time at index 1
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> Integer.compare(a[1], b[1]));
        pq.offer(new int[]{k, 0});
        while (!pq.isEmpty()) {
            int[] currentNodeAndWeight = pq.poll();
            int currentNode = currentNodeAndWeight[0];
            int currentWeight = currentNodeAndWeight[1];
            // this makes Dijkstra efficient, if the current weight itself is greater than the time
            // PQ may contain multiple entries for the same node.
            // 1 → 2 (10)
            // 1 → 3 (1)
            // 3 → 2 (1)
            // Start: k = 1
            // in the above example -> at one time pq will contain node 2 with weight 10 and 1
            if (currentWeight > time[currentNode]) {
                continue;
            }

            for (int[] neighbour : graph.get(currentNode)) {
                int newWeight = currentWeight + neighbour[1];
                // notice that we are adding the currentWeight above,
                // it might raise a question that since all currentWeights are initialized by Integer.MAX_VALUE
                // we are adding to max value
                // but that will never happen
                // we are always adding the node to queue AFTER we have updated its weight to a value which is less than Integer.MAX_VALUE
                // 0 <= wi <= 100
                if (newWeight < time[neighbour[0]]) {
                    time[neighbour[0]] = newWeight;
                    pq.offer(new int[]{neighbour[0], newWeight});
                }
            }
        }

        // after all nodes are visited
        // We have to Return the minimum time it takes for all the n nodes to receive the signal
        // minimum time to reach all nodes is the max value in time array.
        // if any array index has value = INTEGER.MAX_VALUE, it means we couldn't reach that node
        // we return -1, in that case
        int ans = Integer.MIN_VALUE;
        for (int i : time) {
            if (i == Integer.MAX_VALUE) {
                return -1;
            }
            ans = Math.max(ans, i);
        }
        return ans;
    }
}