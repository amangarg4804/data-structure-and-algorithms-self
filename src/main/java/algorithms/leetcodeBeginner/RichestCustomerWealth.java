package algorithms.leetcodeBeginner;

public class RichestCustomerWealth {

    public int maximumWealth(int[][] accounts) {
        int max = Integer.MIN_VALUE;
        for(int i = 0; i< accounts.length; i++) {
            int wealth = 0;
            for(int j=0; j< accounts[i].length; j++) {
                wealth+= accounts[i][j];
            }
            max = Math.max(max, wealth);
        }
        return max;
    }
}
