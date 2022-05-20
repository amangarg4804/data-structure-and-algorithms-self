package algorithms.patterns.topkelements;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;

// Given an array of numbers and a number ‘K’, we need to remove ‘K’ numbers from the array such that we are left with maximum distinct numbers
public class MaximumDistinctElements {

  public static void main(String[] args) {
    System.out.println(maxDistinct(new int[]{7, 3, 5, 8, 5, 3, 3}, 2));
    System.out.println(maxDistinct(new int[]{3, 5, 12, 11, 12}, 3));
    System.out.println(maxDistinct(new int[]{1, 2, 3, 3, 3, 3, 4, 4, 5, 5, 5}, 2));
  }

  private static int maxDistinct(int[] arr, int k) {
    Map<Integer, Integer> map = new HashMap<>();
    for (int i = 0; i < arr.length; i++) {
      map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);
    }

    PriorityQueue<Entry<Integer, Integer>> pq = new PriorityQueue<>(
        (n1, n2) -> n1.getValue() - n2.getValue());
    int distinct = 0;
    for (Entry<Integer, Integer> entry : map.entrySet()) {
      if (entry.getValue() == 1) {
        distinct++;
      } else {
        pq.offer(entry);
      }
    }
    while (k > 0 && !pq.isEmpty()) {
      Entry<Integer, Integer> entry = pq.poll();
      k = k - (entry.getValue() - 1);
      if (k >= 0) {
        distinct++;
      }
    }
    if (k > 0) {
      distinct -= k;
    }
    return distinct;
  }
}
