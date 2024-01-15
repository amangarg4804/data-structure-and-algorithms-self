package algorithms.leetcodegraph;


//You have a graph of n nodes. You are given an integer n and an array edges where edges[i] = [ai, bi] indicates that there is an edge between ai and bi in the graph.
//
//Return the number of connected components in the graph.

//Input: n = 5, edges = [[0,1],[1,2],[3,4]]
//Output: 2

//Input: n = 5, edges = [[0,1],[1,2],[2,3],[3,4]]
//Output: 1

//Constraints:
//
//1 <= n <= 2000
//1 <= edges.length <= 5000
//edges[i].length == 2
//0 <= ai <= bi < n
//ai != bi
//There are no repeated edges.

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class NoOfConnectedComponents {

    public int countComponents(int n, int[][] edges) {
        // To count no of connected components we can use Disjoint Set
        // Initially, we can keep no of connected components as no of nodes, In other words, evey node is a component in itself not connected to any other node
        // As we iterate over edges, we decrement no of connected components if the nodes forming the edge were not already connected
        int count = n;
        OptimizedDisjointSet optimizedDisjointSet = new OptimizedDisjointSet(n);
        for(int[] edge: edges) {
            if(!optimizedDisjointSet.connected(edge[0], edge[1])) {
                optimizedDisjointSet.union(edge[0],edge[1]);
                count--;
            }
        }
        return count;
    }

    public int countComponents2(int n, int[][] edges) {
        // To count no of connected components we can use DFS
        // First we will have to create Adjacency list
        List<List<Integer>> adjacencyList = new ArrayList<>();
        for(int i=0; i< n; i++) {
            adjacencyList.add(new ArrayList<>());
        }
        for(int[] edge: edges) {
            adjacencyList.get(edge[0]).add(edge[1]);
            adjacencyList.get(edge[1]).add(edge[0]);
        }
        int count = 0;
//        Set<Integer> visited = new HashSet<>();
        boolean[] visited = new boolean[n];
        for(int i=0; i< n; i++) {
            if(!visited[i]) {
                count++;
                dfs(i, visited, adjacencyList);
            }
        }
         return count;
        //Time complexity: O(E+V)
        //Building the adjacency list will take O(E) operations, as we iterate over the list of edges once,
        // and insert each edge into two lists.
        //During the DFS traversal, each vertex will only be visited once. This is because we mark each vertex as visited as soon as we see it,
        // and then we only visit vertices that are not marked as visited. In addition, when we iterate over the edge list of each vertex,
        // we look at each edge once.
        // This has a total cost of O(E+V)
        //Space complexity: O(E+V)
        //
        //Building the adjacency list will take O(E)space. To keep track of visited vertices, an array of size O(V){O}(V)O(V)
        // is required.
        // Also, the run-time stack for DFS will use O(V) space.
    }

    private void dfs(int i, boolean[] visited, List<List<Integer>> adjacencyList) {
        visited[i]=true;
        for(int neighbour: adjacencyList.get(i)) {
            if(!visited[neighbour]) {
                dfs(neighbour, visited, adjacencyList);
            }
        }
    }
}
