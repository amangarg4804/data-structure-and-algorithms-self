package algorithms.leetcodestackandqueues;

import java.util.*;

public class CloneGraph {

    class Node {
        public int val;
        public List<Node> neighbors;
        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }
        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }
        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }
    public Node cloneGraphBfs(Node node) {
        if(node ==null) {
            return null;
        }
        Queue<Node> q = new LinkedList<>();
        q.offer(node);
        Map<Node, Node> map = new HashMap<>();
        Node newStartNode = new Node(node.val);
        map.put(node, newStartNode);
        while (!q.isEmpty()) {
            int size = q.size();
            for(int i = 0; i< size; i++) {
                Node currentNode = q.poll();
                for(Node neighbour: currentNode.neighbors) {
                    if(!map.containsKey(neighbour)) {
                        Node newNeighbourNode = new Node(neighbour.val);
                        map.put(neighbour, newNeighbourNode);
                        q.offer(neighbour);
                    }
                    map.get(currentNode).neighbors.add(map.get(neighbour));
                }
            }
        }
        return newStartNode;
    }

    public Node cloneGraphDfs(Node node) {
        if(node ==null) {
            return null;
        }
        Map<Node, Node> map = new HashMap<>();
        Node newStartNode = new Node(node.val);
        map.put(node, newStartNode);
        cloneGraphDfs(node, map);
        return newStartNode;
    }

    private void cloneGraphDfs(Node node, Map<Node, Node> map) {
        for(Node neighbour: node.neighbors) {
            if(!map.containsKey(neighbour)) {
                Node newNeighbour = new Node(neighbour.val);
                map.put(neighbour, newNeighbour);
                cloneGraphDfs(neighbour, map);
            }
            map.get(node).neighbors.add(map.get(neighbour));
        }
    }
}


