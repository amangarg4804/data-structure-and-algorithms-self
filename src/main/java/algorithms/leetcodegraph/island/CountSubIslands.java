package algorithms.leetcodegraph.island;

//You are given two m x n binary matrices grid1 and grid2 containing only 0's (representing water) and 1's (representing land).
// An island is a group of 1's connected 4-directionally (horizontal or vertical). Any cells outside of the grid are considered water cells.
//
//An island in grid2 is considered a sub-island if there is an island in grid1 that contains all the cells that make up this island in grid2.
//
//Return the number of islands in grid2 that are considered sub-islands.
public class CountSubIslands {

    public int countSubIslands(int[][] grid1, int[][] grid2) {
        int subIslands = 0;
        boolean[][] visited = new boolean[grid2.length][grid2[0].length];
        for (int row = 0; row < grid2.length; row++) {
            for (int col = 0; col < grid2[0].length; col++) {
                if (grid2[row][col] == 1 && !visited[row][col]) {
                    if (isSubIsland(grid1, grid2, row, col, visited) == 1) {
                        subIslands++;
                    }
                }
            }
        }
        return subIslands;
    }

    private int isSubIsland(int[][] grid1, int[][] grid2, int row, int col, boolean[][] visited) {
        if (row < 0 || col < 0 || row >= grid2.length || col >= grid2[0].length || visited[row][col] || grid2[row][col] == 0) {
            return 0; // boundary reached, no need to visit further in this direction
        }
        visited[row][col] = true;

        if ((grid2[row][col] == 1 && grid1[row][col] == 0)
                | isSubIsland(grid1, grid2, row + 1, col, visited) == -1
                | isSubIsland(grid1, grid2, row - 1, col, visited) == -1
                | isSubIsland(grid1, grid2, row, col + 1, visited) == -1
                | isSubIsland(grid1, grid2, row, col - 1, visited) == -1) {
            return -1;
        }
        return 1;
    }


}
