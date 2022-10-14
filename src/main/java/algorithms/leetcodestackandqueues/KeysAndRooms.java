package algorithms.leetcodestackandqueues;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class KeysAndRooms {

    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        boolean[] visited = new boolean[rooms.size()];
        Queue<Integer> q = new LinkedList<>();
        q.offer(0);
        visited[0] = true;
        while (!q.isEmpty()) {
            int index = q.poll();
            List<Integer> visitableRooms  = rooms.get(index);

            for(Integer roomIndex : visitableRooms) {
                if(!visited[roomIndex]) {
                    q.offer(roomIndex);
                    visited[roomIndex] = true;
                }
            }
        }

        for (boolean isVisited : visited) {
            if(!isVisited) {
                return false;
            }
        }
        return true;
    }
}
