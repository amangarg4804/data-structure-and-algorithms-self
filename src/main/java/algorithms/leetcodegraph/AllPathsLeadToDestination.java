package algorithms.leetcodegraph;

//Given the edges of a directed graph where edges[i] = [ai, bi] indicates there is an edge between nodes ai and bi, and two nodes source and destination of this graph, determine whether or not all paths starting from source eventually, end at destination, that is:
//
//At least one path exists from the source node to the destination node
//If a path exists from the source node to a node with no outgoing edges, then that node is equal to destination.
//The number of possible paths from source to destination is a finite number.
//Return true if and only if all roads from source lead to destination.

//Input: n = 3, edges = [[0,1],[0,2]], source = 0, destination = 2
//Output: false
//Explanation: It is possible to reach and get stuck on both node 1 and node 2.

//Input: n = 4, edges = [[0,1],[0,3],[1,2],[2,1]], source = 0, destination = 3
//Output: false
//Explanation: We have two possibilities: to end at node 3, or to loop over node 1 and node 2 indefinitely.

//Input: n = 4, edges = [[0,1],[0,2],[1,3],[2,3]], source = 0, destination = 3
//Output: true

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//Constraints:
//
//1 <= n <= 104
//0 <= edges.length <= 104
//edges.length == 2
//0 <= ai, bi <= n - 1
//0 <= source <= n - 1
//0 <= destination <= n - 1
//The given graph may have self-loops and parallel edges.
//NOTE:In a graph, if a pair of vertices is connected by more than one edge, then those edges are called parallel edges e.g a->b b<-a
public class AllPathsLeadToDestination {
    public boolean leadsToDestination(int n, int[][] edges, int source, int destination) {
        //Memory Limit Exceeded Time limit exceeded.55 / 56 testcases passed
        // bad problem description
        // Main points:
        // 1. It is a directed graph
        // 2. Graph can have cycles and parallel edges
        // 3. If the graph contains a cycle then it can infinitely loop and never reach destination so we return false
        // 4. vertices are from 0 to n-1
        // 5. A node can be visited multiple times through different paths so we have to backtrack and mark it not visited
        // 6. we have to make sure atleast one path exists from source to destination. e.g. if the graph has just one node then answer is false
        // 7. Graph may not be connected. There could be stray nodes
        //8. It is given that the graph will for sure have one path from source to destination
        // Step 1: create a graph. We don't know how many neighbours each vertex have before iterating,
        // so we can't use a fixed size array to represent graph for this problem. Let's use a map
        if (n == 1 && edges.length == 0 && source == destination) {
            return true;
        }
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int[] edge : edges) {
            int start = edge[0];
            int end = edge[1];
            List<Integer> neighbours = graph.getOrDefault(start, new ArrayList<>());
            neighbours.add(end);
            graph.put(start, neighbours);
        }
        // if the expected destination itself has outgoing edges, then we return false. A destination should not have any outgoing edges
        //source node must have an edge
        if (graph.containsKey(destination) || !graph.containsKey(source)) {
            return false;
        }
        // We only need to only look for destination node now across each path from source, no need to worry about outgoing edges from destination


