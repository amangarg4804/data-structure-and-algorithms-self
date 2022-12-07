package algorithms.leetcodearray101;

public class SortedSquares {
    public int[] sortedSquares(int[] nums) {
        int negativeIndex = -1;
        int[] result = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < 0) {
                negativeIndex = i;
            }
        }
        int resultIndex = 0;
        int positiveIndex = negativeIndex + 1;

        while (negativeIndex >= 0 && positiveIndex < nums.length) {
            if (-1 * nums[negativeIndex] < nums[positiveIndex]) {
                result[resultIndex++] = nums[negativeIndex] * nums[negativeIndex];
                negativeIndex--;
            } else {
                result[resultIndex++] = nums[positiveIndex] * nums[positiveIndex];
                positiveIndex++;
            }
        }
        while (negativeIndex >= 0) {
            result[resultIndex++] = nums[negativeIndex] * nums[negativeIndex];
            negativeIndex--;
        }

        while (positiveIndex < nums.length) {
            result[resultIndex++] = nums[positiveIndex] * nums[positiveIndex];
            positiveIndex++;
        }
        return result;
    }

    public int[] sortedSquaresOnePass(int[] nums) {
        // two pointer approach
        int[] result = new int[nums.length];
        int start = 0;
        int end = nums.length - 1;

        for (int i = nums.length - 1; i >= 0; i--) { // either the last square could be greatest or the first one
            if (Math.abs(nums[start]) > Math.abs(nums[end])) {
                result[i] = nums[start] * nums[start]; // set greater element
                start++;
            } else {
                result[i] = nums[end] * nums[end];
                end--;
            }
        }
        return result;
    }
}
