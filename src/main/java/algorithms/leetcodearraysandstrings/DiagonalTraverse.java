package algorithms.leetcodearraysandstrings;


import java.util.*;

//Input: mat =
// [[1,2,3],
// [4,5,6],
// [7,8,9]]
//Output: [1,2,4,7,5,3,6,8,9]
public class DiagonalTraverse {

    public int[] findDiagonalOrder(int[][] mat) {
        // doesn't print in diagonal order
        int[] result = new int[mat.length* mat[0].length];
        int index =0;
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{0,0});
        boolean[][] visited = new boolean[mat.length][mat[0].length];
        visited[0][0] = true;
        while (!q.isEmpty()) {
            int size = q.size();
            for(int i=0; i< size ;i++) {
                int[] current = q.poll();
                int row = current[0];
                int col = current[1];
                result[index++] = mat[row][col];
                int right = col+1;
                int down = row+1;
                if(right < mat[0].length && !visited[row][right]) {
                    q.offer(new int[]{row, right});
                    visited[row][right] = true;
                }
                if(down <mat.length && !visited[down][col]) {
                    q.offer(new int[]{down, col});
                    visited[down][col] = true;
                }
            }
        }
        return result;
    }

    public int[] findDiagonalOrder1(int[][] mat) {
        int[] result = new int[mat.length* mat[0].length];
        int row =0;
        int col =0;
        for(int i=0; i< result.length;i++) {
            result[i] = mat[row][col];
            if((row+col) %2==0) { // for {0,0}, {2, 0}->  going up
                if(col == mat[0].length-1) { // last column - change row
                    row++;
                } else if(row==0) {// reached 0th row? cant move up, change col
                    col++;
                } else {
                    col++;
                    row--;
                }
            } else {  // going down
                if(row == mat.length-1) { // last row , change column
                    col++;
                } else if(col ==0) {
                    row++;
                } else {
                    row++;
                    col--;
                }
            }
        }
        return result;
    }

    public int[] findDiagonalOrder2(int[][] mat) {
        // extra space
        int[] result = new int[mat.length* mat[0].length];
        Map<Integer, List<Integer>> map = new HashMap<>(); // row +col will be key, a diagonal has same value for row+col
        for(int row=0; row< mat.length;row++) {
            for(int col=0; col<mat[row].length; col++ ) {
                int key= row+col;
                List<Integer> diagonal = map.getOrDefault(key, new ArrayList<>());
                diagonal.add(mat[row][col]);
                map.put(key, diagonal);
            }
        }
        boolean up =true; // 0 is up, 1 is down
        int index =0;
        for(int i =0; i<map.size(); i++ ) {
            List<Integer> diagonal = map.get(i);
            if(up) {// instead of using this boolean we can also check i%2==0
                for(int j=diagonal.size()-1; j>=0; j--) {
                    result[index++] = diagonal.get(j);
                }
                up=false;
            } else {
                for(int j=0; j< diagonal.size(); j++) {
                    result[index++] = diagonal.get(j);
                }
                up=true;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        new DiagonalTraverse().findDiagonalOrder1(new int[][]{{1, 2,3}, {4, 5, 6}, {7, 8, 9}});
    }
}
