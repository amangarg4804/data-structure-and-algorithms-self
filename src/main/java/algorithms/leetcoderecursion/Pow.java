package algorithms.leetcoderecursion;

public class Pow {

    public double myPow(double x, int n) {
        if(n==0) {
            return 1;
        }
        if(n< 0) {
            return myPowRecursive(1/x, -n);
        }

        return myPowRecursive(x, n);
    }

    public double myPowRecursive(double x, int n) {
        if(n==0) {
            return 1;
        }
        return n%2==0 ? myPowRecursive(x*x, n/2) : x* myPowRecursive(x*x, n/2);  // multiply with x everytime n is odd. 50 -25- 12- 6- 3
    }

    public double myPowIterative(double x, int n) {
        double result = 1;
        long l = Math.abs((long)n); // integer overflow for -2147483648, so need to use long
        while (l > 0) {
            if (l % 2 != 0) {
                result = result * x;
            }
            x *= x;
            l = l/2;
        }
        return n < 0 ? 1 / result : result;
    }
}
