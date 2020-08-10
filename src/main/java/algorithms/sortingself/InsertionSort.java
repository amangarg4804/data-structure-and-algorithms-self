package algorithms.sortingself;

import java.util.Arrays;

public class InsertionSort {

    public static void main(String[] args) {
        Integer[] array = {1, 8, 54, 9, 65, 1, 100, 0, 45};
        insertionSort(array);
        System.out.println(Arrays.toString(array));
    }

    private static void insertionSort(Integer [] arr) {
        //  9 15 22 17
        for( int i = 1; i< arr.length; i++) {
            // Hold arr[i]
            int key = arr[i];
            int j = i-1;
            while (j>=0 && arr[j] > key) {
                arr[j+1] = arr[j];
                j--;
            }
            arr[j+1] = key;
        }
    }
}
