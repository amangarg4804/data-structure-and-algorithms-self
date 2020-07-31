package data_structures.arrays;

public class MoveZeros {
//    Given an array nums, write a function to move all 0's
//    to the end of it while maintaining the relative order of the non-zero elements.

//    Input: [0,1,0,3,12]
//    Output: [1,3,12,0,0]

    public static void main(String[] args) {

        int[] arr = new int[]{0,0,1};
        moveZeroes(arr);
        moveZeroesInPlace(arr);

        for (int i : arr) {
            System.out.print(i);
        }

    }

    public static void moveZeroes(int[] nums) {
        int[] result = new int[nums.length];

        int countOfZeros = 0;
        int resultArrIndex = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                countOfZeros++;
                continue;
            }
            result[resultArrIndex] = nums[i];
            resultArrIndex++;
        }

        while (countOfZeros > 0) {
            result[resultArrIndex] = 0;
            resultArrIndex++;
            countOfZeros--;
        }

        for (int i : result) {
            System.out.print(i);
        }
        System.out.println();
    }

    public static void moveZeroesInPlace(int[] nums) {
        int index = 0;
        for(int i = 0; i< nums.length; i++) {
            if(nums[i] != 0) {
                // Think this way: we are filling the array with non-zero items first
                // Remaining items are filled with zero
                nums[index] = nums[i];
                index++;
            }
        }

        while(index <nums.length) {
            nums[index] = 0;
            index++;
        }

    }

}
