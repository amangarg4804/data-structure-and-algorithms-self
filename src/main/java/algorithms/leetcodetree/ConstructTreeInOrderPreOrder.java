package algorithms.leetcodetree;

import java.util.HashMap;
import java.util.Map;

public class ConstructTreeInOrderPreOrder {

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        Map<Integer, Integer> inorderMap = new HashMap<>();
        for(int i=0; i< inorder.length ;i++) {
            inorderMap.put(inorder[i], i);
        }
        return buildTree(inorderMap, inorder, preorder, 0, inorder.length -1, 0, preorder.length-1);

    }

    private TreeNode buildTree(Map<Integer, Integer> inorderMap, int[] inorder, int[] preorder, int inorderStart,
                               int inorderEnd,  int preOrderStart, int preOrderEnd) {
        if(inorderStart > inorderEnd || preOrderStart > preOrderEnd) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[preOrderStart]);
        int rootIndexIndorder = inorderMap.get(preorder[preOrderStart]);
        int leftNodesCount = rootIndexIndorder - inorderStart;
        root.left = buildTree(inorderMap, inorder, preorder, inorderStart, rootIndexIndorder-1, preOrderStart+1, preOrderStart +1 + leftNodesCount -1);
        root.right = buildTree(inorderMap, inorder, preorder, rootIndexIndorder+1, inorderEnd, preOrderStart +1 + leftNodesCount -1 +1, preOrderEnd );
        return root;
    }


}
