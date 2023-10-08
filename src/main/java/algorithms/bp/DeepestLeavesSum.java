package algorithms.bp;

import java.util.LinkedList;
import java.util.Queue;

public class DeepestLeavesSum {
    public int deepestLeavesSum(TreeNode root) {
        // We can use BFS. Keep a sum variable, initialize it every level and calculate sum

        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        int sum = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            sum =0;
            for (int i = 0; i < size; i++) {
                TreeNode node = q.poll();
                sum += node.val;
                if (node.left!= null) {
                    q.add(node.left);
                }
                if (node.right!= null) {
                    q.add(node.right);
                }
            }

        }
        return sum;
        // Time O(N) - BFS- visiting all nodes once
        //Space O(N)- q
    }

    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {
        }
        TreeNode(int val) {
            this.val = val;
        }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
