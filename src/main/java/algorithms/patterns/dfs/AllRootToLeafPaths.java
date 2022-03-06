package algorithms.patterns.dfs;

import java.util.ArrayList;
import java.util.List;

public class AllRootToLeafPaths {

  public static void main(String[] args) {
    TreeNode root = new TreeNode(12);
    root.left = new TreeNode(7);
    root.right = new TreeNode(1);
    root.left.left = new TreeNode(4);
    root.right.left = new TreeNode(10);
    root.right.right = new TreeNode(5);
    System.out.println(findPaths(root));
  }

  private static List<List<Integer>> findPaths(TreeNode root) {
    List<List<Integer>> allPaths = new ArrayList<>();
    List<Integer> currentPath = new ArrayList<>();
    findPaths(root, allPaths, currentPath);
    return allPaths;
  }

  private static void findPaths(TreeNode root, List<List<Integer>> allPaths, List<Integer> currentPath) {
    if(root==null) {
      return;
    }
    currentPath.add(root.value);
    if(root.left == null && root.right ==null) {
      allPaths.add(new ArrayList<>(currentPath));
    } else {
      findPaths(root.left, allPaths, currentPath);
      findPaths(root.right, allPaths, currentPath);
    }
    currentPath.remove(currentPath.size() -1);
  }
}
