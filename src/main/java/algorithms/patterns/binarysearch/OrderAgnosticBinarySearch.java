package algorithms.patterns.binarysearch;

public class OrderAgnosticBinarySearch {

  public static void main(String[] args) {
    System.out.println(search(new int[] {4, 6, 10}, 10));
    System.out.println(search(new int[] {1, 2, 3, 4, 5, 6, 7}, 5));
    System.out.println(search(new int[] {10, 6, 4}, 10));
    System.out.println(search(new int[] {10, 6, 4}, 4));

    System.out.println(search2(new int[] {4, 6, 10}, 10));
    System.out.println(search2(new int[] {1, 2, 3, 4, 5, 6, 7}, 5));
    System.out.println(search2(new int[] {10, 6, 4}, 10));
    System.out.println(search2(new int[] {10, 6, 4}, 4));
  }

  public static int search(int[] arr, int k ) {
    if(arr == null || arr.length==0 ) {
      return -1;
    }

    int start = 0;
    int end = arr.length -1;
    boolean isAscending = arr[end] > arr[start];
    while(start <= end) {
      int mid = start + (end-start)/2;
      if(k > arr[mid]) {
        if(isAscending) {
          start = mid+1;
        } else {
          end = mid-1;
        }
      } else if(k < arr[mid]) {
        if(isAscending) {
          end = mid-1;
        } else {
          start = mid+1;
        }
      } else {
        return mid;
      }
    }
    return -1;
  }

  public static int search2(int[] arr, int k ) {
    if(arr == null || arr.length==0 ) {
      return -1;
    }

    int start = 0;
    int end = arr.length -1;
    boolean isAscending = arr[end] > arr[start];
    while(start <= end) {
      int mid = start + (end-start)/2;

      if(arr[mid] == k) {
        return mid;
      }
      if(isAscending) {
        if(arr[mid] < k) {
          start = mid+1;
        } else {
          end = mid-1;
        }
      } else {
        if(arr[mid] < k) {
          end = mid -1;
        } else {
          start = mid +1;
        }
      }
    }
    return -1;
  }
}
