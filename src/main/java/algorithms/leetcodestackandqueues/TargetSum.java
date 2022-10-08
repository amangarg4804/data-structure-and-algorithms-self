package algorithms.leetcodestackandqueues;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class TargetSum {


    public int findTargetSumWaysBfs(int[] nums, int target) {

        Queue<Integer> q = new LinkedList<>();

        int level =0;
        q.offer(nums[0]);
        q.offer(-1 * nums[0]);
        int ways =0;
        while (!q.isEmpty()) {
            int size = q.size();
            for(int i=0; i< size; i++) {
                Integer current = q.poll();
                if(level ==nums.length-1) {
                    if(current==target) {
                        ways++;
                    }
                    continue;
                }
                int positive = current + nums[level+1];
                int negative = current- nums[level+1];
                q.offer(current + nums[level+1]);
                q.offer(current -nums[level+1]);
            }
            level++;
        }
        return ways;
    }

    public int findTargetSumWaysDfs(int[] nums, int target) {
        return findTargetSumWaysDfs(nums, target, 0, nums[0]) + findTargetSumWaysDfs(nums, target, 0, -nums[0]);
    }

    private int findTargetSumWaysDfs(int[] nums, int target, int index, int currentSum) {
        if(index+1 == nums.length) {
            if(currentSum ==target) {
                return 1;
            } else {
                return 0;
            }
        }


        return findTargetSumWaysDfs(nums, target, index+1, currentSum+ nums[index+1])
                + findTargetSumWaysDfs(nums, target, index+1, currentSum-nums[index+1]);

    }


}
