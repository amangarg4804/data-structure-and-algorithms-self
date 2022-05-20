package algorithms.patterns.topkelements;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

//Given a sorted number array and two integers ‘K’ and ‘X’, find ‘K’ closest numbers to ‘X’ in the array. Return the numbers in the sorted order.
// ‘X’ is not necessarily present in the array.
public class KClosestNumbers {

  public static void main(String[] args) {
    System.out.println(closestNumbers(new int[]{5, 6, 7, 8, 9}, 3, 7));
    System.out.println(closestNumbers(new int[]{2, 4, 5, 6, 9}, 3, 6));
    System.out.println(closestNumbers(new int[]{5, 6, 7, 8, 9}, 3, 10));
  }

  private static List<Integer> closestNumbers(int[] arr, int k, int x ) {
    int closestIndex = binarySearch(arr, x);
    int left = Math.max(closestIndex -k , 0);
    int right = Math.min(closestIndex + k, arr.length-1);

    PriorityQueue<HeapEntry> minHeap = new PriorityQueue<>(Comparator.comparingInt(n -> n.diff));
    for(int i= left ; i<=right; i++) {
      minHeap.offer(new HeapEntry(Math.abs(arr[i]-x), i));
    }

    List<Integer> result = new ArrayList<>();
    for(int i=0; i< k; i++) {
      result.add(arr[minHeap.poll().index]);
    }
    Collections.sort(result);
    return result;
  }

  private static int binarySearch(int[] arr, int x) {
    if(x < arr[0]) {
      return 0;
    }
    if(x > arr[arr.length -1]) {
      return arr.length-1;
    }
    int start = 0;
    int end = arr.length-1;
    while (start <= end) {
      int mid = start + (end - start) /2;
      if(arr[mid] == x) {
        return mid;
      }
      if(arr[mid] < x) {
        start = mid +1;
      } else {
        end = mid -1;
      }
    }
    if(arr[start]- x > x-arr[end]) {
      return end;
    }
    return start;
  }
}

class HeapEntry {
  int diff;
  int index ;

  public HeapEntry(int diff, int index) {
    this.diff = diff;
    this.index = index;
  }

  public int getDiff() {
    return diff;
  }

  public void setDiff(int diff) {
    this.diff = diff;
  }

  public int getIndex() {
    return index;
  }

  public void setIndex(int index) {
    this.index = index;
  }
}
