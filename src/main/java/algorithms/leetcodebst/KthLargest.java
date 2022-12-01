package algorithms.leetcodebst;

public class KthLargest {

    TreeNode root;
    int k;


    public KthLargest(int k, int[] nums) {
        this.k = k;
        for(int i=0; i< nums.length; i++) {
            root = insert(nums[i], root);
        }

    }
    // stream can contain duplicate elements
    private TreeNode insert(int num, TreeNode root) {
        if(root ==null) {
            return new TreeNode(num);
        }
        if(num < root.val) {
            root.left = insert(num, root.left);
        } else {
            root.right = insert(num, root.right);
        }
        root.childrenCount = root.childrenCount+1;
        return root;
    }

    public int add(int val) {
        root = insert(val, root); // initial array could be empty in case k is 1, so we need to assign root here
        return kthLargest(root, k);
    }

    private int kthLargest(TreeNode node, int k) {
        int rightChildrenCount = node.right!=null? node.right.childrenCount +1: 0; // we need to do +1, right node doesn't count itself, leaf nodes have count as 0.

        if(k == rightChildrenCount +1) {
            return node.val;
        }
        if(rightChildrenCount >=k) {
            return kthLargest(node.right, k); // If right children (right children will be greater or equal to current element) are greater than k,
            // it means there are more than k greater elements than the current element,
            // so we need to find a greater element than current element which will be to the right of current
        } else {
            return kthLargest(node.left, k-rightChildrenCount-1); // we have to move left. elements greater than left are rightChildrenCount +1,
            // -1 because of the node itself
        }

    }

    private class TreeNode {

        int val;
        TreeNode left;
        TreeNode right;
        int childrenCount;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
