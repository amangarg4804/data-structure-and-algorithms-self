package algorithms.patterns.bfs;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class RightView {

  public static void main(String[] args) {
    TreeNode root = new TreeNode(12);
    root.left = new TreeNode(7);
    root.right = new TreeNode(1);
    root.left.left = new TreeNode(9);
    root.right.left = new TreeNode(10);
    root.right.right = new TreeNode(5);
    root.left.left.left = new TreeNode(3);
    System.out.println(rightView(root));
  }

  public static List<TreeNode> rightView(TreeNode root) {
    List<TreeNode> list = new ArrayList<>();
    if(root == null) {
      return list;
    }
    Deque<TreeNode> queue = new LinkedList<>();
    queue.offer(root);
    while (!queue.isEmpty()) {
      int currentLevelSize = queue.size();
      TreeNode rightMost = null;
      for(int i = 0; i < currentLevelSize; i++) {
        TreeNode current = queue.poll();
        rightMost = current;
        // we can also:
        // if(i == currentLevel-1) {list.add(current)}
        if(current.left != null) {
          queue.add(current.left);
        }
        if(current.right != null) {
          queue.add(current.right);
        }
      }
      list.add(rightMost);
    }
    return list;
  }
}
