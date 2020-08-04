package data_structures.arrays;

import java.util.Arrays;
import java.util.HashMap;

public class CountTheTriplets {

    // Given an array of distinct integers.
    // The task is to count all the triplets
    // such that sum of two elements equals the third element.

    public static void main(String[] args) {
        int[] arr = {1, 5, 3, 2};
        //There are 2 triplets: 1 + 2 = 3 and 3 + 2 = 5
        System.out.println(countTripletsBruteForce(arr));
        System.out.println(countTripletsBruteForce2(arr));
    }

    private static int countTripletsBruteForce(int[] arr) {
        // n^3
        int count=0;
        for (int i = 0; i< arr.length; i++) {
            for(int j = i+1; j< arr.length; j++) {
                for (int k=0; k< arr.length; k++) {
                 if(k!=i && k!=j && arr[k]== arr[i] + arr[j]) {
                     System.out.println(arr[k] + " == " + arr[i] + " + " + arr[j]);
                     count++;
                 }
                }
            }
        }

        return count ==0? -1: count;
    }

    private static int countTripletsBruteForce2(int[] arr) {

        //Sort the given array, then the problem reduces to find the triplet where two numbers giving sum X.
        int count=0;

        Arrays.sort(arr);

        for (int i = arr.length-1; i >0; i--) {
            int left = 0;
            int right = i-1;

            while (left < right) {
                if(arr[left] + arr[right] == arr[i]) {
                    count++;
                    left++;
                    right--;
                } else if(arr[left] + arr[right] > arr[i]) {
                    right--;
                } else {
                    left++;
                }
            }
        }

        return count ==0? -1: count;
    }



}
