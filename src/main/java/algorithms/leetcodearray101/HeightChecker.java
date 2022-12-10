package algorithms.leetcodearray101;

import java.util.Arrays;

public class HeightChecker {
    public int heightChecker(int[] heights) {
        int[] copy = Arrays.copyOf(heights, heights.length);
        Arrays.sort(copy);
        int notMatched =0;
        for(int i=0 ; i< heights.length; i++) {
            if(heights[i] != copy[i]) {
                notMatched++;
            }
        }
        return notMatched;
    }

    public int heightChecker1(int[] heights) {
        // counting sort
        // instead of sorting array in nLog n time, maintain an array of 101 elements
        int[] heightToFrequency = new int[101];
        for(int i=0; i< heights.length ; i++) {
            heightToFrequency[heights[i]]++;  // given that height[i] <=100
        }
        int heightToFrequencyIndex =0;
        int result = 0;
        for(int i=0; i< heights.length; i++) {
            while(heightToFrequency[heightToFrequencyIndex] ==0) {
                heightToFrequencyIndex++; // find the next element with frequency not zero
            }

            if(heightToFrequencyIndex != heights[i]) { // index must be equal to current element in array if student are standing in correct order of height
                result++;
            }
            heightToFrequency[heightToFrequencyIndex]--;
        }
        return result;
    }
}
