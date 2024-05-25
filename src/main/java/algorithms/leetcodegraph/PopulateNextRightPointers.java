package algorithms.leetcodegraph;

import java.util.LinkedList;
import java.util.Queue;

//You are given a perfect binary tree where all leaves are on the same level, and every parent has two children.
//Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.
//
//Initially, all next pointers are set to NULL.
//Input: root = [1,2,3,4,5,6,7]
//Output: [1,#,2,3,#,4,5,6,7,#]
//Explanation: Given the above perfect binary tree (Figure A), your function should populate each next pointer to point to its next right node,
// just like in Figure B. The serialized output is in level order as connected by the next pointers, with '#' signifying the end of each level.

//Input: root = []
//Output: []

//Constraints:
//The number of nodes in the tree is in the range [0, 212 - 1].
//-1000 <= Node.val <= 1000

//Follow-up:
//
//You may only use constant extra space.
//The recursive approach is fine. You may assume implicit stack space does not count as extra space for this problem.

public class PopulateNextRightPointers {

    public Node connect(Node root) {
        // using BFS
        if(root ==null) {
            return root;
        }
        Queue<Node> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            int size = q.size();
            Node previous = q.poll();
            for(int i=1;i< size; i++) {
                Node current = q.poll();
                previous.next = current;
                if(previous.left!=null) {
                    q.offer(previous.left);
                }
                if(previous.right!=null) {
                    q.offer(previous.right);
                }
                previous =current;
            }
            if(previous.left!=null) {
                q.offer(previous.left);
            }
            if(previous.right!=null) {
                q.offer(previous.right);
            }
            previous.next =null;
        }
        return root;

    }

    // Definition for a Node.
    private static class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }
}
