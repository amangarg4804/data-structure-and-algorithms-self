package algorithms.leetcodegraph;


//You have a graph of n nodes labeled from 0 to n - 1. You are given an integer n and a list of edges where edges[i] = [ai, bi] indicates that there is an undirected edge between nodes ai and bi in the graph.
//
//Return true if the edges of the given graph make up a valid tree, and false otherwise.

//Input: n = 5, edges = [[0,1],[0,2],[0,3],[1,4]]
//Output: true

//Input: n = 5, edges = [[0,1],[1,2],[2,3],[1,3],[1,4]]
//Output: false

import java.util.*;

//Constraints:
//1 <= n <= 2000
//0 <= edges.length <= 5000
//edges[i].length == 2
//0 <= ai, bi < n
//ai != bi
//There are no self-loops or repeated edges.
public class GraphValidTree {

    //NOTE:As per graph theory, For the graph to be a valid tree, it must have exactly n - 1 edges.
    // Any less, and it can't possibly be fully connected. Any more, and it has to contain cycles.
    // Example: 1
    //        /   \
    //       2     3
    // The above tree has 3 nodes and 2(i.e, 3-1) edges. It is a valid tree.
    // Now to check whether a Graph is tree, it is not enough to check for just n-1 edges. Check the example below
    // Example: 0--1    3
    //         /  /
    //          2
    // The above example has 4 nodes (from 0 to 3) and 3 (i.e, 4-1) edges. But it is not a valid tree. Because
    // 1. 3 is not connected to any node or no node is connected to 3
    // 2. Graph has a cyle. 0-1-2-0
    // To correctly check for valid tree, along with the n-1 edges check,
    // we can either check that All nodes are connected with each other or we can check that there is no cycle
    // So to check for a valid tree using Graph theory. We must check the following
    // 1. Graph has n-1 edges, where n is no of nodes/vertices
    // 2. Either of the following
    //    a) Each node is connected to every other node, i.e. graph is fully connected
    //    b) Graph contains no cycle,i.e. Once two nodes are connected directly or indirectly, there should be no edge connecting them again

    public boolean validTree(int n, int[][] edges) {
        // properties of a valid tree
        // 1) All the nodes are connected to each other
        // 2) There is no cycle
        // 0--1---2
        //    | \ |
        //    4  3

        // We can form a Disjoint set by iterating over the edges
        // 1) Whenever we connect two nodes, we can check if they are already connected. If they are, it means this edge will form a cycle and it can't be a valid tree
        // e.g.edges = [[0,1],[1,2],[2,3],[1,3],[1,4]]. when we check for edge [1,3], they were already connected. so connecting 1 to 3 directly forms an edge
        // 2) Once we are done creating Disjoint set. Verify that each node is connected to every other node.
        // e.g. edges = [[0, 1], [1, 2], [3, 4]] This doesn't form a valid tree

        //

        // To solve this problem using Disjoint Set, we will check for condition 1 and 2b
        if(edges.length !=n-1) {
            return false;
        }
        OptimizedDisjointSet optimizedDisjointSet = new OptimizedDisjointSet(n);
        for (int[] edge : edges) {
            // Checks for condition 2: No cycle. Once two nodes are connected directly or indirectly, there should be no edge connecting them again
            if (optimizedDisjointSet.connected(edge[0], edge[1])) {// as per problem constrains, There are no self-loops or repeated edges.
                return false;
            }
            optimizedDisjointSet.union(edge[0], edge[1]);
        }
//        for (int i = 0; i < n; i++) {
//            for (int j = i + 1; j < n; j++) {
//                if (!disjointSetPathCompression.connected(i, j)) {
//                    return false;
//                }
//            }
//        } //This logic is not required because of condition added at the start of this method
       return true;
        // For time complexity: //https://drive.google.com/file/d/1NnNfNF9oC77UISi8AKslrG37GmP2TIku/view?usp=drive_link
    }

    private static class OptimizedDisjointSet {
        int[] rank;
        int[] root;

        public OptimizedDisjointSet(int size) {
            rank = new int[size];
            root = new int[size];
            for (int i = 0; i < size; i++) {
                rank[i] = 1;
                root[i] = i;
            }
        }

