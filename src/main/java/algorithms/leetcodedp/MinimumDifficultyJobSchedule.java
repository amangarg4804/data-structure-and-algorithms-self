package algorithms.leetcodedp;

import java.util.Arrays;

//You want to schedule a list of jobs in d days. Jobs are dependent (i.e To work on the ith job, you have to finish all the jobs j where 0 <= j < i).
//
//You have to finish at least one task every day. The difficulty of a job schedule is the sum of difficulties of each day of the d days.
// The difficulty of a day is the maximum difficulty of a job done on that day.
//
//You are given an integer array jobDifficulty and an integer d. The difficulty of the ith job is jobDifficulty[i].
//
//Return the minimum difficulty of a job schedule. If you cannot find a schedule for the jobs return -1.
//Input: jobDifficulty = [6,5,4,3,2,1], d = 2
//Output: 7
//Explanation: First day you can finish the first 5 jobs, total difficulty = 6.
//Second day you can finish the last job, total difficulty = 1.
//The difficulty of the schedule = 6 + 1 = 7
//Example 2:
//
//Input: jobDifficulty = [9,9,9], d = 4
//Output: -1
//Explanation: If you finish a job per day you will still have a free day. you cannot find a schedule for the given jobs.
//Example 3:
//
//Input: jobDifficulty = [1,1,1], d = 3
//Output: 3
//Explanation: The schedule is one job per day. total difficulty will be 3.
//
//
//Constraints:
//
//1 <= jobDifficulty.length <= 300
//0 <= jobDifficulty[i] <= 1000
//1 <= d <= 10
public class MinimumDifficultyJobSchedule {
    int[] hardestJobRemaining;
    int[][]dp;
    public int minDifficulty(int[] jobDifficulty, int d) {
        if(jobDifficulty.length < d) {
            return -1;
        }
        // base case, recurrence relation, A data structure and a way of visiting each DP state
        // for a job i, either we can do it on same day or we can do it on next day. the answer will be minimum of these two options.
        // if the current day is already d, then we have to do the job on same day
        // first job has to be done on day 1
        // if first job has to be done on day 2, second job can be done either on day 1 or day 2. But if d=2, second job has to be done on day 2 , because each day we have to do a job
        // each day we have to decide how many jobs to take
        // we can create a array to hold hardestJobRemaining from a particular index
        hardestJobRemaining = new int[jobDifficulty.length];
        int hardest = Integer.MIN_VALUE;
        for( int i =jobDifficulty.length -1; i>=0; i--) {
            hardest= Math.max(hardest, jobDifficulty[i]);
            hardestJobRemaining[i] = hardest;
        }
        dp= new int[jobDifficulty.length][d];
        for(int i = 0; i< jobDifficulty.length; i++) {
            Arrays.fill(dp[i], -1);
        }

        return dpTopDown(jobDifficulty, d, 1, 0);
    }
    //2. A recurrence relation to transition between states
    //At each state, we are on day 'day' and need to do job i. Then, we can choose to do a few more jobs. How many more jobs are we allowed to do? The problem says that we need to do at least one job per day. This means we must leave at least
    //d - day jobs so that all the future days have at least one job that can be scheduled on that day. If
    //n is the total number of jobs, jobDifficulty.length, that means from any given state (i, day), we are allowed to do the jobs from index
    //i up to but not including index n - (d - day).
    //We should try all the options for a given day - try doing only one job, then two jobs, etc. until we can't do any more jobs. The best option is the one that results in the easiest job schedule.
    //The difficulty of a given day is the most difficult job that we did that day. Since the jobs have to be done in order, if we are trying all the jobs we are allowed to do on that day (iterating through them), then we can use a variable
    //hardest to keep track of the difficulty of the hardest job done today. If we choose to do jobs up to the
    //jth job (inclusive), where
    //i≤j<n - (d - day) (as derived above), then that means on the next day, we start with the
    //(j+1)th job. Therefore, our total difficulty is
    //hardest + dp(j + 1, day + 1). This gives us our scariest recurrence relation so far:
    //
    //dp(i, day) = min(hardest + dp(j + 1, day + 1)) for all
    //i≤j<n - (d - day), where
    //hardest = max(jobDifficulty[k]) for all
    //i≤k≤j.
    //
    //The codified recurrence relation is a scary one to look at for sure. However, it is easier to understand when we break it down bit by bit. On each day, we try all the options - do only one job, then two jobs, etc. until we can't do any more (since we need to leave some jobs for future days).
    //hardest is the hardest job we do on the current day, which means it is also the difficulty of the current day. We add
    //hardest to the next state which is the next day, starting with the next job. After trying all the jobs we are allowed to do, choose the best result.

