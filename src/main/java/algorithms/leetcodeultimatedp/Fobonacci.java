package algorithms.leetcodeultimatedp;

public class Fobonacci {
    public int fib(int n) {
//        F(0) = 0, F(1) = 1
//        F(n) = F(n - 1) + F(n - 2), for n > 1.
        if(n<2) {
            return n;
        }
        int[] dp = new int[n+1];
        dp[0] = 0;
        dp[1] = 1;
        for(int i=2; i<=n; i++) {
            dp[i] = dp[i-1] + dp[i-2];
        }
        return dp[n];
    }
}
