package algorithms.interviews.bp;

import java.util.Arrays;

// https://takeuforward.org/data-structure/aggressive-cows-detailed-solution/
//Problem Statement: You are given an array ‘arr’ of size ‘n’ which denotes the position of stalls.
//        You are also given an integer ‘k’ which denotes the number of aggressive cows.
//        You are given the task of assigning stalls to ‘k’ cows such that the minimum distance between any two of them is the maximum possible.
//        Find the maximum possible minimum distance.
public class AggressiveCows {

    // NOTE: When we have to find maximum of minimum or minimum of maximum, we can use binary search
    private static boolean canBePlaced(int[] stalls, int k, int d) {
        int placed = 1;
        int previous = stalls[0];
        for(int i=1; i< stalls.length; i++) {
            if(stalls[i] - previous >=d) {
                placed++;
                previous = stalls[i];
            }
        }
        return placed >= k;
    }

    public static int aggressiveCows(int [] stalls, int k) {
        // initialize min and max distance. We want to maximize the minimum distance
        Arrays.sort(stalls);
        int start =1 ;
        int end =  stalls[stalls.length -1] - stalls[0];
        int d = -1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (canBePlaced(stalls, k, mid)) {
                d= start;
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return d;
    }
}
