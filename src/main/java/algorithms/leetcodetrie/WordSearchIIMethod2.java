package algorithms.leetcodetrie;

import java.util.*;

public class WordSearchIIMethod2 {
    int[][] directions = new int[][] {{0,1}, {0, -1}, {-1, 0}, {1, 0}};
    public List<String> findWords(char[][] board, String[] words) {
        Set<String> input = new HashSet<>();
        Set<String> result = new HashSet<>(); // using set because it is possible a given word can be formed multiple times using different paths on board
        int maxLength =0; // This is an optimization, If the length of the string being formed inside dfs increases beyond the maximum length of given words, we can stop dfs
        for(String word: words) {
            input.add(word); // contains on set is amortized O(1)
            maxLength= Math.max(maxLength, word.length());
        }

        for(int i=0; i< board.length; i++) {
            for(int j=0; j< board[i].length; j++) {
                boolean[][] visited = new boolean[board.length][board[0].length];
                visited[i][j] = true;
                dfsBacktrack(i, j, "", input, result, visited, board, maxLength);
            }
        }
        return new ArrayList<>(result);
    }

    private void dfsBacktrack(int row, int col, String current, Set<String> input, Set<String> result, boolean[][] visited, char[][] board, int maxLength) {
        current = current + board[row][col];
        if(input.contains(current)) {
            result.add(current);
        }
        if(current.length() ==maxLength) {  // This solution works only after adding this optimization for TLE
            return;// If current's string length is equal to maxLength, we don't need to check all directions as they will only be adding chars to word and increasing the length beyond maximum given length
        }
        for(int i=0; i< directions.length; i++) {
            int newRow = row + directions[i][0];
            int newCol = col + directions[i][1];
            if (newRow < 0 || newRow >= board.length || newCol < 0 || newCol >= board[0].length || visited[newRow][newCol]) {
                continue;
            }
            visited[newRow][newCol] = true;
            dfsBacktrack(newRow, newCol, current, input, result, visited, board, maxLength);
            visited[newRow][newCol] = false;

        }
    }

    public static void main(String[] args) {
        System.out.println(new WordSearchIIMethod2().findWords(new char[][] {{'o','a','a','n'},{'e','t','a','e'},{'i','h','k','r'},{'i','f','l','v'}}, new String[]{"eat"}));
    }
}
