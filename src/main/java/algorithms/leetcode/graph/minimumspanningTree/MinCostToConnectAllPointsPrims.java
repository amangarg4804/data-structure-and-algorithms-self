package algorithms.leetcode.graph.minimumspanningTree;

//You are given an array points representing integer coordinates of some points on a 2D-plane, where points[i] = [xi, yi].
//The cost of connecting two points [xi, yi] and [xj, yj] is the manhattan distance between them: |xi - xj| + |yi - yj|,
// where |val| denotes the absolute value of val.
//Return the minimum cost to make all points connected. All points are connected if there is exactly one simple path between any two points.

import java.util.Comparator;
import java.util.PriorityQueue;

public class MinCostToConnectAllPointsPrims {

    // While in Kruskal's algo, we add edges one by one. In Prims's algo, we add points one by one.
    //1. Add the first point and mark it as visited
    // 2. Add all the edges starting from first point to a min heap (priority queue)
    // 3. While the min heap is not empty
    //      a) Remove the edge with the smallest weight from the min heap
    //      b) If point2 of the edge is not visited, add it to the min heap and mark it as visited
    public int minCostConnectPoints(int[][] points) {
        if(points==null || points.length <=1) {
            return 0;
        }
        int cost = 0;
        int n = points.length;
        //min heap. Minimum element is always at the top. It will be the first to get polled
        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(e -> e.weight));
        // visited array is for points,so it's a 1-D array. Each points index in the points array will represent whether the point is visited or not
        boolean[] visited = new boolean[n];
        int[] first = points[0];
        for(int i = 1; i < n; i++) {
            // Add all the edges staring from first point to all other points.
            // Note that the edges are not given to us in the problem statement, we are considering edges from each point to all other points
            pq.add(new Edge(0, i, Math.abs(first[0] - points[i][0]) + Math.abs(first[1] - points[i][1])));
        }
        visited[0] = true;
        while(!pq.isEmpty()) {
            Edge edge = pq.poll();
            // now two cases are possible for this edge, either its point 2 is already visited or not
            // if point 2 is already visited, we don't consider this point.
            // if point 2 is not visited, then we add all the edges starting from point 2 to a min heap
            // but we should only add the edges where the destination point is not already visited to avoid cycle
            if(!visited[edge.end]) {
                visited[edge.end] = true;// this is necessary otherwise we will check the edges from this point to itself in the next loop
                cost += edge.weight;
                for(int i = 0; i < n; i++) {
                    if(!visited[i]){
                        int weight = Math.abs(points[edge.end][0] - points[i][0]) + Math.abs(points[edge.end][1] - points[i][1]);
                        pq.add(new Edge(edge.end, i, weight));
                    }
                }
            }
        }
        return cost;
    }

    private static class Edge {
        int start, end, weight;
        public Edge(int start, int end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }

    }
}
