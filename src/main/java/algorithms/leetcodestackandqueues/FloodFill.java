package algorithms.leetcodestackandqueues;

public class FloodFill {
    int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        boolean[][] visited = new boolean[image.length][image[0].length];
        floodFill(image, sr, sc, image[sr][sc] ,color, visited);
        return image;
    }

    private void floodFill(int[][] image, int sr, int sc, int oldColour, int color, boolean[][] visited) {
        //DFS
        if (sr < 0 || sr >= image.length || sc < 0 || sc >= image[0].length || visited[sr][sc] || image[sr][sc] != oldColour) {
            return;
        }
        visited[sr][sc] = true;
        image[sr][sc] = color;
        for(int i =0; i< directions.length; i++) {
            floodFill(image, sr+ directions[i][0], sc + directions[i][1], oldColour,  color, visited);
        }
    }
}


