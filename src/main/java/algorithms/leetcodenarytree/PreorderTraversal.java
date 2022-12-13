package algorithms.leetcodenarytree;

import java.util.*;

public class PreorderTraversal {

    public List<Integer> preorder(Node root) {
        List<Integer> result = new ArrayList<>();
        preorder(root, result);
        return result;
    }

    private void preorder(Node root, List<Integer> result) { // instead of creating another method, we could also create result list as instance variable of thi class
        if(root==null) {
            return;
        }
        result.add(root.val);
        for(Node child : root.children) {
            preorder(child, result);
        }
    }

    public List<Integer> preorderIterative(Node root) {
        List<Integer> result = new ArrayList<>();
        if(root ==null) {
            return result;
        }
        Deque<Node> stack = new LinkedList<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node node = stack.pop();
            result.add(node.val);
            if(node.children ==null) {
                continue;
            }
            for(int i = node.children.size() -1; i>=0; i--) { // leftmost node has to be processed first, since stack is LIFO, we push rightmost first
                stack.push(node.children.get(i));
            }
        }
        return result;
    }
}
