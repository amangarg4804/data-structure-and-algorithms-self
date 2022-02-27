package algorithms.patterns.bfs;

class TreeNodeSibling {

  int value;
  TreeNodeSibling left;
  TreeNodeSibling right;
  TreeNodeSibling next;

  public TreeNodeSibling(int value) {
    this.value = value;
  }

  public int getValue() {
    return value;
  }

  public void setValue(int value) {
    this.value = value;
  }

  public TreeNodeSibling getLeft() {
    return left;
  }

  public void setLeft(TreeNodeSibling left) {
    this.left = left;
  }

  public TreeNodeSibling getRight() {
    return right;
  }

  public void setRight(TreeNodeSibling right) {
    this.right = right;
  }

  public TreeNodeSibling getNext() {
    return next;
  }

  public void setNext(TreeNodeSibling next) {
    this.next = next;
  }

  public void printLevelOrder() {
    TreeNodeSibling nextLevelRoot = this;
    while (nextLevelRoot != null) {
      TreeNodeSibling current = nextLevelRoot;
      nextLevelRoot = null;
      while (current != null) {
        System.out.print(current.value + " ");
        if (nextLevelRoot == null) {
          if (current.left != null) {
            nextLevelRoot = current.left;
          } else if (current.right != null) {
            nextLevelRoot = current.right;
          }
        }
        current = current.next;
      }
      System.out.println();
    }
  }
}
