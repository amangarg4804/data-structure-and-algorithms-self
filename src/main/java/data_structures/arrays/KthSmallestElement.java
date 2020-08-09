package data_structures.arrays;

import java.util.Arrays;

//TODO After quicksort
public class KthSmallestElement {

    public static void main(String[] args) {
        int[] arr = {7, 10, 4, 3, 20, 15};
        int k = 3;
        System.out.println(findElement(arr, 3));
    }

    private static int findElement(int[] arr, int k) {
        // O(nLog(n))
        Arrays.sort(arr);
        return arr[k-1];
    }

    private static int findElementOptimized(int[] arr, int k) {
        // O(n)


        return 0;
    }
}
