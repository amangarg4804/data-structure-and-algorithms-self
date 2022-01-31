package algorithms.patterns.twopointers;

import java.util.Arrays;
// Given an array containing 0s, 1s and 2s, sort the array in-place. You should treat numbers of the array as objects, hence, we can’t count 0s, 1s, and 2s to recreate the array.
public class DutchNationalFlag {

  public static void main(String[] args) {
    System.out.println(Arrays.toString(sort(new int[]{1, 0, 2, 1, 0})));
    System.out.println(Arrays.toString(sort(new int[]{2, 2, 0, 1, 2, 0})));
  }

  public static int[] sort(int[] arr) {
    int availableZeroindex = 0;
    int availableTwoIndex = arr.length - 1;
    for (int i = 0; i <= availableTwoIndex; ) {
      if (arr[i] == 0) {
        swap(arr, i, availableZeroindex);
        availableZeroindex++;
        i++;
      } else if (arr[i] == 1) {
        i++;
      } else if (arr[i] == 2) {
        swap(arr, i, availableTwoIndex); // at this point, the value at index i could be 0, 1 or 2. So, we won't increment i
        availableTwoIndex--;
      }
    }
    return arr;
  }

  private static void swap(int[] arr, int i, int j) {
    int temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
  }
}
