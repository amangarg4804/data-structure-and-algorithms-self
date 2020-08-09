package data_structures.arrays;

public class ReverseArrayInGroups {

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};
        reverseArrayinGroups2(arr, 3);
        for (int i : arr) {
            System.out.print(i);
        }
        System.out.println();
        int[] arr2 = {10, 20, 30, 40, 50, 60};
        reverseArrayinGroups2(arr2, 2);
        for (int i : arr2) {
            System.out.print(i);
        }
    }

    private static void reverseArrayinGroups(int[] arr, int groupSize) {
        int count = 1;
        for (int i = 0; i < arr.length; ) {
            int end = count * groupSize - 1;
            if (end >= arr.length) {
                end = arr.length - 1;
            }
            reverse(arr, i, end);
            i = end + 1;
            count++;
        }
    }

    private static void reverseArrayinGroups2(int[] arr, int groupSize) {
        for (int start = 0; start < arr.length; ) {
            int end = start + groupSize - 1;
            if (end >= arr.length) {
                end = arr.length - 1;
            }
            reverse(arr, start, end);
            start = end + 1;
        }
    }

    private static void reverse(int[] arr, int start, int end) {
        while (start < end) {
            int temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            start++;
            end--;
        }
    }

}
