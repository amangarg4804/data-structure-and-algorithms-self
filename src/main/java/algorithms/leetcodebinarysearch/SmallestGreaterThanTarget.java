package algorithms.leetcodebinarysearch;

//You are given an array of characters letters that is sorted in non-decreasing order, and a character target. There are at least two different characters in letters.
//
//Return the smallest character in letters that is lexicographically greater than target. If such a character does not exist, return the first character in letters
public class SmallestGreaterThanTarget {

    public char nextGreatestLetter(char[] letters, char target) {
        if(letters[0] > target || letters[letters.length-1] <= target) {
            return letters[0];
        }

        // now, target is greater than or equal to letters[0] and less than letters[letters.length-1]
        int start =0;
        int end = letters.length-1;
        while (start < end) { // it is given that array contains at least two letters, so loop will run at least once
            int mid = start + (end -start)/2;

            if(letters[mid] <= target) { // if mid is less than equal to target, we know that greater is going to be towards right
                start=mid+1;
            } else {
                end = mid;// //If mid is greater than target, all characters to the right are going to be greater 2, 4, 6, 7, 8 target 5
            }
        }
        // at the end of loop, start =end
        return letters[end];
    }
}
