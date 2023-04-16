package algorithms.leetcodegreedy;

// refer examples after reading comments in avoidFlood method
//Example 1:
//
//Input: rains = [1,2,3,4]
//Output: [-1,-1,-1,-1]
//Explanation: After the first day full lakes are [1]
//After the second day full lakes are [1,2]
//After the third day full lakes are [1,2,3]
//After the fourth day full lakes are [1,2,3,4]
//There's no day to dry any lake and there is no flood in any lake.
//Example 2:
//
//Input: rains = [1,2,0,0,2,1]
//Output: [-1,-1,2,1,-1,-1]
//Explanation: After the first day full lakes are [1]
//After the second day full lakes are [1,2]
//After the third day, we dry lake 2. Full lakes are [1]
//After the fourth day, we dry lake 1. There is no full lakes.
//After the fifth day, full lakes are [2].
//After the sixth day, full lakes are [1,2].
//It is easy that this scenario is flood-free. [-1,-1,1,2,-1,-1] is another acceptable scenario.
//Example 3:
//
//Input: rains = [1,2,0,1,2]
//Output: []
//Explanation: After the second day, full lakes are  [1,2]. We have to dry one lake in the third day.
//After that, it will rain over lakes [1,2]. It's easy to prove that no matter which lake you choose to dry in the 3rd day, the other one will flood.


//Return an array ans where:
//
//        ans.length == rains.length
//        ans[i] == -1 if rains[i] > 0.
//        ans[i] is the lake you choose to dry in the ith day if rains[i] == 0. If no lake to dry, we can set ans[i] to 1
//        If there are multiple valid answers return any of them. If it is impossible to avoid flood return an empty array.
//

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class AvoidFloodInCity {

    public int[] avoidFlood(int[] rains) {
        // each index repesents a day. If rains array has 5 elements, it means 5 days
        // each element at index represents lake number.
        // {4 , 5, 4, 5} -> it means we are given two lakes: lake 4 and lake 5
        // It also means there was rain on lake 4 and lake 5 twice which resulted in flood (see examples above)
        // each element at index may also be 0, in that case, it doesn't represent any lake no. It represents that it didn't rain that day
        // Solution: when we encounter 0, we have to choose which lake to dry. We should choose the lake on which it might rain again soon,
        // that can only be known after iterating the whole array
        // For example:  [1,2,0,0,2,1], when we encounter first 0, we should dry lake 2, because it is going to rain over lake 2 soon, and it will get flooded if it does
        //Which lake to dry on a day when there is no rain, can not be determined without knowing the rain sequence that is coming next.
        //For example - If you have rains = [1, 2, 0, ??]. Without knowing what the '??' is,
        // you can not determine which lake to dry on the 3rd day (rains[2]), this is because if '??' = 1,
        // then you must dry the lake #1 to avoid flooding. Similarly, if '??' =2, then you must dry lake #2 to avoid flooding.
        //https://leetcode.com/problems/avoid-flood-in-the-city/solutions/697687/a-set-and-a-map-lucid-code-with-documented-comments/

        Map<Integer, Integer> fullLakeNoToDayNo = new HashMap<>(); // map that contains the lake number of lake that got full and the day on which it got full
        TreeSet<Integer> dryDays = new TreeSet<>(); // dry day no
        int[] ans = new int[rains.length];
        for(int i =0; i< rains.length; i++) {
            if(rains[i]==0) {
                dryDays.add(i);// it is a dry day
                ans[i] =1; // we set it to 1 as default. it can be overriden  when we dry any full lake using this dry day.
            } else {
                int lakeNo = rains[i];
                if(fullLakeNoToDayNo.containsKey(lakeNo)) { // if this lake is already full, we should try to avoid flood by using one of the dry days
                    int fullDay = fullLakeNoToDayNo.get(lakeNo); // find the day no on which this lake got full
                    //dry day should be after the fullday and before the current day

                    Integer dryDay = dryDays.ceiling(fullDay); //Returns the least element in this set greater than or equal to the given element, or null if there is no such element.
                    if(dryDay ==null) {
                        return new int[0];
                    }
                    dryDays.remove(dryDay);// as this dry day is used to avoid flood in lakeNo, it can not be used again, so we remove it
                    fullLakeNoToDayNo.put(lakeNo, i); // after using one of the dry days, the lake is now full again, so we add it to full lake map.
                    ans[dryDay] = lakeNo; // dry day index should contain the lake no that the dry day dried
                    ans[i] =-1;
                } else {

                    fullLakeNoToDayNo.put(lakeNo, i);  // if lake was not full yet, we add it to full lake map
                    ans[i] = -1; // since rains[i] >0
                }
            }
        }
        return ans;
    }
}
