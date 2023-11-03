package algorithms.interviews.apollointerview;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class StringsOnlyOnceInList {
    public static void main(String[] args) {
        List<String> l1= new ArrayList<>();
        l1.add("list");
        l1.add("of");
        l1.add("strings");
        List<String> l2 = new ArrayList<>();
        l2.add("strings");
        l2.add("cool");
        l2.add("cool");
        System.out.println(unique(l1, l2));
    }


    private static List<String> unique(List<String> l1, List<String> l2) {
        if(l1.isEmpty()) {
            return l2;
        }
        if(l2.isEmpty()) {
            return l1;
        }
        Set<String> s1 = new HashSet<>();
        for(String s : l1) {
            s1.add(s);
        }
        Set<String> s2 = new HashSet<>();
        for(String s : l2) {
                s2.add(s);
        }
        List<String> result = new ArrayList<>();
        for(String s: s1) {
            if(!s2.contains(s)) {
                result.add(s);
            }
        }
        for(String s: s2) {
            if(!s1.contains(s)) {
                result.add(s);
            }
        }
        return result;
    }
}
