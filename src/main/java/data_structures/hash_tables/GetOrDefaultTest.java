package data_structures.hash_tables;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class GetOrDefaultTest {

  private static final Map<Long, Set<Long>> map =new ConcurrentHashMap<>();

  public static void main(String[] args) {
    Set<Long> set1 = new HashSet<>();
    set1.add(1L);
    set1.add(2L);
    set1.add(3L);
    map.put(1L, set1);

    Set<Long> set2 = new HashSet<>();
    set2.add(1L);
    set2.add(2L);
    set2.add(3L);
    map.put(2L, set2);

    System.out.println(map);

    Set<Long> getSet1 = map.getOrDefault(1L, new HashSet<>());
    getSet1.add(4L);
    map.put(1L, getSet1 );
    System.out.println(map);

    Set<Long> getSet2 = map.getOrDefault(2L, new HashSet<>());
    getSet2.add(4L);
    map.put(2L, getSet2 );
    System.out.println(map);

    Set<Long> getSet3 = map.getOrDefault(3L, new HashSet<>());
    getSet3.add(1L);
    map.put(3L, getSet3);
    System.out.println(map);

  }

}
