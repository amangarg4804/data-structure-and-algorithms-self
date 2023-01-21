package algorithms.leetcodehashtable;

public class MyHashSet {
    MyHashMap map;

    public MyHashSet() {
        map = new MyHashMap();
    }

    public void add(int key) {
        map.put(key, 1);// value can always be one because we care about keys only in hashset
    }

    public void remove(int key) {
        map.remove(key);
    }

    public boolean contains(int key) {
        int value = map.get(key);
        return value !=-1;
    }
}
