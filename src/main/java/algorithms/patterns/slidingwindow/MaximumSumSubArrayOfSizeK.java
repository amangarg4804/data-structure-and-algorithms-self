package algorithms.patterns.slidingwindow;

public class MaximumSumSubArrayOfSizeK {

  public static void main(String[] args) {
    int[] arr1 = {2, 1, 5, 1, 3, 2};
    int[] arr2 = {2, 3, 4, 1, 5};
    System.out.println(maxSum(3, arr1));
    System.out.println(maxSum(2, arr2));
  }

  public static long maxSum(int k, int[] arr) {
    long currentSum = 0;
    long resultSum = 0;
    int windowStart = 0;
    for (int windowEnd = 0; windowEnd < arr.length; windowEnd++) {
      currentSum += arr[windowEnd];
      if (windowEnd >= k - 1) {
        resultSum = Math.max(resultSum, currentSum);
        currentSum -= arr[windowStart];
        windowStart++;
      }
    }
    return resultSum;
  }
}
