package algorithms.leetcodehashtable;

import java.util.*;

public class RandomizedSet2 {
    //optimized for O(1) removal

    Map<Integer, Integer> map = new HashMap<>(); //storing value and index in list
    List<Integer> list = new ArrayList<>();
    Random random = new Random();
    public RandomizedSet2() {

    }

    public boolean insert(int val) {
        if(map.containsKey(val)) {
            return false;
        }
        map.put(val, list.size()); // For first integer, list size is 0, so index is 0 based
        list.add(val);
        return true;
    }

    public boolean remove(int val) {
        if(!map.containsKey(val)) {
            return false;
        }
        int index = map.get(val); // get the index of integer to be deleted
        int lastVal = list.get(list.size()-1); // last integer in list, because we want to swap last integer with the current integer. So that deletion can happen in O(1) complexity
        list.set(index, lastVal);// replace value at index with last value, now last value is twice in list
        list.remove(list.size()-1); // removed last index
        map.remove(val);
        if(lastVal !=val) {
            map.put(lastVal, index); // we can put this on line 30 to avoid condition. This is done for the case where map has only one integer
        }
        return true;
    }

    public int getRandom() {
//        int randomIndex = (int)(Math.random() * list.size()); // NOTE: Math.random returns a double value between 0.0 to 1.0.
        int randomIndex = random.nextInt(list.size());
        return list.get(randomIndex);
    }
}
