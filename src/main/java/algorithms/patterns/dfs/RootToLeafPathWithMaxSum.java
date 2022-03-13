package algorithms.patterns.dfs;

import java.util.ArrayList;
import java.util.List;

public class RootToLeafPathWithMaxSum {

  static int maxSum = 0;

  public static void main(String[] args) {
    TreeNode root = new TreeNode(12);
    root.left = new TreeNode(7);
    root.right = new TreeNode(1);
    root.left.left = new TreeNode(4);
    root.right.left = new TreeNode(10);
    root.right.right = new TreeNode(5);
    System.out.println(pathWithMaxSum(root));
  }

  private static List<Integer> pathWithMaxSum(TreeNode root) {
    List<Integer> currentPath = new ArrayList<>();
    List<Integer> resultPath = new ArrayList<>();
    pathWithMaxSum(root, currentPath, resultPath, 0);
    return resultPath;
  }

  private static void pathWithMaxSum(TreeNode root, List<Integer> currentPath,
      List<Integer> resultPath, int currentSum) {
    if (root == null) {
      return;
    }
    currentPath.add(root.value);
    currentSum += root.value;
    if (root.left == null && root.right == null && currentSum > maxSum) {
      resultPath.clear();
      resultPath.addAll(currentPath);
      maxSum = currentSum;
    } else {
      pathWithMaxSum(root.left, currentPath, resultPath, currentSum);
      pathWithMaxSum(root.right, currentPath, resultPath, currentSum);
    }
    currentPath.remove(currentPath.size() - 1);
  }
}
