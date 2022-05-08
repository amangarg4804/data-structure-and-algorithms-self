package algorithms.patterns.binarysearch;

//  The floor of the ‘key’ will be the biggest element in the given array smaller than or equal to the ‘key’

public class FloorOfNumber {

  public static void main(String[] args) {
    System.out.println(floor(new int[]{4, 6, 10}, 6));
    System.out.println(floor(new int[]{1, 3, 8, 10, 15}, 12));
    System.out.println(floor(new int[]{4, 6, 10}, 17));
    System.out.println(floor(new int[]{4, 6, 10}, -1));
  }

  private static int floor(int[] arr, int k) {
    if (k < arr[0]) {
      return -1;
    }
    int start = 0;
    int end = arr.length - 1;
    int floor = -1;
    while (start <= end) {
      int mid = start + (end - start) / 2;
      if(arr[mid] ==k) {
        return mid;
      } else if(arr[mid] < k) {
        floor = mid;
        start = mid + 1;
      } else {
        end = mid-1;
      }
    }
    return floor;
  }
}
