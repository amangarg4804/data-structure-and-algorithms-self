package algorithms.leetcodestackandqueues;

public class CountNoOfIslandsDfs {
    int[][] directions = new int[][]{{-1, 0}, {1, 0}, {0, 1}, {0, -1}};

    public int numIslandsBFS(char[][] grid) {
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
        for (int i = 0; i < directions.length; i++) {
            int newRowNum = row + directions[i][0];
            int newColNum = col + directions[i][1];

            if (newRowNum < 0 || newColNum < 0
                    || newRowNum >= grid.length || newColNum >= grid[0].length
                    || visited[newRowNum][newColNum]
                    || grid[newRowNum][newColNum]=='0') {
                continue;
            }
            visited[newRowNum][newColNum] = true;
            visit(newRowNum, newColNum, visited, grid);
        }
    }

}
