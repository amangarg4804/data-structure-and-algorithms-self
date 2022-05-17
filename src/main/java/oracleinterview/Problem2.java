package oracleinterview;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public class Problem2 {

  /// nth largest string from an array of non-null strings
  public static void main(String[] args) {
    // "abc" "abcd" "abcde" "abcdf"
    System.out.println(nthLargest(new String[]{"abc", "abcd", "a", "abcde", "abcdf", "", "" }, 5));
  }


  public static List<String> nthLargest (String[] inputs, int n) {
    if(n > inputs.length) {
      return Collections.emptyList();
    }
    Map<Integer, List<String>> map = new TreeMap<>( Comparator.reverseOrder());

    for(String input : inputs) {
      if(!map.containsKey(input.length())) {
        List<String> list = new ArrayList<>();
        list.add(input);
        map.put(input.length(), list);
      } else {
        map.get(input.length()).add(input);
      }
    }

    int count = 1;

    for(Entry<Integer, List<String>> entry : map.entrySet()) {
      if(count ==n) {
        return entry.getValue();
      }
      count++;
    }
    return Collections.emptyList();
  }
}
