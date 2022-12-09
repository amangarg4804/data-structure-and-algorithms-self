package algorithms.leetcodearray101;

import java.util.Arrays;
import java.util.HashSet;

public class NandDouble {

    public boolean checkIfExist(int[] arr) {
        // time: O(nLog n), space: O(1)
        Arrays.sort(arr);
        for(int i=0; i< arr.length;i++) {
            if(arr[i] < 0 && arr[i] %2!=0) {
                continue;
            }
            boolean exists =binarySearch(arr, i+1, arr.length-1, arr[i] <0? arr[i]/2 : arr[i]*2);
            if(exists) {
                return true;
            }
        }
        return false;
    }

    private boolean binarySearch(int[] arr, int start, int end, int num) {
        while (start <=end) {
            int mid = start + (end-start)/2;
            if(arr[mid] ==num) {
                return true;
            }
            if(num <arr[mid]) {
                end =mid-1;
            } else {
                start = mid+1;
            }
        }
        return false;
    }

    public boolean checkIfExist1(int[] arr) {
        // time: O(n), space: O(n)
        HashSet<Integer> set = new HashSet<>();
        for(int i=0; i< arr.length; i++) {
            if(set.contains(arr[i]*2)) {
                return true;
            }
            if(arr[i] %2==0 && set.contains(arr[i]/2)) {
                return true;
            }
            set.add(arr[i]);
        }
        return false;
    }
}
