package algorithms.patterns.binarysearch;

public class RotationCount {

  public static void main(String[] args) {
    System.out.println(count(new int[]{10, 15, 1, 3, 8}));
    System.out.println(count(new int[]{4, 5, 7, 9, 10, -1, 2}));
    System.out.println(count(new int[]{1, 3, 8, 10}));
  }

  private static int count (int [] arr) {
    // 10, 15, 1, 3, 8
    int start = 0;
    int end = arr.length -1;
    while (start < end) {
      int mid = start + (end- start)/2;

      if(arr[start] < arr[mid]) {
        start = mid + 1;
      } else {
        end = mid -1;
      }
    }
    return 0;
  }
}
