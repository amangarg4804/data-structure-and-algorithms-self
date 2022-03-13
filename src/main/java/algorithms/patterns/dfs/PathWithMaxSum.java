package algorithms.patterns.dfs;

public class PathWithMaxSum {

  static int maxSum = 0;

  public static void main(String[] args) {
    TreeNode root = new TreeNode(1);
    root.left = new TreeNode(2);
    root.right = new TreeNode(3);
    maxSum(root);
    System.out.println(maxSum);
    root.left.left = new TreeNode(1);
    root.left.right = new TreeNode(3);
    root.right.left = new TreeNode(5);
    root.right.right = new TreeNode(6);
    root.right.left.left = new TreeNode(7);
    root.right.left.right = new TreeNode(8);
    root.right.right.left = new TreeNode(9);
    maxSum(root);
    System.out.println(maxSum);
    root= new TreeNode(-1);
    root.left = new TreeNode(-3);
    maxSum(root);
    System.out.println(maxSum);
  }
  private static int maxSum(TreeNode root) {
    maxSum = Integer.MIN_VALUE;
    maxSumRecursive(root);
    return maxSum;
  }

  private static int maxSumRecursive(TreeNode root) {
    if (root == null) {
      return 0;
    }
    int leftSum = maxSumRecursive(root.left);
    int rightSum = maxSumRecursive(root.right);
    leftSum = Math.max(leftSum, 0);
    rightSum = Math.max(rightSum, 0); // If we don't do this, we would be adding negative values on line 41
    int currentSum = leftSum + rightSum + root.value;
    maxSum = Math.max(currentSum, maxSum);
    return Math.max(leftSum, rightSum) + root.value;
  }
}
