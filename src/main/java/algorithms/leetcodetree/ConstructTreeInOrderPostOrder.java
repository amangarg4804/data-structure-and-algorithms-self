package algorithms.leetcodetree;

import java.util.HashMap;
import java.util.Map;

public class ConstructTreeInOrderPostOrder {

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        Map<Integer, Integer> inorderMap = new HashMap<>();
        for(int i=0; i< inorder.length ;i++) {
            inorderMap.put(inorder[i], i);
        }
        return buildTree(inorderMap, inorder, postorder, 0, inorder.length -1, 0, postorder.length-1);

    }

    private TreeNode buildTree(Map<Integer, Integer> inorderMap, int[] inorder, int[] postorder, int inorderStart,
                               int inorderEnd,  int postOrderStart, int postOrderEnd) {
        if(inorderStart > inorderEnd || postOrderStart > postOrderEnd) {
            return null;
        }
        TreeNode root = new TreeNode(postorder[postOrderEnd]);
        int rootindex = inorderMap.get(postorder[postOrderEnd]);
        int leftElements = rootindex-inorderStart;
        root.left = buildTree(inorderMap, inorder, postorder,inorderStart,rootindex-1, postOrderStart, postOrderStart +leftElements-1);
        root.right = buildTree(inorderMap, inorder, postorder,rootindex+1,inorderEnd, postOrderStart+leftElements, postOrderEnd-1);
        return root;
    }


}
