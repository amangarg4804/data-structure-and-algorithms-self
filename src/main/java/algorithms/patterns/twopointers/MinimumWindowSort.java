package algorithms.patterns.twopointers;

// Given an array, find the length of the smallest subarray in it which when sorted will sort the whole array.
public class MinimumWindowSort {

  public static void main(String[] args) {
    System.out.println(sort(new int[]{1, 2, 5, 3, 7, 10, 9, 12}));
    System.out.println(sort(new int[]{1, 3, 2, 0, -1, 7, 10}));
    System.out.println(sort(new int[]{1, 2, 3}));
    System.out.println(sort(new int[]{3, 2, 1}));
  }

  public static int sort(int[] arr) {
    int low = 0;
    int high = 0;
    while (low < arr.length - 1 && arr[low] <= arr[low + 1]) {
      low++;
    }
    if (low == arr.length - 1) {
      return 0;
    }
    for (int i = arr.length - 2; i >= 0; i--) {
      if (arr[i] < arr[i + 1]) {
        high = i - 1;
        break;
      }
    }
    int lowest = Integer.MAX_VALUE;
    int highest = Integer.MIN_VALUE;
    for (int i = low; i <= high; i++) {
      lowest = Math.min(lowest, arr[i]);
      highest = Math.max(highest, arr[i]);
    }
    while (low > 0 && arr[low - 1] > lowest) {
      low--;
    }
    while (high < arr.length - 1 && arr[high + 1] < highest) {
      high++;
    }
    return high - low + 1;
  }
}
