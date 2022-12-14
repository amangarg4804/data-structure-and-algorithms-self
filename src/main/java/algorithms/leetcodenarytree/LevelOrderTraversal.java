package algorithms.leetcodenarytree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LevelOrderTraversal {

    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> result = new ArrayList<>();
        if(root ==null) {
            return result;
        }
        Queue<Node> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            int size = q.size();
            List<Integer> currentLevelList = new ArrayList<>();
            for(int i =0; i< size;i++) {
                Node current = q.poll();
                for(Node child: current.children) { // could also do queue.addAll(current.children)
                    q.offer(child);
                }
                currentLevelList.add(current.val);
            }
            result.add(currentLevelList);
        }
        return result;
    }
}
