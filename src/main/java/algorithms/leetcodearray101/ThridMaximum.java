package algorithms.leetcodearray101;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.TreeSet;

public class ThridMaximum {

    public int thirdMax(int[] nums) {
        // nums length >=1
            PriorityQueue<Integer> pq = new PriorityQueue<>();
            Set<Integer> set = new HashSet<>();
            for(int i=0; i< nums.length; i++) {
                if(set.add(nums[i]) && (pq.size() <3 || pq.peek() < nums[i])) {
                    pq.offer(nums[i]);
                    if(pq.size() > 3) {
                        pq.poll();
                    }
                }
            }

            if(set.size() >=3) {
                return pq.peek();
            } else {
                Integer result =null;
                while (!pq.isEmpty()) {
                    result = pq.poll();
                }
                return result;
            }
    }

    public int thirdMax1(int[] nums) {
        // nums length >=1
        // without hashset
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int i=0; i< nums.length; i++) {
            if(!pq.contains (nums[i]) && (pq.size() <3 || pq.peek() < nums[i])) {
                pq.offer(nums[i]);
                if(pq.size() > 3) {
                    pq.poll();
                }
            }
        }

        if(pq.size() >=3) {
            return pq.peek();
        } else {
            Integer result =null;
            while (!pq.isEmpty()) {
                result = pq.poll();
            }
            return result;
        }
    }

    public int thirdMax2(int[] nums) {
        // nums length >=1
        // treeSet
        TreeSet<Integer> set = new TreeSet<>();
        for(int i=0; i< nums.length; i++) {
            if(!set.contains (nums[i]) && (set.size() <3 || set.first() < nums[i])) {
                set.add(nums[i]);
                if(set.size() > 3) {
                    set.remove(set.first());
                }
            }
        }

        if(set.size() >=3) {
            return set.first();
        } else {
            return set.last();
        }
    }

    public int thirdMax3(int[] nums) {
        // nums length >=1
        // not scalable
        long max1 = Long.MIN_VALUE;
        long max2 = Long.MIN_VALUE;
        long max3 = Long.MIN_VALUE;

        for(int i=0; i< nums.length; i++) {
            if(max1 == nums[i] || max2 ==nums[i] || max3 ==nums[i] ) {
                continue;
            }
            if(max1 == Long.MIN_VALUE || nums[i] > max1) {
                max3 = max2;
                max2 = max1;
                max1 = nums[i];
            } else if(max2 ==Long.MIN_VALUE || nums[i] > max2) {
                max3 = max2;
                max2 = nums[i];
            } else if(max3 ==Long.MIN_VALUE || nums[i] > max3) {
                max3 = nums[i];
            }
        }
        return (int)(max3 ==Long.MIN_VALUE? max1: max3); // NOTE: converting long to int
    }

}
