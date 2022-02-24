package algorithms.patterns.bfs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

//Given a binary tree, populate an array to represent its level-by-level traversal.
//You should populate the values of all nodes of each level from left to right in separate sub-arrays.
public class PrintNodesLevelByLevel {

  public static void main(String[] args) {
    TreeNode root = new TreeNode(12);
    root.left = new TreeNode(7);
    root.right = new TreeNode(1);
    root.left.left = new TreeNode(9);
    root.right.left = new TreeNode(10);
    root.right.right = new TreeNode(5);
    System.out.println(traverse(root));
  }

  public static List<List<Integer>> traverse(TreeNode root) {
    List<List<Integer>> result = new ArrayList<>();
    if(root==null) {
      return result;
    }
    Queue<TreeNode> queue = new LinkedList<>();
    queue.offer(root);
    while (!queue.isEmpty()) {
      List<Integer> currentLevelList = new ArrayList<>();
      int currentLevelSize = queue.size();
      for (int i = 0; i < currentLevelSize; i++) {
        TreeNode currentNode = queue.poll();
        currentLevelList.add(currentNode.value);
        if (currentNode.left != null) {
          queue.offer(currentNode.left);
        }
        if (currentNode.right != null) {
          queue.offer(currentNode.right);
        }
      }
      result.add(currentLevelList);
    }
    return result;
  }
}
