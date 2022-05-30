package algorithms.patterns.kwaymerge;

import java.util.PriorityQueue;

public class KthSmallestInSortedMatrix {

  public static void main(String[] args) {
    int[][] matrix = {
        {2, 6, 8},
        {3, 7, 10},
        {5, 8, 11}
    };
    System.out.println(kthSmallest(matrix, 5));
  }

  private static int kthSmallest(int[][] matrix, int k) {
    PriorityQueue<Node> minHeap = new PriorityQueue<>(
        (n1, n2) -> matrix[n1.row][n1.col] - matrix[n2.row][n2.col]);
    for (int i = 0; i < matrix.length; i++) { // can also add "&& i< k"
      if (matrix[i] != null) {
        minHeap.offer(new Node(i, 0));
      }
    }
    int result = -1;
    while (!minHeap.isEmpty()) {
      Node current = minHeap.poll();
      result = matrix[current.row][current.col];
      if (--k == 0) {
        return result;
      }
      current.col++;
      if (current.col < matrix[current.row].length) {
        minHeap.offer(current);
      }
    }
    return result;
  }
}

class Node {

  int row;
  int col;

  public Node(int row, int col) {
    this.row = row;
    this.col = col;
  }

  public int getRow() {
    return row;
  }

  public void setRow(int row) {
    this.row = row;
  }

  public int getCol() {
    return col;
  }

  public void setCol(int col) {
    this.col = col;
  }
}