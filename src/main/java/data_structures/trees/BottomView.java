package data_structures.trees;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;

import data_structures.trees.bst.MyBinarySearchTree.Node;

public class BottomView {

    // Uses BFS
    // and the idea that the root node height is 0
    // and elements to the left have height parent height -1
    // and elements to the right have height parent height +1
    // https://www.youtube.com/watch?v=FPPkYYWZTkg

    public ArrayList<Integer> bottomView(Node root) {
        Node currentNode = root;
        Queue<Node> queue = new LinkedList<>();
        queue.add(currentNode);
        ArrayList<Integer> resultList = new ArrayList<>();
        Map<Integer, Node> heightToNodeMap = new TreeMap<>();
        Map<Node, Integer> nodeToHeightMap = new HashMap<>();
        heightToNodeMap.put(0, currentNode);
        nodeToHeightMap.put(currentNode, 0);
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            if (node.left != null) {
                queue.add(node.left);
                heightToNodeMap.put(nodeToHeightMap.get(node) - 1, node.left);
                nodeToHeightMap.put(node.left, nodeToHeightMap.get(node) - 1);
            }
            if (node.right != null) {
                queue.add(node.right);
                heightToNodeMap.put(nodeToHeightMap.get(node) + 1, node.right);
                nodeToHeightMap.put(node.right, nodeToHeightMap.get(node) + 1);
            }
        }
        heightToNodeMap.values().forEach(n -> resultList.add(n.key));
        return resultList;
    }

}
