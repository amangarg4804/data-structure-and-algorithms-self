package algorithms.leetcodebst;


public class SearchBSTIterative {

    public TreeNode searchBST(TreeNode root, int val) {
        while (root!=null) {
            if(root.val == val) {
                return root;
            }
            if(val < root.val) {
                root =root.left;
            } else {
                root = root.right;
            }
        }
        return null;
    }

     public class TreeNode {
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
}
