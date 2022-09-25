package algorithms.leetcodetree;

import java.util.LinkedList;
import java.util.Queue;

public class SerializeDeserializeBinaryTree {
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        if(root == null) {
            return sb.toString();
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()) {
            TreeNode current = queue.poll();
            if(current==null) {
                sb.append("null" + ",");
                continue;
            }
            sb.append(current.val + ",");
            queue.offer(current.left);
            queue.offer(current.right);
        }
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if(data =="") {
            return null;
        }
        String [] arr = data.split(",");
        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode root = new TreeNode(Integer.parseInt(arr[0]));
        queue.offer(root);
        int index = 1;
        while(!queue.isEmpty()) {
            int size = queue.size();
            for(int i=0; i< queue.size(); i++) {
                TreeNode currentRoot = queue.poll();
                String left = arr[index++];
                String right = arr[index++];
                if(!left.equals("null")) {
                    TreeNode leftNode = new TreeNode(Integer.parseInt(left));
                    queue.offer(leftNode);
                    currentRoot.left = leftNode;
                }
                if(!right.equals("null")) {
                    TreeNode rightNode = new TreeNode(Integer.parseInt(right));
                    queue.offer(rightNode);
                    currentRoot.right = rightNode;
                }
            }
        }
        return root;
    }
}
