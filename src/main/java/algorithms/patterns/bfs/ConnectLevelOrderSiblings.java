package algorithms.patterns.bfs;

import java.util.LinkedList;
import java.util.Queue;

public class ConnectLevelOrderSiblings {

  public static void main(String[] args) {
    TreeNodeSibling root = new TreeNodeSibling(12);
    root.left = new TreeNodeSibling(7);
    root.right = new TreeNodeSibling(1);
    root.left.left = new TreeNodeSibling(9);
    root.right.left = new TreeNodeSibling(10);
    root.right.right = new TreeNodeSibling(5);
    connectSiblings(root);
    root.printLevelOrder();
  }

  public static void connectSiblings(TreeNodeSibling root) {
    if (root==null) {
      return;
    }
    Queue<TreeNodeSibling> queue = new LinkedList<>();
    queue.add(root);
    while (!queue.isEmpty()) {
      int currentQueueSize = queue.size();
      TreeNodeSibling previous = null;
      for(int i=0; i < currentQueueSize ;i++) {
        TreeNodeSibling current = queue.poll();
        if(previous != null) {
          previous.next = current;
        }
        previous = current;
        if(current.left != null) {
          queue.add(current.left);
        }
        if(current.right != null) {
          queue.add(current.right);
        }
      }
    }
    System.out.println("hello");
  }
}
