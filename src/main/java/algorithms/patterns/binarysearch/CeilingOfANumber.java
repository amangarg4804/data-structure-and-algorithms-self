package algorithms.patterns.binarysearch;

//the ceiling of the ‘key’ will be the smallest element in the given array greater than or equal to the ‘key'
// Write a function to return the index of the ceiling of the ‘key’. If there isn’t any ceiling return -1.
public class CeilingOfANumber {

  public static void main(String[] args) {
    System.out.println(ceiling(new int[]{4, 6, 10}, 6));
    System.out.println(ceiling(new int[]{1, 3, 8, 10, 15}, 12));
    System.out.println(ceiling(new int[]{4, 6, 10}, 17));
    System.out.println(ceiling(new int[]{4, 6, 10}, -1));
  }

  private static int ceiling(int[] arr, int k) {
    if(k > arr[arr.length-1]) {
      return -1;
    }
    int start = 0;
    int end = arr.length -1;
    int ceiling =-1;
    while (start <=end) {
      int mid = start + (end-start)/2;
      if(arr[mid] >=k) {
        ceiling = mid;
        end = mid -1;
      } else {
        start = mid +1;
      }
    }
    return ceiling;
  }
}
