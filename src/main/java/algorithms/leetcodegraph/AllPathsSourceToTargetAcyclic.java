package algorithms.leetcodegraph;

//Given a directed acyclic graph (DAG) of n nodes labeled from 0 to n - 1, find all possible paths from node 0 to node n - 1 and return them in any order.
//
//The graph is given as follows: graph[i] is a list of all nodes you can visit from node i (i.e., there is a directed edge from node i to node graph[i][j]).
//
//Input: graph = [[1,2],[3],[3],[]]
//Output: [[0,1,3],[0,2,3]]
//Explanation: There are two paths: 0 -> 1 -> 3 and 0 -> 2 -> 3.

// Input: graph = [[4,3,1],[3,2,4],[3],[4],[]]
//Output: [[0,4],[0,3,4],[0,1,3,4],[0,1,2,3,4],[0,1,4]]

//Constraints:
//
//n == graph.length
//2 <= n <= 15
//0 <= graph[i][j] < n
//graph[i][j] != i (i.e., there will be no self-loops).
//All the elements of graph[i] are unique.
//The input graph is guaranteed to be a DAG.

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class AllPathsSourceToTargetAcyclic {

    // acyclic graph
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        // graph is already given, no need of an adjacency list
        // each index in the array contains an array of neighbours
        // we have to find all paths from node 0 to node n-1
        // nogte that this is a directed graph
        List<List<Integer>> result = new ArrayList<>();
        int target = graph.length-1;
        // which all data structures do we require?
        // 1. a list for current path
        // 2. A Stack containing all eligible paths
        // 3. Do we require a visited set or boolean array? The answer is NO, because this is an "acyclic" graph.
        // 4. A result list
        // Once we have visited a node, we can never reach that node again by following a path

        // all paths have to start from 0, so add 0 to list
        List<Integer> path = new ArrayList<>();
        path.add(0);
        Stack<List<Integer>> stack = new Stack<>();
        stack.push(path);
        while (!stack.isEmpty()) {
            List<Integer> currentPath = stack.pop();
            int lastNodeInCurrentPath = currentPath.get(currentPath.size()-1);
            if(lastNodeInCurrentPath==target) {
                result.add(currentPath);
                continue;
            }
            //     1
            //  0
            //     2    3
            // start node 1, target 3
            for(int neighbour: graph[lastNodeInCurrentPath]) {
                    List<Integer> nextPath = new ArrayList<>(currentPath);
                    nextPath.add(neighbour);
                    stack.add(nextPath);
            }
        }
        return result;
    }

    public List<List<Integer>> allPathsSourceTarget2(int[][] graph) {
        // recursive DFS
        // which all data structures do we require?
        // 1. a list for current path
        // 2. Do we require a visited set or boolean array? The answer is NO, because this is an "acyclic" graph.
        // 3. result list
        int target = graph.length-1;
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> currentPath = new ArrayList<>();
        currentPath.add(0);
        dfs(result, currentPath, graph, target);
        return result;
    }

    private void dfs(List<List<Integer>> result, List<Integer> currentPath, int[][] graph, int target) {
        int lastNodeCurrentPath = currentPath.get(currentPath.size()-1);
        if(lastNodeCurrentPath == target) {
            result.add(currentPath);
            return;
        }
        for(int neighbour : graph[lastNodeCurrentPath]) {
            // Two cases to terminate the recursion
            // 1. As we keep on visiting neighbours of deeper nodes, as this is an acyclic graph, there will come a node from where we can't go any further
            // that will help in terminating the recursion
            // 2. We will reach target node
            List<Integer> nextPath = new ArrayList<>(currentPath);
            nextPath.add(neighbour);
            dfs(result, nextPath, graph, target);
        }
    }

    // using backtracking
    public List<List<Integer>> allPathsSourceTarget3(int[][] graph) {
        // recursion with backtracking
        // which all data structures do we require?
        // 1. a list for current path
        // 2. Do we require a visited set or boolean array? The answer is NO, because this is an "acyclic" graph.
        // 3. result list
        int target = graph.length-1;
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> currentPath = new ArrayList<>();// we can use LinkedList and then use addlast(int n) and removeLast() methods
        currentPath.add(0);
        backtrack(result, currentPath, graph, target);
        return result;
    }

    private void backtrack(List<List<Integer>> result, List<Integer> currentPath, int[][] graph, int target) {
        int lastNodeCurrentPath = currentPath.get(currentPath.size()-1);
        if(lastNodeCurrentPath == target) {
            result.add(new ArrayList<>(currentPath));
            return;
        }
        for(int neighbour : graph[lastNodeCurrentPath]) {
            // Two cases to terminate the recursion
            // 1. As we keep on visiting neighbours of deeper nodes, as this is an acyclic graph, there will come a node from where we can't go any further
            // that will help in terminating the recursion
            // 2. We will reach target node
            currentPath.add(neighbour);
            backtrack(result, currentPath, graph, target);
            currentPath.remove(currentPath.size()-1);// we are passing the index to the remove method of list
        }
    }
}
