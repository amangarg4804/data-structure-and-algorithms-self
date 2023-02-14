package algorithms.leetcodebinarysearch;

public class IsPerfectSquare {
    public boolean isPerfectSquare(int num) {
        int start = 1;
        int end = num;
        while (start <=end) {
            int mid = start + (end -start)/2;
            long square = (long) mid * mid;
            if(square == num) { //to avoid integer overflow
                return true;
            }
            if(mid < num/mid) {
                start = mid+1;
            } else {
                end =  mid -1;
            }
        }
        return false;
    }


}
