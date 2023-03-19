package algorithms.leetcodegreedy;

//iven an integer array flowerbed containing 0's and 1's, where 0 means empty and 1 means not empty, and an integer n,
// return if n new flowers can be planted in the flowerbed without violating the no-adjacent-flowers rule.
//Example 1:
//
//Input: flowerbed = [1,0,0,0,1], n = 1
//Output: true
//Example 2:
//
//Input: flowerbed = [1,0,0,0,1], n = 2
//Output: false

public class CanPlaceFlowers {

    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        if(n==0) {
            return true;
        }
        for(int i=0;i< flowerbed.length ; i++) {
            if(flowerbed[i]==1) {
                //already planted
                continue;
            }
            // check both left and right
            boolean leftEmpty = i == 0 || flowerbed[i - 1] == 0;
            boolean rightEmpty= i == flowerbed.length - 1 || flowerbed[i + 1] == 0;
            if(leftEmpty && rightEmpty) {
                flowerbed[i]=1;
                n--;
                if(n==0) {
                    return true;
                }
            }
        }
        return false;
    }
}
