package data_structures.arrays;

public class MaxSubArrayKadaneAlgorithmMemoization {
    // Kadane's Algorithm (Microsoft interview question)
    //The maximum sum in the first I elements
    // is either the maximum sum in the first i - 1 elements (which we'll call MaxSoFar),
    // or it is that of a subvector that ends in position i (which we'll call MaxEndingHere).

    public static void main(String[] args) {
        int result = maxSubArray(new int [] {9, -3, 7, 5, -2});

        System.out.println(result);

        System.out.print(maxSubArrayBruteForce(new int [] {9, -3, 7, 5, -2}));
    }

    public static int maxSubArray(int[] A) {
        int maxSoFar=A[0], maxEndingHere=A[0];
        for (int i=1;i<A.length;++i){
            maxEndingHere= Math.max(maxEndingHere+A[i],A[i]);
            maxSoFar=Math.max(maxSoFar, maxEndingHere);
        }
        return maxSoFar;
    }

    public static int maxSubArrayBruteForce(int[] arr) {
        // 9, -3, 7, 5, -2
        int max = arr[0];

        for (int i = 0; i< arr.length-1; i++) {
            int sumStartingHere  =arr[i];
            for(int j = i+1; j<arr.length; j++ ) {
                sumStartingHere = sumStartingHere + arr[j];
                max = Math.max(max, sumStartingHere);
            }
        }
        return max;
    }

}
