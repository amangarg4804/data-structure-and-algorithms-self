package algorithms.leetcodearraysandstrings;

// 2 <= nums.length <= 50
public class LargestNumberAtleastTwice {

    public int dominantIndex(int[] nums) {
        // one pass solution
        int maxIndex = 0; // 0 <= nums[i] <= 100
        boolean atleasttwice = true;
        for(int i=1; i< nums.length;i++) {
            if(nums[i] > nums[maxIndex]) {
                if(nums[i]  >= 2* nums[maxIndex]) {
                    atleasttwice = true;
                } else {
                    atleasttwice = false;
                }
                maxIndex =i;
            } else if (nums[maxIndex] < nums[i]*2) {
                atleasttwice = false;
            }
        }
        return atleasttwice? maxIndex: -1;
    }

    public int dominantIndex2(int[] nums) {
        // one pass solution
        int max =0;
        int secondMax = 0; // 0 <= nums[i] <= 100
        int index = -1;
        for(int i=0; i< nums.length;i++) {
            if(nums[i] > max) {
                secondMax = max;
                max = nums[i];
                index =i;
            } else if (secondMax < nums[i]) {
                secondMax = nums[i];
            }
        }
        return max >= secondMax*2 ? index : -1;
    }
}
