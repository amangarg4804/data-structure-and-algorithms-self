package algorithms.leetcoderecursion2;

public class ValidateBST {

    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        return isValidBST(root, Long.MAX_VALUE, Long.MIN_VALUE); // use Long instead of Integer for corner case
                                                            // For example: if tree contains only one node with value 2147483647 (Integer.MAX_VALUE).
        // Due to condition on code on line 18, method will return false, but it is a valid BST. Same for MIN_VALUE
    }

    public boolean isValidBST(TreeNode root, long max, long min) {
        if (root == null) {
            return true;
        }
        if (root.val >= max || root.val <= min) {
            return false;
        }
        return isValidBST(root.left, root.val, min) && isValidBST(root.right, max,root.val); // // Every node should know its range
       // return isValidBST(root.left, Math.min(max, root.val), min) && isValidBST(root.right, max, Math.max(min, root.val)); Math.min and Math.max are unnecessary. Long is used only for the case where treenode has only one node with value Integer.MAX_VALUE or Integer.MIN_VALUE
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
