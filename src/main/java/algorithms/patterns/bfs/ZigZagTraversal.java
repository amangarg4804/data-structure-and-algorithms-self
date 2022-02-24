package algorithms.patterns.bfs;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class ZigZagTraversal {

  public static void main(String[] args) {
    TreeNode root = new TreeNode(12);
    root.left = new TreeNode(7);
    root.right = new TreeNode(1);
    root.left.left = new TreeNode(9);
    root.right.left = new TreeNode(10);
    root.right.right = new TreeNode(5);
    root.right.left.left = new TreeNode(20);
    root.right.left.right = new TreeNode(17);
    System.out.println(zigZagTraverse(root));
  }

  private static List<List<Integer>> zigZagTraverse(TreeNode root) {
    List<List<Integer>> result = new ArrayList<>();
    if(root ==null) {
      return result;
    }
    Deque<TreeNode> queue = new LinkedList<>();
    queue.add(root);
    boolean leftToRight = true;
    while (!queue.isEmpty()) {
      int currentLevelSize = queue.size();
      List<Integer> currentLevelList = new LinkedList<>();
      for(int i = 0; i< currentLevelSize; i++) {
        TreeNode currentNode = queue.poll();
        if(leftToRight) {
          currentLevelList.add(currentNode.value);
        } else {
          currentLevelList.add(0, currentNode.value);
        }
        if (currentNode.left != null) {
          queue.offer(currentNode.left);
        }
        if (currentNode.right != null) {
          queue.offer(currentNode.right);
        }
      }
      leftToRight = !leftToRight;
      result.add(currentLevelList);
    }
    return result;
  }
}
