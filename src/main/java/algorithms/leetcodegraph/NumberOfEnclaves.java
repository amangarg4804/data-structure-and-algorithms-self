package algorithms.leetcodegraph;

//You are given an m x n binary matrix grid, where 0 represents a sea cell and 1 represents a land cell.
//
//A move consists of walking from one land cell to another adjacent (4-directionally) land cell or walking off the boundary of the grid.
//
//Return the number of land cells in grid for which we cannot walk off the boundary of the grid in any number of moves.
public class NumberOfEnclaves {

    public int numEnclaves(int[][] grid) {
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        int totalEnclaves = 0;
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                if (grid[row][col] == 1 && !visited[row][col]) {
                    int enclaves = enclaves(grid, visited, row, col);
                    if (enclaves > 0) {
                        totalEnclaves += enclaves;
                    }

                }
            }
        }
        return totalEnclaves;
    }

    private int enclaves(int[][] grid, boolean[][] visited, int row, int col) {
        if (row < 0 || col < 0 || row >= grid.length || col >= grid[0].length ) {
            return -1;
        }
        if(visited[row][col] || grid[row][col] ==0) {
            return 0;
        }
        visited[row][col] = true;
        int down = enclaves(grid, visited, row + 1, col);
        int up = enclaves(grid, visited, row - 1, col);
        int right = enclaves(grid, visited, row, col + 1);
        int left = enclaves(grid, visited, row, col - 1);
        if (        //row == grid.length - 1 || row == 0 || col == 0 || col == grid[0].length - 1||
                 down == -1
                || up == -1
                || right == -1
                || left == -1) {
            return -1;
        }
        return 1 + down + up + right + left;
    }
//[[0,0,0,1,1,1,0,1,0,0],
// [1,1,0,0,0,1,0,1,1,1],
// [0,0,0,1,1,1,0,1,0,0],
// [0,1,1,0,0,0,1,0,1,0],
// [0,1,1,1,1,1,0,0,1,0],
// [0,0,1,0,1,1,1,1,0,1],
// [0,1,1,0,0,0,1,1,1,1],
// [0,0,1,0,0,1,0,1,0,1],
// [1,0,1,0,1,1,0,0,0,0],
// [0,0,0,0,1,1,0,0,0,1]]
}

