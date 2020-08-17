package data_structures.trees;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;

import data_structures.trees.bst.MyBinarySearchTree.Node;

public class TopView {

    static void topView(Node root)
    {
        Node currentNode = root;
        Queue<Node> queue = new LinkedList<>();
        queue.add(currentNode);
        ArrayList<Integer> resultList = new ArrayList<>();
        Map<Integer, Node> heightToNodeMap = new TreeMap<>();
        Map<Node, Integer> nodeToHeightMap = new HashMap<>();
        heightToNodeMap.put(0, currentNode);
        nodeToHeightMap.put(currentNode, 0);
        while(!queue.isEmpty()) {
            Node node = queue.poll();
            if(node.left !=null) {
                int leftHeight = nodeToHeightMap.get(node) -1;
                queue.add(node.left);
                if(heightToNodeMap.get(leftHeight) == null) {
                    heightToNodeMap.put(leftHeight, node.left);
                }
                nodeToHeightMap.put(node.left, leftHeight);
            }
            if(node.right !=null) {
                queue.add(node.right);
                int rightHeight = nodeToHeightMap.get(node) +1;
                if(heightToNodeMap.get(rightHeight)==null) {
                    heightToNodeMap.put(rightHeight, node.right);
                }
                nodeToHeightMap.put(node.right, rightHeight);
            }
        }
        heightToNodeMap.values().stream().forEach(n -> System.out.print(n.key + " "));

    }

}
