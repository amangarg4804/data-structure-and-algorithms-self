package algorithms.leetcode.graph;

// A tree is an undirected graph in which any two vertices are connected by exactly one path. In other words, any connected graph without simple cycles is a tree.
//
//Given a tree of n nodes labelled from 0 to n - 1, and an array of n - 1 edges where edges[i] = [ai, bi] indicates that there is an undirected edge between the two nodes ai and bi in the tree, you can choose any node of the tree as the root. When you select a node x as the root, the result tree has height h. Among all possible rooted trees, those with minimum height (i.e. min(h))  are called minimum height trees (MHTs).
//
//Return a list of all MHTs' root labels. You can return the answer in any order.
//
//The height of a rooted tree is the number of edges on the longest downward path between the root and a leaf.


// Input: n = 4, edges = [[1,0],[1,2],[1,3]]
//Output: [1]
//Explanation: As shown, the height of the tree is 1 when the root is the node with label 1 which is the only MHT.

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

//Input: n = 6, edges = [[3,0],[3,1],[3,2],[3,4],[5,4]]
//Output: [3,4]
public class MinimumHeightTrees {
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        //The distance between two nodes is the number of edges that connect the two nodes.
        //  since the input graph can form a tree from any node, as specified in the problem, there could only be one path between any two nodes.
        // In addition, there would be no cycle in the graph.
        //  there would be no ambiguity in the above definition of "distance"
        // The height of a tree can be defined as the maximum distance between the root and all its leaf nodes.
        // With the above definitions, we can rephrase the problem as finding out the nodes that are overall close to all other nodes, especially the leaf nodes.



        // For the tree-alike graph, the number of centroids is no more than 2.
        // If the number of nodes is even, then there would be two centroids.
        // If the number of nodes is odd, then there would be only one centroid.


        // The idea is that we trim out the leaf nodes layer by layer, until we reach the core of the graph, which are the centroids nodes.
        // Once we trim out the first layer of the leaf nodes (nodes that have only one connection), some of the non-leaf nodes would become leaf nodes.

        // 1. If the nodes are less than 2, then the result should contain all nodes
        List<Integer> result = new ArrayList<>();
        if(n<2) {
            for(int i =0; i< n ; i++) {
                result.add(i);
            }
            return result;
        }
        // 2. prepare the adjacency list
        List<Set<Integer>> graph = new ArrayList<>();
        for(int i=0; i<n ; i++) {
            graph.add(new HashSet<>());
        }
        for(int[] edge: edges) {
            int node1 = edge[0];
            int node2 = edge[1];
           graph.get(node1).add(node2);
           graph.get(node2).add(node1);
        }

        // 3. Now we need to find out the nodes which have only one edge (which have only one neighbor, or we can call them leaves),
        // these nodes can't be minimum height nodes or centroids
        // we need a data structure to store them, later we will remove these nodes from graph and find out next leaf nodes
        // and these new leaf nodes then will be the candidate for removal
        // but this way, we will delete all the nodes, when do we stop?
        // Let's track the nodesRemaining in graph/tree by a variable. And when this nodesRemaining variable reaches less than 2 we can stop
        Queue<Integer> q = new LinkedList<>();
        int nodesRemaining = n;
        for(int i=0; i < n ;i++) {
            if(graph.get(i).size() ==1) {
                q.offer(i);
            }
        }

        while ( nodesRemaining >2) {
            // we don't need to break both the links - neighbor to leaf node and leaf node to neighbor
            // only removing the incoming path to leaf node is enough
            int queueSize  = q.size();
            // We have to be careful here. Make sure that we poll all the nodes within the while loop
            // that were present in queue when we entered in while loop
            // 1. We can't have a condition (!q.isEmpty) in the outer while loop (and avoid the inner for loop) because
            // it will lead to some nodes that were in the queue when we entered the while loop to never get polled
            // 2. We can't have a condition while(!q.isEmpty) inside the condition while ( nodesRemaining >2)
            // because that will lead to the result nodes also getting into the queue and then getting polled.
            // This will lead to an empty result always
            for(int i=0 ; i< queueSize; i++ ) {
                Integer leafNode = q.poll();
                nodesRemaining--;
                graph.get(leafNode).forEach(
                        neighbour ->
                        {
                            graph.get(neighbour).remove(leafNode);
                            if (graph.get(neighbour).size() == 1) {
                                q.offer(neighbour);
                            }
                        });
            }
        }
        while (!q.isEmpty()) {
            result.add(q.poll());
        }
        return result;
    }
}
