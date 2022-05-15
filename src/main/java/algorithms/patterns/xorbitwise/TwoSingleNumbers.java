package algorithms.patterns.xorbitwise;

import java.util.Arrays;

// In a non-empty array of numbers, every number appears exactly twice except two numbers that appear only once. Find the two numbers that appear only once.
public class TwoSingleNumbers {

  public static void main(String[] args) {
    System.out.println(Arrays.toString(twoMissingNumbers(new int[]{1, 4, 2, 1, 3, 5, 6, 2, 3, 5})));
    System.out.println(Arrays.toString(twoMissingNumbers(new int[]{2, 1, 3, 2})));
  }

  private static int[] twoMissingNumbers(int[] arr) {
    int x1 = arr[0];
    for (int i = 1; i < arr.length; i++) {
      x1 = x1 ^ arr[i];
    }
    // x1 is ^ of two missing numbers
    //find the rightmost different bit of these two numbers, which will be 1
    int rightmostSetBit = 1;
    while ((x1 & rightmostSetBit) == 0) {
      rightmostSetBit = rightmostSetBit << 1;
    }
    int num1 = 0;
    int num2 = 0;
    for (int i = 0; i < arr.length; i++) {
      if ((rightmostSetBit & arr[i]) != 0) { // bit is set
        num1 = num1 ^ arr[i];
      } else {
        num2 = num2 ^ arr[i];
      }
    }
    return new int[]{num1, num2};
    // time O(n), space O(1)
  }
}

