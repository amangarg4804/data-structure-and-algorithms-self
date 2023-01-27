package algorithms.leetcodehashtable;

import java.util.*;
import java.util.stream.Collectors;

// Given the root of a binary tree, return all duplicate subtrees.
// For each kind of duplicate subtrees, you only need to return the root node of any one of them.
// Two trees are duplicate if they have the same structure with the same node values.
public class DuplicateSubtrees {

    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        // The idea is to traverse the tree, treat every node as root and do its traversal. Store the traversal as key and root node as value.
        // key is formed by traversing the node creating a string based on left and right children
        //      1
        //    2   3
        //  4       5
        // Key for 1 will be 1l2r3l2r5
        // key for 2 will be 2l4
        Map<String, List<TreeNode>> map = new HashMap<>();
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root); // given that root is not null
        while (!q.isEmpty()) {
            TreeNode current = q.poll();
            postOrder(current, map);
            if(current.left!=null) {
                q.offer(current.left);
            }
            if(current.right!=null) {
                q.offer(current.right);
            }
        }
        List<TreeNode> result = new ArrayList<>();
        for(Map.Entry<String,List<TreeNode>> entry : map.entrySet()) {
            if(map.get(entry.getKey()).size()>1) {
                result.add(map.get(entry.getKey()).get(0));
            }
        }
        return result;
    }

    private void postOrder(TreeNode root, Map<String, List<TreeNode>> map) {
        StringBuilder sb= new StringBuilder();
        postOrder(root, sb, 'n');
        String key = sb.toString();
        List<TreeNode> list = map.getOrDefault(key, new ArrayList<>());
        list.add(root);
        map.put(key, list);
    }
    private void postOrder(TreeNode root,StringBuilder key , char c) {
        if(root==null) {
            return;
        }
        postOrder(root.left, key, 'l');
        key.append(c).append(root.val);
        postOrder(root.right, key, 'r');
    }

    public List<TreeNode> findDuplicateSubtrees1(TreeNode root) {
        // We can't use inorder traversal, because it will find the following trees as duplicate
        //  1
        //   \
        //    2
        //   1
        //  /
        // 2
        Map<String, Integer> map = new HashMap<>(); // key is String formed by traversing root node and all its children. Value is how many times we found the same key
        List<TreeNode> result = new ArrayList<>();
        postOrder(root, result, map);
        return result;
    }

    private String postOrder(TreeNode root,List<TreeNode> result ,Map<String, Integer> map) {
        if(root==null) {
            return "#";// to identify end of left or right otherwise the tests fail for below example when left, right and root have same values
            //  0
            //   \
            //    0
            //   0
            //  /
            // 0
        }
        String left =postOrder(root.left, result, map);
        String right = postOrder(root.right, result, map);
        String key =  root.val + "-"+ right + "-" +left ;
        // we need to have separator between concatenations for above case.
        // the separator has to different than #
        // left and right have to be together
        //String key =  left + "-"+root.val  + "-"+  right  ; wrong answer
       // String key =   right + "-" +left + "-" +root.val; Right answer
        map.put(key, map.getOrDefault(key, 0) +1);
        if(map.get(key)==2) {
            result.add(root);
        }
        return key;
    }

    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
