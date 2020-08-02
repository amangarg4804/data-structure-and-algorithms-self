package data_structures.arrays;

import java.util.HashSet;
import java.util.Set;

public class MissingNumberInArray {

    public static void main(String[] args) {
        int[] arr = {1, 2, 5, 6, 7, 3};
        int arrSize = 7;

        printMissingNoBruteForce(arr, arrSize);
        printMissingNoOptimization1(arr, arrSize);
        printMissingNoOptimization2(arr, arrSize);
    }

    private static void printMissingNoBruteForce(int[] arr, int arrSize) {
        for (int i = arrSize; i > 0; i--) {
            boolean numberFound = false;
            for (int j = 0; j < arr.length; j++) {
                if (i == arr[j]) {
                    numberFound = true;
                    break;
                }
            }
            if (!numberFound) {
                System.out.println(i);
                break;
            }
        }
    }

    private static void printMissingNoOptimization1(int[] arr, int arrSize) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < arr.length; i++) {
            set.add(arr[i]);
        }
        for (int j = 1; j <= arrSize; j++) {
            if (!set.contains(j)) {
                System.out.println(j);
            }
        }
    }
    private static void printMissingNoOptimization2(int[] arr, int arrSize) {
        // Using Gauss' Formula
        // Sum of first n natural numbers is (n(n+1))/2
        // If array had numbers starting from 0 , n would be array's length
        int expectedSum = (arrSize * (arrSize+1)) /2;
        int actualSum = 0;

        for (int i : arr) {
            actualSum+=i;
        }

        System.out.println(expectedSum - actualSum);

    }

}

