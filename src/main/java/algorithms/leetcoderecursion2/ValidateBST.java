package algorithms.leetcoderecursion2;

public class ValidateBST {

    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        return isValidBST(root, Long.MAX_VALUE, Long.MIN_VALUE); // use Long instead of Integer for corner case where node value is equal to Integer.MAX_VALUE or INTEGER_MIN value.
                                                            // The code fails on line 17 in such cases because of eqauals opertaor
    }

    public boolean isValidBST(TreeNode root, long max, long min) {
        if (root == null) {
            return true;
        }
        if (root.val >= max || root.val <= min) {
            return false;
        }
        return isValidBST(root.left, Math.min(max, root.val), min) && isValidBST(root.right, max, Math.max(min, root.val)); // Every node should know its range
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
