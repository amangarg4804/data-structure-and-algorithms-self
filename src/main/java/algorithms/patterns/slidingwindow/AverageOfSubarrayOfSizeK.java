package algorithms.patterns.slidingwindow;

import java.util.Arrays;

public class AverageOfSubarrayOfSizeK {

  public static void main(String[] args) {
    int[] arr = {1, 3, 2, 6, -1, 4, 1, 8, 2};
    System.out.println(Arrays.toString(findAverage(5, arr)));
  }

  public static double[] findAverage(int k, int[] arr) {
    double[] result = new double[arr.length - k + 1];
    int windowStart = 0;
    double sum = 0;
    for (int windowEnd = 0; windowEnd < arr.length; windowEnd++) {
      sum += arr[windowEnd];

      if (windowEnd >= k - 1) {
        result[windowStart] = sum / k;
        sum -= arr[windowStart];
        windowStart++;
      }
    }
    return result;
  }


}
