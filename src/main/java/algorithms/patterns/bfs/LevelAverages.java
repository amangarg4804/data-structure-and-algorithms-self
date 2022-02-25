package algorithms.patterns.bfs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LevelAverages {

  public static void main(String[] args) {
    TreeNode root = new TreeNode(12);
    root.left = new TreeNode(7);
    root.right = new TreeNode(1);
    root.left.left = new TreeNode(9);
    root.left.right = new TreeNode(2);
    root.right.left = new TreeNode(10);
    root.right.right = new TreeNode(5);
    System.out.println(levelAverages(root));
  }

  private static List<Double> levelAverages(TreeNode root) {

    List<Double> result = new ArrayList<>();
    if(root ==null) {
      return result;
    }
    Queue<TreeNode> queue = new LinkedList<>();
    queue.add(root);
    while (!queue.isEmpty()) {
      int currentLevelSize = queue.size();
      double sum = 0;
      for(int i=0 ; i< currentLevelSize ; i++) {
        TreeNode currentNode = queue.poll();
        sum += currentNode.value;
        if (currentNode.left != null) {
          queue.offer(currentNode.left);
        }
        if (currentNode.right != null) {
          queue.offer(currentNode.right);
        }
      }
      result.add(sum/currentLevelSize);
    }
    return result;
  }
}
