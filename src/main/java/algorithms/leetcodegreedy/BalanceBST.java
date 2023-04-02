package algorithms.leetcodegreedy;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

//Given the root of a binary search tree, return a balanced binary search tree with the same node values. If there is more than one answer, return any of them.
//
//A binary search tree is balanced if the depth of the two subtrees of every node never differs by more than 1.
public class BalanceBST {
    public TreeNode balanceBST(TreeNode root) {
        //since it is a binary search tree, inorder traversal will give us sorted array
        // we can store all sorted integer values in a list
        // then the problem reduces to : given a sorted integer array create a BST

        List<Integer> sortedNodeValues =inorderTraversal(root);
        //System.out.println(sortedNodeValues);
        // prepare a new tree, we can provide start index and end index of the list to the method so it can calculate middle.
        // Middle should be root of the tree if we want the tree to be balanced
        return prepareBalancedBST(sortedNodeValues, 0, sortedNodeValues.size()-1);
    }

    private TreeNode prepareBalancedBST( List<Integer> sortedNodeValues, int start, int end) {
        // 20, 21, 22, 25
        //22,25-> tree with two values start =0, end =1
        if(start > end) {
            return null;
        }
        int middle = (start + end)/2;
        TreeNode root = new TreeNode(sortedNodeValues.get(middle));
        root.left = prepareBalancedBST(sortedNodeValues, start, middle -1); // left of 21, start remains same, end becomes middle -1
        root.right = prepareBalancedBST(sortedNodeValues, middle+1, end); // right of 21, start becomes middle +1, end remains same,
        // with the above two calls, it can happen that start becomes greater than end, so we add a check at the start of the method to return null in that case
        return root; // root is 21
    }


    private List<Integer> inorderTraversal(TreeNode root) {
        // inorder is left root right
        // we have to go to the left most, we can use stack

        //        25
        //       /
        //      20
        //        \
        //          22
        //          /
        //        21
        List<Integer> result = new ArrayList<>();
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode current = root;
        while (current!=null || !stack.isEmpty()) {
            while (current!=null) {
                stack.push(current);
                current = current.left;
            }
            TreeNode node = stack.pop(); //reached 20, now check right
            result.add(node.val);
            if(node.right!=null) {
                current= node.right;
            }
        }
        return result;
    }
}

  class TreeNode {
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