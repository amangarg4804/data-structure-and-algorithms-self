package algorithms.recursion;

public class Staircase {

    public static void main(String[] args) {
        System.out.println(countNoOfWays(10));
        System.out.println(countNoOfWaysDP(10));
    }

    private static int countNoOfWays(int noOfSteps) {
        if(noOfSteps <= 2) {
            return noOfSteps;
        }
        return countNoOfWays(noOfSteps-1) + countNoOfWays(noOfSteps-2);
    }

    private static int countNoOfWaysDP(int noOfSteps) {
        if(noOfSteps <= 2) {
            return noOfSteps;
        }
        int[] dp = new int[noOfSteps+1];
        dp[0] =0;
        dp[1] =1;
        dp[2] =2;

        for(int i = 2; i< noOfSteps; i++) {
            dp[i+1]= dp[i] + dp[i-1];
        }
        return dp[noOfSteps];
    }

}
