package algorithms.patterns.dfs;

public class PathSum {

  public static void main(String[] args) {
    TreeNode root = new TreeNode(12);
    root.left = new TreeNode(7);
    root.right = new TreeNode(1);
    root.left.left = new TreeNode(9);
    root.right.left = new TreeNode(10);
    root.right.right = new TreeNode(5);
    System.out.println(hasPathWithSum(root, 23));
    System.out.println(hasPathWithSum(root, 16));
  }

  public static boolean hasPathWithSum(TreeNode root, int sum) {
    if (root == null) {
      return false;
    }
    if (root.left == null && root.right == null && root.value == sum) {
      return true;
    }
    return hasPathWithSum(root.left, sum - root.value) || hasPathWithSum(root.right,
        sum - root.value);
  }
}
