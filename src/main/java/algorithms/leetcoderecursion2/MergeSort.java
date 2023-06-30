package algorithms.leetcoderecursion2;

import java.util.Arrays;

public class MergeSort {


    public static void main(String[] args) {
        mergeSortIterative(new int[]{-4,0,7,4,9,-5,-1,0,-7,-1});
    }
    public int [] merge_sort(int [] input) {
        //top down
        if(input.length <= 1) {
            return input;
        }
        int mid = input.length/2;
        int[] part1 = merge_sort(Arrays.copyOfRange(input, 0, mid)); // NOTE: "to" index is exclusive and can lie outside the array range
        int[] part2 = merge_sort(Arrays.copyOfRange(input, mid, input.length));
        return merge(part1, part2);
    }

    public int [] merge(int [] arr1, int [] arr2) {
        int [] ret = new int[arr1.length + arr2.length];
        int index1 = 0, index2 = 0, resultIndex = 0;

        while (index1 < arr1.length &&
                index2 < arr2.length) {
            if (arr1[index1] < arr2[index2]) {
                ret[resultIndex++] = arr1[index1++];
            } else {
                ret[resultIndex++] = arr2[index2++];
            }
        }
        // append what is remain the above lists
        while (index1 < arr1.length) {
            ret[resultIndex++] = arr1[index1++];
        }
        while (index2 < arr2.length) {
            ret[resultIndex++] = arr2[index2++];
        }
        return ret;
    }

    public static int[] mergeSortIterative(int[] nums) {
        // Bottom up, we consider each element of array as list.
        // Since there is only one element, the list is already sorted, so we can start the merge process

//        for(int mergeCount =2 ; mergeCount <= nums.length; mergeCount=mergeCount*2) {  In case array has 6 elements, mergeCount increases to 8. loop never runs for 6 elements leaving first 4 elements sorted and last 2 elements sorted. Hence we take the approach to start mergeCount with 1 instead of 2.
//            for(int i=0; i< nums.length; i = i+mergeCount){
//                int  end= Math.min(i + (mergeCount-1), nums.length-1);
//                merge(nums, i, end);
//            }
//        }

        for (int mergeCount = 1; mergeCount <= nums.length; mergeCount = mergeCount * 2) {
            for (int i = 0; i < nums.length; i = i + (2*mergeCount)) {
                int end = Math.min(i + (2*mergeCount - 1), nums.length - 1); // if the array length is not in series 1, 2, 4, 8.., end/mid could reach beyond length of the array, in such cases, we have to use nums.length-1 as mid/end
                int mid = Math.min(i +  (mergeCount-1), nums.length-1); // mid calculation requires merge count because in case of 6 elements mid should be index 3 (4th element)
                merge(nums, i, mid, end);
            }
        }
        return nums;
    }

    private static void merge(int[] nums, int start, int mid, int end) {
        int[] temp = new int[end-start+1];
        int index =0;
        int left = start;
        int right = mid+1;
        while(left <= mid && right <=end) {
            if(nums[left] < nums[right]) {
                temp[index++] = nums[left++];
            } else {
                temp[index++] = nums[right++];
            }
        }

        while (left<= mid) {
            temp[index++] = nums[left++];
        }
        while (right <= end) {
            temp[index++] = nums[right++];
        }
        index=0;
        for(int i=start; i<= end; i++) {
            nums[i] = temp[index++];
        }
    }
}
