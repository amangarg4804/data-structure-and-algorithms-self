package algorithms.leetcodehashtable;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

//Given an integer array nums and an integer k, return the k most frequent elements. You may return the answer in any order.
//Input: nums = [1,1,1,2,2,3], k = 2
//        Output: [1,2]
public class TopKFrequent {
    public int[] topKFrequent(int[] nums, int k) {
        // We need k most frequent elements
        // Thinking of frequency - hashmap
        // thinking of top k - priority queue
        Map<Integer, Integer> numToFreq = new HashMap<>();
        for(int i : nums) {
            numToFreq.put(i, numToFreq.getOrDefault(i, 0)+1);
        }
        PriorityQueue<Map.Entry<Integer, Integer>> pq = new PriorityQueue<>((e1, e2) -> e1.getValue() - e2.getValue());// 1. we require min heap because we require max 2.  must pass comparator
        for(Map.Entry<Integer, Integer> entry : numToFreq.entrySet()) {
            pq.offer(entry);
            if(pq.size() > k) {
                pq.poll();
            }
        }
        int[] result =new int[pq.size()];
        int index = 0;
        while (!pq.isEmpty()) {  // k is in the range [1, the number of unique elements in the array]. No need to worry about NPE
            result[index++] = pq.poll().getKey();
        }
        return result;
    }
}
