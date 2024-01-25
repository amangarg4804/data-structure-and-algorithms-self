package algorithms.leetcodegraph;

//There is a bi-directional graph with n vertices, where each vertex is labeled from 0 to n - 1 (inclusive).
// The edges in the graph are represented as a 2D integer array edges, where each edges[i] = [ui, vi] denotes a bi-directional edge between vertex ui
// and vertex vi. Every vertex pair is connected by at most one edge, and no vertex has an edge to itself.
//You want to determine if there is a valid path that exists from vertex source to vertex destination.
//
//Given edges and the integers n, source, and destination, return true if there is a valid path from source to destination, or false otherwise.


//Input: n = 3, edges = [[0,1],[1,2],[2,0]], source = 0, destination = 2
//Output: true
//Explanation: There are two paths from vertex 0 to vertex 2:
//- 0 → 1 → 2
//- 0 → 2

//Input: n = 6, edges = [[0,1],[0,2],[3,5],[5,4],[4,3]], source = 0, destination = 5
//Output: false
//Explanation: There is no path from vertex 0 to vertex 5.

//Constraints:
//
//1 <= n <= 2 * 105
//0 <= edges.length <= 2 * 105
//edges[i].length == 2
//0 <= ui, vi <= n - 1
//ui != vi
//0 <= source, destination <= n - 1
//There are no duplicate edges.
//There are no self edges.

import java.util.*;

public class PathExistsInGraph {

    public boolean validPath(int n, int[][] edges, int source, int destination) {
        //iterative
        // first we prepare the graph data structure using adjacency list
        // then we start from the source node and add it to Stack
        // we visit all neighbours of source node and for each source to neighbour path, go deeper to check if it can lead to destination node
        // we can mark each neighbour as visited
        // n vertices. each vertex is labeled from 0 to n - 1 (inclusive) so we can use List of List for preparing adjacency list.
        // Index of outer list will represent the label of vertex and the correspoding list will represent neighbours
        List<List<Integer>> adjacencyList = new ArrayList<>();
        for(int i=0; i< n;i++) {
            adjacencyList.add(new ArrayList<>());
        }
        for(int[]edge: edges ) {
            adjacencyList.get(edge[0]).add(edge[1]);
            adjacencyList.get(edge[1]).add(edge[0]); //bidirectional edge
        }
        // once the graph is prepared, we push the source node to stack to start dfs on its neighbours to find path to destination
        Stack<Integer> stack = new Stack<>();
        Set<Integer> visited = new HashSet<>();
        stack.add(source);
        visited.add(source); // we don't want a path with source node twice. So we mark it as visited. It won't get pushed to stack again
        return dfs(destination, stack, adjacencyList, visited);
    }

    private static boolean dfs(int destination, Stack<Integer> stack, List<List<Integer>> adjacencyList, Set<Integer> visited) {
        while (!stack.isEmpty()) {
            int current = stack.pop();
            if(current== destination){
                return true;
            }
            for(int neighbour : adjacencyList.get(current)) {
                if(!visited.contains(neighbour)) {
                    stack.push(neighbour);
                    visited.add(neighbour);
                }
            }
        }
        return false;
    }

    private static boolean dfs2(int destination, Stack<Integer> stack, List<List<Integer>> adjacencyList, Set<Integer> visited) {
        // order of adding to visited set changed
        while (!stack.isEmpty()) {
            int current = stack.pop();
            visited.add(current);
            if(current== destination){
                return true;
            }
            for(int neighbour : adjacencyList.get(current)) {
                if(!visited.contains(neighbour)) {
                    stack.push(neighbour);
                }
            }
        }
        return false;
    }

