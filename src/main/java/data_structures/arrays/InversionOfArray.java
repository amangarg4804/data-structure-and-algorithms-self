package data_structures.arrays;

public class InversionOfArray {

    static int noOfInversions = 0;

    // Formally, two elements a[i] and a[j] form an inversion if a[i] > a[j] and i < j.
    // The sequence 2, 4, 1, 3, 5 has three inversions (2, 1), (4, 1), (4, 3).

    public static void main(String[] args) {
        int[] arr = {2, 4, 1, 3, 5};
        System.out.println(countInversionsBruteForce(arr));
        mergeSort(arr);
        System.out.println(noOfInversions);
    }

    private static int countInversionsBruteForce(int[] arr) {
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    count++;
                }
            }
        }
        return count;
    }

    private static int[] mergeSort(int[] array) {
        if (array.length == 1) {
            return array;
        }
        // Split array into two
        int mid = array.length / 2;
        int[] leftArray = new int[mid];
        int inputArrayIndex = 0;
        for (int i = 0; i < mid; i++) {
            leftArray[i] = array[inputArrayIndex];
            inputArrayIndex++;
        }
        int[] rightArray = new int[array.length - mid];

        for (int i = 0; i < rightArray.length; i++) {
            rightArray[i] = array[inputArrayIndex];
            inputArrayIndex++;
        }
        return merge(mergeSort(leftArray), mergeSort(rightArray));
    }

    private static int[] merge(int[] leftArr, int[] rightArr) {
        int[] mergedArr = new int[leftArr.length + rightArr.length];

        int leftIndex = 0;
        int rightIndex = 0;

// [2, 4]   [1, 3, 5]
        for (int i = 0; i < mergedArr.length; i++) {
            if (leftIndex < leftArr.length && rightIndex < rightArr.length) {
                if (leftArr[leftIndex] < rightArr[rightIndex]) {
                    mergedArr[i] = leftArr[leftIndex];
                    leftIndex++;
                } else {
                    noOfInversions = noOfInversions + leftArr.length -leftIndex;
                    mergedArr[i] = rightArr[rightIndex];
                    rightIndex++;
                }
            } else if (leftIndex >= leftArr.length) {
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
