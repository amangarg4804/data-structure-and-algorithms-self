package algorithms.leetcodebinarysearch;

public class FindPeakElement {

    public int findPeakElement(int[] nums) {

        for(int i=0; i< nums.length; i++) {
            long left = i-1 < 0? Long.MIN_VALUE: nums[i-1]; // We can't use Integer.MIN_VALUE because the array might contain Integer.MIN_VALUE
            long right = i+1 == nums.length? Long.MIN_VALUE: nums[i+1];

            if(nums[i] > right && nums[i] > left) {
                return i;
            }

        }
        return -1;
    }
}
