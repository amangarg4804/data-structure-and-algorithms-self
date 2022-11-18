package algorithms.leetcoderecursion2;

import java.util.Deque;
import java.util.LinkedList;

public class LargestRectangleInHistogram {

    public int largestRectangleArea1(int[] heights) {
        // Working but TLE
        int maxArea = Integer.MIN_VALUE;
        for (int i = 0; i < heights.length; i++) {
            int minHeight = heights[i];
            int width = 1;
            maxArea = Math.max(maxArea, minHeight * width);
            for (int j = i + 1; j < heights.length; j++) {
                width++;
                minHeight = Math.min(minHeight, heights[j]);
                maxArea = Math.max(maxArea, width * minHeight);
            }
        }
        return maxArea;
    }

    public int largestRectangleArea2(int[] heights) {
        // Intuition. Largest area including an element at ith position is the area between leftmost larger element than heights[i] and rightmost largest element than heights [i]
        // Keep moving left to include elements with larger height than heights[i],
        // keep moving right to include elements with larger height than heights[i]
        //
        // TLE O(n*n)
        int maxArea = Integer.MIN_VALUE;
        for (int i = 0; i < heights.length; i++) {
            int leftMostElementHigherThanCurrent = findLeft(i, heights);
            int rightMostElementHigherThanCurrent = findRight(i, heights);

            int width = rightMostElementHigherThanCurrent-leftMostElementHigherThanCurrent +1;
            int height = heights[i];
            maxArea = Math.max(maxArea, width*height);
        }
        return maxArea;
    }

    private int findRight(int i, int[] heights) {
        int current = heights[i];
        int right = i+1;
        while (right < heights.length) {
            if(heights[right] >= current) {
                right++;
            } else {
                break;
            }
        }
        return right -1;
    }

    private int findLeft(int i, int[] heights) {
        int current = heights[i];
        int left = i-1;
        while (left>=0) {
            if(heights[left] >= current) {
                left--;
            } else {
                break;
            }
        }
        return left +1;
    }

    public int largestRectangleArea3(int[] heights) {

        // O(n)
        // Intuition. Largest area including an element at ith position is the area between leftmost larger element than heights[i]
        // and rightmost largest element than heights [i]
        // Create two arrays: one holding leftmost greater or equal element index and another holding rightmost greater or equal element than heights[i]
        // sub problem is to find next greater element (nge). Here we will find next greater or equal to element and keep it in array.
        // After we find next smaller element's index, we can to index - 1 to find rightmost greater or equal element
        int leftGreaterOrEqual[] = new int[heights.length];
        int rightGreaterOrEqual[] = new int[heights.length];

        Deque<Integer> stack = new LinkedList<>();
        for (int i = 0; i < heights.length; i++) {
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                stack.pop();
            }
            if (stack.isEmpty()) {
                leftGreaterOrEqual[i] = 0; // If stack becomes empty it means all elements to the left of current element are greater or equal to current element, so boundary will be 0
            } else {
                leftGreaterOrEqual[i] = stack.peek()+ 1; // stack will contain smaller element than the element at ith position because we removed all greater and equal elements
            }
            stack.push(i);

        }
        stack.clear();
        for (int i = heights.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                stack.pop();
            }

            if (stack.isEmpty()) {
                rightGreaterOrEqual[i] = heights.length-1;
            } else {
                rightGreaterOrEqual[i] = stack.peek() -1;
            }
            stack.push(i);
        }
        int maxArea = Integer.MIN_VALUE;
        for (int i = 0; i < heights.length; i++) {
            int leftMostElementHigherThanCurrent = leftGreaterOrEqual[i];
            int rightMostElementHigherThanCurrent = rightGreaterOrEqual[i];

            int width = rightMostElementHigherThanCurrent-leftMostElementHigherThanCurrent +1;
            int height = heights[i];
            maxArea = Math.max(maxArea, width*height);
        }
        return maxArea;
    }

    public int largestRectangleArea4(int[] heights) {
        // O(n) without stack
//        Here's the intuition to understand p = lessFromLeft[p]; :
//
//        Consider the test case
//        indices.......... : 0 1 2 3 4 5 6 7 8 9 10 11
//        heights.......... : 4 9 8 7 6 5 9 8 7 6 5   4.
//        lessFromLeft :-1 0 0 0 0 0 5 5 5 5 . .
//
//        In this, when we reach 5 at index 10, we start searching for idx=9, i.e. p points at 6.
//        6 > 5.
//        Now, we want something which is smaller than 5, so it should definitely be smaller than 6. So 6 says to 5:
//
//        I've already done the effort to find the nearest number that's smaller than me and you needn't traverse the array again till that point. My lessFromLeft points at index 5 and all the elements between that and me are greater than me so they are surely greater than you. So just jump to that index and start searching from there.
//
//        So you next p directly points at idx = 5, at value 5.
//
//        There, this 5 again says the same statement to current 5 and asks it to jump directly to idx = 0. So in the second iteration itself, our search has reached idx=0 and that's our answer for the current element.
//      we are not traversing the array again and again. it's O(n).
        int maxArea = Integer.MIN_VALUE;
        int leftSmaller[] = new int[heights.length];
        int rightSmaller[] = new int[heights.length];
        leftSmaller[0] =-1; // NOTE: For 0th index left smaller is -1 index, doesn't exist in array, but this is done to ensure we can calculate area for 0th index later
        rightSmaller[heights.length-1] = heights.length;
        for(int i=1; i< heights.length; i++) { // we start from 1
            int left = i-1;
            while(left>=0 && heights[left]>= heights[i]) {
                left = leftSmaller[left];
            }
            leftSmaller[i] = left;
        }
        for(int i=heights.length-1; i>= 0; i--) {
            int right = i+1;
            while(right <heights.length && heights[right]>= heights[i]) {
                right = rightSmaller[right];
            }
            rightSmaller[i] = right; // we don't do -1 in this approach because as shown in above example,what's less than 5 will surely be less than 6 but what's greater than or equal to 5 , won't be equal to what's great than or equal to 6
        }
        for (int i = 0; i < heights.length; i++) {
            int leftSmallerThanCurrent = leftSmaller[i];
            int rightSmallerThanCurrent = rightSmaller[i];

            int width = rightSmallerThanCurrent-leftSmallerThanCurrent -1; // Note: -1 instead of +1 we did when we had greater or equal element
            int height = heights[i];
            maxArea = Math.max(maxArea, width*height);
        }
        return maxArea;

    }

    public static void main(String[] args) {

        new LargestRectangleInHistogram().largestRectangleArea3(new int[]{2,4});
    }
}
