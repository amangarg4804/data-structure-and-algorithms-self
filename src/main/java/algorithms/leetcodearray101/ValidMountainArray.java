package algorithms.leetcodearray101;

public class ValidMountainArray {

    public boolean validMountainArray(int[] arr) {
        if (arr.length < 3) {
            return false;
        }
        boolean increasing = true;
        for (int i = 1; i < arr.length; i++) {
            if (increasing && arr[i] <= arr[i - 1]) {
                if (i == 1) {
                    return false;
                }
                increasing = false;
            }
            if (!increasing && arr[i] >= arr[i - 1]) {
                return false;
            }
        }
        return !increasing;
    }

    public boolean validMountainArray1(int[] arr) {
        // Two pointers
        int start = 0;
        int end = arr.length - 1;
        while (start + 1 < arr.length && arr[start] < arr[start + 1]) {
            start++;
        }
        while (end - 1 >= 0 && arr[end - 1] > arr[end]) {
            end--;
        }
        return start == end && start != 0 && end != arr.length - 1;
    }
}
