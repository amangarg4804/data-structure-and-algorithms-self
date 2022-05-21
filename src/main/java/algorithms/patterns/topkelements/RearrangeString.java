package algorithms.patterns.topkelements;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;

// Given a string, find if its letters can be rearranged in such a way that no two same characters come next to each other.
public class RearrangeString {

  public static void main(String[] args) {
    System.out.println(rearrange("aappp"));
    System.out.println(rearrange("Programming"));
    System.out.println(rearrange("aapa"));
  }

  private static String rearrange(String input) {
    Map<Character, Integer> map  = new HashMap<>();
    for(int i= 0; i< input.length() ; i++) {
      map.put(input.charAt(i), map.getOrDefault(input.charAt(i), 0) + 1);
    }
    PriorityQueue<Entry<Character, Integer>> maxHeap = new PriorityQueue<>((e1, e2) -> e2.getValue() - e1.getValue());
    maxHeap.addAll(map.entrySet());
    StringBuilder sb  = new StringBuilder();
    Entry<Character, Integer> previousEntry = null;
    while (!maxHeap.isEmpty()) {
      Entry<Character, Integer> currentEntry = maxHeap.poll();
      sb.append(currentEntry.getKey());
      if(previousEntry != null && previousEntry.getValue() > 0) {
        maxHeap.offer(previousEntry);
      }
      currentEntry.setValue(currentEntry.getValue() -1);
      previousEntry = currentEntry;
    }
    return sb.toString().length() == input.length() ? sb.toString() : "";
  }


}

