package algorithms.leetcoderecursion;

public class Pow {

    public double myPow(double x, int n) {
        if(n==0) {
            return 1;
        }
        if(n< 0) {
            return myPowRecursive(1/x, n); // NOTE: x^(-n) == (1/x)^n
        }

        return myPowRecursive(x, n);
    }

    public double myPowRecursive(double x, int n) {
        if(n==0) {
            return 1;
        }
        return n%2==0 ? myPowRecursive(x*x, n/2) : x* myPowRecursive(x*x, n/2);  // NOTE:  x^n == (x*x)^n/2 . multiply with x everytime n is odd. 50 -25- 12- 6- 3
    }

    public double myPowIterative(double x, int n) {
        double result = 1;
        long l = Math.abs((long)n); // integer overflow for -2147483648, so need to use long
        while (l > 0) {
            if (l % 2 != 0) {
                result = result * x; // This line will get executed atleast once because l will resolve to 1 when we keep dividing l by 2 4/2  2/2 1
            }
            x *= x;
            l = l/2;
        }
        return n < 0 ? 1 / result : result;
    }
}
