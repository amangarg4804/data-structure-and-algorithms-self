package algorithms.leetcodearraysandstrings;

public class MaxConsecutiveOnes {

    public int findMaxConsecutiveOnes(int[] nums) {
        int maxConsecutive =0;
        int current = 0;
        for(int i=0; i< nums.length; i++) {
            if(nums[i]==1) {
                current++;
                maxConsecutive = Math.max(current, maxConsecutive);
            } else {
                current =0;
            }
        }
        return maxConsecutive;
    }

    public int findMaxConsecutiveOnes1(int[] nums) {
        int maxConsecutive =0;
        int currentCount = 0;
        int i=0;
        while (i<nums.length) {
            while (i< nums.length && nums[i]==1 ) {
                currentCount++;
                maxConsecutive = Math.max(currentCount, maxConsecutive);
                i++;
            }
            currentCount=0;
            i++;
        }
        return maxConsecutive;
    }
}
