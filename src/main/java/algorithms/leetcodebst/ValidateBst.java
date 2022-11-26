package algorithms.leetcodebst;

import java.util.Deque;
import java.util.LinkedList;

public class ValidateBst {


    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        return isValidBST(root, null, null); // pass null for root because input tree could be a single node tree with value Integer_MAX_VALUE or Integer_MIN
        // the other solution is to use Long instead of null
        // For example: if tree contains only one node with value 2147483647 (Integer.MAX_VALUE).
    }

    private boolean isValidBST(TreeNode current, Integer min, Integer max) {
        if(current==null) {
            return true;
        }

        if((max!=null && current.val >= max ) || (min !=null && current.val <= min)) {
            return false;
        }

        return isValidBST(current.left, min, current.val) && isValidBST(current.right, current.val, max);
    }

    private boolean isValidBSTIterative(TreeNode root) {
        // we can use inorder traversal to check for BST. For a valid BST, inorder traversal will be in sorted order
        // NOTE: Because of sorted nature of Inorder traversal,this  pattern can be used to solve multiple problems, e.g, kth smallest element in BST
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode current = root;
        TreeNode previous = null;
        while (current!=null || !stack.isEmpty()) {
            while (current!=null) {
                stack.push(current);
                current=current.left;
            }
            if(stack.isEmpty()) {
                break;
            }
            TreeNode node = stack.pop();
            if(previous!=null && previous.val >=node.val) { // previous node should always be less than node popped from stack(values should be sorted in ascending order)
                return false;
            }
            previous = node;
            if(node.right !=null) {
                current = node.right;
            }
        }
        return true;
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

        TreeNode(TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
