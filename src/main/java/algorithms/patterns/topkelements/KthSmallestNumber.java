package algorithms.patterns.topkelements;

import java.util.PriorityQueue;

public class KthSmallestNumber {

  public static void main(String[] args) {
    System.out.println(kthSmallest(new int[]{1, 5, 12, 2, 11, 5}, 3));
    System.out.println(kthSmallest(new int[]{1, 5, 12, 2, 11, 5}, 4));
    System.out.println(kthSmallest(new int[]{5, 12, 11, -1, 12}, 3));
  }

  private static int kthSmallest(int [] arr, int k) {
    PriorityQueue<Integer> pq = new PriorityQueue<>((n1, n2) -> n2-n1);

    for(int i = 0; i< k; i++) {
      pq.offer(arr[i]);
    }
    for(int i=k; i< arr.length; i++) {
      if(pq.peek() > arr[i]) {
        pq.poll();
        pq.offer(arr[i]);
      }
    }
    return pq.peek();
  }
}
