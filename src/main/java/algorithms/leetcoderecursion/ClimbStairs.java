package algorithms.leetcoderecursion;

import java.util.HashMap;
import java.util.Map;

public class ClimbStairs {

    Map<Integer, Integer> cache = new HashMap();
    public int climbStairs(int n) {
        //TLE
        if(n<=1) {
            return 1;
        }
        return climbStairs(n-1) + climbStairs(n-2);
    }

    public int climbStairs2(int n) {
        if(cache.containsKey(n)) {
            return cache.get(n);
        }
        if(n<=1) {
            return 1;
        }

        int result  =  climbStairs2(n-1) + climbStairs2(n-2);
        cache.put(n, result);
        return result;
    }
}
