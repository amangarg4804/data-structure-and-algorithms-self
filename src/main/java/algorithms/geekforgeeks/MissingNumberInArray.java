package algorithms.geekforgeeks;

public class MissingNumberInArray {

  int MissingNumber(int[] array, int n) {
// Your Code Here
    int sum = 0;
    int arraySum = 0;

    for (int i = 1; i <= n; i++) {
      sum += i;

    }
    // System.out.println(sum);
    for (int i = 0; i < array.length; i++) {
      arraySum += array[i];
    }
    // System.out.println(arraySum);
    int result = sum - arraySum;
    return result;
  }

}
