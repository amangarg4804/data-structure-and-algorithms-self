package algorithms.leetcodetree;

public class SymmetricTreeRecursive {

    private boolean result;
    public boolean isSymmetric(TreeNode root) {
        result = true;
        isSymmetric(root.left, root.right);
        return result;
    }

    private void isSymmetric(TreeNode left, TreeNode right) {
        if(!result) {
            return;
        }
        if(left ==null && right ==null) {
            return;
        }
        if(left==null || right ==null) {
            result=false;
            return;
        }
        if(left.val != right.val) {
            result=false;
        }

        isSymmetric(left.left, right.right);
        isSymmetric(left.right, right.left);

    }


    public boolean isSymmetricCleaner(TreeNode root) {
        return isSymmetricCleaner(root, root);
    }

    private boolean isSymmetricCleaner(TreeNode left, TreeNode right) {
        if(left ==null && right ==null) {
            return true;
        }
        if(left ==null || right ==null) {
            return false;
        }
        return left.val ==right.val
                && isSymmetricCleaner(left.left, right.right)
                && isSymmetricCleaner(left.right, right.left);
    }
}
