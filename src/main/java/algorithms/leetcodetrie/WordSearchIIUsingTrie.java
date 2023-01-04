package algorithms.leetcodetrie;

import java.util.ArrayList;
import java.util.List;

public class WordSearchIIUsingTrie {
    int[][] directions = new int[][] {{0,1}, {0, -1}, {-1, 0}, {1, 0}};

    TrieNode root = new TrieNode();
    public List<String> findWords(char[][] board, String[] words) {
        List<String> result = new ArrayList<>();
        prepareTrie(words);
        for(int i=0; i< board.length; i++) {
            for(int j=0; j< board[i].length; j++) {
                boolean[][] visited = new boolean[board.length][board[0].length];
                visited[i][j] = true;
                dfsbacktrack(root, visited, result, i, j, board);
            }
        }
        return result;
    }

    private void dfsbacktrack(TrieNode currentNode, boolean[][] visited, List<String> result , int row,int col, char[][] board) {
        TrieNode node = currentNode.children[board[row][col] -'a'];
        if(node ==null) {
            return;
        } else if(node.isWord) {
            result.add(node.word);
            node.isWord = false; // same TrieNode can be reached using multiple paths in board, but we want the word only once in result, so once we have added the word in result, we can mark isWord =false
            node.word = null;
        }

        for( int i=0; i< directions.length; i++) {
            int newRow = row + directions[i][0];
            int newCol = col + directions[i][1];
            if(newRow < 0 || newRow >= board.length
                    || newCol <0 || newCol >= board[0].length || visited[newRow][newCol]) {
                continue;
            }
            visited[newRow][newCol] = true;
            dfsbacktrack(currentNode.children[board[row][col]-'a'], visited, result, newRow, newCol, board);
            visited[newRow][newCol] = false;
        }
    }

    private void prepareTrie(String[] words) {
        for(String s : words) {
            TrieNode current = root;
            for(char c: s.toCharArray()) { // NOTE: converting to char array instead of using charAt for speed
                int index = c -'a';
                if(current.children[index] == null) {
                    current.children[index] = new TrieNode();
                }
                current = current.children[index];
            }
            current.isWord = true; // is word can be removed, we can check whether word is null instead
            current.word = s;
        }
    }


    class TrieNode {
       TrieNode[] children = new TrieNode[26];
       boolean isWord;
       String word;
    }
}
