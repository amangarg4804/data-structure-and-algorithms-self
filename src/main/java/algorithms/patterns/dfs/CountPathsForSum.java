package algorithms.patterns.dfs;
// Given a binary tree and a number ‘S’, find all paths in the tree such that the sum of all the node values of each path equals ‘S’.
// Please note that the paths can start or end at any node but all paths must follow direction from parent to child (top to bottom).

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class CountPathsForSum {

  public static void main(String[] args) {
    TreeNode root = new TreeNode(12);
    root.left = new TreeNode(7);
    root.right = new TreeNode(1);
    root.left.left = new TreeNode(4);
    root.right.left = new TreeNode(10);
    root.right.right = new TreeNode(5);
    System.out.println(countPaths(root, 11));
  }

  public static int countPaths(TreeNode root, int sum) {
    List<Integer> list = new LinkedList<>();
    return countPaths(root, sum, list);
  }

  private static int countPaths(TreeNode root, int sum, List<Integer> list) {
    if (root == null) {
      return 0;
    }
    list.add(root.value);
    ListIterator<Integer> iterator = list.listIterator(list.size());
    int pathSum = 0;
    int pathCount = 0;
    while (iterator.hasPrevious()) {
      pathSum += iterator.previous();
      if (pathSum == sum) {
        pathCount++;
      }
    }
    pathCount += countPaths(root.left, sum, list);
    pathCount += countPaths(root.right, sum, list);
    list.remove(list.size() - 1);
    return pathCount;
  }

}
