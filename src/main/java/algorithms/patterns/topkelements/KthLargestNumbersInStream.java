package algorithms.patterns.topkelements;

import java.util.PriorityQueue;


// Design a class to efficiently find the Kth largest element in a stream of numbers.
public class KthLargestNumbersInStream {
  PriorityQueue<Integer> minHeap = new PriorityQueue<>();
  int k;

  KthLargestNumbersInStream(int[] nums, int k) {
    this.k = k;
    for(int i : nums) {
      add(i);
    }

  }

  public static void main(String[] args) {
    KthLargestNumbersInStream kthLargestNumbersInStream = new KthLargestNumbersInStream(new int[]{3, 1, 5, 12, 2, 11},4 );
    System.out.println(kthLargestNumbersInStream.add(6));
    System.out.println(kthLargestNumbersInStream.add(13));
    System.out.println(kthLargestNumbersInStream.add(4));
  }

  private int add(int i) {
    minHeap.add(i);
    if(minHeap.size() >k) {
      minHeap.poll();
    }
    return minHeap.peek();
    // time O(logK)
  }


}