        // Step 2: Perform DFS, start from source node
//        List<List<Integer>> allPaths = new ArrayList<>();
        //how do we check that atleast one path exist? We can add all paths to result and check the list size is >1
        // or we only return true if we found destination. We have already checked above that graph contains source node as key and return false if it doesn't
        // This essentially means that there are edges starting from source node. And since we will return false as soon as we find a single path not leading to destination
        // We will return false. Hence we don't need to keep track of paths
        // If the problem asked for returning all paths too, then we would create the allPaths and currentPath List
        // at the same time how do we know none of the path ends at a node other than destination? - We can make the method return boolean
        // visited array- same node can be visited multiple time in different paths but it is not allowed to be visited twice in same path
        // example: if source =1, destination = 3
        // there could be an edge 1-> 2-> 3 (after this path we should mark 2 and 3 as not visited)
        // or 1-> 2-> 4-> 3
        boolean[] visited = new boolean[n];
        visited[source] = true;
        List<Integer> currentPath = new ArrayList<>();
        currentPath.add(source);
        boolean result = backtrack(graph, source, destination, visited); //, allPaths, currentPath );
        //NOTE: As the problem don't ask us about an
        return result; //&& !allPaths.isEmpty();
    }

    private boolean backtrack(Map<Integer, List<Integer>> graph, int source, int destination, boolean[] visited) {//, List<List<Integer>> allPaths, List<Integer> currentPath)
        if (!graph.containsKey(source) && source != destination) {
            return false;
        }
        if (source == destination) {
//            allPaths.add( new ArrayList<>(currentPath));
            return true;
        }
        for (int neighbour : graph.get(source)) {
            if (visited[neighbour]) { // if a neighbour was already visited along this path, it means that there is a cycle and we have reached this node twice in the same path
                return false;
            }
            visited[neighbour] = true;
//            currentPath.add(neighbour);
            boolean leadingToDestination = backtrack(graph, neighbour, destination, visited);//, allPaths, currentPath);
            if (!leadingToDestination) {
                return false;
            }
            visited[neighbour] = false;
            //currentPath.remove(currentPath.size()-1);
        }
        return true;
    }

    public boolean leadsToDestination2(int n, int[][] edges, int source, int destination) {
        // https://drive.google.com/file/d/1H3dZgdAyol9gq6VxJe9NWmom612AwNQ4/view?usp=drive_link
        // the above solution is inefficient. Once we know that a node leads to destination, we don't need to recalculate the path from that node
        // For example: if we have 3 paths from 1 to 5
        // 1->4->3->5 (here we know that node 3 leads to destination 5)
        // 1->2->3->5 (when we visit 3 again, we shouldn't be checking whether 3 leads to 5)
        // 1->6->3->5

        // How to implement? instead of primitive boolean visited array, which can store only true false. we can have a wrapper Boolean array storing whether the node leads to a result
        // null value- not visited
        // true- leads to destination
        // false- doesn't lead to destination or all child nodes of current node are not yet processed. This will help in detecting cycle when we visit this already visited node again
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int[] edge : edges) {
            int start = edge[0];
            int end = edge[1];
            List<Integer> neighbours = graph.getOrDefault(start, new ArrayList<>());
            neighbours.add(end);
            graph.put(start, neighbours);
        }
        // if the expected destination itself has outgoing edges, then we return false. A destination should not have any outgoing edges
        //source node must have an edge
        if (graph.containsKey(destination)) {
            return false;
        }
        Boolean[] result = new Boolean[n];
        return dfs(graph,result,source, destination);
    }

    private boolean dfs(Map<Integer, List<Integer>> graph, Boolean[] result, int source, int destination) {
        if(result[source]!=null) {
            return result[source];
        }
        if(!graph.containsKey(source)) {
            // if no outgoing edges from this node, then this node must be destination for the result to be true, otherwise we found a path that doesn't lead to destination
            return source == destination;
        }
        // we have to mark the node as visited, to detect cycle
        // if we don't do this, we will get a stackoverflow error because of cycle in graph
        // we set the result to false.
        result[source] =false;
        for(Integer neighbour : graph.get(source)) {
            boolean leadsToDestination = dfs(graph, result, neighbour, destination);
            if(!leadsToDestination) {
                return false;
            }
        }
        // if the for loop ends, meaning that we have visited all the paths starting from this node and haven't found any path that doesn't lead to destination
        // so the result is true
        result[source]=true;
        return true;
    }

    private boolean dfs2(Map<Integer, List<Integer>> graph, Boolean[] result, int source, int destination) {
        if(result[source]!=null) {
            return result[source];
        }
        if(!graph.containsKey(source)) {
            // if no outgoing edges from this node, then this node must be destination for the result to be true, otherwise we found a path that doesn't lead to destination
            return source == destination;
        }
        // we have to mark the node as visited, to detect cycle
        // if we don't do this, we will get a stackoverflow error because of cycle in graph
        // we set the result to false.
        result[source] =false;
        for(Integer neighbour : graph.get(source)) {
            boolean leadsToDestination = dfs(graph, result, neighbour, destination);
            if(!leadsToDestination) {
                return false;
            }
        }
        // if the for loop ends, meaning that we have visited all the paths starting from this node and haven't found any path that doesn't lead to destination
        // so the result is true
        result[source]=true;
        return true;
    }

}