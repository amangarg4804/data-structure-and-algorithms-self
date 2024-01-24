package algorithms.leetcodegraph;

//There are n houses in a village. We want to supply water for all the houses by building wells and laying pipes.
//
//For each house i, we can either build a well inside it directly with cost wells[i - 1] (note the -1 due to 0-indexing), or pipe in water from another well to it. The costs to lay pipes between houses are given by the array pipes where each pipes[j] = [house1j, house2j, costj] represents the cost to connect house1j and house2j together using a pipe. Connections are bidirectional, and there could be multiple valid connections between the same two houses with different costs.
//
//Return the minimum total cost to supply water to all houses.

//Input: n = 3, wells = [1,2,2], pipes = [[1,2,1],[2,3,1]]
//Output: 3
//Explanation: The image shows the costs of connecting houses using pipes.
//The best strategy is to build a well in the first house with cost 1 and connect the other houses to it with cost 2 so the total cost is 3.

//Input: n = 2, wells = [1,1], pipes = [[1,2,1],[1,2,2]]
//Output: 2
//Explanation: We can supply water with cost two using one of the three options:
//Option 1:
//  - Build a well inside house 1 with cost 1.
//  - Build a well inside house 2 with cost 1.
//The total cost will be 2.
//Option 2:
//  - Build a well inside house 1 with cost 1.
//  - Connect house 2 with house 1 with cost 1.
//The total cost will be 2.
//Option 3:
//  - Build a well inside house 2 with cost 1.
//  - Connect house 1 with house 2 with cost 1.
//The total cost will be 2.
//Note that we can connect houses 1 and 2 with cost 1 or with cost 2 but we will always choose the cheapest option.


import java.util.*;

public class OptimizeWaterDistribution {

    // 3 houses . wells[10, 5, 10] pipe[{0,1,1}, {0,2,1}, {1,2, 1} ]
    // In the above example, we should put a well at house 1 with cost 5 and put pipes from house 0 and 2 with cost 1. Total cost = 5+1+1 =7
    // 3 houses . wells[10, 5, 10] pipe[{0,1,50}, {0,2,100}, {1,2, 100} ]
    // Here the cost of pipes is huge it is better to build well at each house
    // So, how do we decide to build a well or a pipe?
    // if we build a well at any house, the cost of pipe for previous house may change
    // How to choose where to build a well? We can't simply choose to build a well at a house with least building well cost
    // because it is possible that although the cost of building a well is less at house1 but cost of pipes from house 1 is huge
    // if the cost of building pipe for house 1 is huge, it is huge bi-directionally because pipes are bidirectional
    // let's take a smaller examples with just 2 houses. For any number of houses, we have to build one well atleast
    // 2 houses: wells[2,2] pipe[{0,1,5},{0,1,10}]- better to build wells at each house.Note that it is possible to have pipes connecting same houses with different costs
    // 2 houses: wells[5,10] pipe[{0,1,5} ]- in this case, we build the well at a house with lowest well building cost
    public int minCostToSupplyWater(int n, int[] wells, int[][] pipes) {
        // use Minimum Spanning tree
        // Prim's algorithm- a greedy way to create MST, we start from any random node and keep adding more nodes to MST based on lowest edge weight
        // MST edges have weights, nodes themselves don't have weight. But here each node has a weight too. The cost to create a well at that node
        // To reduce this problem to MST problem, we will require a virtual node
        //1. First we create a weighted graph, we use List<List<Pair>> (we can also use a Map<Map<Integer, Integer>>) Pair can have target houseNo and cost of building pipe from source to target house
        // 2. We also require a set to keep track of nodes(houses) that are already added to MST
        // 3. When we start from a random node, there can be multiple edges connecting this node. We always require the edge with least weight.
        // We can use priority queue to add all edges so we always poll the edge with least weight
        List<List<Pair>> graph = new ArrayList<>();
        for(int i=0; i< n+1; i++) { // n houses + 1 virtual house, we want adjacency list for each of these nodes including the virtual node
            graph.add(new ArrayList<>());
        }
        //create a virtual pipe from node 0 to each node to represent cost of building well
        for(int i=0; i< n; i++) {
            int wellCost =wells[i];
            graph.get(0).add(new Pair(i+1, wellCost));//// create an edge from virtual node to each node with weight equals the cost of building well
//            graph.get(i+1).add(new Pair(0, wellCost)); The code works with or without this edge because we start creating MST with Node 0,i.e we add 0 to set, and never visit Node 0 again
        }
        // create the graph
        for(int[] pipe: pipes) {
            int house1 = pipe[0];
            int house2 = pipe[1];
            int cost = pipe[2];
            graph.get(house1).add(new Pair(house2, cost));
            graph.get(house2).add(new Pair(house1, cost));
        }
        Set<Integer> set = new HashSet<>();
        // we add node 0 to set first (note that we can choose any node, we just randomly chose virtual node)
        set.add(0);
        // after we add node 0, we can visit any of the neighbours of node 0
        // we want to visit the neighbour with least cost. So we put all neighbours in priority q
        PriorityQueue<Pair> pq = new PriorityQueue<>((p1, p2) -> p1.pipeCost-p2.pipeCost);
        for(Pair neighbour: graph.get(0)) {
            pq.add(neighbour);
        }
        int cost =0;
        // create MST by greedily adding neighbours with least cost
        while (set.size() != n+1) {// MST is created only when all the nodes of a graph are present in Set
            Pair next = pq.poll();
            // it is possible that the next is already visited (added to MST)
            if(set.contains(next.targetHouseNo)) {
                continue;
            }
            set.add(next.targetHouseNo);
            cost+=next.pipeCost;
            // once we have added the node to MST. All its neighbours are now eligible to be part of MST
            // so we add them to pq
            for(Pair neighbour: graph.get(next.targetHouseNo)) {
                if(!set.contains(neighbour.targetHouseNo)) {// add only if the node is not already part of MST
                    pq.add(neighbour);
                }
            }
        }
        return cost;
    }
    private static class Pair {
        int targetHouseNo; // the word target can be misleading, these are bidirectional edges.
        int pipeCost;

