package algorithms.leetcodenarytree;

import java.util.*;

public class PostorderTraversal {
    List<Integer> result = new ArrayList<>();
    public List<Integer> postorder(Node root) {
        if(root == null) {
            return result;
        }
        for(Node child : root.children) {
            postorder(child);
        }
        result.add(root.val);
        return result;
    }

    public List<Integer> postorderIterative1(Node root) {
        // NOTE: first thing to do in such questions to create a tree and write its postorder traversal and make observations
        if(root == null) {
            return result;
        }
        Deque<Node> stack  = new LinkedList<>();
        stack.push(root);
        Set<Node> visited = new HashSet<>();
        while (!stack.isEmpty()) {
            Node node  = stack.peek();
            if(!visited.contains(node) && node.children !=null) {
                for(int i = node.children.size()-1 ;i >=0; i--) {
                    stack.push(node.children.get(i));
                }
                visited.add(node);
            } else {
                result.add(stack.pop().val);
            }
        }
        return result;
    }

    public List<Integer> postorderIterative2(Node root) {
        // NOTE: using linked list instead of arraylist to insert elements at 0th index
        // without extra space
        List<Integer> result = new LinkedList<>();
        if(root == null) {
            return result;
        }
        Deque<Node> stack  = new LinkedList<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node node  = stack.pop();
            for(Node child : node.children) {
                stack.push(child);
            }
            result.add(0, node.val); // another way could be to add elements to arraylist and call Collections.reverse() after while loop ends
        }
        return result;
    }

    public List<Integer> postorderIterative3(Node root) {
        // without using set.
        // NOTE: using another stack to store result.
        List<Integer> result = new LinkedList<>();
        if(root == null) {
            return result;
        }
        Deque<Node> stack1  = new LinkedList<>();
        Deque<Node> stack2  = new LinkedList<>();
        stack1.push(root);
        while (!stack1.isEmpty()) {
            Node node  = stack1.pop(); // popped node has to be last node among the remaining nodes as it is postorder traversal. Root has to be last
            for(Node child : node.children) {
                stack1.push(child);
            }
            stack2.push(node);
        }
        while (!stack2.isEmpty()) {
            result.add(stack2.pop().val);
        }
        return result;
    }

    public List<Integer> postorderIterative4(Node root) {
        // without using extra space
        if(root == null) {
            return result;
        }
        Deque<Node> stack  = new LinkedList<>();
        stack.push(root);
        Node previous = null;
        while (!stack.isEmpty()) {
            Node node  = stack.peek();
            if(node.children.size()!=0 && previous != node.children.get(node.children.size()-1)) { // if last added node to result is last child of current node, current node's children are already processed and shouldn't be added to stack again
                for(int i = node.children.size()-1 ;i >=0; i--) {
                    stack.push(node.children.get(i)); // leftmost will be popped first
                }

            } else {
                result.add(stack.pop().val);
                previous = node;
            }
        }
        return result;
    }
}
