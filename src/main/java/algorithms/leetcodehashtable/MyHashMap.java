package algorithms.leetcodehashtable;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class MyHashMap {

    Bucket[] buckets = new Bucket[1000];

    public MyHashMap() {

    }

    public void put(int key, int value) {
        int hash = hash(key);
        Bucket bucket = buckets[hash];
        if(bucket==null) {
            bucket = new Bucket();
            buckets[hash] =  bucket;
        }
        for(Entry entry : bucket.entries) {
            if(entry.key ==key) {
                entry.value = value;
                return;
            }
        }
        Entry entry = new Entry();
        entry.key = key;
        entry.value = value;
        bucket.entries.add(entry);
    }

    public int get(int key) {
        int hash = hash(key);
        Bucket bucket = buckets[hash];
        if(bucket==null) {
            return -1;
        }
        for(Entry entry : bucket.entries) {
            if(entry.key ==key) {
                return entry.value;
            }
        }
        return -1;
    }

    public void remove(int key) {
        int hash = hash(key);
        Bucket bucket = buckets[hash];
        if(bucket==null) {
            return;
        }
        Iterator<Entry> iterator = bucket.entries.iterator();
        while (iterator.hasNext()) { // if not allowed to use built in LinkedList, we can define ListNode with key, value and next and use next to iterate over
            Entry entry = iterator.next();
            if(entry.key ==key) {
                iterator.remove();
                break;
            }
        }
    }

    private int hash(int num) {
//        return num%999;
        return num % (buckets.length-1);
    } // 0 to 999, index of list is 0 based

    private class Bucket {
        LinkedList<Entry> entries = new LinkedList<>();

    }
    private class Entry {
        int key, value;
    }
}
