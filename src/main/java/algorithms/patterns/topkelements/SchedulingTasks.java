package algorithms.patterns.topkelements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Queue;

//You are given a list of tasks that need to be run, in any order, on a server.
// Each task will take one CPU interval to execute but once a task has finished,
// it has a cooling period during which it can’t be run again.
// If the cooling period for all tasks is ‘K’ intervals, find the minimum number of CPU intervals that the server needs to finish all tasks.
//If at any time the server can’t execute any task then it must stay idle.

// the difference between this problem  and k distance is that here, for k =2, we need to have abca(a executes after two other ), instead of aba
public class SchedulingTasks {

  public static void main(String[] args) {
    System.out.println(minCpuIntervals(new char[]{'a', 'a', 'a', 'b', 'c', 'c'}, 2));
    System.out.println(minCpuIntervals(new char[]{'a', 'b', 'a'}, 3));
    System.out.println(minCpuIntervals2(new char[]{'a', 'a', 'a', 'b', 'c', 'c'}, 2));
    System.out.println(minCpuIntervals2(new char[]{'a', 'b', 'a'}, 3));
  }

  private static int minCpuIntervals(char[] input, int k) {
    Map<Character, Integer> map = new HashMap<>();
    for (Character c : input) {
      map.put(c, map.getOrDefault(c, 0) + 1);
    }
    PriorityQueue<Entry<Character, Integer>> maxHeap = new PriorityQueue<>(
        (n1, n2) -> n2.getValue() - n1
            .getValue());
    maxHeap.addAll(map.entrySet());

    int cpuIntervals = 0;
    while (!maxHeap.isEmpty()) {
      List<Entry<Character, Integer>> blockedJobs = new ArrayList<>();
      int n = k + 1;
      for (; n > 0; n--) {
        if (maxHeap.isEmpty()) {
          break;
        }
        Entry<Character, Integer> currentEntry = maxHeap.poll();
        cpuIntervals++;
        currentEntry.setValue(currentEntry.getValue() - 1);
        if (currentEntry.getValue() > 0) {
          blockedJobs.add(currentEntry);
        }
      }
      if (!blockedJobs.isEmpty()) {
        if (n != 0) {
          cpuIntervals += n;
        }
        maxHeap.addAll(blockedJobs);
      }
    }
    return cpuIntervals;
  }

  private static int minCpuIntervals2(char[] input, int k) {
    Map<Character, Integer> map = new HashMap<>();
    for (Character c : input) {
      map.put(c, map.getOrDefault(c, 0) + 1);
    }
    PriorityQueue<Entry<Character, Integer>> maxHeap = new PriorityQueue<>(
        (n1, n2) -> n2.getValue() - n1
            .getValue());
    maxHeap.addAll(map.entrySet());
    Queue<Entry<Character, Integer>> queue = new LinkedList<>();
    int cpuIntervals = 0;
    while (!maxHeap.isEmpty()) {
      Entry<Character, Integer> currentEntry = maxHeap.poll();
      if (currentEntry.getValue() > 0) {
        cpuIntervals++;
        currentEntry.setValue(currentEntry.getValue() - 1);
        queue.offer(currentEntry);
      }
      if (queue.size() > k) {
        maxHeap.offer(queue.poll());
      }
      if (maxHeap.isEmpty() && !queue.isEmpty()) {
        Entry<Character, Integer> entry = queue.poll();
        if (entry.getValue() > 0) {
          cpuIntervals += k - queue.size();
          maxHeap.offer(entry);
        }
      }
    }
    return cpuIntervals;
  }
}
