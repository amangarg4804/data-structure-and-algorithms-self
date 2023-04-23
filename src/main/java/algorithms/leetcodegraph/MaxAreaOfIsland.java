package algorithms.leetcodegraph;

//The area of an island is the number of cells with a value 1 in the island.
public class MaxAreaOfIsland {
    public int maxAreaOfIsland(int[][] grid) {
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        int maxArea = 0; //if no island, we have to return 0
        for(int row = 0; row< grid.length; row++) {
            for(int col = 0 ; col < grid[row].length; col++) {
                if(grid[row][col]==1 && !visited[row][col]) {
                    visited[row][col] = true;
                    int area = calculateArea2(grid, row, col, visited);
                    maxArea = Math.max(area, maxArea);
                }
            }
        }
        return maxArea;
    }
    int[][] directions = new int[][] {{0,1}, {1,0}, {0,-1}, {-1,0}};
    private int calculateArea(int[][] grid, int row, int col, boolean[][] visited, int area) {

        area++;
        for(int i =0; i< directions.length; i++) {
            int newRow = row + directions[i][0];
            int newCol = col +directions[i][1];
            if(newRow <grid.length && newCol < grid[0].length && newRow>=0 && newCol >=0 && grid[newRow][newCol]==1 && !visited[newRow][newCol]) {
                visited[newRow][newCol]=true;
                area+= calculateArea(grid, newRow, newCol, visited, 0); // can't directly return, we have to go in all 4 directions
            }
        }
        return area;
    }

    public int maxAreaOfIsland2(int[][] grid) {
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        int maxArea = 0; //if no island, we have to return 0
        for(int row = 0; row< grid.length; row++) {
            for(int col = 0 ; col < grid[row].length; col++) {
                if(grid[row][col]==1 && !visited[row][col]) {
//                    visited[row][col] = true;     // we can't set visitted to true here in this solution. Answer will always be 0 if we do
                    int area = calculateArea2(grid, row, col, visited);
                    maxArea = Math.max(area, maxArea);
                }
            }
        }
        return maxArea;
    }

    private int calculateArea2(int[][] grid, int row, int col, boolean[][] visited) {
        if(row >=grid.length  || col >= grid[0].length || row<0  || col<0  || grid[row][col]==0 || visited[row][col]) {
            return 0;
        }
        visited[row][col] =true;
        return 1 + calculateArea2(grid, row+1, col, visited) +
                calculateArea2(grid, row-1, col, visited) +
                calculateArea2(grid, row, col+1, visited) +
                calculateArea2(grid, row, col-1, visited);
    }


}
