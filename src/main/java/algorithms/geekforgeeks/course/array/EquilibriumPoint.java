package algorithms.geekforgeeks.course.array;

public class EquilibriumPoint {

  // a: input array
  // n: size of array
  // Function to find equilibrium point in the array.
  public static int equilibriumPoint(long[] arr, int n) {

    // Your code here
    if (n == 1) {
      return 1;
    }
    if (n < 3) {
      return -1;
    }
    int left = 0;
    int right = arr.length - 1;
    long leftSum = arr[left];
    long rightSum = arr[right];
    while (left <= right) {
      if (leftSum > rightSum) {
        rightSum += arr[--right];
      } else if (leftSum < rightSum) {
        leftSum += arr[++left];
      } else {
        if (right - left == 2) {
          return left + 2;
        }
        leftSum += arr[++left];
        rightSum += arr[--right];
      }
    }
    return -1;

  }
}
