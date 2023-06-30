package algorithms.leetcodehashtable;


import java.util.*;


public class ValidSudoku {

    public boolean isValidSudoku(char[][] board) {
//        HashSet<Character> [] rows = new HashSet<Character>[9]; NOTE: can't mix array with generics
        List<HashSet<Character>> rows = new ArrayList<>();
        List<HashSet<Character>> cols = new ArrayList<>();
        Map<String, HashSet<Character>> cubes = new HashMap<>();
        for(int i=0; i< 9; i++) {
            rows.add(new HashSet<>());
            cols.add(new HashSet<>());
        }
        for(int row=0; row< board.length; row++) {
            for (int col = 0; col < board[row].length; col++ ) {
                if(board[row][col] =='.') {
                    continue;
                }
                if(!rows.get(row).add(board[row][col])) {
                    return false;
                }
                if(!cols.get(col).add(board[row][col])) {
                    return false;
                }
                String cubeKey = row/3 + "-" + col/3;
                if(!cubes.containsKey(cubeKey)) {
                    cubes.put(cubeKey, new HashSet<>());
                }
                if(!cubes.get(cubeKey).add(board[row][col])) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isValidSudoku2(char[][] board) {
//        HashSet<Character> [] rows = new HashSet<Character>[9]; NOTE: can't mix array with generics
        List<HashSet<Character>> rows = new ArrayList<>();
        List<HashSet<Character>> cols = new ArrayList<>();
        List< HashSet<Character>> cubes = new ArrayList<>();
        for(int i=0; i< 9; i++) {
            rows.add(new HashSet<>());
            cols.add(new HashSet<>());
            cubes.add(new HashSet<>());
        }
        for(int row=0; row< board.length; row++) {
            for (int col = 0; col < board[row].length; col++ ) {
                if(board[row][col] =='.') {
                    continue;
                }
                if(!rows.get(row).add(board[row][col])) {
                    return false;
                }
                if(!cols.get(col).add(board[row][col])) {
                    return false;
                }
                int cubeIndex = ((row/3) *3 )+ col/3; // NOTE: formula for cube index in sudoku
                if(!cubes.get(cubeIndex).add(board[row][col])) {
                    return false;
                }
            }
        }
        return true;
    }
    public boolean isValidSudoku3(char[][] board) {
//        HashSet<Character> [] rows = new HashSet<Character>[9]; NOTE: can't mix array with generics
        Set<String> rows = new HashSet<>();
        Set<String> cols = new HashSet<>();
        Set<String> cubes = new HashSet<>();

        for(int row=0; row< board.length; row++) {
            for (int col = 0; col < board[row].length; col++ ) {
                if(board[row][col] =='.') {
                    continue;
                }
                if(!rows.add("Number: " + board[row][col] + "Row: " + row)) {
                    return false;
                }
                if(!cols.add("Number: " + board[row][col] + "Col: " + col)) {
                    return false;
                }
                if(!cubes.add("Number: " + board[row][col] + "Cube: " + row/3 + "-" + col/3)) {
                    return false;
                }
            }
        }
        // we can also use a single hashset
        return true;
    }
}


//  0 1 2 3 4 5 6 7 8
// 0
// 1
// 2
// 3
// 4
// 5
// 6
// 7
// 8
//           Row    Col      Sum
//          2/3 =0, 2/3 =0    0
//          5/3 =1, 2/3 =0    1
//          8/3 =2, 2/3 =0    2
//          2/3 =0, 5/3 =1    1
//          5/3 =1, 5/3 =1    2
//          8/3 =2, 5/3 =1    3
//          2/3 =0, 8/3 =2    2
//          5/3 =1, 8/3 =2    3
//          8/3 =2, 8/3 =2    4

// We can see the sum is not a unique key to identify a cube uniquely. What else can be used?
// We can use Map with String row/3 +"=" + col/3 as key