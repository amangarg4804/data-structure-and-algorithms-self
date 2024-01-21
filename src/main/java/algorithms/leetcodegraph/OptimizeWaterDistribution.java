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


}
