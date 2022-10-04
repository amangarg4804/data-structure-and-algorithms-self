package algorithms.leetcodestackandqueues;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class OpenLock {

    public int openLock(String[] deadends, String target) {
        Set<String> deadendSet = new HashSet<>();
        for(String deadend: deadends) {
            deadendSet.add(deadend);
        }
        Set<String> visited = new HashSet<>();
        Queue<String> q = new LinkedList<>();
        q.offer("0000");
        visited.add("0000");
        int level = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            for(int i=0; i< size;i++) {
                String s = q.poll();
                if(target.equals(s)) {
                    return level;
                }
                if(deadendSet.contains(s)) {
                    continue;
                }
                for(int j=0; j<4 ;j++) {
                    char c = s.charAt(j);
                    String plus = s.substring(0, j) + (c-'0'==9? 0: c-'0'+1) + s.substring(j+1);
                    String minus = s.substring(0, j) + (c-'0'==0? 9: c-'0'-1) + s.substring(j+1);
                    if(!deadendSet.contains(plus) && !visited.contains(plus) ) {
                        q.offer(plus);
                    }
                    if(!deadendSet.contains(minus) && !visited.contains(minus) ) {
                        q.offer(minus);
                    }
                    visited.add(plus);
                    visited.add(minus);
                }
            }
            level++;
        }
        return -1;
    }
}
