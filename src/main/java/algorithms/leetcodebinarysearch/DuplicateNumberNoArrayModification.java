package algorithms.leetcodebinarysearch;

import java.util.HashSet;
import java.util.Set;

// Input: nums = [1,3,4,2,2]
//Output: 2
//Input: nums = [3,1,3,4,2]
//      Output: 3
public class DuplicateNumberNoArrayModification {
// numbers are not sorted
    public int findDuplicate1(int[] nums) {
        // using extra space
        Set<Integer> set = new HashSet<>();
        for(int i: nums) {
            if(!set.add(i)) {
                return i;
            }
        }
        return -1;
    }

    public int findDuplicate2(int[] nums) {
        // We use floyd cycle detection algorithm to solve it without extra space
        // numbers are from 1 to n . where n is one less than length of array
        int slow =nums[0];
        int fast =nums[0];
//        while (slow !=fast) { // duplicate number exists for sure
//            slow = nums[slow];
//            fast = nums[nums[fast]];
//        }  initially slow = fast so while loop will never run, we should use do while loop instead
        do { // duplicate number exists for sure
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (slow!=fast);
        // slow equals fast.
        // Duplicate number will be the start of the cycle. To find start of the cycle we initialize either slow or fast to 0 and move both by one till slow and fast meet.
        // They will meet at the cycle start

        slow = nums[0];
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }

        return slow;
    }
}
