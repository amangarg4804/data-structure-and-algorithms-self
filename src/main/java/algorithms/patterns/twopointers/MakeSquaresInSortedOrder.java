package algorithms.patterns.twopointers;

import java.util.Arrays;

public class MakeSquaresInSortedOrder {

  public static void main(String[] args) {
    System.out.println(Arrays.toString(makeSquares(new int[]{-2, -1, 0, 2, 3})));
    System.out.println(Arrays.toString(makeSquares(new int[]{-3, -1, 0, 1, 2})));
    System.out.println(Arrays.toString(makeSquaresSingleLoop(new int[]{-2, -1, 0, 2, 3})));
    System.out.println(Arrays.toString(makeSquaresSingleLoop(new int[]{-3, -1, 0, 1, 2})));
  }

  public static int[] makeSquares(int[] arr) {
    int[] squares = new int[arr.length];
    int left = 0;
    int right = 0;
    int currentIndex = 0;

    for (int i = 0; i < arr.length; i++) {
      if (arr[i] < 0) {
        left = i;
        right = i + 1;
      }
    }

    while (left >=0 && right <=arr.length-1) {
        int leftValue = Math.abs(arr[left]);
        int rightValue = Math.abs(arr[right]);
        if(leftValue < rightValue) {
          squares[currentIndex] = leftValue * leftValue;
          left--;
        } else {
          squares[currentIndex] = rightValue * rightValue;
          right++;
        }
        currentIndex++;
    }
    while (right <= arr.length-1) {
      int rightValue = Math.abs(arr[right]);
      squares[currentIndex] = rightValue * rightValue;
      right++;
      currentIndex++;
    }
    while (left >= 0) {
      int leftValue = Math.abs(arr[left]);
      squares[currentIndex] = leftValue * leftValue;
      left--;
      currentIndex++;
    }
    return squares;
  }

  public static int[] makeSquaresSingleLoop(int[] arr) {
    int[] squares = new int[arr.length];
    int left = 0;
    int right = arr.length-1;
    int currentIndex = arr.length-1;

    while (left <=right) {
      int leftValue = Math.abs(arr[left]);
      int rightValue = Math.abs(arr[right]);
      if(leftValue > rightValue) {
        squares[currentIndex] = leftValue * leftValue;
        left++;
      } else {
        squares[currentIndex] = rightValue * rightValue;
        right--;
      }
      currentIndex--;
    }
    return squares;
  }
}
