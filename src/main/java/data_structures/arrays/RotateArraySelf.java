package data_structures.arrays;

public class RotateArraySelf {

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4};

        int k = 2;

        int[] result = rotateArray(nums, k);

        for (int i : result) {
            System.out.print(i);
        }
    }

    private static int[] rotateArray(int[] nums, int k) {

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

    private static int[] rotateArrayInPlace(int[] nums, int k) {
        // TODO:
        return null;
    }
}