        public Pair(int targetHouseNo, int pipeCost) {
            this.targetHouseNo = targetHouseNo;
            this.pipeCost = pipeCost;
        }
    }

    public int minCostToSupplyWater2(int n, int[] wells, int[][] pipes) {
        // Another way to form an MST is by using Kruskal's Algorithm using Disjoint set
        // In prim's algorithm we used greedy approach to add nodes one by one
        // here we won't add nodes, but we will sort all edges by their weight and join  them
        // we won't be using a set data structure but a Disjoint Set
        // Here too, we will create a virtual node to handle well cost
        // We require a sorted list of weighted edges. we can either create a class Edge with house1, house2 and weight or we can use an int[] array
        // a class is more readable
        List<Edge> edges = new ArrayList<>();
        //first let's create edges for wells, for each well create the edge from corresponding house no to 0th node
        // notice that edges are automatically bidirectional when we use the Edge class, no need to add twice
        for(int i=0; i< wells.length; i++) {
            edges.add(new Edge(0, i+1, wells[i])); // house numbers inside wells array are 1 based, so we do i+1
        }
        //we now add edges for all pipes
        for(int i=0; i< pipes.length; i++) {
            edges.add(new Edge(pipes[i][0], pipes[i][1], pipes[i][2]));
        }
        // sort the edges by their cost
        Collections.sort(edges, (e1,e2) -> e1.cost -e2.cost);
        // create a disjoint set with size equals no of houses + 1 for virtual house/node
        OptimizedDisjointSet ds = new OptimizedDisjointSet(n+1);
        // iterate over all edges and add the edge if not already connected
        // keep track of total cost
        int cost=0;
        for(Edge edge : edges) {
            if(ds.find(edge.house1) != ds.find(edge.house2)) {// roots are not equal, in other words, the nodes are not connected
                // add the node to MST and increment cost
                ds.union(edge.house1, edge.house2);
                cost+=edge.cost;
            }
            // Possible optimization: we know that union function itself calls find() method of given nodes and performs merge
            // only if result of find() is not equal. What we can do is make the union method return a boolean. If union methods starts returning whether the merge
            // operation was performed or not, the won't require to check the find method condition here. This optimization will reduce calls to find method

        }
        return cost;
     }

     private static class Edge {
        private int house1;
        private int house2;
        private int cost;

         public Edge(int house1, int house2, int cost) {
             this.house1 = house1;
             this.house2 = house2;
             this.cost = cost;
         }
     }
}
