package algorithms.patterns.subset;

import java.util.ArrayList;
import java.util.List;

public class UniqueBst {

  public static void main(String[] args) {
    System.out.println(uniqueTrees(2).size());
    System.out.println(findUniqueTrees(2));
    System.out.println(findUniqueTrees(3));
  }

  public static List<TreeNode> uniqueTrees(int n) {
    if(n<1) {
      return new ArrayList<>();
    }

    return findUniqueTrees(1, n);

  }
  public static List<TreeNode> findUniqueTrees(int start, int end) {
    List<TreeNode> result = new ArrayList<>();
    if(start > end) {
      result.add(null);
    }

    for(int i=start; i<=end ; i++) {
      List<TreeNode> leftSubtrees = findUniqueTrees(start, i-1);
      List<TreeNode> rightSubTrees = findUniqueTrees(i+1, end);
      for(TreeNode left : leftSubtrees) {
        for(TreeNode right : rightSubTrees) {
          TreeNode treeNode = new TreeNode(i);
          treeNode.left = left;
          treeNode.right = right;
          result.add(treeNode);
        }
      }
    }
    return result;
  }

  public static int findUniqueTrees(int n) {
    // catalan number
    int[] dp = new int[n+1];
    dp[0] =1;
    dp[1] =1;
    for(int i= 2; i<=n; i++) {
      int left = 0;
      int right = i-1; //because i is root
      while(left <= i-1) {
        dp[i] += dp[left]* dp[right];
        left++;
        right--;
      }
    }
    return dp[n];
  }
}


class TreeNode {
   int value;
   TreeNode left;
   TreeNode right;

  public TreeNode(int value) {
    this.value = value;
  }

  public int getValue() {
    return value;
  }

  public void setValue(int value) {
    this.value = value;
  }

  public TreeNode getLeft() {
    return left;
  }

  public void setLeft(TreeNode left) {
    this.left = left;
  }

  public TreeNode getRight() {
    return right;
  }

  public void setRight(TreeNode right) {
    this.right = right;
  }
}