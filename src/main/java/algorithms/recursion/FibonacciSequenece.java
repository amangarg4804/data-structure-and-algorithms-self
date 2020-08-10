package algorithms.recursion;

public class FibonacciSequenece {

    public static void main(String[] args) {
        System.out.println(fibonacci(3));
        System.out.println(fibonacci(4));

        System.out.println(fibonacciIterative(3));
        System.out.println(fibonacciIterative(4));
    }

    private static int fibonacci(int n) {

        // O(2^n) very expensive, greater than n^2
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    private static int fibonacciIterative(int n) {
        // O(n)
        // 0 , 1 , 1 , 2 , 3

        if (n == 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        }
        // could be simplified to
        //        if(n < 2) {
        //            return n;
        //        }

        int beforePrevious = 0;
        int previous = 1;
        int result = 0;

        for (int i = 2; i < n; i++) {
            result = beforePrevious + previous;
            beforePrevious = previous;
            previous = result;
        }
        return result;
    }


}
