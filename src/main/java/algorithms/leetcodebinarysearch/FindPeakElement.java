package algorithms.leetcodebinarysearch;

public class FindPeakElement {

    public int findPeakElement1(int[] nums) {
        // O(n) - iterate the array, compare each element with its left and right neighbour. If it is greater, return it
        // For 0th  element, left element can be Long.MIN_VALUE, for last element, right can be Long.MIN_VALUE
        for(int i=0; i< nums.length; i++) {
            long left = i-1 < 0? Long.MIN_VALUE: nums[i-1]; // We can't use Integer.MIN_VALUE because the array might contain Integer.MIN_VALUE
            long right = i+1 == nums.length? Long.MIN_VALUE: nums[i+1];
            if(nums[i] > right && nums[i] > left) {
                return i;
            }
        }
        return -1;
    }

    public int findPeakElement2(int[] nums) {
        // using binary search Log(n)
        // 5, 10, 15
        // mid is 10,  move start to 15, i.e, 2nd index
        // either 15 will be peak, or 15 may have greater integer to the right to it, let's say 20, in that case 20 will be peak
        // in any case, if the mid+1 is greater than mid, there will exist a peak on right. So, we can move the left pointer to mid +1
        // same for when mid -1 is greater than mid
        int start =0;
        int end = nums.length-1;
        while (start <= end) {
            int mid = start + (end-start)/2;
            if(mid !=0 && mid !=nums.length-1) {
                if(nums[mid-1] < nums[mid] && (nums[mid+1] < nums[mid])) {
                    return mid;
                } else if(nums[mid] < nums[mid-1]) {
                    end= mid-1;
                } else {
                    start = mid+1;
                }
            } else if (mid==0){
                if(mid+1 < nums.length && nums[mid+1] > nums[mid]) {
                    return mid+1;
                } else {
                    return mid; // mid is 0
                }
            } else {
                // mid is nums.length-1
                if(mid-1>=0 && nums[mid-1] > nums[mid]) {
                    return mid-1;
                } else {
                    return mid; // mid is nums.length-1
                }
            }
        }
        return -1;
    }
    // binary search on answer
    public int findPeakElement3(int[] nums) {
        // using binary search Log(n)
        // 5, 10, 15
        // mid is 10,  move start to 15, i.e, 2nd index
        // either 15 will be peak, or 15 may have greater integer to the right to it, let's say 20, in that case 20 will be peak
        // in any case, if the mid+1 is greater than mid, there will exist a peak on right. So, we can move the left pointer to mid +1
        // same for when mid -1 is greater than mid
        int start = 0;
        int end = nums.length - 1;
        if (nums.length == 1) {
            return 0;
        }
        // array size is at least 2, we can check if start or end index is peak
        if (nums[0] > nums[1]) {
            return 0;
        }
        if (nums[end] > nums[end - 1]) {
            return end;
        }
        start =1;
        end = nums.length-2;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (nums[mid - 1] < nums[mid] && (nums[mid + 1] < nums[mid])) { // No need to check for index out of bound
                return mid;
            } else if (nums[mid] < nums[mid - 1]) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return -1;
    }

    public int findPeakElement4(int[] nums) {
        // using binary search Log(n)
        // 5, 10, 15
        // mid is 10,  move start to 15, i.e, 2nd index
        // either 15 will be peak, or 15 may have greater integer to the right to it, let's say 20, in that case 20 will be peak
        // in any case, if the mid+1 is greater than mid, there will exist a peak on right. So, we can move the left pointer to mid +1
        if(nums.length==1) {
            return 0;
        }
        int start = 0;
        int end = nums.length - 1;
        while (start +1 < end) { // run loop only when at least 3 elements
            int mid = start + (end - start) / 2;
            if (nums[mid] < nums[mid+1]) { // won't work if we check nums[mid] > nums[mid+1] and set start = mid. Example: [1,2,3,1] because we can't be sure there will be a greater element on the left of mid
                start = mid+1 ;
            } else {
                end = mid;
            }
        }
        // after loop ends, only two integers
        if(start == nums.length-1 || nums[start] > nums[start+1]) {
            return start;
        }
        return end;
        //2 integers: 1, 2 or 2, 1
    }
}
