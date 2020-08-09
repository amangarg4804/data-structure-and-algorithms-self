package data_structures.arrays;

public class RotateArraySelf {

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4};

        int k = 2;

        int[] result = rotateArray(nums, k);

        for (int i : result) {
            System.out.print(i);
        }
        System.out.println();
        rotateArrayOptimized(nums, k);
        for (int i : nums) {
            System.out.print(i);
        }
    }

    private static int[] rotateArray(int[] nums, int k) {
        // O(n) time
        // O(n) extra space

        int[] resultArr = new int[nums.length];

        for (int currentIndex = 0; currentIndex < nums.length; currentIndex++) {
            int newindex;
            if (k > nums.length) {
                k = k % nums.length;
            }
//            if (currentIndex + k < nums.currentSize) {
//                newindex = currentIndex + k;
//            } else {
//                newindex = currentIndex + k -nums.currentSize;
//            }

            newindex = (currentIndex + k) % nums.length;
            resultArr[newindex] = nums[currentIndex];
        }
        return resultArr;
    }

    private static void rotateArrayOptimized(int[] nums, int k) {
        // In place
        // time O(n) Space O(1)

        // TODO: Test
        k = k % nums.length;
        reverseArray(nums, 0, nums.length - 1);
        reverseArray(nums, 0, k - 1);
        reverseArray(nums, k, nums.length - 1);
    }

    private static void reverseArray(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }
}
