package algorithms.leetcodehashtable;

import java.util.HashSet;
import java.util.Set;

public class HappyNumber {

    public boolean isHappy(int n) {
        Set<Integer> sumsSet = new HashSet<>();
        int sum=n;
        while (sum!=1) {
            int num =sum;
            sum=0;
            while (num>0) {
                int remain = num % 10;
                sum+=remain*remain;
                num=num/10;
            }
            if(!sumsSet.add(sum)) {
                return false;
            }
        }
        return true;
    }
}
