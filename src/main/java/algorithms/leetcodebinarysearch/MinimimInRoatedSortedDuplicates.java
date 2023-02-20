package algorithms.leetcodebinarysearch;

//Input: nums = [2,2,2,0,1]
//Output: 0
public class MinimimInRoatedSortedDuplicates {
    public int findMin(int[] nums) {
            //[3,3,1,3]
        // if nums[mid] <=nums[end], it is not necessary that right part is sorted
        // [1,3,3]
        //  If we use combination of if(nums[mid] <nums[end]) and else. we would be increasing start = mid+1 in else and that would also be wrong
        // We don't have to return the index but the  number itself
        // [3, 5, 1]
        // [10,1,10,10,10]
        // 10, 10, 10, 10, 1, 2, 10
        // it is possible that start, mid and end indexes have same integer, how to know in which direction to move?
        int start =0;
        int end = nums.length-1;
        while (start <end) {
            int mid = start +(end -start)/2;
            if(nums[mid] <nums[end]) {// if end is greater than mid, all the elements to the right of mid are going to be greater than mid
                // right side is sorted
                end = mid; // 2,2,2,0,1 -> if mid was 0 then the minimum is on the left side or the mid itself
            } else if(nums[mid] > nums[end]){
                // if mid is greater than end, it is confirmed that right side is not sorted. Since this is a rotated and sorted array,
                // left side must be sorted. If left side is sorted and mid is greater than end. All elements to the left of mid must be greater than right
                // This is the difference between the problem with all distinct elements and the problem that allows duplicates
                // When duplicates were not allowed, we incremented start index in else block, but when duplicates are allowed, we will have to handle the equal case too
                start = mid+1;
            } else {
                //  nums[mid]==nums[end]
                end--; // start++ won't work, we are only checking for end index. Integer at end index is same as integer at mid index  , so end index should be decremented
            }
        } // [3,3,1,3]
        return nums[end];
    }
}
