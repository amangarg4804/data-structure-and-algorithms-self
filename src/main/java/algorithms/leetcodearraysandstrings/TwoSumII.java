package algorithms.leetcodearraysandstrings;

public class TwoSumII {
    public int[] twoSum(int[] numbers, int target) {
        int start = 0;
        int end = numbers.length -1;
        while (start < end) {
            int sum = numbers[start] +numbers[end]; // assuming no overflow
            if(sum ==target) {
                return new int[] {start+1, end+1}; // given array is 1-indexed and not 0-indexed
            }
            if(sum< target) {
                start++;
            } else {
                end--;
            }
        }
        return new int[]{-1, -1};
    }
}
