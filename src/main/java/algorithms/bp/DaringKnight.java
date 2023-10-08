package algorithms.bp;

//1. Daring Knight Coding Problem:
//• Given a chessboard of size N x N and the starting position of a knight, what is the minimum number of moves required for the knight to reach a given destination square?
//• How would you optimize the algorithm to find the shortest path for the knight?
//• How would you handle cases where the starting position or destination square is not on the board?
//• How would you handle cases where there are obstacles on the board that the knight cannot move past?
//https://www.geeksforgeeks.org/minimum-steps-reach-target-knight/

import java.util.LinkedList;
import java.util.Queue;

public class DaringKnight {
    // A knight moves 2x and 1y or 2y and 1x. 8 possible combinations:
    int[] dirX = {-2, -2, 2, 2, -1, 1, -1, 1};
    int[] dirY = {-1, 1, -1, 1, -2, -2, 2, 2};

    private int minMoves(int[] start, int[] destination, int boardSize) {
        if (start[0] < 0 || start[0] >= boardSize || destination[0] < 0 || destination[0] >= boardSize
                || start[1] < 0 || start[1] >= boardSize || destination[1] < 0 || destination[1] >= boardSize) {
            return -1;
        }
        int moves = -1;
        Queue<int[]> queue = new LinkedList<>();
        queue.add(start);
        boolean[][] visited = new boolean[boardSize][boardSize];
        visited[start[0]][start[1]] = true;
        while (!queue.isEmpty()) {
            moves++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] curr = queue.poll();
                if (curr[0] == destination[0] && curr[1] == destination[1]) {
                    return moves;
                }
                for (int j = 0; j < dirX.length; j++) {
                    int newX = curr[0] + dirX[j];
                    int newY = curr[1] + dirY[j];
                    if (newX >= 0 && newX < boardSize && newY >= 0 && newY < boardSize && !visited[newX][newY]) {
                        queue.add(new int[]{newX, newY});
                        visited[newX][newY] = true;
                    }
                }
            }
        }
        return -1;
        //Time complexity: O(N^2). In the worst case, all the cells will be visited
        //Auxiliary Space: O(N^2). The nodes are stored in a queue.
    }

    // geeksforgeeks for 1 based index
    public int minStepToReachTarget(int start[], int destination[], int boardSize)
    {
        // Code here
        int moves = -1;
        Queue<int[]> queue = new LinkedList<>();
        queue.add(start);
        boolean[][] visited = new boolean[boardSize+1][boardSize+1];
        visited[start[0]][start[1]] = true;
        while (!queue.isEmpty()) {
            moves++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] curr = queue.poll();
                if (curr[0] == destination[0] && curr[1] == destination[1]) {
                    return moves;
                }
                for (int j = 0; j < dirX.length; j++) {
                    int newX = curr[0] + dirX[j];
                    int newY = curr[1] + dirY[j];
                    if (newX >= 1 && newX <= boardSize && newY >= 1 && newY <= boardSize && !visited[newX][newY]) {
                        queue.add(new int[]{newX, newY});
                        visited[newX][newY] = true;
                    }
                }
            }
        }
        return -1;
    }

    private int minMoves2(int[] start, int[] destination, int boardSize, int[][] obstacles) {
        // check that the start and destination are inside board
        if (start[0] < 0 || start[0] >= boardSize || destination[0] < 0 || destination[0] >= boardSize
                || start[1] < 0 || start[1] >= boardSize || destination[1] < 0 || destination[1] >= boardSize) {
            return -1;
        }
        Queue<KnightMove> q = new LinkedList<>();
        q.add(new KnightMove(start[0], start[1], 0));
        boolean[][] visited = new boolean[boardSize][boardSize];
        visited[start[0]][start[1]] = true;

        // mark all obstacles as visited
        for (int[] obstacle : obstacles) {
            visited[obstacle[0]][obstacle[1]] = true;
        }

        while (!q.isEmpty()) {
            KnightMove knightMove = q.poll();
            if (knightMove.x == destination[0] && knightMove.y == destination[1]) {
                return knightMove.moves;
            }
            for (int j = 0; j < dirX.length; j++) {
                int newX = knightMove.x + dirX[j];
                int newY = knightMove.y + dirY[j];
                if (newX >= 0 && newX < boardSize && newY >= 0 && newY < boardSize && !visited[newX][newY]) {
                    q.add(new KnightMove(newX, newY, knightMove.moves +1));
                    visited[newX][newY] = true;
                }
            }
        }
        return -1;
        //Time complexity: O(N^2). In the worst case, all the cells will be visited
        //Auxiliary Space: O(N^2). The nodes are stored in a queue.
    }

    class KnightMove {
        int x;
        int y;
        int moves;

        KnightMove(int x, int y, int moves) {
            this.x = x;
            this.y = y;
            this.moves = moves;
        }
    }
}