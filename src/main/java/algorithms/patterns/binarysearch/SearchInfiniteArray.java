package algorithms.patterns.binarysearch;

import java.util.Arrays;

public class SearchInfiniteArray {


  public static void main(String[] args) {
    ArrayReader arrayReader = new ArrayReader(
        new int[]{4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30});
    System.out.println(search(arrayReader, 16));
    System.out.println(search(arrayReader, 11));
    arrayReader = new ArrayReader(new int[]{1, 3, 8, 10, 15});
    System.out.println(search(arrayReader, 15));
    System.out.println(search(arrayReader, 200));
  }

  private static int search(ArrayReader arrayReader, int key) {
    int start = 0;
    int end = 1;
    while (key > arrayReader.valueAt(end)) {
      int newStart = end + 1;
      end += (end - start + 1) * 2;
      start = newStart;
    }
    return search(arrayReader, key, start, end);
  }

  private static int search(ArrayReader arrayReader, int key, int start, int end) {
    while (start <= end) {
      int mid = start + (end - start) / 2;
      if (arrayReader.valueAt(mid) == key) {
        return mid;
      }
      if (arrayReader.valueAt(mid) > key) {
        end = mid - 1;
      } else {
        start = mid + 1;
      }
    }
    return -1;
  }

}

class ArrayReader {

  int[] arr;

  public ArrayReader(int[] arr) {
    this.arr = arr;
  }

  public int valueAt(int index) {
    if (index >= arr.length) {
      return Integer.MAX_VALUE;
    }
    return arr[index];
  }
}