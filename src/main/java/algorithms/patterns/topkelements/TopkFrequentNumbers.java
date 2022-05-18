package algorithms.patterns.topkelements;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;

public class TopkFrequentNumbers {

  public static void main(String[] args) {
    System.out.println(topKFrequent(new int[]{1, 3, 5, 12, 11, 12, 11}, 2));
    System.out.println(topKFrequent(new int[]{5, 12, 11, 3, 11}, 2));
  }

  private static List<Integer> topKFrequent(int[] arr, int k) {
    Map<Integer, Integer> map = new HashMap<>();
    for(int i=0; i< arr.length; i++) {
      map.put(arr[i], map.getOrDefault(arr[i], 0)+1);
    }
    PriorityQueue<Entry<Integer, Integer>> pq = new PriorityQueue<>(
        Comparator.comparingInt(Entry::getValue));
    for(Entry<Integer, Integer> entry : map.entrySet()) {
      pq.offer(entry);
      if(pq.size() >k) {
        pq.poll();
      }
    }
    List<Integer> result = new ArrayList<>();
    while (!pq.isEmpty()) {
      result.add(pq.poll().getKey());
    }
    return result;
    // time O(NLogK)
  }
}
