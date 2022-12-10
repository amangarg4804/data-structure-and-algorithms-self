package algorithms.leetcodearray101;

public class ReplaceWithGreatestRight {
    public int[] replaceElements(int[] arr) {
        int max = -1; // given all elements in array are positive
        for(int i= arr.length -1; i>=0; i--) {
            int current = arr[i];
            arr[i] =max;
            max = Math.max(max, current);
        }
        return arr;
    }
}
