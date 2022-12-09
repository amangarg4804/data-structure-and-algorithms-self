package algorithms.leetcodearray101;

public class RemoveElementInPlace {

    public int removeElement(int[] nums, int val) {
        int nextIndex =0;
        for(int i=0; i< nums.length;i++) {
            if(nums[i] !=val) {
                nums[nextIndex++] = nums[i];
            }
        }
        return nextIndex;
    }

    public int removeElement1(int[] nums, int val) {
        // replace element with last element
        int start =0; int end =nums.length -1;

        while (start<= end) {
            while (nums[start] ==val && end >=start) {
                swap(nums, start, end);
                end--;
            }
            if(nums[start] !=val) { // start will contain the number of elements that are not equal to val, so we increment start only when start !=val even after swapping it with end element
                start++;
            }
        }
        return start;
    }

    public int removeElement2(int[] nums, int val) {
        // returning end instead of start
        int start =0; int end =nums.length;

        while (start< end) {
            while (nums[start] ==val && end > start) {
                end--;
                swap(nums, start, end);
            }
            start++;
        }
        return end;
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[j];
        nums[j] = nums[i];
        nums[i] = temp;
    }
}
