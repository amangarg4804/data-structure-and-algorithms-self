package algorithms.leetcodegreedy;

//You are given an integer array arr. You can choose a set of integers and remove all the occurrences of these integers in the array.
//
//Return the minimum size of the set so that at least half of the integers of the array are removed.

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

//
//Example 1:
//
//Input: arr = [3,3,3,3,5,5,5,2,2,7]
//Output: 2
//Explanation: Choosing {3,7} will make the new array [5,5,5,2,2] which has size 5 (i.e equal to half of the size of the old array).
//Possible sets of size 2 are {3,5},{3,2},{5,2}.
//Choosing set {2,7} is not possible as it will make the new array [3,3,3,3,5,5,5] which has a size greater than half of the size of the old array.
public class ReduceArraySizeToHalf {

    public int minSetSize1(int[] arr) {
        // we can calculate frequency of each integer
        // Since we want to minimize the unique integers to be removed, we remove the integer with most frequency first and check the remaining size
        // we keep removing next integers with most frequency till the remaining elements are less than equal of half of the size of array
        int total = arr.length;
        Map<Integer, Integer> intToFrequency = new HashMap<>();
        for(int i =0; i< arr.length; i++) {
            intToFrequency.put(arr[i], intToFrequency.getOrDefault(arr[i], 0) +1);
        }

        PriorityQueue<Map.Entry<Integer, Integer>> pq = new PriorityQueue<Map.Entry<Integer, Integer>>((e1, e2) -> e2.getValue()-e1.getValue()); // Using Max heap to keep max frequency element on top of pq
//        for(Map.Entry<Integer, Integer> entry :  intToFrequency.entrySet()) {
//            pq.offer(entry);
//        }
        pq.addAll(intToFrequency.entrySet());
        int remaining = total;
        int count =0;
        while (remaining > total/2) {
            remaining -= pq.poll().getValue();
            count++;
        }
        return count;
    }

    public int minSetSize2(int[] arr) {
        // we can also use counting sort
        //2 <= arr.length <= 105
        //1 <= arr[i] <= 10^5
        int[] frequency = new int[100001];
        for(int i: arr) {
            frequency[i]++;
        }
        // 4, 4, 4, 2,2
        // frequency:  {0, 0, 2,0, 4.... }
        Arrays.sort(frequency);
        int total=arr.length;
        int remaining = arr.length;
        int count =0;
        for(int i=10000; i>0; i--) {
            if(remaining <= total/2) {
                break;
            }
            if(frequency[i]==0) {
                continue;
            }
//            remaining-=frequency    ;
            count++;

        }
        return count;
    }
}
