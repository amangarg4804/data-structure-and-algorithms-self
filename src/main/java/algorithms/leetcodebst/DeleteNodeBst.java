package algorithms.leetcodebst;

public class DeleteNodeBst {

    public TreeNode deleteNode(TreeNode root, int key) {
        if(root ==null) {
            return null;
        }
        if(root.val==key) {
            return delete(root);
        }
        TreeNode current = root;
        while (current!=null) {
            if(key < current.val) {
                if(current.left !=null && current.left.val == key) {
                    current.left = delete(current.left);
                    return root;  // return root and not current
                } else {
                    current = current.left;
                }
            } else {
                if(current.right!=null && current.right.val ==key) {
                    current.right = delete(current.right);
                    return root;
                } else {
                    current = current.right;
                }
            }
        }
        return root;
    }


    TreeNode delete(TreeNode root) {
        if(root.left==null) {
            return root.right;
        }
        if(root.right ==null) {
            return root.left;
        }

        TreeNode left = root.left; //making left as new root
        TreeNode right = root.right;
        TreeNode current = left;
        while (current.right!=null) { // when we make left as root. The exist right subtree of previous root should be attached to rightmost node of left node
            current = current.right;
        }
        current.right = right;
        return left;
    }

    public TreeNode deleteNodeRecursive(TreeNode root, int key) {
        if(root ==null) {
            return null;
        }
        if(key > root.val) {
            root.right = deleteNodeRecursive(root.right, key); // treat root as parent in this case and its right node need to set
        } else if(key < root.val) {
            root.left = deleteNodeRecursive(root.left, key);
        } else {
            // root's value equals key
            if(root.left ==null) {
                return root.right;
            }
            if(root.right ==null) {
                return root.left;
            }
            root.val = findSuccessor(root.right).val; //successor will be leftmost child of right child. copy successor's value on current node
            root.right = deleteNodeRecursive(root.right, root.val);// delete the duplicate node
        }
        return root;
    }

    TreeNode findSuccessor(TreeNode node) {
        TreeNode current = node;
        while (current.left!=null) {
            current = current.left;
        }
        return current;
    }
}
