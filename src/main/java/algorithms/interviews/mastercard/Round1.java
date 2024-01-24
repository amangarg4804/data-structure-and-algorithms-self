package algorithms.interviews.mastercard;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

//
public class Round1 {
    //Implement iterative post-order traversal of a tree
    // left, right, root
    //     25
    //   10   30
    //  5  15   40
    //

    // 5 15 10 40 30 25- post order
    //  20
    //
    private static List<Integer> postOrder(Node root) {

        List<Integer> result = new ArrayList<>();
        if(root==null) {
            return result;
        }
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        Node current = root;
        while (true) {
            if(current !=null && current.left!=null) {// stack: 25 current: null, current.left=null
                stack.push(current.left);
                current =current.left;
            } else {
                Node node = stack.peek(); // node = 25
                if(stack.isEmpty()) {
                    result.add(node.key);
                    break;
                }
                if(current!=null && node.right!=null) {
                    stack.push(node.right);
                    current = node.right;
                    continue;
                }
                result.add(stack.pop().key);// result = 5,15,10
                current = null;
            }
        }
        return result;
    }

    //   //     25
    //    //   10   30
    //    //  5  15   40
    //    //

    static class Node {
        int key;
        Node left, right;
        public Node(int item)
        {
            key = item;
            left = right = null;
        }
    }
}
