package algorithms.leetcodehashtable;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class IntersectionOfTwoArrays {

    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set1 = new HashSet<>();
        Set<Integer> set2 = new HashSet<>();
        for(int i: nums1) {
            set1.add(i);
        }

        for(int i: nums2) {
            if(set1.contains(i)) {
                set2.add(i);
            }
        }

        if(set2.size()==0) {
            return new int[0]; // or return new int[]{}// NOTE: returning empty array
        }

        int[] result = new int[set2.size()];
        int index =0;
        for(int i: set2) {
            result[index++] = i;
        }
        return result;
    }

    public int[] intersection2(int[] nums1, int[] nums2) {
        Set<Integer> a = Arrays.stream(nums1).boxed().collect(Collectors.toSet());
        Set<Integer> b = Arrays.stream(nums2).boxed().collect(Collectors.toSet());
        a.retainAll(b);
        return a.stream().mapToInt(Integer::intValue).toArray();
    }

    // 3rd solution : sort one of the arrays. Iterate over the other array and do a binary search on the sorted one
    //4th solution: sort both arrays. keep two pointers pointing to arr1 index. and arr2 index.Move the pointers based on which array's current index value is greater. If same, add to result


}
