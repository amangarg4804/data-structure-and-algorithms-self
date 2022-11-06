package algorithms.leetcoderecurion2;

public class QuickSort {

    public void quickSort(int[] arr) {
        quickSort(arr, 0, arr.length-1);
    }

    private void quickSort(int[] arr, int start, int end) {
        if(start<end) {
          int pivotIndex = partition(arr, start, end);
          quickSort(arr, start, pivotIndex-1);
          quickSort(arr, pivotIndex+1, arr.length-1);
        }
    }


    private int partition(int[] arr, int startIndex, int endIndex) { // 2, 8, 12, 6, 4, 7 ->
        // swap the elements when the current element is less than pivot
        int valueAtPivotIndex = arr[endIndex];
        int swapIndex = startIndex;
        for(int i=startIndex; i< endIndex;i++) { // we can't start from startIndex+1, in case the first element is less than pivot, we should increment swapIndex although the swap step would do nothing
            if(arr[i]<valueAtPivotIndex) {
                swap(arr, swapIndex, i); // all elements to the left of swap index will be less than pivot value
                swapIndex++;
            }
        }
        // Since swap index has all elements to the left of it smaller than pivot value,
        // we can now swap the element at swap index with element at end/pivot index. So, pivot will have all smaller elements than itself to the left of it
        swap(arr, swapIndex, endIndex);
        return swapIndex;
    }

    private static void swap(int[] arr, int swapIndex, int i) {
        int temp = arr[i];
        arr[i] = arr[swapIndex];
        arr[swapIndex] = temp;
    }


}