        public int find(int n) {
            if (n == root[n]) {
                return n;
            }
            root[n] = find(root[n]);
            return root[n];
        }

        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX != rootY) {
                if (rank[rootY] > rank[rootY]) {
                    root[rootY] = rootX;
                } else if (rank[rootY] > rank[rootX]) {
                    root[rootX] = rootY;
                } else {
                    root[rootY] = rootX;
                    rank[rootX] += 1;
                }
            }
        }

        public boolean connected(int x, int y) {
            return find(x) == find(y);
        }
    }

    public boolean validTree2(int n, int[][] edges) {
        // To solve using DFS , we will check condition 1 and 2a
        //  1. Graph has n-1 edges, where n is no of nodes/vertices
        //  2. Either of the following
        //     a) Each node is connected to every other node, i.e. graph is fully connected
        //     b) Graph contains no cycle,i.e. Once two nodes are connected directly or indirectly, there should be no edge connecting them again
        if(edges.length !=n-1) {
            return false;
        }
        // using iterative DFS
        //first we prepare adjacency list
        List<List<Integer>> adjacencyList = new ArrayList<>();
        for(int i=0; i< n;i++) {
            adjacencyList.add(new ArrayList<>());
        }
        for(int[] edge : edges) {
            adjacencyList.get(edge[0]).add(edge[1]);
            adjacencyList.get(edge[1]).add(edge[0]);
        }
        // for iterative DFS, we use Stack
        Stack<Integer> stack = new Stack<>();
        // to check that a Graph is fully connected, we use a Set containing all visited nodes.
        // We start from Node 0 and visit all connected nodes. At the end the visited Set should contain n number of nodes for the grapoh to be fully connected
        // In other words, from Node 0, we should be able to visit every node
        Set<Integer> visited =new HashSet<>();
        stack.push(0);
        visited.add(0);
        while(!stack.isEmpty()) {
            int current = stack.pop();
            for(Integer neighbour : adjacencyList.get(current)) {
                if(!visited.contains(neighbour)) {
                    visited.add(neighbour);
                    stack.push(neighbour);
                }
            }
        }
        return visited.size() ==n;
    }

    public boolean validTree3(int n, int[][] edges) {
        // To solve using DFS , we will check condition 1 and 2a
        //  1. Graph has n-1 edges, where n is no of nodes/vertices
        //  2. Either of the following
        //     a) Each node is connected to every other node, i.e. graph is fully connected
        //     b) Graph contains no cycle,i.e. Once two nodes are connected directly or indirectly, there should be no edge connecting them again
        if(edges.length !=n-1) {
            return false;
        }
        // using recursive DFS
        //first we prepare adjacency list
        List<List<Integer>> adjacencyList = new ArrayList<>();
        for(int i=0; i< n;i++) {
            adjacencyList.add(new ArrayList<>());
        }
        for(int[] edge : edges) {
            adjacencyList.get(edge[0]).add(edge[1]);
            adjacencyList.get(edge[1]).add(edge[0]);
        }
        // to check that a Graph is fully connected, we use a Set containing all visited nodes.
        // We start from Node 0 and visit all connected nodes. At the end the visited Set should contain n number of nodes for the grapoh to be fully connected
        // In other words, from Node 0, we should be able to visit every node
        Set<Integer> visited =new HashSet<>();
        visited.add(0);
        dfs(0, adjacencyList, visited);
        return visited.size() ==n;
    }

    private void dfs(int current, List<List<Integer>> adjacencyList, Set<Integer> visited) {
        for(Integer neighbour: adjacencyList.get(current)) {
            if(!visited.contains(neighbour)) {
                visited.add(neighbour);
                dfs(neighbour, adjacencyList, visited);
            }
        }
    }

    public boolean validTree4(int n, int[][] edges) {
        // To solve using BFS , we will check condition 1 and 2a
        //  1. Graph has n-1 edges, where n is no of nodes/vertices
        //  2. Either of the following
        //     a) Each node is connected to every other node, i.e. graph is fully connected
        //     b) Graph contains no cycle,i.e. Once two nodes are connected directly or indirectly, there should be no edge connecting them again
        if(edges.length !=n-1) {
            return false;
        }
        // using recursive DFS
        //first we prepare adjacency list
        List<List<Integer>> adjacencyList = new ArrayList<>();
        for(int i=0; i< n;i++) {
            adjacencyList.add(new ArrayList<>());
        }
        for(int[] edge : edges) {
            adjacencyList.get(edge[0]).add(edge[1]);
            adjacencyList.get(edge[1]).add(edge[0]);
        }

        // for BFS, we use Queue
        Queue<Integer> q = new LinkedList<>();
        // to check that a Graph is fully connected, we use a Set containing all visited nodes.
        // We start from Node 0 and visit all connected nodes. At the end the visited Set should contain n number of nodes for the grapoh to be fully connected
        // In other words, from Node 0, we should be able to visit every node
        Set<Integer> visited =new HashSet<>();
        q.offer(0);
        visited.add(0);
        while(!q.isEmpty()) {
            int current = q.poll();
            for(Integer neighbour : adjacencyList.get(current)) {
                if(!visited.contains(neighbour)) {
                    visited.add(neighbour);
                    q.offer(neighbour);
                }
            }
        }
        return visited.size() ==n;
    }
}