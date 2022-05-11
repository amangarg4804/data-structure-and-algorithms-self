package algorithms.patterns.binarysearch;

public class SearchBitonicArray {

  public static void main(String[] args) {
    System.out.println(search(new int[]{1, 3, 8, 4, 3}, 4));
    System.out.println(search(new int[]{3, 8, 3, 1}, 8));
    System.out.println(search(new int[]{1, 3, 8, 12}, 12));
    System.out.println(search(new int[]{10, 9, 8}, 10));
  }

  public static int search(int[] arr, int key) {
    int max = findIndexofMax(arr);
    int index = search(arr, key, 0, max);
    if(index == -1) {
      index = search(arr, key, max, arr.length -1);
    }
    return index;
  }

  private static int search(int[] arr, int key, int start, int end) {
    while (start <=end) {
      int mid = start + (end - start)/2;
      if(arr[mid] == key) {
        return mid;
      }
      if(arr[mid] > key) {
        end = mid -1;
      } else {
        start = mid +1;
      }
    }
    return -1;
  }

  private static int findIndexofMax(int[] arr) {
    int start = 0;
    int end = arr.length -1;
    while (start < end) {
      int mid = start + (end-start)/2;
      if(arr[mid] > arr[mid+1]) {
        end = mid;
      } else {
        start = mid +1;
      }
    }
    return start;
  }


}
