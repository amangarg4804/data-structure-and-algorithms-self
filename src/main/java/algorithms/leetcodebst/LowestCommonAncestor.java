package algorithms.leetcodebst;

public class LowestCommonAncestor {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // if both p and q are less than root, then lca must be on left
        if(p.val < root.val && q.val < root.val) {
            return lowestCommonAncestor(root.left, p, q);
        }
        if(p.val > root.val && q.val > root.val) {
            return lowestCommonAncestor(root.right, p, q);
        }
        return root; // base case - when we have established that both p and q are not greater/less than root, then root must be the LCA, in this case, either p or 1 will be equal to root.
        // Another possibility is  that p and q are of either side of root
        //Time Complexity: O(N), where N is the number of nodes in the BST. In the worst case we might be visiting all the nodes of the BST.
        //Space Complexity: O(N).
        // This is because the maximum amount of space utilized by the recursion stack would be NNN since the height of a skewed BST could be NNN.
    }

    public TreeNode lowestCommonAncestorIterative(TreeNode root, TreeNode p, TreeNode q) {
        while (root!=null) {
            if(p.val < root.val && q.val < root.val) {
                root = root.left;
            } else if(p.val > root.val && q.val > root.val){
                root = root.right;
            } else {
                break;
            }
        }
        return root;
        //Time Complexity : O(N), where NNN is the number of nodes in the BST. In the worst case we might be visiting all the nodes of the BST.
        //Space Complexity : O(1).
    }


}
