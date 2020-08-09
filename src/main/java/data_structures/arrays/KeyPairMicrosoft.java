package data_structures.arrays;

import java.util.HashSet;
import java.util.Set;

public class KeyPairMicrosoft {

    public static void main(String[] args) {
        int []arr = {1, 4, 45, 6, 10, 8};
        int sum = 16;
        System.out.println(containsSumBruteForce(arr, sum));
    }

    private static boolean containsSumBruteForce(int[] arr, int sum) {
        // n*n complexity
        for(int i = 0 ; i< arr.length; i++) {
            for (int j = i+1; j< arr.length; j++) {
                if(arr[i] + arr[j] ==sum) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean containsSum(int[] arr, int sum) {
        Set<Integer> set = new HashSet<>();
        for(int i = 0 ; i< arr.length; i++) {
            if(set.contains(arr[i])) {
                return true;
            }
            set.add(sum-arr[i]);
        }
        return false;
    }
}
