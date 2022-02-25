package algorithms.patterns.bfs;

import java.util.LinkedList;
import java.util.Queue;

public class MinimumDepth {

  public static void main(String[] args) {
    TreeNode root = new TreeNode(12);
    root.left = new TreeNode(7);
    root.right = new TreeNode(1);
    root.right.left = new TreeNode(10);
    root.right.right = new TreeNode(5);
    System.out.println(minDepth(root));
    root.left.left = new TreeNode(9);
    root.right.left.left = new TreeNode(11);
    System.out.println(minDepth(root));
  }

  private static int minDepth(TreeNode root) {
    int minDepth = 0;
    if (root == null) {
      return minDepth;
    }
    Queue<TreeNode> queue = new LinkedList<>();
    queue.add(root);
    while (!queue.isEmpty()) {
      int currentLevelSize = queue.size();
      minDepth++;
      for (int i = 0; i < currentLevelSize; i++) {
        TreeNode currentNode = queue.poll();
        if (currentNode.left == null && currentNode.right == null) {
          return minDepth;
        }
        if (currentNode.left != null) {
          queue.offer(currentNode.left);
        }
        if (currentNode.right != null) {
          queue.offer(currentNode.right);
        }
      }
    }

    return minDepth;
  }
}
