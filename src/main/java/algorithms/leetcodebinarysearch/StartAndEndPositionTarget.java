package algorithms.leetcodebinarysearch;

//Input: nums = [5,7,7,8,8,10], target = 8
//Output: [3,4]
//Input: nums = [5,7,7,8,8,10], target = 6
//Output: [-1,-1]
public class StartAndEndPositionTarget {
    public int[] searchRange(int[] nums, int target) {
        // check class FirstAndLastIndex for cleaner solution
        if(nums.length==0) {
            return new int[]{-1, -1};
        }

        int firstOccurrence = findFirstOccurrence(nums, target);
        if(firstOccurrence ==-1) {
            return new int[]{-1, -1};
        }
        int lastOccurrence = findLastOccurrence(nums, target);

        return new int[]{firstOccurrence, lastOccurrence};

    }

    private int findFirstOccurrence(int[] nums, int target) {
        int start =0;
        int end = nums.length -1;
        int ans =-1;
        while (start <= end) {
            int mid = start + (end - start)/2;
            if(nums[mid]==target) {
                end = mid -1; // because we want to find first occurrence, we change end to mid -1 to search left of the array
                ans = mid;
            } else if(nums[mid] >  target) {
                end = mid -1;
            } else {
                start = mid+1;
            }
        }
        return ans;
    }

    private int findLastOccurrence(int[] nums, int target) {
        int start =0;
        int end = nums.length -1;
        int ans =-1;
        while (start <= end) {
            int mid = start + (end - start)/2;
            if(nums[mid]==target) {
                start = mid + 1; // because we want to find last occurrence, we change start to mid +1 to search right of the array
                ans = mid;
            } else if(nums[mid] >  target) {
                end = mid -1;
            } else {
                start = mid+1;
            }
        }
        return ans;
    }


}
