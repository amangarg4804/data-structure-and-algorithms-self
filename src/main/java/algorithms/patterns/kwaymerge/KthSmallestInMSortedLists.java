package algorithms.patterns.kwaymerge;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.PriorityQueue;

//Given ‘M’ sorted arrays, find the K’th smallest number among all the arrays.
public class KthSmallestInMSortedLists {

  public static void main(String[] args) {
    System.out.println(kthSmallest(Arrays.asList(new Integer[]{2, 6, 8}, new Integer[]{3, 6, 7}, new Integer[]{1, 3, 4}), 5));
    System.out.println(median(Arrays.asList(new Integer[]{2, 6, 8}, new Integer[]{3, 6, 7}, new Integer[]{1, 3, 4})));

    // 1, 2, 3, 3, 4, 6,6, 7,8

  }

  private static int median(List<Integer[]> list) {
    Integer totalElements = list.stream().map(arr -> arr.length)
        .reduce(Integer::sum).get();
    return kthSmallest(list, totalElements/2 +1); //only for odd
    // If an array is sorted, median is the middle element of an array in case of odd number of elements in an array and
    // when number of elements in an array is even than it will be an average of two middle elements.
    //If the array is not sorted first task is to sort the array
  }

  private static int kthSmallest(List<Integer[]> list, int k) {
    PriorityQueue<Element> minHeap = new PriorityQueue<>((e1, e2)-> list.get(e1.listIndex)[e1.elementIndex] -list.get(e2.listIndex)[e2.elementIndex] );
    for(int i = 0; i< list.size(); i++) {
      if(list.get(i)!=null) {
        minHeap.offer(new Element(i, 0));
      }
    }
    int result = 0;
    while (!minHeap.isEmpty()) {
      Element min = minHeap.poll();
      result = list.get(min.listIndex)[min.elementIndex];
      if(--k==0) {
        break;
      }
      min.elementIndex++;
      if(min.elementIndex < list.get(min.listIndex).length) {
        minHeap.offer(min);
      }
    }
    return result;
  }
}

