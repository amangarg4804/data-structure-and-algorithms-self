package algorithms.patterns.slidingwindow;

//Given an array of positive numbers and a positive number ‘S’,
//    find the length of the smallest contiguous subarray whose sum is greater than or equal to ‘S’.
//    Return 0, if no such subarray exists.
public class SmallestSubArrayWithGivenSum {

  public static void main(String[] args) {
    int[] arr1 = {2, 1, 5, 2, 3, 2};
    int[] arr2 = {2, 1, 5, 2, 8};
    int[] arr3 = {3, 4, 1, 1, 6};
    System.out.println(smallestSubArray(7, arr1));
    System.out.println(smallestSubArray(7, arr2));
    System.out.println(smallestSubArray(8, arr3));
  }

  public static int smallestSubArray(int requiredSum, int[] arr) {
    int resultMinLength = Integer.MAX_VALUE;
    int windowStart = 0;
    int sum = 0;
    for (int windowEnd = 0; windowEnd < arr.length; windowEnd++) {
      sum += arr[windowEnd];
      while (sum >= requiredSum) {
        resultMinLength = Math.min(resultMinLength, windowEnd - windowStart + 1);
        sum -= arr[windowStart];
        windowStart++;
      }
    }

    return resultMinLength == Integer.MAX_VALUE ? 0 : resultMinLength;
  }
}
