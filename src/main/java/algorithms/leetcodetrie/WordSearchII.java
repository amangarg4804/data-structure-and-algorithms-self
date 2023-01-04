package algorithms.leetcodetrie;

import java.util.ArrayList;
import java.util.List;

public class WordSearchII {
    int[][] directions = new int[][] {{0,1}, {0, -1}, {-1, 0}, {1, 0}};
    public List<String> findWords(char[][] board, String[] words) {
        // TLE
        List<String> result = new ArrayList<>();
        for(String word : words) {
            boolean exists;
            for(int i=0; i< board.length; i++) {
                for(int j=0; j< board[0].length; j++) {
                    boolean[][] visited = new boolean[board.length][board[0].length];
                    visited[i][j] =true;
                    exists = dfs(i, j, word, 0, board, visited);
                    if(exists) {
                        result.add(word);
                        i= board.length; // we can also add the result to Set to avoid this hack and then covert set to arraylist later
                        break;
                    }
                }
            }

        }
        return result;
    }

    private boolean dfs(int row, int col, String word, int index , char[][] board, boolean[][] visited) {
        if(board[row][col] != word.charAt(index)) {
            return false;
        }
        if(index == word.length()-1) {
            return true;
        }
        for(int i=0; i< directions.length; i++) {
            int newRow = row + directions[i][0];
            int newCol = col + directions[i][1];
            if( newRow <0 || newRow >= board.length || newCol <0 || newCol >=board[0].length || visited[newRow][newCol] || board[newRow][newCol] != word.charAt(index+1)) {
                continue;
            }
            visited[newRow][newCol] = true;
            if(dfs(newRow, newCol, word, index+1, board, visited)) {
                return true;
            }
            visited[newRow][newCol] = false;
        }
        return false;

    }
}

