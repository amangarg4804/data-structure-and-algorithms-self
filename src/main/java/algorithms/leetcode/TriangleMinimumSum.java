package algorithms.leetcode;

import java.util.List;

public class TriangleMinimumSum {

  // Dynamic programming, Bottom-up

  public int minimumTotal(List<List<Integer>> triangle) {
    if (triangle.size() == 0) {
      return 0;
    }
    int[] dp = new int[triangle.size() + 1];
    for (int i = triangle.size() - 1; i >= 0; i--) {
      for (int j = 0; j <= i; j++) {
        dp[j] = Math.min(dp[j], dp[j + 1]) + triangle.get(i).get(j);
      }
    }
    return dp[0];
  }

  //Bottom-up

  public int minimumTotalInPlace(List<List<Integer>> triangle) {
    for (int row = triangle.size() - 2; row >= 0; row--) {
      for (int col = 0; col <= row; col++) {
        int minBelow = Math.min(triangle.get(row + 1).get(col), triangle.get(row + 1).get(col + 1));
        triangle.get(row).set(col, minBelow + triangle.get(row).get(col));
      }
    }
    return triangle.get(0).get(0);
  }


}
