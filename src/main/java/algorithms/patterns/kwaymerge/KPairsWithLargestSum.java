package algorithms.patterns.kwaymerge;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class KPairsWithLargestSum {

  public static void main(String[] args) {
    int[] nums1 = {9, 8, 2};
    int[] nums2 = {6, 3, 1};
    List<int[]> result = findPairs(nums1, nums2, 3);
    for(int i =0; i< result.size(); i++) {
      System.out.println(result.get(i)[0] + " " + result.get(i)[1]);
    }

  }

  private static List<int[]> findPairs(int[] nums1, int[] nums2, int k) {
    PriorityQueue<int[]> minheap = new PriorityQueue<>(
        (a1, a2) -> a1[0] + a1[1] - (a2[0] + a2[1]));
    for (int i = 0; i < nums1.length && i < k; i++) {
      for (int j = 0; j < nums2.length && j < k; j++) {
        if (minheap.size() < k) {
          minheap.offer(new int[]{nums1[i], nums2[j]});
        } else {

          if (nums1[i] + nums2[j] < minheap.peek()[0] + minheap.peek()[1]) {
            break;
          } else {
            minheap.poll();
            minheap.offer(new int[]{nums1[i], nums2[j]});
          }
        }
      }
    }
    return new ArrayList<>(minheap);
    // time O(N*M*logK), where ‘N’ and ‘M’ are the total number of elements in both arrays, respectively.
  }
}
