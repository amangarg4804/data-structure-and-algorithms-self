package algorithms.leetcodedp;

import java.util.HashMap;
import java.util.Map;

public class Tribonacci {
    //One advantage of this solution is that we precompute each tribonacci number. If we have multiple requests on the value of the ithi^{th}ith
        //  tribonacci number later, we can simply refer to dp[i] in a constant time, rather than computing dp[i] again. This method is called tabulation.
    public int tribonacci(int n) {
        if(n == 0) {
            return 0;
        }
        if(n <=2) {
            return 1;
        }
        int[] dp = new int[n+1];
        dp[0]= 0;
        dp[1] = 1;
        dp[2] = 1;
        for(int i = 3; i<=n; i++) {
            dp[i] = dp[i-1] + dp[i-2] + dp[i-3];
        }
        return dp[n];
        // time : O(n)- for loop
        // space: O(n)- dp array
    }

    public int tribonacci2(int n) {
        return recurseMemoize(n);
    }

    private int recurse(int n) {
        if(n == 0) {
            return 0;
        }
        if(n <=2) {
            return 1;
        }
        return recurse(n-1) + recurse(n-2) + recurse(n-3);
    }

    // NOTE a way to initialize map inline
    Map<Integer, Integer> map = new HashMap<>() {{
        put(0, 0);
        put(1, 1);
        put(2,2);
    }

    };

    private int recurseMemoize(int n) {
        if(n == 0) {
            return 0;
        }
        if(n <=2) {
            return 1;
        }
        if(!map.containsKey(n)) {
            int result= recurseMemoize(n-1) + recurseMemoize(n-2) + recurseMemoize(n-3);
            map.put(n, result);
        }
        return map.get(n);
    }
    // bottom up withut memoization, we require only 3 values
    public int tribonacci3(int n) {
        if(n == 0) {
            return 0;
        }
        if(n <=2) {
            return 1;
        }
        int nminus3= 0;
        int nminus2 = 1;
        int nminus1 = 1;
        int result = 0;
        for(int i = 3; i<=n; i++) {
                result = nminus1 + nminus2 + nminus3;
                nminus3 = nminus2;
                nminus2 = nminus1;
                nminus1 = result;
        }
        return result;
    }
}
