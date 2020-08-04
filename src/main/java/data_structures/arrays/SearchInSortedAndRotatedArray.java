package data_structures.arrays;

public class SearchInSortedAndRotatedArray {

    public static void main(String[] args) {
        int []arr = {5, 6, 7, 8, 9, 10, 1, 2, 3};
        int searchElement = 10;

        System.out.println(search(arr, searchElement));
        System.out.println(searchOptimized(arr, searchElement));
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
        // O(Log(n))
        int left = 0;
        int right = arr.length -1;

        while(left<=right) {
            int mid = (left + right)/2;
            if(arr[mid] ==searchElement) {
                return mid;
            } else if(arr[left] <= arr[mid]) {
                // graph on left side is uniformly increasing
                if(searchElement <=arr[mid] && searchElement >= arr[left]) {
                    right = mid -1;
                } else {
                    left = mid +1;
                }
            } else {
                // graph on right side is uniformly increasing
                if(searchElement >= arr[mid] && searchElement <= arr[right]) {
                    left = mid+1;
                } else {
                    right = mid-1;
                }
            }
        }
       return -1;
    }
}
