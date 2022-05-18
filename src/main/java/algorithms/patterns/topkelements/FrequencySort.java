package algorithms.patterns.topkelements;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;

//Given a string, sort it based on the decreasing frequency of its characters.
public class FrequencySort {

  public static void main(String[] args) {
    System.out.println(frequencySort("Programming"));
    System.out.println(frequencySort("abcbab"));
  }

  private static String frequencySort(String input) {
    Map<Character, Integer> map = new HashMap<>();
    for(int i=0; i< input.length(); i++) {
      map.put(input.charAt(i), map.getOrDefault(input.charAt(i), 0) + 1);
    }
    PriorityQueue<Entry<Character, Integer>> pq = new PriorityQueue<>((e1, e2) -> e2.getValue() - e1.getValue());
    for(Entry<Character, Integer> entry : map.entrySet()) {
      pq.offer(entry);
    } // pq.addAll(map.entrySet())
    StringBuilder sb = new StringBuilder();
    while (!pq.isEmpty()) {
      Entry<Character, Integer> entry = pq.poll();
      Character character = entry.getKey();
      int count = entry.getValue();
      while (count!=0) {
        sb.append(character);
        count--;
      }
    }
    return sb.toString();
  }
}
