package algorithms.geekforgeeks.course.array;

public class RotateLeft {

  static void rotateArrExtraSpace(int[] arr, int d, int n) {
    // n is array length, d is number of steps
    int[] result = new int[arr.length];
    if (d > n) {
      d = d % n;
    }
    for (int i = 0; i < n; i++) {
      int index = i - d;
      if (index < 0) {
        index = index + n;
      }
      result[index] = arr[i];
    }
    for (int i = 0; i < n; i++) {
      arr[i] = result[i];
    }
  }

  static void rotateArrWithDExtraSpace(int arr[], int d, int n)
  {
    // add your code here
    int[] temp = new int[d];
    if (d > n) {
      d = d % n;
    }
    for (int i = 0; i < d; i++) {
      temp[i] = arr[i];
    }
    for (int i = 0; i < n-d; i++) {
      arr[i] = arr[i+d];
    }
    int tempIndex =0;
    for(int i=n-d; i< n; i++) {
      arr[i] = temp[tempIndex];
      tempIndex++;
    }
  }


  static void rotateArrInPlace(int arr[], int d, int n)
  {
    // add your code here
    int[] temp = new int[d];
    if (d > n) {
      d = d % n;
    }
    for (int i = 0; i < d; i++) {
      temp[i] = arr[i];
    }
    for (int i = 0; i < n-d; i++) {
      arr[i] = arr[i+d];
    }
    int tempIndex =0;
    for(int i=n-d; i< n; i++) {
      arr[i] = temp[tempIndex];
      tempIndex++;
    }
  }

  //Function to rotate an array by d elements in counter-clockwise direction.
  static void rotateArr(int arr[], int d, int n)
  {
    d=d%n;
    reverse(arr, 0, n-1);
    reverse(arr, 0, n-d-1);
    reverse(arr, n-d, n-1);


  }

  private static void reverse(int[] arr , int left, int right) {
    while (left< right) {
      int temp = arr[right];
      arr[right]=arr[left];
      arr[left] = temp;
      left++;
      right--;
    }
  }



}
