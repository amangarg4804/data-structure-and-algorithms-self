package algorithms.leetcodetree;

public class LowestCommonAncestor {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root==null) {
            return null;
        }
        if(root.val == p.val || root.val ==q.val) { // given both of them are present in tree or none of them
            return root;
        }
        // keep checking for left and right result.
        // If one of them is null and the other is not, return the non-null.
        // If we found a non-null value in either left or right. that's our answer
        TreeNode leftResult = lowestCommonAncestor(root.left, p, q);
        TreeNode rightResult = lowestCommonAncestor(root.right, p, q);

        if(leftResult==null) {
            return rightResult;
        } else if(rightResult ==null) {
            return leftResult;
        }
        return root;
        //Complexity Analysis
        //
        //Time Complexity: O(N), where N is the number of nodes in the binary tree. In the worst case we might be visiting all the nodes of the binary tree.
        //
        //Space Complexity: O(N).
        // This is because the maximum amount of space utilized by the recursion stack would be NNN since the height of a skewed binary tree could be NNN.
    }
}
