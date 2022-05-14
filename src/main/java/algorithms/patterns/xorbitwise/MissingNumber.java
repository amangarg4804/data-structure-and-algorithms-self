package algorithms.patterns.xorbitwise;

// Given an array of n-1 integers in the range from 1 to n, find the one number that is missing from the array.
public class MissingNumber {

  public static void main(String[] args) {
    System.out.println(findMissingNumber(new int[] {1, 2, 3, 4, 6}));
  }

  private static int findMissingNumber(int[] arr) {
    int n = arr.length + 1;
    int x1 = 1;
    for (int i = 2; i <= n; i++) {
      x1 = x1 ^ i;
    }

    int x2 = arr[0];
    for (int i = 1; i < arr.length; i++) {
      x2 = x2 ^ arr[i];
    }
    return x1 ^ x2;
  }


}
