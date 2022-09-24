package algorithms.leetcodetree;

import java.util.LinkedList;
import java.util.Queue;

public class PopulateRightPointersEachNode {

    public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(6);
        root.right.right = new Node(7);
        connect(root);

    }

    public static Node connect(Node root) {
        if (root == null) {
            return null;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            Node previous = queue.poll();
            if (previous.left != null) {
                queue.offer(previous.left);
            }
            if (previous.right != null) {
                queue.offer(previous.right);
            }
            for (int i = 1; i < size; i++) {
                Node current = queue.poll();
                previous.next = current;
                if (current.left != null) {
                    queue.offer(current.left);
                }
                if (current.right != null) {
                    queue.offer(current.right);
                }
                previous = current;
            }
        }
        return root;
    }

    public static Node connectWithoutExtraSpace(Node root) {
        Node left = root;
        while (left != null && left.left != null) {
            Node current = left;
            while (true) {
                current.left.next = current.right;
                if (current.next == null) {
                    break;
                }
                current.right.next = current.next.left;
                current = current.next;
            }
            left = left.left;
        }
        return root;
    }

    public static Node connectRecursive(Node root) {
        if(root==null) {
            return null;
        }
        if(root.left !=null) {
            root.left.next = root.right;
        }
        if(root.right!=null && root.next!=null) {
            root.right.next = root.next.left;
        }
        connectRecursive(root.left);
        connectRecursive(root.right);
        return root;
    }

    public Node connectIncompleteBinaryTree(Node root) {
        Node leftMostForThisLevel = root;
        while(leftMostForThisLevel!=null) {
            Node current = leftMostForThisLevel;
            Node dummy = new Node(0);
            Node dummyref = dummy;
            while(true) {
                if(current.left!=null) {
                    dummy.next=current.left;
                    dummy = dummy.next;
                }
                if(current.right!=null){
                    dummy.next=current.right;
                    dummy= dummy.next;
                }
                if(current.next==null) {
                    break;
                }
                current = current.next;
            }

            leftMostForThisLevel = dummyref.next;
        }
        return root;
    }



}
