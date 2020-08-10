package algorithms.sortingself;

import java.util.Arrays;

public class MergeSort {

    public static void main(String[] args) {
        Integer[] array = {1, 8, 54, 9, 65, 1, 100, 0, 45, 50, 0, 90, 30, 46};
        System.out.println(Arrays.toString(mergeSort(array)));
    }

    private static Integer[] mergeSort(Integer[] array) {
        if(array.length ==1) {
            return array;
        }

        // Split array into two
        int mid = array.length/2;
        Integer [] leftArray = new Integer[mid];
        int inputArrayIndex = 0;
        for (int i = 0; i< mid; i ++) {
            leftArray[i] = array[inputArrayIndex];
            inputArrayIndex++;
        }
        Integer [] rightArray = new Integer [array.length - mid];

        for (int i = 0; i < rightArray.length; i++) {
            rightArray[i] = array[inputArrayIndex];
            inputArrayIndex++;
        }
        //System.out.println("Array length: " +array.length + " mid: " + mid);
//
//        Integer [] leftArray  = Arrays.copyOfRange(array, 0, mid );
//        Integer [] rightArray = Arrays.copyOfRange(array, mid, array.length);



        return merge(mergeSort(leftArray), mergeSort(rightArray));


    }

    private static Integer[] merge(Integer[] leftArr, Integer[] rightArr) {
        Integer[] mergedArr = new Integer[leftArr.length + rightArr.length];

        int leftIndex =0;
        int rightIndex = 0;
        for(int i =0; i< mergedArr.length; i++) {
            if(leftIndex <leftArr.length && rightIndex < rightArr.length) {
                if(leftArr[leftIndex] < rightArr[rightIndex]) {
                    mergedArr[i] = leftArr[leftIndex];
                    leftIndex++;
                } else {
                    mergedArr[i] = rightArr[rightIndex];
                    rightIndex++;
                }
            } else if(leftIndex >=leftArr.length) {
                mergedArr[i] = rightArr[rightIndex];
                rightIndex++;
            } else {
                mergedArr[i] = leftArr[leftIndex];
                leftIndex++;
            }
        }
        return mergedArr;
    }


}