    public boolean validPath2(int n, int[][] edges, int source, int destination) {
        //recursive
        // first we prepare the graph data structure using adjacency list
        // then we start from the source node and add it to Stack
        // we visit all neighbours of source node and for each source to neighbour path, go deeper to check if it can lead to destination node
        // we can mark each neighbour as visited
        // n vertices. each vertex is labeled from 0 to n - 1 (inclusive) so we can use List of List for preparing adjacency list.
        // Index of outer list will represent the label of vertex and the correspoding list will represent neighbours
        List<List<Integer>> adjacencyList = new ArrayList<>();
        for(int i=0; i< n;i++) {
            adjacencyList.add(new ArrayList<>());
        }
        for(int[]edge: edges ) {
            adjacencyList.get(edge[0]).add(edge[1]);
            adjacencyList.get(edge[1]).add(edge[0]); //bidirectional edge
        }
        // once the graph is prepared, we push the source node to stack to start dfs on its neighbours to find path to destination
        Set<Integer> visited = new HashSet<>();
        visited.add(source); // we don't want a path with source node twice. So we mark it as visited. It won't get pushed to stack again
        return dfsRecursive(adjacencyList, visited, source, destination);
    }

    private boolean dfsRecursive( List<List<Integer>> adjacencyList, Set<Integer> visited, int source, int destination) {
        if(source==destination) {
            return true;
        }
        for(int neighbour : adjacencyList.get(source)) {
            if(!visited.contains(neighbour)) {
                visited.add(neighbour);
                boolean result = dfsRecursive(adjacencyList, visited, neighbour, destination);// can't directly return from here till we have checked all neighbours
                if(result) {
                    return true;
                }
            }
        }
        return false;
    }
    public boolean validPath3(int n, int[][] edges, int source, int destination) {
        //BFS
        // first we prepare the graph data structure using adjacency list
        // then we start from the source node and add it to Queue
        // we can mark each neighbour as visited
        // n vertices. each vertex is labeled from 0 to n - 1 (inclusive) so we can use List of List for preparing adjacency list.
        // Index of outer list will represent the label of vertex and the corresponding list will represent neighbours
        List<List<Integer>> adjacencyList = new ArrayList<>();
        for(int i=0; i< n;i++) {
            adjacencyList.add(new ArrayList<>());
        }
        for(int[]edge: edges ) {
            adjacencyList.get(edge[0]).add(edge[1]);
            adjacencyList.get(edge[1]).add(edge[0]); //bidirectional edge
        }
        // once the graph is prepared, we push the source node to queue to start BFS
        Queue<Integer> q = new LinkedList<>();
        q.offer(source);
        boolean[] visited =new boolean[n];
        visited[source] = true;
        while(!q.isEmpty()) {
            int current = q.poll();
            if(current == destination) {
                return true;
            }
            for(int neighbour : adjacencyList.get(current)) {
                if(!visited[neighbour]) {
                    visited[neighbour]=true;
                    q.offer(neighbour);// Note that we could also check q.size and run two nested for loops to visit level by level.
                    // In both ways, we are visiting level by level because we use queue the nodes added at the end will be visited last
                }
            }
        }
        return false;
        //time complexity is O(V+E). We build adjacent list of all E edges in graph which takes O(E).
        //Each node is added to the queue and popped from queue once, it takes O(V) to handle all nodes.
        //Space complexity: O(n+m)O(n + m)O(n+m)
        //Space complexity: O(V+E)
        // We used a list of list  to store all edges, which requires O(E) space for E edges.
        // We use seen, either a hash set or an array to record the visited nodes, which takes O(V) space.
        //There may be up to V nodes stored in queue and O(V) space is required.
    }

    public boolean validPath4(int n, int[][] edges, int source, int destination) {
        // using Disjoint set
        // First we prepare disjoint set by connecting all edges
        OptimizedDisjointSet ds = new OptimizedDisjointSet(n);
        for(int[]edge : edges) {
            ds.union(edge[0],edge[1]);
        }
        // There is a path from source and destination if they belong to same connected component
        // in other words, when they have same root
        return ds.find(source)==ds.find(destination);
        //Time complexity: O(m⋅α(n))
        //The amortized complexity for performing m union find operations is O(m⋅α(n)) where α/alpha is the Inverse Ackermann Function.
        //To sum up, the overall time complexity is O(m⋅α(n))
        //Space complexity: O(n)
        //We used two arrays root and rank to save the root and rank of each node in the DSU data structure, each of them takes O(n) space.
        //To sum up, the overall time complexity is O(n)
    }


}
