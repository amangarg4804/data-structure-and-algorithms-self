package algorithms.leetcodehashtable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindRestaurant {

    public String[] findRestaurant(String[] list1, String[] list2) {
        Map<String, Integer> map1 = new HashMap<>();
        for(int i=0; i<list1.length; i++ ) {
            map1.put(list1[i], i);
        }
        Map<String, Integer> stringToIndexSum = new HashMap<>();
        int smallestSum = Integer.MAX_VALUE;
        for(int i=0; i< list2.length; i++) {
            if(map1.containsKey(list2[i])) {
                int indexSum = i + map1.get(list2[i]);
                if(indexSum <= smallestSum) {
                    stringToIndexSum.put(list2[i], indexSum);
                    smallestSum = indexSum;
                }
            }
        }
        List<String> result = new ArrayList<>();
        for(Map.Entry<String, Integer> entry : stringToIndexSum.entrySet()) {
            if(entry.getValue()==smallestSum) {
                result.add(entry.getKey());
            }
        }
        return result.toArray(String[]::new);
    }

    public String[] findRestaurant2(String[] list1, String[] list2) {
        Map<String, Integer> map1 = new HashMap<>();
        for(int i=0; i<list1.length; i++ ) {
            map1.put(list1[i], i);
        }
        List<String> result = new ArrayList<>();
        int smallestSum = Integer.MAX_VALUE;
        for(int i=0; i< list2.length; i++) {
            if(map1.containsKey(list2[i])) {
                int indexSum = i + map1.get(list2[i]);
                if(indexSum < smallestSum) {
                    result.clear();
                    smallestSum = indexSum;
                    result.add(list2[i]);
                } else if(indexSum ==smallestSum) {
                    result.add(list2[i]);
                }
            }
        }
        return result.toArray(String[]::new);
    }
    //       if (list2.length < list1.length) {
    //            return findRestaurant(list2, list1); this optimization can be added to create hashmap with the smaller list to reduce space requirements
    //        }
}
