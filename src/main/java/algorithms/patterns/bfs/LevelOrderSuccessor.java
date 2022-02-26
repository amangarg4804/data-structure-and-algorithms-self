package algorithms.patterns.bfs;

import java.util.LinkedList;
import java.util.Queue;

public class LevelOrderSuccessor {

  public static void main(String[] args) {
    TreeNode root = new TreeNode(12);
    root.left = new TreeNode(7);
    root.right = new TreeNode(1);
    root.left.left = new TreeNode(9);
    root.right.left = new TreeNode(10);
    root.right.right = new TreeNode(5);
    System.out.println(levelOrderSuccessor(root, 12));
    System.out.println(levelOrderSuccessor(root, 9));
  }

  public static int levelOrderSuccessor(TreeNode root, int key) {
    if(root ==null) {
      return -1;
    }
    Queue<TreeNode> queue = new LinkedList<>();
    queue.offer(root);
    boolean found = false;
    while (!queue.isEmpty()) {
      TreeNode currentNode = queue.poll();
      if(found) {
        return currentNode.value;
      }
      if(key == currentNode.value) {
        found = true;
      }
      if (currentNode.left != null) {
        queue.offer(currentNode.left);
      }
      if (currentNode.right != null) {
        queue.offer(currentNode.right);
      }

    }
    return -1;
  }
}