    private int recurse(int[] jobDifficulty, int d, int currentDay, int currentJobIndex) {
        // 9 / 34 testcases passed - TLE
        if(currentDay ==d) {
            // if it is last day , we have to finish all remaining jobs on this day
            // the answer would be minimum job difficulty of all remaining jobs
            return hardestJobRemaining[currentJobIndex]; // currentDay is 1 based
        }

        //if not last day, we have to try taking 1 or more jobs on this day
        // since on each day, atleast one job has to be done, we have to leave d-currentDay jobs
        // so we can do a maximum of  jobDifficulty -(d-currentDay) jobs on this day
        int best = Integer.MAX_VALUE; // best hold the best combination, meaning the minimum overall sum of job difficulties
        int hardest = 0; // hardest job for currentDay
        // currentIndex =2, d= 6, currentDay = 3, jobDifficulty.length = 10
        // 0, 1 ,2(currentIndex), 3, 4, 5, 6(upto this index we can take), 7, 8, 9
        //    10- (6-3) =7
        // so less than 7,
        for(int i = currentJobIndex; i < jobDifficulty.length -(d -currentDay); i++) {
            hardest = Math.max(hardest, jobDifficulty[i]);
            best = Math.min(best, hardest + recurse(jobDifficulty, d, currentDay+1, i+1));
        }
        return best;
    }

    private int dpTopDown(int[] jobDifficulty, int d, int currentDay, int currentJobIndex) {
        // memoize dp array
        if(currentDay ==d) {
            // if it is last day , we have to finish all remaining jobs on this day
            // the answer would be minimum job difficulty of all remaining jobs
            return hardestJobRemaining[currentJobIndex]; // currentDay is 1 based
        }

        //if not last day, we have to try taking 1 or more jobs on this day
        // since on each day, atleast one job has to be done, we have to leave d-currentDay jobs
        // so we can do a maximum of  jobDifficulty -(d-currentDay) jobs on this day
        if(dp[currentJobIndex][currentDay] ==-1) {
            int best = Integer.MAX_VALUE; // best hold the best combination, meaning the minimum overall sum of job difficulties
            int hardest = 0; // hardest job for currentDay
            // currentIndex =2, d= 6, currentDay = 3, jobDifficulty.length = 10
            // 0, 1 ,2(currentIndex), 3, 4, 5, 6(upto this index we can take), 7, 8, 9
            //    10- (6-3) =7
            // so less than 7,
            for(int i = currentJobIndex; i < jobDifficulty.length -(d -currentDay); i++) {
                hardest = Math.max(hardest, jobDifficulty[i]);
                best = Math.min(best, hardest + dpTopDown(jobDifficulty, d, currentDay+1, i+1));
            }
            dp[currentJobIndex][currentDay] = best;
        }

        return dp[currentJobIndex][currentDay];
        //Let nnn be the length of the jobDifficulty array, and ddd be the total number of days.
        //Time complexity: O(n^2.d) since there are n⋅d possible states, and we need O(n) time to calculate the result for each state.
        //Space complexity: O(n⋅d) space is required to memoize all n⋅d states.
    }


}
