package data_structures.arrays;

import java.util.Arrays;

public class Sort012 {
    // sort an array of 0's 1's and 2's

    public static void main(String[] args) {
        int[] arr = {0, 2, 1, 2, 0};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    private static void sort(int[] arr) {
        int count1 = 0;//1
        int count2 = 0; //2
        int currentIndex =0; // 1
        for(int i =0; i< arr.length; i++) {
            if(arr[i]==1) {
                count1++;
            } else if(arr[i] ==2) {
                count2++;
            } else {
                arr[currentIndex] = 0;
                currentIndex++;
            }
        }

        while(count1-- >0) {
            arr[currentIndex] =1;
            currentIndex++;
        }
        while (count2-- > 0) {
            arr[currentIndex] =2;
            currentIndex++;
        }
    }

}
