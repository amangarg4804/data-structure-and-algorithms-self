package algorithms.patterns.topkelements;

//Given a string and a number ‘K’, find if the string can be rearranged such that the same characters are at least ‘K’ distance apart from each other.

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Queue;

public class RearrangeStringKDistanceApart {

  public static void main(String[] args) {
    System.out.println(rearrange("mmpp", 2));
    System.out.println(rearrange("Programming", 2));
    System.out.println(rearrange("aab", 2));
    System.out.println(rearrange("aappa", 3));
  }

  private static String rearrange(String input, int k) {
    Map<Character, Integer> map = new HashMap<>();
    for (int i = 0; i < input.length(); i++) {
      map.put(input.charAt(i), map.getOrDefault(input.charAt(i), 0) + 1);
    }
    PriorityQueue<Entry<Character, Integer>> maxHeap = new PriorityQueue<>(
        (n1, n2) -> n2.getValue() - n1
            .getValue());
    maxHeap.addAll(map.entrySet());
    Queue<Entry<Character, Integer>> queue = new LinkedList<>();
    StringBuilder sb = new StringBuilder();
    while (!maxHeap.isEmpty()) {
      Entry<Character, Integer> currentEntry = maxHeap.poll();
      if(currentEntry.getValue() > 0) {
        sb.append(currentEntry.getKey());
        currentEntry.setValue(currentEntry.getValue() - 1);
        queue.offer(currentEntry);
      }
      if (queue.size() == k) {
        maxHeap.offer(queue.poll());
      }
    }
    return sb.toString().length() == input.length() ? sb.toString() : "";
    //time O(N*LogN) , N = no of characters in input string
  }

}
