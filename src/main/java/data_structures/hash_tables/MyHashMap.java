package data_structures.hash_tables;

import java.util.HashSet;
import java.util.Set;

public class MyHashMap {

    private Bucket[] buckets;

    private int noOfBuckets;

    MyHashMap(int noOfBuckets) {
        this.noOfBuckets = noOfBuckets;
        buckets = new Bucket[noOfBuckets];
    }

    MyHashMap() {
        this.noOfBuckets = 16;
        buckets = new Bucket[noOfBuckets];
    }

    public static void main(String[] args) {
        MyHashMap myHashMap = new MyHashMap();
        myHashMap.put("key1", "value1");
        myHashMap.put("key2", "value2");
        myHashMap.put("key3", "value3");
        myHashMap.put("key3", "value3Modified");

        System.out.println(myHashMap.get("key1"));
        System.out.println(myHashMap.get("key2"));
        System.out.println(myHashMap.get("key3"));
        System.out.println(myHashMap.keySet());
    }

    private int hash(String key) {
        return key.hashCode() % noOfBuckets;
    }

    public void put(String key, String value) {
        int bucketNo = hash(key);
        if (buckets[bucketNo] == null) {
            Bucket bucket = new Bucket();
            buckets[bucketNo] = bucket;
        }
        for (MyEntry myEntry : buckets[bucketNo]) {
            if (myEntry.getKey().equals(key)) {
                myEntry.setValue(value);
                return;
            }
        }

        buckets[bucketNo].add(new MyEntry(key, value));
    }

    public Set<String> keySet() {
        Set<String> keySet = new HashSet<>();
        for (Bucket bucket : buckets) {
            if (bucket != null) {
                for (MyEntry myEntry : bucket) {
                    keySet.add(myEntry.getKey());
                }
            }
        }
        return keySet;
    }

    public String get(String key) {
        int bucketNo = hash(key);
        if (buckets[bucketNo] == null) {
            return null;
        }
        for (MyEntry myEntry : buckets[bucketNo]) {
            if (myEntry.getKey().equals(key)) {
                return myEntry.getValue();
            }
        }
        return null;
    }
}
