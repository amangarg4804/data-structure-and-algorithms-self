package data_structures.arrays;

import java.util.HashSet;
import java.util.Set;

public class ContainsDuplicate {

    public static void main(String[] args) {
        int [] arr= {1,2,3,1};

        System.out.print(containsDuplicate(arr));
    }

    private static boolean containsDuplicate(int[] arr) {
        Set<Integer> set = new HashSet<>();
        for(int i = 0; i< arr.length; i++) {
            if(set.contains(arr[i])) {
             return true;
            }
            set.add(arr[i]);
        }
        return false;
    }

}
