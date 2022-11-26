package algorithms.leetcodebst;

public class InsertInBST {

    public TreeNode insertIntoBST(TreeNode root, int val) {
        // TC : O(h), h is height, note that O(h) doesn't mean O(log n). In case of skewed tree, h=n
        TreeNode newNode = new TreeNode(val);
        if(root ==null) {
            return newNode;
        }
        TreeNode current = root;
        while(current.left !=null || current.right!=null) {
            if(val > current.val) {
                if(current.right==null) {
                    break;
                }
                current = current.right;
            }
            else if(val < current.val){
                if(current.left ==null) {
                    break;
                }
                current = current.left;
            }
        }
        if(val > current.val) {
            current.right = newNode;
        } else {
            current.left =newNode;
        }
        return root;
    }

    public TreeNode insertIntoBST2(TreeNode root, int val) {
        TreeNode newNode = new TreeNode(val);
        if(root ==null) {
            return newNode;
        }
        TreeNode current = root;
        while(true) {
            if(val > current.val) {
                if(current.right==null) {
                    current.right = newNode;
                    break;
                }
                current = current.right;
            }
            else if(val < current.val){
                if(current.left ==null) {
                    current.left =newNode;
                    break;
                }
                current = current.left;
            }
        }
        return root;
    }

    public TreeNode insertIntoBST3(TreeNode root, int val) {
        TreeNode newNode = new TreeNode(val);
        if(root ==null) {
            return newNode;
        }
        insertIntoBSTRecursive(root, val);
        return root;
    }

    private void insertIntoBSTRecursive(TreeNode current, int val) {
        if(val > current.val) {
            if(current.right==null) {
                current.right = new TreeNode(val);
                return;
            }
            insertIntoBSTRecursive(current.right, val);
        }
        if(val < current.val) {
            if(current.left==null) {
                current.left = new TreeNode(val);
                return;
            }
            insertIntoBSTRecursive(current.left, val);
        }
    }

    public TreeNode insertIntoBST4Recursive(TreeNode root, int val) {
        if(root ==null) {
            return new TreeNode(val);
        }
        if(val > root.val) {
            root.right = insertIntoBST4Recursive(root.right, val);
        } else {
            root.left = insertIntoBST4Recursive(root.left, val);
        }
        return root;
    }

    public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
}
