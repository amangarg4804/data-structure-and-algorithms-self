package algorithms.leetcodebinarysearch;

import java.util.Arrays;
import java.util.PriorityQueue;

// The distance of a pair of integers a and b is defined as the absolute difference between a and b.
//Given an integer array nums and an integer k, return the kth smallest distance among all the pairs nums[i] and nums[j] where 0 <= i < j < nums.length.
public class KthSmallestPairDistance {

    public int smallestDistancePair1(int[] nums, int k) {
        // Use a priority queue to keep the kth pair on top of queue

        PriorityQueue<Pair> pq = new PriorityQueue<>((pair1, pair2) ->
                Math.abs(pair2.num1-pair2.num2) - Math.abs(pair1.num1-pair1.num2)); // kth smallest, so we use max heap
        for(int i =0; i< nums.length;i++) {
            for(int j=i+1; j< nums.length; j++) {
                pq.offer(new Pair(nums[i],nums[j]));
                if(pq.size() >k) {
                    pq.poll();
                }
            }
        }
        Pair kth = pq.poll();
        return Math.abs(kth.num1-kth.num2);
        // memory limit exceeded
    }
    public int smallestDistancePair2(int[] nums, int k) {
        // sort the array
        // for an element at index 0, if k=3, we can iterate till index 4. All elements beyond index 4 will have distance more than k

        Arrays.sort(nums);
        PriorityQueue<Pair> pq = new PriorityQueue<>((pair1, pair2) ->
                Math.abs(pair2.num1-pair2.num2) - Math.abs(pair1.num1-pair1.num2));
        for(int i=0; i< nums.length; i++) {
            for(int j=i+1; j< i+k+1 && j< nums.length; j++) {
                pq.offer(new Pair(nums[i],nums[j]));
                if(pq.size() >k) {
                    pq.poll();
                }
            }
        }
        Pair kth = pq.poll();
        return Math.abs(kth.num1-kth.num2);
        // memory limit exceeded
    }

    public int smallestDistancePair3(int[] nums, int k) {
        // 2, 5, 8, 10, 12, 14
        // no of pairs with difference less than or equal to 5
        // starting at 2: {2, 5}
        // starting at 5: {5, 8}, {5,10}- no need to checks as soon as we encounter 12
        // starting at 8: {8, 10}, {8, 12}-no need to checks as soon as we encounter 14 because difference between 14 and 8 is more than 5 and since array is sorted, any number after 14 will have larger difference
        // We have to find kth smallest difference
        // First we sort the array and find the smallest and largest difference
        Arrays.sort(nums);
        int largestDiff = nums[nums.length-1] - nums[0]; // it is given that nums.length >=2
        int smallestDiff = Integer.MAX_VALUE;
        for(int i=1; i<nums.length;i++ ) {
            smallestDiff = Math.min(nums[i] -nums[i-1], smallestDiff);
        }

        // kth smallest difference will be between smallestDiff and largest Diff. We can do a binary search with start as smallestDiff and end as largestDiff
        while (smallestDiff < largestDiff) {
            int midDiff = smallestDiff + (largestDiff-smallestDiff)/2;

            // now we have to check if midDiff is kth smallest difference.
            // To do this, we can check the number of pairs in nums array that have difference less than or equal to midDiff
            // For this difference to be kth smallest difference, the count of such pairs should be 1 less than k
            // kth smallest difference means finding difference between all pairs and sorting those differences. And finding the kth difference among sorted difference.
            // it doesn't matter if all differences are same , uniqueness of difference is not taken into account
            // e.g. {1, 1, 1}
            // Pairs are {1,1},{1,1}, {1,1}, differences are 0, 0, 0. So for k=2, kth smallest difference is 0

            if(countOfPairs(nums, midDiff) < k ) {
                smallestDiff = midDiff+1;
            } else {
                // count of pairs is greater than or equal to k, meaning this difference can also be the answer
                largestDiff = midDiff;
            }

        }
        return smallestDiff; // at this point smallestDiff = largestDiff
    }

    private int countOfPairs(int[] nums, int midDiff) {
        // this is a subproblem. Given an integer array nums and target difference, find the no of pairs that have difference less than or equal to target difference
        // instead of checking each pair, which will take O(N^2). we can iterate over nums and do a binary search for each index.
        // We try to find first number on the right with greater difference
        int pairsCount =0;
        for(int i=0; i< nums.length; i++) {
            // for pairs starting with index i (left), what is the leftmost value on the right, let's call it j,
            // such that nums[j]-nums[i] > midDiff or nums[j] > midDiff + nums[i]
            pairsCount +=upperBound(nums, i, midDiff+nums[i]) -i-1  ;
            // consider last index is 5. upperBound will return 6. 6-5-1 =0
        }
        return pairsCount;
    }

    private int upperBound(int[] nums, int start, int target) {
        // another subproblem.
        //
        // Returns index of first element which is greater than target or smallest element greater than target. Check SmallestGreaterThanTarget.java
        int end = nums.length-1;
        if(nums[end] <= target) {
            // if no element exists in array that is greater than target. Then return end+1
            return end+1;
        }
        while (start < end) {
            int mid = start + (end - start)/2;
            if(nums[mid] <= target) {
                start = mid+1;
            } else {
                end = mid;
            }
        }
        return start;
    }

    private static class Pair {
        int num1;
        int num2;

        public Pair(int num1, int num2) {
            this.num1 = num1;
            this.num2 = num2;
        }
    }
}
