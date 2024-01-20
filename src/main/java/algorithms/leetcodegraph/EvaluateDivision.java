package algorithms.leetcodegraph;

//You are given an array of variable pairs equations and an array of real numbers values, where equations[i] = [Ai, Bi] and values[i] represent the equation Ai / Bi = values[i]. Each Ai or Bi is a string that represents a single variable.
//
//You are also given some queries, where queries[j] = [Cj, Dj] represents the jth query where you must find the answer for Cj / Dj = ?.
//
//Return the answers to all queries. If a single answer cannot be determined, return -1.0.
//
//Note: The input is always valid. You may assume that evaluating the queries will not result in division by zero and that there is no contradiction.
//
//Note: The variables that do not occur in the list of equations are undefined, so the answer cannot be determined for them.

//Input: equations = [["a","b"],["b","c"]], values = [2.0,3.0], queries = [["a","c"],["b","a"],["a","e"],["a","a"],["x","x"]]
//Output: [6.00000,0.50000,-1.00000,1.00000,-1.00000]
//Explanation:
//Given: a / b = 2.0, b / c = 3.0
//queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ?
//return: [6.0, 0.5, -1.0, 1.0, -1.0 ]
//note: x is undefined => -1.0

//Input: equations = [["a","b"],["b","c"],["bc","cd"]], values = [1.5,2.5,5.0], queries = [["a","c"],["c","b"],["bc","cd"],["cd","bc"]]
//Output: [3.75000,0.40000,5.00000,0.20000]

//Input: equations = [["a","b"]], values = [0.5], queries = [["a","b"],["b","a"],["a","c"],["x","y"]]
//Output: [0.50000,2.00000,-1.00000,-1.00000]

//Constraints:
//
//1 <= equations.length <= 20
//equations[i].length == 2
//1 <= Ai.length, Bi.length <= 5
//values.length == equations.length
//0.0 < values[i] <= 20.0
//1 <= queries.length <= 20
//queries[i].length == 2
//1 <= Cj.length, Dj.length <= 5
//Ai, Bi, Cj, Dj consist of lower case English letters and digits.

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class EvaluateDivision {

    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        // This is a graph problem. First we prepare a Graph data structure with weight. Weight from a to b is a/b
        // to calculate x/y, we have to do the following
        // 1. check if there is a path from  x to y (we use DFS)
        // 2. multiply path weights
        //
        // for example. if we are given a/b and b/c we create a graph with path  a->b->c and c->b-a.
        // if a/b =2 is given, Then path exists in both directions,i.e, path a to b with weight 2. And path b to a with weight 1/2
        // https://drive.google.com/file/d/1w3oHIezVsfY_n4yIua8dAh6kWD_s9-gz/view?usp=drive_link
        //Note that if we are given ac/ad. ac is not a*c. It is just another variable.
        Map<String, Map<String, Double>> graph = new HashMap<>(); // NOTE: the data structure used to initialize a graph with weights
        for(int i=0; i< equations.size(); i++) {
            String firstOperand = equations.get(i).get(0);
            String secondOperand = equations.get(i).get(1);
            double result = values[i];
            // so we create paths from firstOperand to second Operand  and secondOperand to firstOperand
            Map<String, Double> firstOperandToSecondOperandMap = graph.getOrDefault(firstOperand, new HashMap<>());
            Map<String, Double> secondOperandToFirstOperanddMap = graph.getOrDefault(secondOperand, new HashMap<>());
            firstOperandToSecondOperandMap.put(secondOperand, result);
            secondOperandToFirstOperanddMap.put(firstOperand, 1/result);
            graph.put(firstOperand, firstOperandToSecondOperandMap);
            graph.put(secondOperand, secondOperandToFirstOperanddMap);
        }

        // create a result array, iterate over all queries and populate the result array
        double[] result =new double[queries.size()];
        for(int i=0; i< queries.size(); i++) {
            String firstOperand = queries.get(i).get(0);
            String secondOperand = queries.get(i).get(1);
            // 3 cases:
            // Case 1: if either of the operand doesn't contain in graph, result is -1
             if(!graph.containsKey(firstOperand) || !graph.containsKey(secondOperand)) {
                result[i] =-1;
            }
             // Order of case1 and case 2 matters. The given question expects us to return -1 for query (x,x) instead of 1, when x is not contained in graph
            // Case 2. if both operands of query are same, result is 1
            else if(firstOperand.equals(secondOperand)) {
                result[i] = 1;
            }

            // Case 3: Check for path and prepare result
            else {
                result[i] = dfs(firstOperand, secondOperand, graph, new HashSet<>());
            }


        }
        return result;
    }

    private double dfs(String firstOperand, String secondOperand, Map<String, Map<String, Double>> graph, HashSet<Object> visited) {

        visited.add(firstOperand);
        // if we want to calculate path from a to b and graph is a-b. when we reach b, we will find a as the neighbour of b.
        // Since we add a to visited set, we won't call dfs for a again
        if(firstOperand.equals(secondOperand)) {
            return 1; // when we have reached our destination node
        }
        // check all neighbours of first operand
        // Node a has neighbours stored in a map. Each entry of this map is a neghbour of a
        for(Map.Entry<String, Double> neighbour: graph.get(firstOperand).entrySet()) {
            if(!visited.contains(neighbour.getKey()) ) {
                double neighbourResult =  dfs(neighbour.getKey(), secondOperand, graph, visited);
                // e.g. a->b->c is graph and query is a/c
                // a calls for b, b calls for c, c returns 1. b returns b*1. a returns a*b*1
                // a->b c is graph (note that c is not connected with a and b). query is a/c
                // a calls for b, b doesn't have any non-visited neighbours, b returns -1, a also returns -1
                if(neighbourResult!=-1) {
                    return neighbour.getValue() * neighbourResult;
                    // note that neighbour.getValue() contains the weight of source node to target node
                    // a->b if a is source node. Map<A, <B, Double>> b.getValue() contains result of a/b
                }
            }
        }
        return -1;
    }

    public double[] calcEquation2(List<List<String>> equations, double[] values, List<List<String>> queries) {

    }
}
