package algorithms.leetcodestackandqueues;

import java.util.LinkedList;
import java.util.Queue;

public class CountNoOfIslandsBfs {
    int[][] directions = new int[][]{{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
    public int numIslands(char[][] grid) {
        boolean visited[][] = new boolean[grid.length][grid[0].length];
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (!visited[i][j] && grid[i][j] == '1') {
                    count++;
                    visit(i, j, visited, grid);
                }
            }
        }
        return count;
    }
    private void visit(int row, int col, boolean[][] visited, char[][] grid) {
        Queue<int[]> q = new LinkedList<>();

        q.offer(new int[]{row, col});
        visited[row][col] =true;
        while (!q.isEmpty()) {
            int[] current = q.poll();
            //visited[current[0]][current[1]] =true; // This instead of line 25 and 40 gave TLE. It is less time consuming to mark the node as soon in the loop itself (at line 40).
            //If we don't mark it as true in loop and just add it to the end of the queue, the adjacent nodes will also check it and may offer it to queue again
            for(int i = 0; i< directions.length; i++) {
                int newRow = current[0] + directions[i][0];
                int newCol = current[1] + directions[i][1];
                if(newRow < 0 || newCol< 0
                        || newRow>= grid.length || newCol >=grid[0].length
                        || visited[newRow][newCol]
                        || grid[newRow][newCol] =='0') {
                    continue;
                }
                q.offer(new int[]{newRow, newCol});
                visited[newRow][newCol] = true;
            }
        }
    }

}

class Pair{
    int first;
    int second;

    public Pair(int first, int second) {
        this.first = first;
        this.second = second;
    }
}
