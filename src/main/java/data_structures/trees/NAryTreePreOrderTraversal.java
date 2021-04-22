package data_structures.trees;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class NAryTreePreOrderTraversal {

  public List<Integer> preorder(Node root) {
    List<Integer> result = new ArrayList<>();
    preorder(root, result);
    return result;
  }

  public void preorder(Node root, List<Integer> list) {
    if (root != null) {
      list.add(root.val);
      for (Node node : root.children) {
        preorder(node, list);
      }
    }
  }


  public List<Integer> preorderWithoutRecursion(Node root) {
    List<Integer> result = new ArrayList<>();
    if(root==null) {
      return result;
    }
    LinkedList<Node> stack = new LinkedList<>();
    stack.add(root); //add equals addlast
    while (!stack.isEmpty()) {
      Node currentNode = stack.pollLast();
      result.add(currentNode.val);
      Collections.reverse(currentNode.children);
      stack.addAll(currentNode.children);
    }
    return result;
  }
}

class Node {

  public int val;
  public List<Node> children;

  public Node() {
  }

  public Node(int _val) {
    val = _val;
  }

  public Node(int _val, List<Node> _children) {
    val = _val;
    children = _children;
  }
}
