package algorithms.leetcodehashtable;

import java.util.*;

// Given two integer arrays nums1 and nums2, return an array of their intersection.
// Each element in the result must appear as many times as it shows in both arrays and you may return the result in any order.
//Example 1:
//Input: nums1 = [1,2,2,1], nums2 = [2,2]
//Output: [2,2]
public class ArrayIntersectionII {
    public int[] intersect(int[] nums1, int[] nums2) {
        // 1. store frequency of every integer in nums1 in map1
        Map<Integer, Integer> map1 = new HashMap<>();
        for(int i: nums1) {
            map1.put(i, map1.getOrDefault(i,0)+1);
        }
        Map<Integer, Integer> intersection = new HashMap<>();
        int size = 0;
        for(int i: nums2) {
            if(map1.containsKey(i)) {
                // 2. Only put to intersection map, when integer i is already in map1
                int arr1Freq = map1.get(i);
                int intersectionFreq = intersection.getOrDefault(i, 0);
                // 3. Check frequency. If intersecting map has less frequency of current integer, increment and put
                if(intersectionFreq < arr1Freq) {
                    intersection.put(i, intersectionFreq+1);
                    size++;
                }
            }
        }
        if(size==0) {
            return new int[]{};
        }
        int[] result = new int[size];
        int index = 0;
        for(Map.Entry<Integer, Integer> entry : intersection.entrySet()) {
            int freq = entry.getValue();
            while (freq--> 0) {
                result[index++] = entry.getKey();
            }
        }
        return result;
    }
    public int[] intersect2(int[] nums1, int[] nums2) {
        // Using single map, instead of using second map to add, keep adding the elements to intersection list and decrement frequency
        // 1. store frequency of every integer in nums1 in map1
        Map<Integer, Integer> map1 = new HashMap<>();
        for (int i : nums1) {
            map1.put(i, map1.getOrDefault(i, 0) + 1);
        }
        List<Integer> intersection = new ArrayList<>();
        for (int i : nums2) {
            if (map1.containsKey(i) && map1.get(i) > 0) {// we keep decrementing frequency every time we encounter the integer. If the frequency is 0, it means nums1 has fewer occurrences for an integer, so it shouldn't be added to intersection
                // 2. Only put to intersection map, when integer i is already in map1 and remaining frequency is >0
                map1.put(i, map1.get(i) - 1);
                intersection.add(i);
            }
        }
        int[] result = new int[intersection.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = intersection.get(i);
        }
        return result;
    }

    public int[] intersect3(int[] nums1, int[] nums2) {
        // follow up question, what if arrays are sorted?
        // We can solve without map
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        List<Integer> intersection = new ArrayList<>();
        int index1 =0;
        int index2 =0;
        while (index1 < nums1.length && index2 < nums2.length) {
            if(nums1[index1]==nums2[index2]) {
                intersection.add(nums1[index1]); // if both numbers are same, add them to intersection
                index1++;
                index2++;
            } else if (nums1[index1] < nums2[index2]) {
                index1++;// if num1 is less, we should increment index++ to find bigger number because the array is sorted
            } else {
                index2++;
            }
        }
        int[] result = new int[intersection.size()];
        for(int i=0; i< result.length; i++) {
            result[i] = intersection.get(i);
        }
        return result;
    }

    public int[] intersect4(int[] nums1, int[] nums2) {
        // follow up question, what if arrays are sorted?
        // We can solve without map
        // sort both the arrays. Iterate over the smaller array and do a binary search on larger array
        // Find first occurence of integer and then increase the startIndex to startindex+1 if number is found to
        // when element is found, keep track of the last index where element was found so that next binary search ignores previous used indexes
        //	ie. nums1 = 1,1   nums2 = 1,2,2 - output should be [1] - once we found first 1 at index 0 and next search is done as of index 1
        if(nums1.length > nums2.length) {
            return intersect4(nums2, nums1);
        }
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        List<Integer> intersection = new ArrayList<>();
        int start =0;
        for(int i: nums1) {
            int index = binarySearch(i, start, nums2);
            if(index !=-1) {
                start= index+1; // note that we increment start index to found index +1
                intersection.add(i);
            }
        }
        int[] result = new int[intersection.size()];
        for(int i=0; i< result.length; i++) {
            result[i] = intersection.get(i);
        }
        return result;
    }

    private int binarySearch(int target, int start, int[] nums) {
        int end = nums.length-1;
        int found =-1;
        while (start <= end) {
            int mid = start + (end-start)/2;
            if(nums[mid] == target) {
                found = mid;
                end = mid-1;
            } else if(nums[mid] < target) {
                start = mid+1;
            } else {
                end = mid -1;
            }
        }
        return found;
    }
}
