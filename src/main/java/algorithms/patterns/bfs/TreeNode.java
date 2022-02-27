package algorithms.patterns.bfs;

public class TreeNode {

  int value;
  TreeNode left;
  TreeNode right;

  TreeNode(int x) {
    this.value = x;
  }

  @Override
  public String toString() {
    return String.valueOf(value);
  }
}
