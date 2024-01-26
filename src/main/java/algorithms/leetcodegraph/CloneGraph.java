package algorithms.leetcodegraph;

import java.util.*;

//Given a reference of a node in a connected undirected graph.
//
//Return a deep copy (clone) of the graph.
//
//Each node in the graph contains a value (int) and a list (List[Node]) of its neighbors.
//
//class Node {
//    public int val;
//    public List<Node> neighbors;
//}
//
//
//Test case format:
//
//For simplicity, each node's value is the same as the node's index (1-indexed). For example, the first node with val == 1,
// the second node with val == 2, and so on. The graph is represented in the test case using an adjacency list.
//
//An adjacency list is a collection of unordered lists used to represent a finite graph. Each list describes the set of neighbors of a node in the graph.
//
//The given node will always be the first node with val = 1. You must return the copy of the given node as a reference to the cloned graph.

//Constraints:
//
//The number of nodes in the graph is in the range [0, 100].
//1 <= Node.val <= 100
//Node.val is unique for each node.
//There are no repeated edges and no self-loops in the graph.
//The Graph is connected and all nodes can be visited starting from the given node.
public class CloneGraph {
    public Node cloneGraph(Node node) {
        // iterative DFS
        // Graph is connected-meaning each node is connected to other node
        // Graph is undirected-so if node 1 is in neghbour list of  node 2. Then node 2 will also be in neighbour list of node 1
        // the given node is always first node- val 1
        // which data structure to choose?
        // to clone the graph, we have to take care of following:
        // 1. each node value has to be cloned. So if there are n nodes in a graph, we will be creating n new node objects
        // 2. We have to prevent creating duplicate nodes. It is possible that a node is present as neighbour of multiple nodes.
        // ALso we have to keep track whether we already created a new object of node val so we don't mistakenly create another node with same val
        // 3. Connections have to be maintained.
        // 4. While we can encounter a node multiple times, we don't want to visit neighbours of a node twice.
        //5. we have to visit every node- we can use either BFS or DFS- queue or stack
        // data structures required;
        // 1.  can create a hashmap to keep track of whether we have already created new node and reuse the node
        // 2. visited set
        // 3. New node
        if(node==null) {
            return null;
        }
        Map<Integer, Node> valToClonedNode = new HashMap<>();
        Set<Integer> visited = new HashSet<>();
        Stack<Node> stack = new Stack<>();
        stack.add(node);
        Node newNode =new Node(node.val);
        valToClonedNode.put(node.val, newNode);
        visited.add(node.val);// this is a undirected graph. we have to add to visited before so we don't reach this node again through any of the neighbours
        while (!stack.isEmpty()) {
            Node current = stack.pop();

            //         2
            //    1
            //         3
            // 1 has neighbours 2 and 3. 2 has neighbours 1 and 3. 3 has neighbours 1 and 2
            if(current.neighbors ==null) {
                continue;
            }
            for(Node neighbour: current.neighbors) {
                Node cloneNeighbour = valToClonedNode.getOrDefault(neighbour.val, new Node(neighbour.val));
                valToClonedNode.put(neighbour.val, cloneNeighbour);
                // add the cloned neighbour to the cloned node
                valToClonedNode.get(current.val).neighbors.add(cloneNeighbour);
                if(!visited.contains(neighbour.val)) {
                    visited.add(neighbour.val);
                    stack.add(neighbour);
                }
            }
        }
        return newNode;
    }

    private static class Node {
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
}
