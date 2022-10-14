package algorithms.leetcodestackandqueues;

import java.util.LinkedList;
import java.util.Queue;

public class MatrixDistanceToZero {

    public static void main(String[] args) {
        int [][]result = updateMatrix(new int[][] {{0,0,0}, {0, 1, 0}, {1, 1, 1}});

    }
    static int[][] directions = new int[][] {{0, 1}, {-1, 0}, {0, -1}, {1, 0}};
    public static int[][] updateMatrix(int[][] mat) {
        int[][] result = new int[mat.length][mat[0].length];

        for(int i =0; i< mat.length;i++) {
            for(int j=0; j< mat[i].length; j++) {
                minDistanceToZero(i, j, mat, result);
            }
        }
        return result;
    }

    private static void minDistanceToZero(int i, int j, int[][] mat, int[][] result) {
        // TLE in extreme case where the array is of length 1 and it has 10^4 elements and only last element is 0, rest are 1
        if(mat[i][j] ==0) {
            return;
        }
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{i, j});
        boolean[][] visited = new boolean[mat.length][mat[0].length];
        visited[i][j] = true;
        int level =0;
        while (!q.isEmpty()) {
            int size = q.size();
            for(int l =0; l< size ;l++) {
                int[] current = q.poll();
                if(mat[current[0]][current[1]] ==0) {
                    result[i][j] = level;
                    return;
                }
                for(int k =0; k < directions.length;k++) {
                    int newRow = current[0] + directions[k][0];
                    int newCol = current[1] + directions[k][1];

                    if(newRow <0 || newRow >= mat.length || newCol < 0 || newCol >= mat[0].length || visited[newRow][newCol]) {
                        continue;
                    }
                    visited[newRow][newCol]=true;
                    q.offer(new int[]{newRow, newCol});
                }
            }
            level++;
        }
    }

    public static int[][] updateMatrixOptimized(int[][] mat) {
        int[][] result = new int[mat.length][mat[0].length];
        boolean[][] visited = new boolean[mat.length][mat[0].length];
        Queue<int[]> q = new LinkedList<>();
        for(int i =0; i< mat.length;i++) {
            for(int j=0; j< mat[i].length; j++) {
                if(mat[i][j]==0) {
                    q.offer(new int[]{i, j});
                    visited[i][j] = true;
                }
            }
        }

        int level =0;

        while(!q.isEmpty()) {
            int size = q.size();
            for(int i=0; i< size; i++) {
                int[] current = q.poll();
                result[current[0]][current[1]] = level;
                for(int k =0; k < directions.length;k++) {
                    int newRow = current[0] + directions[k][0];
                    int newCol = current[1] + directions[k][1];

                    if(newRow <0 || newRow >= mat.length || newCol < 0 || newCol >= mat[0].length || visited[newRow][newCol]) {
                        continue;
                    }
                    visited[newRow][newCol]=true;
                    q.offer(new int[]{newRow, newCol});
                }
            }
            level++;
        }
        return result;
    }
}
