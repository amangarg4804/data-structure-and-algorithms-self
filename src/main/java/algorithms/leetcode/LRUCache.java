package algorithms.leetcode;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class LRUCache { // beats 5%
    Map<Integer, Integer> map = new HashMap<>();
    Deque<Integer> q = new LinkedList<>();
    int capacity;
    public LRUCache(int capacity) {
        this.capacity = capacity;
    }

    public int get(int key) {
        if(!map.containsKey(key)) {
            return -1;
        }
        int val = map.get(key);
        q.remove(key); // NOTE: remove operation in Deque interface takes on Object by default which is different from LinkedList remove method.
        // Linked list remove method is overloaded, the remove(int ) operation takes index
        // while remove (Object ) operation takes input as object and requires that we cast the input using (Object)
        // this remove operation has time complexity of O(n). A better way to implement LRU cache is to not use built in Deque but use our own Deque
        q.addFirst(key);
        return val;
    }

    public void put(int key, int value) {
        if(map.containsKey(key)) {
            q.remove(key);
            map.remove(key);
        }
        if(map.size() == capacity) {
            Integer lastKey = q.removeLast();
            map.remove(lastKey);
        }
        q.addFirst(key);
        map.put(key, value);
    }

}
