package algorithms.leetcoderecursion2;

import algorithms.leetcodetree.InorderTraversal;
import algorithms.leetcodetree.TreeNode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class InorderTraversalIterative {

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode current = root;
        while (true)  {
            if(current!=null) {
                stack.push(current);
                current = current.left;
            } else {
                if(stack.isEmpty()) {
                    break;
                }
                TreeNode node = stack.pop();
                result.add(node.val);
                if(node.right!=null) {
                    current = current.right;
                }
            }
        }
        return result;
    }

    public class TreeNode {
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
