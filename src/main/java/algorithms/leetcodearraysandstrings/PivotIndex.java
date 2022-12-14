package algorithms.leetcodearraysandstrings;


//The pivot index is the index where the sum of all the numbers strictly to the left of the index is equal to the sum of all the numbers strictly to the index's right
public class PivotIndex {

    public int pivotIndex(int[] nums) {
        // using extra space O(n)
        int [] leftSum = new int[nums.length];
        int[] rightSum = new int[nums.length];
        int currentLeftSum = 0;
        int currentRightSum =0;
        for(int i=0, j=nums.length-1; i< nums.length && j>=0; i++, j--) { // no need of variable j, we can calculate j based on i inside the loop
            leftSum[i] = currentLeftSum;
            currentLeftSum += nums[i];
            rightSum[j] = currentRightSum;
            currentRightSum += nums[j];
        }

        for(int i=0; i< nums.length; i++) {
            if(leftSum[i]== rightSum[i]) {
                return i;
            }
        }
        return -1;
    }

    public int pivotIndex1(int[] nums) {
        // Without using extra space
        int totalSum = 0; // assuming no overflow
        for(int i=0; i< nums.length;i++) {
            totalSum+=nums[i];
        }
        int leftsum =0;
        for(int i=0; i< nums.length; i++) {
            if(i> 0) {
                leftsum += nums[i-1];
            }
            int rightsum = totalSum -leftsum -nums[i];
            if(leftsum ==rightsum) {
                return i;
            }
        }
        return -1;
    }

}
