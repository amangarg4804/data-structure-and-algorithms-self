package algorithms.leetcodebst;

import java.util.Stack;

// Time complexity of next() is amortized O(1). Whenever a node on top of stack contains right node, we will traverse till we reach the deepest left node of the right node
// Space used is O(h), at any point, stack will contain elements equal to height
public class BSTIterator2 {
    Stack<TreeNode> stack;

    public BSTIterator2(TreeNode root) {
        //assuming tree has at least one node
        stack = new Stack<>();
        TreeNode current = root;
        while (current != null) {
            stack.push(current);
            current = current.left;
        }
    }

    public int next() {
        TreeNode node = stack.pop();
        if (node.right != null) {
            TreeNode current = node.right;
            while (current != null) {
                stack.push(current);
                current = current.left;
            }
        }
        return node.val;
    }

    public boolean hasNext() {
        return !stack.isEmpty();
    }

}
