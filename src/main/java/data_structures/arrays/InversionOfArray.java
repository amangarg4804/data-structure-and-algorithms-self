package data_structures.arrays;

public class InversionOfArray {

    // Formally, two elements a[i] and a[j] form an inversion if a[i] > a[j] and i < j.
    // The sequence 2, 4, 1, 3, 5 has three inversions (2, 1), (4, 1), (4, 3).

    public static void main(String[] args) {
        int []arr = {2, 4, 1, 3, 5};
        System.out.println(countInversionsBruteForce(arr));
    }

    private static int countInversionsBruteForce(int[] arr) {
        int count = 0;
        for (int i = 0; i< arr.length; i++) {
            for (int j = i+ 1; j< arr.length; j++) {
                if(arr[i]> arr[j]) {
                    count++;
                }
            }
        }
        return count;
    }

    private static int countInversionsOptimized() {
        //TODO: Uses merge sort
        return 0;
    }

}
