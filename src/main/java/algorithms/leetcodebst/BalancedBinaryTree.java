package algorithms.leetcodebst;


public class BalancedBinaryTree {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.left.left = new TreeNode(4);
        System.out.println(new BalancedBinaryTree().isBalanced(root));
    }

    public boolean isBalanced(TreeNode root) {
        return calculateHeight1(root) != -1;
    }

    private int calculateHeight(TreeNode root) {
        if(root==null) {
            return 0;
        }
        int leftHeight = calculateHeight(root.left);
        int rightHeight = calculateHeight(root.right);
        if(leftHeight ==-1 || rightHeight ==-1) {
            return -1;
        }
        if(Math.abs(leftHeight-rightHeight) > 1) {
            return -1;
        }
        return Math.max(leftHeight, rightHeight) + 1;
    }

    private int calculateHeight1(TreeNode root) {
        if(root==null) {
            return 0;
        }
        int leftHeight = calculateHeight1(root.left) +1;
        int rightHeight = calculateHeight1(root.right) +1;
        if(leftHeight ==0 || rightHeight ==0) { // notice we are checking for 0 instead of -1 in this method because we add 1 to heights at line 36, 37
            return -1;
        }
        if(Math.abs(leftHeight-rightHeight) > 1) {
            return -1;
        }
        return Math.max(leftHeight, rightHeight);
    }
}
