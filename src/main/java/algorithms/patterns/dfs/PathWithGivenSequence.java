package algorithms.patterns.dfs;

//Given a binary tree and a number sequence, find if the sequence is present as a root-to-leaf path in the given tree.
public class PathWithGivenSequence {

  public static void main(String[] args) {
    TreeNode root = new TreeNode(1);
    root.left = new TreeNode(0);
    root.right = new TreeNode(1);
    root.left.left = new TreeNode(1);
    root.right.left = new TreeNode(6);
    root.right.right = new TreeNode(5);
    System.out.println(findPath(root, new int[]{1, 0, 7}));
    System.out.println(findPath(root, new int[]{1, 1, 6}));

  }

  public static boolean findPath(TreeNode root, int[] arr) {
    if(root ==null) {
      return arr.length==0;
    }
    return pathExists(root, arr, 0);
  }


  public static boolean pathExists(TreeNode root, int[] arr, int index) {
    if(root==null) {
      if(index != arr.length) {
        return false;
      } else {
        return true;
      }
    }
    if(index == arr.length) {
      return false;
    }
    if(arr[index] != root.value) {
      return false;
    }
    return pathExists(root.left, arr, index+1) || pathExists(root.right, arr, index+1);
  }

}
