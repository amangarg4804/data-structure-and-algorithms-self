package data_structures.hash_tables;

import java.util.HashMap;
import java.util.Map;

public class MyHashSet<T> {

    private static final Object INSTANCE = new Object();
    private Map<T, Object> map = new HashMap<>();

    public boolean add(T t) {
        return map.put(t, INSTANCE) == null;
    }

    public boolean contains(T t) {
        return map.containsKey(t);
    }

    public int size() {
        return map.size();
    }

    public static void main(String[] args) {
        MyHashSet<String> myHashSet = new MyHashSet<>();
        System.out.println(myHashSet.add("abc")) ;
        System.out.println(myHashSet.size()) ;
        System.out.println(myHashSet.add("def")) ;
        System.out.println(myHashSet.size()) ;
        System.out.println(myHashSet.add("abc")) ;
        System.out.println(myHashSet.size()) ;
        System.out.println(myHashSet.contains("def")) ;
    }
}
