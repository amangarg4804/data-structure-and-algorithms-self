package algorithms.interviews.bp;

//Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it can trap after raining.
public class TrappingRainWater {
    public int trap(int[] height) {
        // at any index. the amount of trapped water for a particular index is minumum of the maximum array element to the left and the maximum array element to the right
        // min (max(left), max(right))
        // if none of max(left) or max(right) is greater than element at current index, the current index can't store any water
        // Input: height = [0,1,0,2,1,0,1,3,2,1,2,1]

        int[] maxLeftArr = new int[height.length];
        int[] maxRightArr = new int[height.length];

        // Create an array holding left max for each index
        maxLeftArr[0] = height[0];
        for(int i = 1; i< height.length; i++) {
            maxLeftArr[i] = Math.max(maxLeftArr[i-1], height[i]);
        }
        // Create an array holding right max for each index, for right max, we have to iterate from right
        maxRightArr[height.length-1] = height[height.length-1];
        for(int i = height.length-2; i>=0 ; i--) {
            maxRightArr[i] = Math.max(maxRightArr[i+1], height[i]);
        }
        int ans = 0;

        for(int i=0; i< height.length; i++) {
            ans += Math.min(maxLeftArr[i], maxRightArr[i]) - height[i]; // we have to subtract current height from minimum of left and right
        }
        return ans;

    }

    public int trap2(int[] height) {
        // at any index. the amount of trapped water for a particular index is minimum of the maximum array element to the left and the maximum array element to the right
        // min (max(left), max(right))
        // if none of max(left) or max(right) is greater than element at current index, the current index can't store any water
        // Input: height = [0,1,0,2,1,0,1,3,2,1,2,1]

        // We can also use two pointer approach to save space
        // https://docs.google.com/document/d/1o8dFJvlXLoi_AMB_EE2P2XawEZCfLrYkC15bmCSkvGc/edit
        int left= 0;
        int right = height.length-1;
        int ans = 0;
        int leftMax = 0 ; // initially both leftMax and rightMax are 0.
        int rightMax = 0;
        while(left <= right) {

            leftMax = Math.max(height[left], leftMax);
            rightMax = Math.max(height[right], rightMax);
            if(leftMax < rightMax) {
                // if leftMax is smaller, we have to increase left
                ans += leftMax - height[left];// for left =0, leftMax and height[left] are same, so we will be adding 0 to answer
                left++;
            } else {
                ans += rightMax- height[right];
                right--;
            }
            // The above logic is similar to doing ans += Math.min(maxLeft, maxRight) - height[i] (like in previous solution)
            // except that we also have to increment left and right
        }
        return ans;
    }
}
