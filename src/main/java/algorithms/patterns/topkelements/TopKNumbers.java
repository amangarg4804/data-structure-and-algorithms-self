package algorithms.patterns.topkelements;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class TopKNumbers {

  public static void main(String[] args) {
    System.out.println(topk(new int[]{3, 1, 5, 12, 2, 11}, 3));
    System.out.println(topk(new int[]{5, 12, 11, -1, 12}, 3));
  }

  private static List<Integer> topk(int[] arr, int k) {
    PriorityQueue<Integer> pq = new PriorityQueue<>();

    for (int i = 0; i< k; i++) {
      pq.offer(arr[i]);
    }

    for(int i = k; i< arr.length; i++) {
      if(pq.peek() < arr[i]) {
        pq.poll();
        pq.offer(arr[i]);
      }
    }
    return new ArrayList<>(pq);
  }

}
