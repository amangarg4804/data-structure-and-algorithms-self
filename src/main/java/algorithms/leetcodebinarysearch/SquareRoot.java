package algorithms.leetcodebinarysearch;

//Given a non-negative integer x, return the square root of x rounded down to the nearest integer. The returned integer should be non-negative as well.
//
//You must not use any built-in exponent function or operator.
public class SquareRoot {

    public int mySqrt1(int x) {
        int left =0;
        int right = x ;
        int ans=0;
        while (left <=right) {
            int mid = left + (right-left)/2;
            long square = (long) mid * mid;// NOTE: square can be out of integer range. (long) converts it to long. Notice that comparison of long and int works fine
            if(square ==x) {
                return mid;
            }
            if(square< x) {
                ans = mid;
                left = mid+1;
            } else {
                right = mid-1;
            }

        }
        return ans;
    }

    public int mySqrt2(int x) {
        // to avoid integer overflow, instead of checking mid*mid == x. We can also check mid == x/mid
        if(x==0) {
            return 0;
        }
        int left =1; // initialize left with 1 instead of 0 to Arithmetic exception
        int right = x ;
        int ans=0;
        while (left <=right) {
            int mid = left + (right-left)/2;
            if(mid ==x/mid) {
                return mid;
            }
            if(mid< x/mid) {
                ans = mid;
                left = mid+1;
            } else {
                right = mid-1;
            }

        }
        return ans;
    }

    public static void main(String[] args) {
        new SquareRoot().mySqrt1(2147395599);
    }
}
