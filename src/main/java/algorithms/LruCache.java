package algorithms;

import java.util.HashMap;
import java.util.LinkedList;

public class LruCache {

  HashMap<Integer, String> map = new HashMap<>();
  LinkedList<Integer> queue = new LinkedList<>();
  int capacity;

  LruCache(int capacity) {
    this.capacity = capacity;
  }

  void put(int key, String val) {
    if (queue.size() >= capacity) {
      int keyRemoved = queue.removeLast(); // This looks wrong, if key already existed in map, there should be no need to remove last cache entry. In this case, cache should not remove any entry, simply update the existing entry
      map.remove(keyRemoved);
    }
    queue.addFirst(key);
    map.put(key, val);
  }

  String get(int key) {
    String result = map.get(key);
    if(result != null) {
      queue.remove((Object)key);
      queue.addFirst(key);
    }
    return result;

  }

  public void display() {
    for (int i = 0; i < queue.size(); i++) {
      int key = queue.get(i);
      System.out.println(key + " => " + map.get(key));
    }
  }

  public static void main(String[] args) {
    LruCache cache = new LruCache(3);
    cache.put(1, "one");
    cache.put(2, "two");
    cache.put(3, "three");
    cache.put(4, "four"); //cache full. 1 will be removed
    cache.get(3); // accessing 3, it will be moved to top
    cache.get(2);// accessing 2, it will be moved to top
    cache.put(1, "one"); // putting new cache when full. 4 will be removed
    cache.get(3);// accessing 3, it will be moved to top
    cache.get(1);// accessing 1, it will be moved to top
    cache.display(); // 1 should be on top
    /*
     * output
     * 1 => one
     * 3 => three
     * 2 => two
     *
     **/
  }

}