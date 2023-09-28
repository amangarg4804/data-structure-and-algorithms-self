package algorithms.leetcode;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.PriorityQueue;

public class LfuCacheWithPriority {
    Map<String, CacheEntry> map = new HashMap<>();
    PriorityQueue<CacheEntry> pq = new PriorityQueue<>((e1, e2) -> {
        if (e1.priority == e2.priority) {
            return e1.usedCount - e2.usedCount;
        }
        return e1.priority - e2.priority;
    });
    int capacity;

    public LfuCacheWithPriority(int capacity) {
        this.capacity = capacity;
    }

    public int get(String key) {
        CacheEntry entry = map.get(key + "");
        if (entry == null) {
            return -1;
        }
        pq.remove(entry);
        entry.usedCount++;
        pq.add(entry);
        return entry.value;
    }

    public void put(String key, int value, int priority, long expirationtime) {
        removeExpiredItems();
        CacheEntry entry = map.get(key);
        if (entry == null) {
            entry = new CacheEntry(key, value, priority, 1, expirationtime);
            map.put(key, entry);
            pq.add(entry);
        } else {
            entry.usedCount++;
            entry.value = value;
            entry.priority = priority;
            pq.remove(entry);
            pq.add(entry);
        }
        if (pq.size() > capacity) {
            CacheEntry e = pq.poll();
            map.remove(e.key);
        }
    }

    private void removeExpiredItems() {
        Iterator<CacheEntry> iterator = pq.iterator();
        while (iterator.hasNext()) {
            CacheEntry e = iterator.next();
            if (e.exprirationTime < System.currentTimeMillis()) {
                iterator.remove();
                map.remove(e.key);
            }
        }
    }

}



class CacheEntry {
    String key;
    int value;
    int priority;
    int usedCount;
    long exprirationTime;
    public CacheEntry(String key, int value, int priority, int usedCount, long exprirationTime) {
        this.key = key;
        this.value = value;
        this.priority = priority;
        this.usedCount = usedCount;
        this.exprirationTime = exprirationTime;
    }
}