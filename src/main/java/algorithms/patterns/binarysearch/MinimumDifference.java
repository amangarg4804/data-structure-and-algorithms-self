package algorithms.patterns.binarysearch;

public class MinimumDifference {

  public static void main(String[] args) {
    System.out.println(minDiffElement(new int[]{4, 6, 10}, 7));
    System.out.println(minDiffElement(new int[]{4, 6, 10}, 4));
    System.out.println(minDiffElement(new int[]{1, 3, 8, 10, 15}, 12));
    System.out.println(minDiffElement(new int[]{4, 6, 10}, 17));
    System.out.println(minDiffElementCleaner(new int[]{4, 6, 10}, 7));
    System.out.println(minDiffElementCleaner(new int[]{4, 6, 10}, 4));
    System.out.println(minDiffElementCleaner(new int[]{1, 3, 8, 10, 15}, 12));
    System.out.println(minDiffElementCleaner(new int[]{4, 6, 10}, 17));
  }

  private static int minDiffElement(int[] arr, int k) {
    // min difference will always be towards right or equal to mid if mid is less than k, because all elements towards left of mid are less than mid
    int start = 0;
    int end = arr.length -1;
    int minDiff = Integer.MAX_VALUE;
    int resultIndex =0;
    while (start<= end) {
      int mid = start + (end -start)/2;
      if(arr[mid] ==k ) {
        return arr[mid];
      } else if(arr[mid] < k) {
          int diff = k -arr[mid];
          if(diff < minDiff) {
            minDiff = diff;
            resultIndex = mid;
          }
          start = mid +1;
      } else {
        int diff = arr[mid] -k;
        if(diff < minDiff) {
          minDiff = diff;
          resultIndex = mid;
        }
        end = mid-1;
      }
    }
    return arr[resultIndex];
  }

  private static int minDiffElementCleaner(int[] arr, int k) {
    if(k < arr[0]) {
      return arr[0];
    }
    if (k > arr[arr.length -1]) {
      return arr[arr.length-1];
    }
    int start = 0;
    int end = arr.length -1;
    while (start<= end) {
      int mid = start + (end -start)/2;
      if(arr[mid] ==k ) {
        return arr[mid];
      } else if(arr[mid] < k) {
        start = mid +1;
      } else {
        end = mid-1;
      }
    }
    // either start or end will have min diff
    // at the end of while loop start = end +1;
    if(arr[start] - k < k -arr[end]) {
      return arr[start];
    }
    return arr[end];
  }
}
