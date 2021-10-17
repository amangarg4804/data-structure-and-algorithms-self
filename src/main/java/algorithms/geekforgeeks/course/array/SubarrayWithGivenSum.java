package algorithms.geekforgeeks.course.array;

import java.util.ArrayList;

public class SubarrayWithGivenSum {
  static ArrayList<Integer> subarraySum(int[] arr, int n, int s)
  {
    // Your code here // 8, 5, 3
    // 5
    ArrayList<Integer> result = new ArrayList<>();
    int start =0;
    int currentSum = arr[0]; // currentSum = 8, start 0, end 0
    int end;
    for(end = 1; end <=n; end++) {
      while(currentSum > s && start < end-1) {
        currentSum = currentSum - arr[start];
        start++;
      }
      if(currentSum == s) {
        end=end-1;
        break;
      }
      if(end < n) {
        currentSum = currentSum + arr[end]; //
      }
    }
    if(currentSum == s) {
      result.add(start+1);
      result.add(end+1);
      return result;
    }
    result.add(-1);
    return result;
  }
}
