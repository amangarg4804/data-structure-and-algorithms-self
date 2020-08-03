package data_structures.arrays;

public class SearchInSortedAndRotatedArray {

    public static void main(String[] args) {
        int []arr = {5, 6, 7, 8, 9, 10, 1, 2, 3};
        int searchElement = 10;

        System.out.println(search(arr, searchElement));
    }

    private static int search(int[] arr, int searchElement) {
        // O(n)
        for(int i = 0; i< arr.length; i ++) {
            if(arr[i] == searchElement) {
                return i;
            }
        }
        return -1;

    }

    private static int searchOptimized(int[] arr, int searchElement) {
        for(int i = 0; i< arr.length; i ++) {
            if(arr[i] == searchElement) {
                return i;
            }
        }
        return -1;
    }
}
