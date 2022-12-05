package algorithms.leetcodebst;

import java.util.Deque;
import java.util.LinkedList;

public class SortedArrayToBST {
    public TreeNode sortedArrayToBST(int[] nums) {
        return sortedArrayToBST(nums, 0, nums.length - 1);
    }

    private TreeNode sortedArrayToBST(int[] nums, int start, int end) {
        if (start < 0 || end >= nums.length || start > end) {
            return null;
        }

        if (start == end) {
            return new TreeNode(nums[start]);
        }
        int mid = start + (end - start) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = sortedArrayToBST(nums, start, mid - 1);
        root.right = sortedArrayToBST(nums, mid + 1, end);
        return root;
    }

    private TreeNode sortedArrayToBSTIterative(int[] nums) {
        // assuming nums is not null or empty
        Deque<Node> stack = new LinkedList<>();
        TreeNode root = new TreeNode(0);
        stack.push(new Node(root, 0, nums.length - 1)); // following the same approach as in recursion. at the beginning, start index is 0, end index is last element of array

        while (!stack.isEmpty()) {
            Node node = stack.pop();
            int mid = node.startIndex + (node.endIndex - node.startIndex) / 2;
            node.current.val = nums[mid]; // current nodes value is middle element's value
            if (node.startIndex <= mid - 1) { // make sure there are elements to the left of middle element
                node.current.left = new TreeNode(0);
                stack.push(new Node(node.current.left, node.startIndex, mid - 1)); // start remains same, end becomes mid -1 for leftNode
            }
            if (node.endIndex >= mid + 1) {
                node.current.right = new TreeNode(0);
                stack.push(new Node(node.current.right, mid + 1, node.endIndex)); // right is not immediate element after current, right will be the middle elemnent in right subaaray, because the right child will itself be a BST           }
            }
        }
        return root;
    }


    private class Node {
        TreeNode current;
        int startIndex;
        int endIndex;

        public Node(TreeNode current, int startIndex, int endIndex) {
            this.current = current;
            this.startIndex = startIndex;
            this.endIndex = endIndex;
        }
    }
}
