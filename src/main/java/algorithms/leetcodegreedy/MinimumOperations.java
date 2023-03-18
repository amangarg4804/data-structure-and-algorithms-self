package algorithms.leetcodegreedy;

//Minimum Operations to Make the Array Increasing

public class MinimumOperations {

    public int minOperations1(int[] nums) {
        // each no should be bigger than the previous no
        // we are allowed to only increase numbers and not decrease them
        //
        int count = 0;
        for(int i=1; i< nums.length;i++) { // index 0 should be minimum, so we start from 1
            if(nums[i-1] < nums[i]) {
                // nothing to do, number is already greater than the previous one
                continue;
            }
            // keep increasing the number till it becomes greater than previous no
            while (nums[i-1] >= nums[i]) {
                nums[i]++;
                count++;
            }
        }
        return count;
    }

    public int minOperations2(int[] nums) {
        //without while loop
        // each no should be bigger than the previous no
        // we are allowed to only increase numbers and not decrease them
        //
        int count = 0;
        for(int i=1; i< nums.length;i++) { // index 0 should be minimum, so we start from 1
            if(nums[i-1] < nums[i]) {
                // nothing to do, number is already greater than the previous one
                continue;
            }
            //current number is less than previous, it should be at least previous + 1
            //4, 2 , expected no is 1+ 4= 5. no of operations to convert 2 to 5 = 5-2 =3
            int increasedNo = nums[i-1] +1;
            count+=increasedNo -nums[i];
            nums[i] = increasedNo;
        }
        return count;
    }

    public int minOperations3(int[] nums) {
        //without while loop and without modifying array
        // each no should be bigger than the previous no
        // we are allowed to only increase numbers and not decrease them
        //
        int count = 0;
        int previous = nums[0];
        for(int i=1; i< nums.length;i++) { // index 0 should be minimum, so we start from 1
            if(previous < nums[i]) {
                // nothing to do, number is already greater than the previous one
                previous = nums[i];
                continue;
            }
            //current number is less than previous, it should be at least previous + 1
            //4, 2 , expected no is 1+ 4= 5. no of operations to convert 2 to 5 = 5-2 =3
            int increasedNo = previous +1;
            count+=increasedNo -nums[i];
            previous = increasedNo;
        }
        return count;
    }
}
