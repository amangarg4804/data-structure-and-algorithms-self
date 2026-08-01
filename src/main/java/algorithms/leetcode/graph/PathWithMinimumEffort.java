package algorithms.leetcode.graph;

//You are a hiker preparing for an upcoming hike. You are given heights, a 2D array of size rows x columns, where heights[row][col] represents the height of cell (row, col). You are situated in the top-left cell, (0, 0), and you hope to travel to the bottom-right cell, (rows-1, columns-1) (i.e., 0-indexed). You can move up, down, left, or right, and you wish to find a route that requires the minimum effort.
//
//A route's effort is the maximum absolute difference in heights between two consecutive cells of the route.
//
//Return the minimum effort required to travel from the top-left cell to the bottom-right cell.
// Input: heights = [[1,2,2],[3,8,2],[5,3,5]]
//Output: 2
//Explanation: The route of [1,3,5,3,5] has a maximum absolute difference of 2 in consecutive cells.
//This is better than the route of [1,2,2,2,5], where the maximum absolute difference is 3.


//Input: heights = [[1,2,3],[3,8,4],[5,3,5]]
//Output: 1
//Explanation: The route of [1,2,3,4,5] has a maximum absolute difference of 1 in consecutive cells, which is better than route [1,3,5,3,5].


// Constraints:
//
//rows == heights.length
//columns == heights[i].length
//1 <= rows, columns <= 100
//1 <= heights[i][j] <= 106

import java.util.Comparator;
import java.util.PriorityQueue;

public class PathWithMinimumEffort {
    int[][] directions = new int[][]{{-1,0}, {0,-1}, {1,0}, {0,1}};
    int maxSoFar = Integer.MAX_VALUE;


    public int minimumEffortPath(int[][] heights) {
        // brute force with some optimization
        // Results in TLE
        // find all paths - whenever we find that any of the existing path has consecutive distance greater than previous traversed path
        // we reject that path
        // Can't use BFS because BFS will try to figure out each path simultaneously,
        // and we won't be able to reject paths quickly without traversing all paths.
        // Treat the array as a graph
        // Which graph?
        // 1. directed graph-each cell is a node and has edges in four directions
        // 2. with no cycles (because we don't visit the same node twice in a path)
        // we need to backtrack every time we reach the target node
        // How to mark the node as visited? - we will set the value as 0
        int height = heights[0][0];
        return backtrack(0, 0, heights, 0);
    }

    private int backtrack(int currentRow, int currentCol, int[][] heights, int maxEffort) {
        // maxEffort denotes the maximum Effort with current path
        // initially, when we start from left top cell, our effort is zero
        int rows = heights.length;
        int cols = heights[0].length;
        if(currentRow==rows-1 && currentCol == cols-1) {
            // we found a path
            // it is possible that this is not the best path
            maxSoFar = Math.min(maxSoFar, maxEffort);
            return maxEffort;
        }
//        for(int dir=0; dir < directions.length; dir++) {
//            int nextRow = currentRow + directions[dir][0];
//            int nextCol = currentCol + directions[dir][1];
//            if(nextRow>=0 && nextCol >=0 && nextRow < rows && nextCol < cols && heights[nextRow][nextCol]!=0) {
//                int height = heights[nextRow][nextCol];
//                int effort = Math.abs(height - currentHeight );
//                int maxEffortOnThisPath = Math.max(effort, maxEffort);
//                heights[nextRow][nextCol] = 0; // we mark this node as visited
//                int totalEffortWithThisPath = backtrack(nextRow, nextCol, heights, height, maxEffortOnThisPath);
//                heights[nextRow][nextCol] = height;
//            }
//        }


        return maxEffort;
    }

    public int minimumEffortPathDijkstra(int[][] heights) {
        // implement Dijkstra
        // Since this is a matrix , we don't require adjacency list. We can use a directions array
        int rows = heights.length;
        int cols = heights[0].length;
        int[][] directions = new int[][]{{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
        // In Dijkstra, we generally require 2 other than the adjacency
        // Priority queue, distance array
        // But in this case, we don't want to visit a node again. Notice that Visited here means
        // that we have traversed through all the neighbors of this node
        // here the distance array will be 2d and it stores the minDifference of height taken to reach the node
        int[][] minDifference = new int[rows][cols];
        boolean[][] visited = new boolean[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                minDifference[i][j] = Integer.MAX_VALUE;
                visited[i][j] = false;
            }
        }
        minDifference[0][0] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(arr -> arr[2]));
        pq.offer(new int[]{0, 0, 0});
        while (!pq.isEmpty()) {
            int[] polled  = pq.poll();
            int currentRow = polled[0];
            int currentCol = polled[1];
            int currentDifference = polled[2];
            // If we have popped the destination node, then we can simply return
            // Is this statement true? :
            //  The first time The destination node (or any other node?) is popped is when we have reached the minDiff path to it
            // No, it is possible that you reach the nrighbouring node from source column, and that neighbouring node has lowest difference from source node.,
            // So, it will be popped next from priority queue, but it is possible that this neighbouring node can be reached from its own another neighbour with less difference of heights
            // [1, [2], 2] , Notice the node labeled 2 in squared brackets
            // [4, 3, 1]
            // The problem could have easily been to find the path from any source to any destination in matrix instead of top left and top right
            // But it is true in this case, because destination node is in the right corner
            if(currentRow == rows-1 && currentCol == cols -1) {
                return minDifference[currentRow][currentCol];
            }
            for(int i =0; i< directions.length;i++) {
                int nextRow = currentRow + directions[i][0];
                int nextCol = currentCol + directions[i][1];
                if(nextRow>=0 && nextCol>=0 && nextRow < rows && nextCol < cols &&!visited[nextRow][nextCol]) {
                    int heightDiff = Math.abs(heights[nextRow][nextCol] - heights[currentRow][currentCol]);
                    int newDifference = Math.max(currentDifference, heightDiff);
                    // Notice that we don't add the differences, in path problems with cost,
                    // we usually add the Cost required to reach the current node + the cost required to reach the neighbur Node from currentNode
                    if(newDifference < minDifference[nextRow][nextCol]) {
                        minDifference[nextRow][nextCol] = newDifference;
                        pq.offer(new int[]{nextRow, nextCol, newDifference});
                    }
                }

            }
            visited[currentRow][currentCol] = true;
        }
        return minDifference[rows - 1][cols - 1];
    }


}
