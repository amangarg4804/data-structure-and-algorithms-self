package algorithms.leetcodearray101;

public class RemoveDuplicatesInSorted {
    public int removeDuplicates(int[] nums) {
        int nonDuplicateIndex =0;
        for(int i=0; i< nums.length;i++) {
            if(nums[i] !=nums[nonDuplicateIndex]) { // 0,0,1,1,1,2,2,3,3,4 -> initially the non-duplicateIndex was 0,
                // as soon as it encountered an element not equal to value at 0th element, it was incremented.
                nonDuplicateIndex++;
                nums[nonDuplicateIndex] =nums[i];
            }
        }
        return nonDuplicateIndex+1;
    }

    public int removeDuplicates1(int[] nums) {
        int nextAvailableIndex =1;
        for(int i=1; i< nums.length;i++) {
            if(nums[i] !=nums[i-1]) {
                nums[nextAvailableIndex] =nums[i];
                nextAvailableIndex++;
            }
        }
        return nextAvailableIndex;
    }
}
