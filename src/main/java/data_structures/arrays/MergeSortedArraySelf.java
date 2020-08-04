package data_structures.arrays;

import java.util.Arrays;

public class MergeSortedArraySelf {

    public static void main(String[] args) {
        int arr1[] = new int[]{1,1,8,10};
        int arr2[] = new int[]{1,2,8,9,11,15,16};

        int[] arr = mergeSortNaive(arr1, arr2);
        Arrays.stream(arr).forEach(element -> System.out.print(element + " "));
        System.out.println();
        mergeSortedArraysInPlace(arr1, arr2);
        Arrays.stream(arr1).forEach(element -> System.out.print(element + " "));
        Arrays.stream(arr2).forEach(element -> System.out.print(element + " "));
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

    private static void mergeSortedArraysInPlace(int[] arr1, int[] arr2) {
        // Comparing ith element in arr1 with 0th element in arr2
        // if 0th element is arr 2 is less,
        // then it means arr1 is having a large element which is larger than the smallest element in arr2
        // use insertion sort on arr2 to keep it sorted
        for(int i=0; i< arr1.length; i++) {
            if(arr1[i] > arr2[0]) {
                int elementFromArr1 = arr1[i];
                arr1[i] = arr2[0];
                arr2[0] = elementFromArr1;
                for(int j = 1; j< arr2.length; j++) {
                    if(arr2[j-1]> arr2[j]) {
                        int temp = arr2[j-1];
                        arr2[j-1] = arr2[j];
                        arr2[j] = temp;
                    }
                }
            }
        }
    }
}
