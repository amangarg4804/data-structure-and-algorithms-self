package algorithms.leetcodehashtable;

import java.util.*;

class RandomizedSet {
    Map<Integer, Object> map = new HashMap<>();
    List<Integer> list = new ArrayList<>();
    Random random = new Random();
    private static final Object OBJ = new Object();
    public RandomizedSet() {

    }

    public boolean insert(int val) {
        list.add(val);
        return OBJ != map.put(val,OBJ); // NOTE: a map returns the previous value associated with key, or null if there was no mapping for key
    }

    public boolean remove(int val) {
        list.remove(Integer.valueOf(val));
        return OBJ ==  map.remove(val); // NOTE: a map returns the previous value associated with key, or null if there was no mapping for key
    }

    public int getRandom() {
//        int randomIndex = (int)(Math.random() * list.size()); // NOTE: Math.random returns a double value between 0.0 to 1.0.
        int randomIndex = random.nextInt(list.size());
        return list.get(randomIndex);
    }
}
