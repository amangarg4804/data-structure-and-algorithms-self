package algorithms.leetcodestackandqueues;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.Collectors;

public class PerfectSquares {

    public static void main(String[] args) {
        numSquares(12);
    }
    public static int numSquares(int n) {
        int sqroot = (int) Math.sqrt(n);
        boolean[] visited = new boolean[n]; // visited array is optional
        int[] squares = new int[sqroot];
        for (int i = 1; i <= squares.length; i++) {
            squares[i - 1] = i * i;
        }
        Queue<Integer> q = new LinkedList<>();
        q.offer(n);
        int level = 0;
        while (!q.isEmpty()) {
            int size = q.size();

            for (int i = 0; i < size; i++) {
                int num = q.poll();
                for (int j = 0; j < squares.length; j++) {
                    int remainder = num - squares[j];
                    if (remainder == 0) {
                        return level + 1;
                    }
                    if (remainder < 0) {
                        break;
                    }
                    if (!visited[remainder]) { //remainder > 0 // visited is optional
                        visited[remainder] = true;
                        q.offer(remainder);
                    }
                }
            }
            level++;
        }
        return level;
    }
}
