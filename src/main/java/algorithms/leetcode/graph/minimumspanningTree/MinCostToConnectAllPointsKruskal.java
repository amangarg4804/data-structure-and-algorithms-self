package algorithms.leetcode.graph.minimumspanningTree;

//You are given an array points representing integer coordinates of some points on a 2D-plane, where points[i] = [xi, yi].
//The cost of connecting two points [xi, yi] and [xj, yj] is the manhattan distance between them: |xi - xj| + |yi - yj|,
// where |val| denotes the absolute value of val.
//Return the minimum cost to make all points connected. All points are connected if there is exactly one simple path between any two points.

import algorithms.leetcode.graph.OptimizedDisjointSet;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MinCostToConnectAllPointsKruskal {

    // 1. first, we can create all edges
    // 2. Each edge will have weight equal to the distance between points
    // 3. Sort the edges by their weight
    // 4. Start from the first edge.
    //      a) Add it to a connected component.
    //      b) Increase the cost by the first edge's weight
    // 5. Try each edge in sorted order of weight
    //      if the points of the edge are already connected (we would need a isConnected function), then discard this edge
    // 6. Repeat step 5 until all points are connected
    public int minCostConnectPoints(int[][] points) {
        List<Edge> edges= new ArrayList<>();
        for (int i = 0; i < points.length; i++) {
            for (int j = i+1; j < points.length; j++) {
                int distance = Math.abs(points[i][0] - points[j][0]) + Math.abs(points[i][1] - points[j][1]);
                edges.add(new Edge(i, j, distance));
            }
        }
        edges.sort(Comparator.comparingInt(edge -> edge.weight));
        OptimizedDisjointSet ds = new OptimizedDisjointSet(points.length);
        int cost = 0;
        int total =0;
        for (Edge edge : edges) {
            if (!ds.connected(edge.start, edge.end)) {
                ds.union(edge.start, edge.end);
                cost += edge.weight;
                if(++total==points.length-1) {
                    // means we have added n-1 edges already, no need to iterate over remaining edges
                    break;
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
