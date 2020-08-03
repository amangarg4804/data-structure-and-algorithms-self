package data_structures.arrays;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class MajorityElement {

    public static void main(String[] args) {
        int[] arr = {3, 1, 3, 3, 2};
        System.out.println(majorityElement(arr));
        System.out.println(majorityElement3(arr));
    }

    private static String majorityElement(int[] arr) {
        int majority = (arr.length / 2) + 1;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            if(map.containsKey(arr[i])) {
                map.put(arr[i], map.get(arr[i]) + 1 );
            } else {
                map.put(arr[i], 1);
            }
        }
        for (Entry<Integer, Integer> entry: map.entrySet()) {
            if(entry.getValue() >= majority) {
                return entry.getKey().toString();
            }
        }
        return "-1";
    }

    private static String majorityElementRemove2ndForLoop(int[] arr) {

        if(arr == null) {
            return "-1";
        }
        if(arr.length ==1) {
            return String.valueOf(arr[0]);
        }
        int majority = (arr.length / 2) + 1;
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < arr.length; i++) {
            if(map.containsKey(arr[i])) {
                int newValue=map.get(arr[i]) + 1;
                map.put(arr[i], newValue);
                if(newValue ==majority) {
                    return String.valueOf(arr[i]);
                }
            } else {
                map.put(arr[i], 1);
            }

        }
        return "-1";
    }

    // Boyer-Moore Voting Algorithm

    private static String majorityElement3(int[] arr) {
        //  1 2 3 3
        //
        // candidate = 3
        // count =2
        //i = 3

        // The idea is if a number is occuring more than n/2 times, then it will survive
        // even if we subtract its occurence by one every time we encounter
        // an element other than majority element

        //3, 1, 3, 3, 2
        // candidate = 3
        // count =2
        //i = 3
        int candidate = arr[0];
        int count = 1;

        for (int i = 1; i< arr.length; i++) {
            if(arr[i] == candidate) {
                count++;
            } else {
                count--;
            }
            if(count ==0) {
                candidate = arr[i];
                count=1;
            }
        }

        // We found the candidate, now we need to check whether this candidate really is majority element
        int total = 0;
        for (int i = 0 ; i < arr.length; i++ ) {
            if(arr[i] == candidate) {
                total++;
            }
        }
        if(total > arr.length/2) {
            return String.valueOf(candidate);
        }
        return "-1";
    }

}
