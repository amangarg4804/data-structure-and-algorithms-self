package algorithms.leetcodetree;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class PostOrderTraversal {

    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        postorderTraversal(root, result);
        return result;
    }

    private void postorderTraversal(TreeNode root, List<Integer> result) {
        if(root==null) {
            return;
        }
        postorderTraversal(root.left);
        postorderTraversal(root.right);
        result.add(root.val);
    }
    //        25
    //      10     30
    //     5  15     40

    //post order: 5 15 10 40 30 25
    public List<Integer> postorderTraversalIterative(TreeNode root) {

        List<Integer> result = new ArrayList<>();
        if(root==null) {
            return result;
        }
        Deque<TreeNode> stack1 = new LinkedList<>();
        Deque<TreeNode> stack2 = new LinkedList<>();
        stack1.push(root);
        while(!stack1.isEmpty()) {
            TreeNode currentNode = stack1.pop();// stack1:5,15 stack2:25,30,40,10,15,5
            stack2.push(currentNode);
            if(currentNode.left !=null) {
                stack1.push(currentNode.left);
            }
            if(currentNode.right !=null) {
                stack1.push(currentNode.right);
            }
        }

        while(!stack2.isEmpty()) {
            TreeNode currentNode = stack2.pop();
            result.add(currentNode.val);
        }
        return result;
    }

}
