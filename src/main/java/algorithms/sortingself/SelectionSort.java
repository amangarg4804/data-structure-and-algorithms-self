package algorithms.sortingself;

import java.util.Arrays;

public class SelectionSort {


    public static void main(String[] args) {
        Integer[] array = {5, 1, 85, 4, 7, 9, 0, 3, 1};
        selectionSort(array);

        System.out.println(Arrays.toString(array));
    }

    private static Integer[] selectionSort(Integer[] array) {

        // 5, 1, 85, 4, 7, 9, 0, 3, 1
        // i =0
        // min = 0
        //j = 1

        for (int i = 0; i< array.length; i++) {
            int min =i ;
            for (int j = i+1; j< array.length; j++) {
                // find the minimum element from i to end of array
                if (array[min] > array[j]) {
                    min = j;
                }
            }
            // swap the min element with i
            int temp = array[i];
            array[i] = array[min];
            array[min] = temp;
        }
        return array;
    }
}
