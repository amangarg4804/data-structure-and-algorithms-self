package algorithms.leetcodegreedy;

//Input: nums1 = [4,3,1,4], nums2 = [1,3,7,1], k = 3
//Output: 2
//Explanation: In 2 operations, we can transform nums1 to nums2.
//1st operation: i = 2, j = 0. After applying the operation, nums1 = [1,3,4,4].
//2nd operation: i = 2, j = 3. After applying the operation, nums1 = [1,3,7,1].
//One can prove that it is impossible to make arrays equal in fewer operations.
public class MinimumOperationsToMakeArrrayEqualII {
    public long minOperations(int[] nums1, int[] nums2, int k) {
        //Intuition: calculate increment count and decrement count for each index.
        // If number of increment count and decrement count match, the count is answer.
        // if number of increments required doesn't match no of decrements, return -1
        // number of increment/decrement count is a multiple of k
        //nums1 = [4,3,1,4],
        // nums2 = [1,3,7,1]
        // For index 0 -> num1[0]>nums2[0]. So nums1[0] should be decremented. Difference = nums1[0] -nums2[0] = 3.
        // how many increments required? 3/k = 3/3 =1; .
        // Here we should also check remainder. 3%k = 3%3 =0. If remainder is not 0, return -1
        // For index 1 -> nums1[1] ==nums2[1]. no operation required
        // For index 2 -> nums1[2] should be incremented. Difference = nums2[2]-nums1[2] =6.
        // how many increments required? 6/3 = 2. remainder is 0
        // For index 3 -> nums1[3] > nums2[3]. nums1[3] should be decremented. Difference = nums1[3] -nums2[3] = 3
        //  // how many increments required? 3/k = 3/3 =1; .remainder is 0
        long increments = 0; // can't use int because of integer overflow in some cases. E.g what if there are multiple indexes where nums1 is 1 and nums2 is Integer.MAX_VALUE
        long decrements = 0;
        for (int i = 0; i < nums1.length; i++) { //num1 and nums2 are of same length
            if (nums1[i] == nums2[i]) {
                continue;
            }
            if (k == 0) {
                return -1;
            }
            if(nums1[i] > nums2[i]) {
                // decrement required
                int diff = nums1[i] - nums2[i];
                if (diff % k != 0) {
                    return -1;
                }
                decrements += diff / k;
            } else {
                int diff = nums2[i] - nums1[i];
                if (diff % k != 0) {
                    return -1;
                }
                increments += diff / k;
            }
        }
        return increments == decrements ? increments : -1;
    }
}
