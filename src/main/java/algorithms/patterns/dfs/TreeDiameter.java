package algorithms.patterns.dfs;

// Given a binary tree, find the length of its diameter.
// The diameter of a tree is the number of nodes on the longest path between any two leaf nodes.
// The diameter of a tree may or may not pass through the root.
//Note: You can always assume that there are at least two leaf nodes in the given tree.


public class TreeDiameter {

  private static int diameter = 0;

  public static void main(String[] args) {
    TreeNode root = new TreeNode(1);
    root.left = new TreeNode(2);
    root.right = new TreeNode(3);
    root.left.left = new TreeNode(4);
    root.right.left = new TreeNode(5);
    root.right.right = new TreeNode(6);
    height(root);
    System.out.println(diameter);
    root.left.left = null;
    root.right.left.left = new TreeNode(7);
    root.right.left.right = new TreeNode(8);
    root.right.right.left = new TreeNode(9);
    root.right.left.right.left = new TreeNode(10);
    root.right.right.left.left = new TreeNode(11);
    height(root);
    System.out.println(diameter);

  }

  public static int height(TreeNode root) {
    if (root == null) {
      return 0; // Some resources (MIT lectures) suggest that the height of a leaf node is 0 which means height of null is -1, they count the edges. Here we have counted nodes
    }
    int leftHeight = height(root.left);
    int rightHeight = height(root.right);
    int currentDiameter = leftHeight + rightHeight + 1;
    diameter = Math.max(currentDiameter, diameter);
    return Math.max(rightHeight, leftHeight) + 1;
  }
}
