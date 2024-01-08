package algorithms.leetcodegraph.island;

//Given a 2D grid consists of 0s (land) and 1s (water).  An island is a maximal 4-directionally connected group of 0s
// and a closed island is an island totally (all left, top, right, bottom) surrounded by 1s.
//Return the number of closed islands.
public class NumberOfClosedIslands {
    public static void main(String[] args) {
//        new NumberOfClosedIslands().closedIsland(new int[][] {
//                {0,1,1,1,0},
//                {1,0,1,0,1}, //row =1, col =1
//                {1,0,1,0,1},
//                {1,0,0,0,1},
//                {0,1,1,1,0}});
        new NumberOfClosedIslands().closedIsland(new int[][]{
                {1, 1, 0, 1, 1, 1, 1, 1, 1, 1},
                {0, 0, 1, 0, 0, 1, 0, 1, 1, 1},
                {1, 0, 1, 0, 0, 0, 1, 0, 1, 0},
                {1, 1, 1, 1, 1, 0, 0, 1, 0, 0},
                {1, 0, 1, 0, 1, 1, 1, 1, 1, 0},
                {0, 0, 0, 0, 1, 1, 0, 0, 0, 0},
                {1, 0, 1, 0, 0, 0, 0, 1, 1, 0},
                {1, 1, 0, 0, 1, 1, 0, 0, 0, 0},
                {0, 0, 0, 1, 1, 0, 1, 1, 1, 0},
                {1, 1, 0, 1, 0, 1, 0, 0, 1, 0}});
    }

    public int closedIsland(int[][] grid) {
        // closed islands will have none of the land connected to edges of the grid. For grid[row][col], row !=grid.length-1 && col != grid[0].length-1
        int noOfClosedIslands = 0;
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                if (grid[row][col] == 0 && !visited[row][col]) {
                    int count = countClosedIslands(grid, visited, row, col);
                    if (count > 0) {
                        noOfClosedIslands++;
                    }
                }
            }
        }
        return noOfClosedIslands;
    }

    // [[0,0,1,0,0],[
    //   0,1,0,1,0], row=1, col = 2
    //  [0,1,1,1,0]]
    private int countClosedIslands(int[][] grid, boolean[][] visited, int row, int col) {
        if (row < 0 || col < 0 || row >= grid.length || col >= grid[0].length || visited[row][col] || grid[row][col] == 1) {
            return 0;
        }
        // at this point we are sure it is land
        visited[row][col] = true;

        if (row == grid.length - 1 | col == grid[0].length - 1
                | row == 0 | col == 0
                | countClosedIslands(grid, visited, row + 1, col) == -1
                | countClosedIslands(grid, visited, row - 1, col) == -1
                | countClosedIslands(grid, visited, row, col + 1) == -1
                | countClosedIslands(grid, visited, row, col - 1) == -1) { //using bitwise or to visit all adjacent land.
            // Note that we still need to visit adjacent nodes even thiugh current node is at the edge, if we don't do this,
            // we would be marking the current node as visited. When the adjacent node of this node will be visited in for loop,
            // the condition at line 48 will return 0. And we will be counting it as closed island.
            // This will result in mor closed islands than actual
            return -1; // return -1 if any of the land belonging to this island is at the edge of the grid
        }
        return 1;
    }

    public int closedIsland2(int[][] grid) {
        // closed islands will have none of the land connected to edges of the grid. For grid[row][col], row !=grid.length-1 && col != grid[0].length-1
        int noOfClosedIslands = 0;
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                if (grid[row][col] == 0 && !visited[row][col]) {
                    if(isClosedIsland(grid, visited, row, col)) {
                        noOfClosedIslands++;
                    }
                }
            }
        }
        return noOfClosedIslands;
    }

    // [[0,0,1,0,0],[
    //   0,1,0,1,0], row=1, col = 2
    //  [0,1,1,1,0]]
    private boolean isClosedIsland(int[][] grid, boolean[][] visited, int row, int col) {
        if (row < 0 || col < 0 || row >= grid.length || col >= grid[0].length) {
            // if we reach outside the edge of the grid, it's not a closed island
            return false;
        }
        if(visited[row][col] || grid[row][col] == 1) {
            // if the land was already visited or we reach water(1), it's a closed island in this direction
            return true;
        }
        // at this point we are sure it is land
        visited[row][col] = true;

        if (!isClosedIsland(grid, visited, row + 1, col)
                | !isClosedIsland(grid, visited, row - 1, col)
                | !isClosedIsland(grid, visited, row, col + 1)
                | !isClosedIsland(grid, visited, row, col - 1) ) { //using bitwise or to visit all adjacent land.
            // Note that we still need to visit adjacent nodes even thiugh current node is at the edge, if we don't do this,
            // we would be marking the current node as visited. When the adjacent node of this node will be visited in for loop,
            // the condition at line 48 will return 0. And we will be counting it as closed island.
            // This will result in mor closed islands than actual
            return false; // return -1 if any of the land belonging to this island is at the edge of the grid
        }
        return true;
    }

}
