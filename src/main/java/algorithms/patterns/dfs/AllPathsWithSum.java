package algorithms.patterns.dfs;

import java.util.ArrayList;
import java.util.List;

public class AllPathsWithSum {

  public static void main(String[] args) {
    TreeNode root = new TreeNode(12);
    root.left = new TreeNode(7);
    root.right = new TreeNode(1);
    root.left.left = new TreeNode(4);
    root.right.left = new TreeNode(10);
    root.right.right = new TreeNode(5);
    System.out.println(findPaths(root, 23));
  }

  public static List<List<Integer>> findPaths(TreeNode root, int sum) {
    List<List<Integer>> allPaths = new ArrayList<>();
    List<Integer> currentPath = new ArrayList<>();
    findPaths(root, sum, allPaths, currentPath);
    return allPaths;
  }

  public static void findPaths(TreeNode root, int sum, List<List<Integer>> allPaths, List<Integer> currentPath) {
    if(root ==null) {
      return;
    }
    currentPath.add(root.value);
    if(root.left ==null && root.right==null && root.value == sum) {
      allPaths.add(new ArrayList<>(currentPath));
    } else {
      findPaths(root.left, sum - root.value, allPaths, currentPath);
      findPaths(root.right, sum - root.value, allPaths, currentPath);
    }
    currentPath.remove(currentPath.size()-1);
  }


}
