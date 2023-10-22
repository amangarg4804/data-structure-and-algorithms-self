package algorithms.interviews.oracleinterview;

public class LargestSumSubarray {
  //Write an efficient program to find the sum of contiguous subarray within a one-dimensional array of numbers that has the largest sum.
  // {-2 -3 4 -1 -2 1 5 -3}Maximum Contiguous Array Sum {4, -1, -2, 1, -2, 1, 5 } => 7


  public static void main(String[] args) {
    System.out.println(sum(new int[]{-2, -3, 4, -1, -2, 1, 5, -3}));
  }

  private static int sum(int[] arr) {
    int currentSum =0;
    int maxSum = Integer.MIN_VALUE;
    for(int i=0; i< arr.length ; i++) {
      currentSum = currentSum + arr[i];
      if(maxSum < currentSum){
        maxSum = currentSum;
      }
      if(currentSum < 0) {
        currentSum = 0;
      }
    }
    return maxSum;
  }
}
