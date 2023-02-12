package algorithms.leetcodebinarysearch;

public class Pow {
    public double myPow1(double x, int n) {
        // wrong
        if(n==0) {
            return 1;
        }
        double original =x ;
        if(n < 0) {
            x = 1 / x;
            original = 1 / x;
            n=-n;
        }
        boolean odd = n%2!=0;
        while (n > 1) { // losing some of the multiplications in case n becomes odd during iteration. Take n=10. because 6/3 = 2, 5/3 is also 2.
            x = x*x;
            n=n/2;
        }
        return odd? x*original: x;
    }

    public double myPow2(double x, int n) {
        // Idea is that x ^n is equal to (x^2) ^ n/2
        // How to deal with odd n?
        // 2^5 = 2^4 * 2 = 4^2 *2
        // If we multiply x once more in case of odd in loop, x will be 2*2*2 = 8 and n will 2, answer will be wrong
        // if we don't consider odd at all, then we lose some multiplications that should be done
        // So we have to keep one variable holding square results of x and one holding current x
        if(n==0) {
            return 1;
        }
        if(n < 0) {
            x = 1 / x;
            n=-n;
        }
        double result = x; // can't initialize result with 1 because :
        //Input:
        //2.00000
        //-2147483648
        //Output:
        //1.00000
        //Expected:
        //0.00000
        n=n-1;
        while (n > 0) {
            if(n%2!=0) {
                result = result *x; // n will reach 1 at some point
            }
            x = x*x; // result =4, n =4-> result = 64, 2 -> result
            n =n/2;
        }
        return result;
    }
    // Another iterative solution in algorithms.leetcoderecursion.Pow
}
