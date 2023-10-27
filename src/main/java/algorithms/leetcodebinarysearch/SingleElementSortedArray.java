package algorithms.leetcodebinarysearch;

//You are given a sorted array consisting of only integers where every element appears exactly twice, except for one element which appears exactly once.
//Return the single element that appears only once.
//Your solution must run in O(log n) time and O(1) space.
//
//Example 1:
//Input: nums = [1,1,2,3,3,4,4,8,8]
//Output: 2

//Example 2:
//Input: nums = [3,3,7,7,10,11,11]
//Output: 10

//Constraints:
//1 <= nums.length <= 105
//0 <= nums[i] <= 105
//lenskart
public class SingleElementSortedArray {
    public int singleNonDuplicate(int[] nums) {
        int start = 0;
        int end = nums.length-1;
        while(start <=end) {
            // 3 cases
            // case 3: middle doesn't match next or previous
            int mid = start + (end -start)/2;
//            System.out.print(mid);
            // case 1: middle is same as next
            if(mid+1 <= end && nums[mid] == nums[mid+1]) {
                // nums = {1, 2, 2}
                //         0  1  2
                // two cases:
                // case 1.1: left array is odd length
                // mid; 5, start : 4, end = 6
                if((mid-1-start+1)%2!=0) {
                    end = mid -1;
                } else {
                    // case 1.2: right array is odd length
                    start = mid +2;
                }
            } // case 2: middle is same as previous
            else if(mid-1 >=start && nums[mid-1] == nums[mid]) {
                //nums = { 2, 2,1}
                // case 2.1: left array is odd length
                if((mid-2-start+1)%2!=0) {
                    end = mid -2;
                } else {
                    // case 1.2: right array is odd length
                    start = mid +1;
                    System.out.println("start: " + start);
                }
            } else {
                return nums[mid];
            }

        }
        return -1;
    }
}
