package algorithms.bp;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//You have n gardens, labeled from 1 to n, and an array paths where paths[i] = [xi, yi] describes a bidirectional path between garden xi to garden yi.
// In each garden, you want to plant one of 4 types of flowers.
//
//All gardens have at most 3 paths coming into or leaving it.
//
//Your task is to choose a flower type for each garden such that, for any two gardens connected by a path, they have different types of flowers.
//
//Return any such a choice as an array answer, where answer[i] is the type of flower planted in the (i+1)th garden.
// The flower types are denoted 1, 2, 3, or 4. It is guaranteed an answer exists.
//
//
//
//Example 1:
//
//Input: n = 3, paths = [[1,2],[2,3],[3,1]]
//Output: [1,2,3]
//Explanation:
//Gardens 1 and 2 have different types.
//Gardens 2 and 3 have different types.
//Gardens 3 and 1 have different types.
//Hence, [1,2,3] is a valid answer. Other valid answers include [1,2,4], [1,4,2], and [3,2,1]
public class FlowerPlantingNoAdjacent {

    public int[] gardenNoAdj(int n, int[][] paths) {
        List<ArrayList<Integer>> adjacencyList = new ArrayList<>();
        // initialize all adjacency lists with a new arraylist
        for(int i =0; i<=n; i++) { // it is a 1 based graph, so we add n+1 lists so we can use index 1 to n to prepare adjacency list
            adjacencyList.add(new ArrayList<>());
        }
        //paths - [[1,2],[2,3],[3,1]]
        // Adjacency list:
        // 1 -> 2,3
        // 2 -> 3,1
        // 3 -> 2,1
        for(int[] path : paths) {
            adjacencyList.get(path[0]).add(path[1]);
            adjacencyList.get(path[1]).add(path[0]);// as this is a undirected graph, the edges are bidirectional
        }
        int[] answers = new int[n]; // answer[i] is the type of flower planted in the (i+1)th garden.
        // now we want to iterate over the graph and fill the colour for each node

        for( int current =1; current<adjacencyList.size(); current++) {
            // for i=1, we are filling the answer for first node
            // which flower can be assigned in first node? - The one that has not been taken by any adjacent nodes
            // iterate over adjacent nodes
            Set<Integer> flowerTypes = new HashSet<>(Set.of(1, 2, 3, 4));// flowers range from 1 to 4
            for(int adj: adjacencyList.get(current)) {
                if(answers[adj-1] != 0) { // if the answer of a adjacent node is not 0, it means the flower was already chosen for this adjacent node. So this flower can't be chosen for current node
                    flowerTypes.remove(answers[adj - 1]);
                }
            } // An alternative solution could be to have two sets. Set1 with all flower types declared at class level and set2 (usedFlowersSet )could be prepared in above for loop
            answers[current-1] = flowerTypes.iterator().next(); // answers[current -1] is answer for current node because answer is 0 based and nodes are 1 based
        }
        return answers;
    }
}
