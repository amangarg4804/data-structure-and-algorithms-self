package data_structures.arrays;

import java.util.Arrays;

public class MergeSortedArraySelf {

    public static void main(String[] args) {
        int[] arr = mergeSortNaive(new int[]{1,1,8,10}, new int[]{1,2,8,9,11,15,16});
        Arrays.stream(arr).forEach(System.out::print);
    }

    private static int[] mergeSortNaive(int[] arr1, int[] arr2) {
        int [] result = new int[arr1.length + arr2.length];
        int arr1Index =0;
        int arr2Index = 0;

        for (int i = 0; i < result.length; i++ ) {
            if(arr1Index>= arr1.length) {
                result[i] = arr2[arr2Index];
                arr2Index++;
            } else if(arr2Index>= arr2.length) {
                result[i] = arr1[arr1Index];
                arr1Index++;
            } else if(arr1[arr1Index] <= arr2[arr2Index]) {
                result[i] = arr1[arr1Index];
                arr1Index++;
            } else {
                result[i] = arr2[arr2Index];
                arr2Index++;
            }
        }
        return result;
    }

}
