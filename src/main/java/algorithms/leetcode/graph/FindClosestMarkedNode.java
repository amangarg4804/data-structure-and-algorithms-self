package algorithms.leetcode.graph;

// You are given a positive integer n which is the number of nodes of a 0-indexed directed weighted graph and a 0-indexed 2D array edges where edges[i] = [ui, vi, wi] indicates that there is an edge from node ui to node vi with weight wi.
//
//You are also given a node s and a node array marked; your task is to find the minimum distance from s to any of the nodes in marked.
//
//Return an integer denoting the minimum distance from s to any node in marked or -1 if there are no paths from s to any of the marked nodes.
//
//
//
//Example 1:
//
//Input: n = 4, edges = [[0,1,1],[1,2,3],[2,3,2],[0,3,4]], s = 0, marked = [2,3]
//Output: 4
//Explanation: There is one path from node 0 (the green node) to node 2 (a red node), which is 0->1->2, and has a distance of 1 + 3 = 4.
//There are two paths from node 0 to node 3 (a red node), which are 0->1->2->3 and 0->3, the first one has a distance of 1 + 3 + 2 = 6 and the second one has a distance of 4.
//The minimum of them is 4.

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

// Example 2:
//
//Input: n = 5, edges = [[0,1,2],[0,2,4],[1,3,1],[2,3,3],[3,4,2]], s = 1, marked = [0,4]
//Output: 3
//Explanation: There are no paths from node 1 (the green node) to node 0 (a red node).
//There is one path from node 1 to node 4 (a red node), which is 1->3->4, and has a distance of 1 + 2 = 3.
//So the answer is 3.
public class FindClosestMarkedNode {

    public int minimumDistance(int n, List<List<Integer>> edges, int s, int[] marked) {
        // Dijkstra
        List<List<int[]>> graph = new ArrayList<>();
        for(int i =0; i< n; i++) {
            graph.add(new ArrayList<>());
        }
        for(List<Integer> edge: edges) {
            graph.get(edge.get(0)).add(new int[]{edge.get(1), edge.get(2)});
        }
        int[] distance = new int[n];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[s] = 0;

        Set<Integer> markedNodes = new HashSet<>();
        for(int i: marked) {
            markedNodes.add(i);
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] -b[1]);
        pq.offer(new int[]{s,0});
        while (!pq.isEmpty()) {
            int[] polled = pq.poll();
            int currentNode = polled[0];
            int currentDist = polled[1];

            if(markedNodes.contains(currentNode)) {
                return currentDist;
            }

            for(int[] neighbour: graph.get(currentNode)) {
                int neighbourNode = neighbour[0];
                int neighbourDistance = currentDist + neighbour[1];
                if(neighbourDistance < distance[neighbourNode]) {
                    distance[neighbourNode] = neighbourDistance;
                    pq.offer(new int[]{neighbourNode, neighbourDistance});
                }
            }

        }
        return -1;
    }
    public int minimumDistanceBellmanFord(int n, List<List<Integer>> edges, int s, int[] marked) {
        // Bellman ford
        // There is no condition of at most K edges
        // So, one array is enough
        // no temp array is needed
        // dist stores the minimum distance required to reach the node from source node
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[s] = 0;

        Set<Integer> markedNodes = new HashSet<>();
        for(int i: marked) {
            markedNodes.add(i);
        }
        // How many times should we iterate? N-1 times because that's the max number of edges a DAG can have to reach any node from any node
        for (int i= 0; i< n; i++) {
            for(List<Integer> edge : edges) {
                int source = edge.get(0);
                int dest = edge.get(1);
                if(dist[source] == Integer.MAX_VALUE) {
                    continue;
                }
                int distanceToReachDest = edge.get(2) + dist[source];
                if(distanceToReachDest < dist[dest]) {
                    dist[dest] = distanceToReachDest;
                }
            }
        }

        int min = Integer.MAX_VALUE;
        for( int i = 0; i< n; i++) {
            if(markedNodes.contains(i) && min > dist[i]) {
                min = dist[i];
            }
        }
        return min == Integer.MAX_VALUE? -1: min;
    }

    public int minimumDistanceSPFA(int n, List<List<Integer>> edges, int s, int[] marked) {
        List<List<int[]>> graph = new ArrayList<>();
        for(int i =0; i< n; i++) {
            graph.add(new ArrayList<>());
        }
        for(List<Integer> edge: edges) {
            graph.get(edge.get(0)).add(new int[]{edge.get(1), edge.get(2)});
        }
        int[] distance = new int[n];
        boolean[] inQueue = new boolean[n];

        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[s] = 0;

        Set<Integer> markedNodes = new HashSet<>();
        for(int i: marked) {
            markedNodes.add(i);
        }

        Queue<Integer> q = new LinkedList<>();
        q.offer(s);
        while (!q.isEmpty()) {
            int currentNode = q.poll();
            inQueue[currentNode] = false;

            for(int[] neighbour: graph.get(currentNode)) {
                int neighbourNode = neighbour[0];
                int neighbourDistance = distance[currentNode] + neighbour[1];
                if(neighbourDistance < distance[neighbourNode]) {
                    distance[neighbourNode] = neighbourDistance;
                    q.offer(neighbourNode);
                }
            }

        }
        int min = Integer.MAX_VALUE;
        for( int i = 0; i< n; i++) {
            if(markedNodes.contains(i) && min > distance[i]) {
                min = distance[i];
            }
        }
        return min == Integer.MAX_VALUE? -1: min;
    }


}
