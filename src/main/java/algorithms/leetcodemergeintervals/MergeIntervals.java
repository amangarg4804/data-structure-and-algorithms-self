package algorithms.leetcodemergeintervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//Given an array of intervals where intervals[i] = [starti, endi], merge all overlapping intervals,
// and return an array of the non-overlapping intervals that cover all the intervals in the input.
//
//Example 1:
//
//Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
//Output: [[1,6],[8,10],[15,18]]
//Explanation: Since intervals [1,3] and [2,6] overlap, merge them into [1,6].
//Example 2:
//
//Input: intervals = [[1,4],[4,5]]
//Output: [[1,5]]
//Explanation: Intervals [1,4] and [4,5] are considered overlapping.
//
//
//Constraints:
//
//1 <= intervals.length <= 104
//intervals[i].length == 2
//0 <= starti <= endi <= 104
public class MergeIntervals {
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (i1, i2) -> i1[0]- i2[0]);
        List<int[]> result = new ArrayList<>();
        int[] previous = intervals[0];
        int currentIndex = 1;
        while (currentIndex < intervals.length ) {
            int[] current = intervals[currentIndex];
            if(current[0] <= previous[1]) {
                //overlapping
                //merge
                previous = new int[]{previous[0], Math.max(previous[1], current[1])};
            } else {
                // not overlapping.So, add previous to result
                result.add(new int[]{previous[0], previous[1]});
                previous = current;
            }
            currentIndex++;
        }
        result.add(new int[]{previous[0], previous[1]});
        return result.toArray(new int[result.size()][2]);
        // create an array from the result arraylist
//        int[][] resultArray = new int[result.size()][2];
//        for(int i = 0; i< result.size(); i++) {
//            resultArray[i] = result.get(i);
//        }
//        return resultArray;
    }
}
