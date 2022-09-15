package algorithms.leetcodetree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class SymmetricTreeIterative {

    public boolean isSymmetric(TreeNode root) {
        Queue<TreeNode> q1 = new LinkedList<>();
        Queue<TreeNode> q2 = new LinkedList<>();
        q1.offer(root.left);
        q2.offer(root.right);
        while (!q1.isEmpty() && !q2.isEmpty()) {
            TreeNode left = q1.poll();
            TreeNode right = q2.poll();
            if(left ==null && right ==null) {
                continue;
            }
            if(left==null || right==null) {
                return false;
            }
            if(left.val != right.val) {
                return false;
            }
            q1.offer(left.left);
            q2.offer(right.right);

            q1.offer(left.right);
            q2.offer(right.left);
        }
        return true;
    }
}
