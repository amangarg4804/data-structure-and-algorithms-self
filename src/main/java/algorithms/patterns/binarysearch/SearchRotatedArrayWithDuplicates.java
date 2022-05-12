package algorithms.patterns.binarysearch;

public class SearchRotatedArrayWithDuplicates {

  public static void main(String[] args) {
    System.out.println(search(new int[]{3, 7, 3, 3, 3}, 7));
  }

  private static int search(int[] arr , int key) {
    int start = 0;
    int end = arr.length -1;
    while (start <=end) {
      int mid = start + (end - start) /2;
      if(key == arr[mid]) {
        return mid;
      }
      if(arr[mid] == arr[start] && arr[mid] ==arr[end]) {
        start++;
        end--;
      }
      if(arr[mid] >= arr[start] ) {
        // left side is sorted
        if(key >= start && key < arr[mid]) {
          end = mid-1;
        } else {
          start = mid +1 ;
        }
      } else {
        // right side is sorted
        if(key > arr[mid]  && key <= arr[arr.length -1]) {
          start = mid + 1;
        } else {
          end = mid -1;
        }
      }
    }
    return -1;
  }
}
