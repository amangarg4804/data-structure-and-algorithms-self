package algorithms.leetcodebst;

import java.util.Deque;
import java.util.LinkedList;

public class InorderSuccessor {

    public TreeNode inorderSuccessor(TreeNode root, TreeNode x) {
        // assuming value of given node x exists in tree
        // Space : O(h), h is height of tree, time O(n)
        TreeNode previous=null;
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode current = root;
        while(current !=null || !stack.isEmpty()) {
            while (current!=null) {
                stack.push(current);
                current =current.left;
            }
            if(stack.isEmpty()) {
                break;
            }

            TreeNode node = stack.pop();
            if(previous !=null && previous.val == x.val) {
                return node;
            }
            previous = node;
            current=node.right;
        }
        return null;
    }

    public TreeNode inorderSuccessorRecursive(TreeNode root, TreeNode x) {
        if(root ==null) {
            return null;
        }
        if(x.val>= root.val) {
            // if x is greater than or equal to root. Successor has to be on right of root
            return inorderSuccessorRecursive(root.right, x);
        } else {
            // if x is less than root, root itself could be successor or the successor would be on the left side of root
            TreeNode left = inorderSuccessorRecursive(root.left, x);
            return left ==null ? root : left;
        }
    }

    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
