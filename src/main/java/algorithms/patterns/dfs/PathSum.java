package algorithms.patterns.dfs;

import java.util.Stack;

// Given a binary tree and a number ‘S’,
// find if the tree has a path from root-to-leaf such that the sum of all the node values of that path equals ‘S’.
public class PathSum {

  public static void main(String[] args) {
    TreeNode root = new TreeNode(12);
    root.left = new TreeNode(7);
    root.right = new TreeNode(1);
    root.left.left = new TreeNode(9);
    root.right.left = new TreeNode(10);
    root.right.right = new TreeNode(5);
    System.out.println(hasPathWithSum(root, 23));
    System.out.println(hasPathWithSum(root, 16));
    System.out.println(hasPathWithSumIterative(root, 23));
    System.out.println(hasPathWithSumIterative(root, 16));
  }

  public static boolean hasPathWithSum(TreeNode root, int sum) {
  if(root ==null) {
    return false;
  }

  if(root.left==null && root.right==null && root.value == sum) {
    return true;
  }
  return hasPathWithSum(root.left, sum - root.value) || hasPathWithSum(root.right,
        sum - root.value);
  }

  public static boolean hasPathWithSumIterative(TreeNode root, int sum) {
    if(root ==null) {
      return false;
    }
    Stack<TreeNode> nodeStack = new Stack<>();
    Stack<Integer> sumStack = new Stack<>();
    nodeStack.push(root);
    sumStack.push(sum- root.value);
    while (!nodeStack.isEmpty()) {
      TreeNode currentNode = nodeStack.pop();
      Integer stackTop = sumStack.pop();
      if(currentNode.left ==null && currentNode.right==null && stackTop.equals(0)) {
        return true;
      }
      if(currentNode.left != null) {
        nodeStack.push(currentNode.left);
        sumStack.push(stackTop-currentNode.left.value);
      }
      if(currentNode.right != null) {
        nodeStack.push(currentNode.right);
        sumStack.push(stackTop-currentNode.right.value);
      }

    }

    return false;
  }
}
