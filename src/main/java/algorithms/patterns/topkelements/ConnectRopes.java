package algorithms.patterns.topkelements;

import java.util.PriorityQueue;

public class ConnectRopes {

  public static void main(String[] args) {
    System.out.println(minCost(new int[]{1, 3, 11, 5}));
    System.out.println(minCost(new int[]{3, 4, 5, 6}));
    System.out.println(minCost(new int[]{1, 3, 11, 5, 2}));
    System.out.println(minCostCleaner(new int[]{1, 3, 11, 5}));
    System.out.println(minCostCleaner(new int[]{3, 4, 5, 6}));
    System.out.println(minCostCleaner(new int[]{1, 3, 11, 5, 2}));
  }

  private static int minCost(int[] arr) {
    if (arr.length < 2) {
      return 0;
    }
    PriorityQueue<Integer> pq = new PriorityQueue<>();
    for (int i = 0; i < arr.length; i++) {
      pq.offer(arr[i]);
    }

    int totalCost = 0;
    while (!pq.isEmpty()) {
      int currentCost = pq.poll();
      if (!pq.isEmpty()) {
        currentCost += pq.poll();
        totalCost += currentCost;
        pq.offer(currentCost);
      }
    }
    return totalCost;
    //time O(NLogN)
  }

  private static int minCostCleaner(int[] arr) {
    if (arr.length < 2) {
      return 0;
    }
    PriorityQueue<Integer> pq = new PriorityQueue<>();
    for (int i = 0; i < arr.length; i++) {
      pq.offer(arr[i]);
    }
    int totalCost = 0;
    while (pq.size() > 1) {
      int currentCost = pq.poll() + pq.poll();
      totalCost += currentCost;
      pq.offer(currentCost);
    }
    return totalCost;
  }
}
