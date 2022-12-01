package algorithms.leetcodebst;

import java.util.*;

// Time complexity O(1), space O(n) for queue, n is no of nodes in tree
public class BSTIterator {
    Queue<Integer> q = new LinkedList<>();
    public BSTIterator(TreeNode root) {
        //assuming tree has atleast one node
        Stack<TreeNode> stack = new Stack<>();
        TreeNode current = root;
        while(current!=null || !stack.isEmpty()) {
            while (current!=null) {
                stack.push(current);
                current = current.left;
            }
            if(!stack.isEmpty()) {
                TreeNode node = stack.pop();
                q.offer(node.val);
                current = node.right;
            }
        }
    }

    public int next() {
        // assuming next is called on non-empty queue
        return q.poll();
    }

    public boolean hasNext() {
        return !q.isEmpty();
    }

}
