package algorithms.leetcodegraph.island;

import java.util.LinkedList;
import java.util.Queue;

//Given an n x n grid containing only values 0 and 1, where 0 represents water and 1 represents land, find a water cell such that its distance to the nearest land cell is maximized, and return the distance. If no land or water exists in the grid, return -1.
//
//The distance used in this problem is the Manhattan distance: the distance between two cells (x0, y0) and (x1, y1) is |x0 - x1| + |y0 - y1|.
// it can also be defined as no of steps taken to reach a point to another point moving only horizontally and vertically
public class AsFarFromLandAsPossible {
    int[][] directions = new int[][]{{0, -1}, {0, 1}, {1, 0}, {-1, 0}};

    public int maxDistance1(int[][] grid) {
        //Using the approach of moving in all 8 directions is wrong
        //  0 , 0, 1
        // "0", 0, 0
        /// for the quoted 0, distance of land is 3, but with the approach of moving in all 8 directions, we will never reach 1 from the quoted 0
        // DFS is not working
        int maxDistance = -1;
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                if (grid[row][col] == 1) {
                    continue; //  it is a land cell, we should calculate distance for water cells only
                }
                int nearestLandDistance = nearestLandDistance(grid, row, col, 0, visited);
                if (nearestLandDistance != Integer.MAX_VALUE) {
                    maxDistance = Math.max(maxDistance, nearestLandDistance);
                }
            }
        }

        return maxDistance;
    }

    private int nearestLandDistance(int[][] grid, int row, int col, int distance, boolean[][] visited) {
        if (row < 0 || col < 0 || row >= grid.length || col >= grid[0].length) {
            return Integer.MAX_VALUE;// we reached the end of grid and no land was found
        }
        if (grid[row][col] == 1) {
            return distance;
        }
        visited[row][col] = true;
        int bottom = nearestLandDistance(grid, row + 1, col, distance + 1, visited);
        int top = nearestLandDistance(grid, row - 1, col, distance + 1, visited);
        int right = nearestLandDistance(grid, row, col + 1, distance + 1, visited);
        int left = nearestLandDistance(grid, row, col - 1, distance + 1, visited);
        return Math.min(Math.min(bottom, top),
                Math.min(right, left));
    }

    public int maxDistance2(int[][] grid) {
        // brute force
        // run bfs from every 0 and find nearest 1
        // take a maximum of all nearest
        // Time limit exceeded
        int maxDistance = -1;
        boolean hasLand = false;
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                if (grid[row][col] == 1) {
                    hasLand = true;
                    break; //  it is a land cell, we should calculate distance for water cells only
                }
            }
        }
        if (!hasLand) {
            return -1;
        }
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                if (grid[row][col] == 1) {
                    continue; //  it is a land cell, we should calculate distance for water cells only
                }
                int nearest = nearestLandDistance(grid, row, col);
                maxDistance = Math.max(maxDistance, nearest);
            }
        }
        return maxDistance;
    }

    private int nearestLandDistance(int[][] grid, int row, int col) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{row, col});
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        int distanceToLand = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int[] cell = q.poll();
                visited[row][col] = true;
                for (int dir = 0; dir < directions.length; dir++) {
                    int nextRow = cell[0] + directions[dir][0];
                    int nextCol = cell[1] + directions[dir][1];
                    if (nextRow < 0 || nextCol < 0 || nextRow >= grid.length || nextCol >= grid[0].length || visited[nextRow][nextCol]) {
                        continue;// we reached the end of grid and no land was found
                    }
                    if (grid[nextRow][nextCol] == 1) {
                        return distanceToLand + 1;
                    }
                    q.offer(new int[]{nextRow, nextCol});
                }
            }
            distanceToLand++;
        }
        return -1;
    }

    public int maxDistance3(int[][] grid) {
        // working solution - multi source BFS
        // run bfs from every 1 (instead of 0). The difference is we don't run bfs for every 1 in a separate loop.
        // All 1's will be added to queue together before running bfs
        // only one visited array to be used, every iteration will make used of the same visited array, this will significantly optimize solution
//We need the shortest distance here for all 0's; breadth-first search traversal is a kind of traversal which is used for shortest path finding.
// Generally, we use it to find the shortest distance from a single source or node, but here we have multiple 1s, so we will use a multi-source breadth-first search.
// The reason we use BFS from 1's and not 0s is that BFS from 0's will need to be executed individually from each 0 to find its nearest 1,
// and hence we will end up exploring every (0, 1) pair.
//
//Essentially we will start with all the 1's, and at each step, we will iterate in all four directions (up, left, right, down) for each 1.
// The moment we reach a water cell 0, we can say that the number of steps we have taken so far is the minimum distance of this water cell from any land cell
// in the matrix. This way, we will iterate over the whole matrix until all cells are covered; the number of steps we would need to cover the last water cell
// is the maximum distance we need.
        //https://www.youtube.com/watch?v=yV-b0amHNVM&t=301s
        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                if (grid[row][col] == 1) {
                    q.offer(new int[]{row, col});
                    visited[row][col] = true;
                }
            }
        }
        if (q.isEmpty() || q.size() == grid.length* grid[0].length) { // if no 1s or all 1s
            return -1;
        }

        int maxDistanceToWater =-1;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int[] cell = q.poll();

                for (int dir = 0; dir < directions.length; dir++) {
                    int nextRow = cell[0] + directions[dir][0];
                    int nextCol = cell[1] + directions[dir][1];
                    if (nextRow < 0 || nextCol < 0 || nextRow >= grid.length || nextCol >= grid[0].length || visited[nextRow][nextCol] || grid[nextRow][nextCol] == 1) {
                        continue;// we reached the end of grid and no land was found
                    }
                    visited[nextRow][nextCol] = true;
                    q.offer(new int[]{nextRow, nextCol});
                }
            }
            maxDistanceToWater++; // if we find water at 1st level while loop will be running twice. maxDistanceToWater will become 1
        }
        return maxDistanceToWater;
    }


    //[[1,1,1,0,1,0,0,0,1,0,0,1,0,0,0,0,0,1,1,1,1,1,0,0,0,1,0,1,0,0,0,1,1,0,1,0,1,1,1,0],
    // [1,0,1,0,0,1,1,1,0,0,1,0,1,1,0,0,1,1,0,0,1,1,1,1,0,1,1,1,0,1,0,1,0,1,0,0,0,1,1,0],
    // [1,0,1,1,0,1,1,0,0,0,1,1,0,1,1,0,1,1,0,1,0,0,1,0,1,0,0,1,1,0,1,1,0,0,1,1,0,1,1,1],
    // [0,1,1,1,1,0,1,0,0,0,0,1,0,1,1,1,1,0,1,1,0,0,0,1,1,1,0,1,1,1,1,1,1,1,0,1,1,0,1,0],
    // [0,0,0,0,1,0,1,0,1,0,0,1,0,1,0,0,1,1,0,1,0,1,1,1,0,0,1,0,1,1,1,0,1,0,1,1,1,1,0,0],
    // [0,0,0,1,1,0,1,0,0,1,1,1,1,0,1,1,0,1,0,0,1,0,1,0,0,1,1,0,1,1,1,0,1,0,1,0,0,0,1,0],
    // [0,1,0,0,0,1,0,0,1,0,1,0,0,1,1,0,0,1,1,0,0,1,0,1,1,1,1,1,0,1,1,0,0,0,1,1,1,0,1,1],
    // [1,1,1,1,0,1,0,1,1,0,1,0,1,0,1,0,0,0,1,1,0,0,1,0,0,1,1,0,1,0,1,0,1,1,0,1,1,0,1,1],
    // [1,0,1,0,0,1,0,1,0,0,1,1,0,0,1,0,0,1,1,0,0,1,1,1,0,0,0,0,1,0,1,1,0,0,1,1,1,0,1,0],
    // [1,1,1,1,0,1,1,0,1,0,1,1,1,1,1,1,1,0,0,1,0,1,1,1,0,1,0,0,0,0,1,0,1,1,0,1,0,1,1,1],
    // [0,1,1,0,1,0,0,1,1,1,1,0,0,0,0,0,1,0,0,0,1,0,1,1,0,1,0,0,1,0,1,0,0,1,1,0,1,0,0,0],
    // [1,1,0,0,1,1,0,1,1,1,0,0,1,1,1,0,1,0,1,1,0,0,0,1,0,0,0,1,1,0,1,0,0,1,1,0,1,1,1,1],
    // [1,1,1,0,1,1,0,1,1,0,0,1,1,0,0,0,0,0,1,0,0,1,0,1,0,1,1,1,0,0,0,0,1,0,1,1,1,0,1,0],
    // [0,1,1,1,1,0,1,1,1,1,1,0,0,1,1,1,1,1,1,0,1,1,0,1,1,0,1,0,0,0,1,1,1,1,1,0,1,1,1,1],
    // [1,0,1,0,1,1,0,0,1,0,0,0,1,1,0,1,0,0,0,0,1,0,1,1,1,1,1,0,1,1,0,1,0,0,1,1,1,1,0,1],
    // [1,1,0,0,0,0,1,1,1,1,0,1,1,1,0,1,1,1,0,0,0,0,0,1,1,0,1,0,0,0,1,0,1,1,1,0,0,1,1,1],
    // [1,1,0,0,0,1,1,1,0,0,1,1,0,0,1,1,0,1,0,0,0,1,0,1,0,1,0,1,1,1,1,1,0,0,1,0,1,0,0,1],
    // [0,1,1,0,0,0,1,0,0,1,0,0,1,0,0,1,1,1,0,1,1,1,1,1,0,0,0,1,1,0,1,0,1,1,1,1,1,0,0,1],
    // [1,0,1,0,0,0,0,0,1,1,1,0,0,0,0,1,1,1,1,1,0,1,1,0,1,0,0,1,1,0,0,0,0,0,0,1,1,1,0,0],
    // [1,1,0,0,1,1,1,1,1,1,0,1,1,0,1,1,0,1,1,0,0,1,0,0,0,0,1,0,0,0,1,0,1,1,0,1,1,1,1,0],
    // [0,0,0,0,1,1,1,1,0,1,0,1,0,1,0,1,1,0,1,0,0,1,0,1,1,0,1,1,0,0,0,1,0,1,0,1,0,0,1,0],
    // [1,1,1,0,1,0,1,1,0,0,1,1,1,0,1,1,0,0,1,1,0,0,1,1,1,1,0,0,0,1,0,1,0,1,0,1,1,1,1,0],
    // [0,0,1,0,0,0,1,1,1,0,1,0,1,0,1,0,1,0,1,1,1,0,0,1,1,0,0,1,0,0,0,1,1,0,1,1,0,1,0,0],
    // [0,1,1,1,0,1,1,0,1,0,0,0,1,1,0,0,1,1,0,1,1,0,0,0,0,1,0,0,1,0,0,1,0,1,0,1,0,1,0,1],
    // [1,0,1,0,1,0,1,1,0,0,0,1,1,1,1,0,0,1,0,0,1,0,1,1,0,1,0,0,0,1,0,0,1,1,0,0,1,1,1,0],
    // [0,1,1,0,0,0,0,1,1,1,0,1,1,1,0,1,0,1,0,0,1,1,0,1,0,0,0,1,0,1,1,0,0,1,0,1,0,1,0,0],
    // [0,0,0,1,0,1,0,1,1,0,0,1,1,1,0,1,0,0,0,1,0,0,1,0,1,1,1,0,0,0,0,0,1,0,0,0,0,0,0,0],
    // [1,0,0,0,0,0,0,0,1,0,1,0,0,0,0,1,1,0,1,1,1,1,0,0,0,0,0,0,0,0,0,1,1,0,1,0,1,1,1,1],
    // [1,0,1,1,0,1,1,0,1,0,0,0,1,1,1,0,1,0,1,1,0,1,0,1,1,0,1,0,0,1,1,0,1,0,0,1,0,0,0,1],
    // [0,1,1,1,0,0,1,1,0,0,0,0,1,0,1,0,0,0,0,0,1,0,0,1,0,0,0,0,1,1,1,1,0,0,0,0,1,0,0,0],
    // [1,1,0,1,1,0,1,1,1,0,1,0,1,1,0,1,1,0,1,0,0,0,0,0,1,0,1,1,0,0,1,1,0,1,0,0,1,0,0,1],
    // [0,0,1,0,0,0,0,1,1,0,1,1,0,0,0,0,1,1,0,1,0,0,1,1,1,0,1,0,0,1,1,0,0,1,1,0,0,1,1,1],
    // [1,0,0,1,0,0,1,1,0,0,1,0,1,0,0,1,1,0,0,0,0,1,0,1,0,0,1,1,1,0,0,0,0,0,0,1,1,1,0,0],
    // [1,0,0,0,1,1,0,1,0,1,1,0,0,0,1,0,0,0,1,1,0,0,0,0,0,0,1,1,1,1,0,0,1,0,0,1,0,1,0,1],
    // [1,0,1,1,0,0,0,0,0,0,1,1,0,1,1,0,1,1,1,1,1,1,1,1,0,1,1,1,0,0,1,1,1,1,1,0,0,0,1,1],
    // [0,1,0,0,0,1,1,1,1,1,1,1,0,1,1,0,0,1,1,0,0,0,0,0,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0],
    // [0,1,0,0,1,0,0,1,1,0,1,1,1,0,1,0,1,1,1,1,0,0,0,1,1,1,1,0,0,1,0,1,0,0,0,1,1,1,1,1],
    // [0,0,0,0,0,1,1,0,0,0,1,1,0,0,0,1,1,1,1,0,0,0,1,1,1,0,0,1,1,1,1,0,0,1,0,0,1,1,0,1],
    // [1,0,1,0,1,0,1,0,0,0,0,1,1,1,0,0,0,0,0,0,0,0,1,1,0,1,0,0,1,0,0,1,1,0,1,0,0,0,0,1],
    // [1,1,1,0,1,1,0,0,0,0,0,0,0,0,1,1,1,0,1,0,1,0,1,1,0,0,1,0,1,0,1,1,0,0,1,1,1,1,0,0]]

    //index 39, 9 -> minimum distance to 1 is 3 and not 4. We can't use the approach of moving one by one in all 8 directions


}
