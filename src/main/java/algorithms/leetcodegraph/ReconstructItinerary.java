package algorithms.leetcodegraph;

//You are given a list of airline tickets where tickets[i] = [fromi, toi] represent the departure and the arrival airports of one flight. Reconstruct the itinerary in order and return it.
//
//All of the tickets belong to a man who departs from "JFK", thus, the itinerary must begin with "JFK". If there are multiple valid itineraries, you should return the itinerary that has the smallest lexical order when read as a single string.
//
//For example, the itinerary ["JFK", "LGA"] has a smaller lexical order than ["JFK", "LGB"].
//You may assume all tickets form at least one valid itinerary. You must use all the tickets once and only once.

//Input: tickets = [["MUC","LHR"],["JFK","MUC"],["SFO","SJC"],["LHR","SFO"]]
//Output: ["JFK","MUC","LHR","SFO","SJC"]

//Input: tickets = [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
//Output: ["JFK","ATL","JFK","SFO","ATL","SFO"]
//Explanation: Another possible reconstruction is ["JFK","SFO","ATL","JFK","ATL","SFO"] but it is larger in lexical order.

import java.util.*;

//Constraints:
//
//1 <= tickets.length <= 300
//tickets[i].length == 2
//fromi.length == 3
//toi.length == 3
//fromi and toi consist of uppercase English letters.
//fromi != toi
public class ReconstructItinerary {
    public List<String> findItinerary(List<List<String>> tickets) {
        // recursion and backtracking
        //TLE 4/81 passed. Using list and sorting it is much faster than Priority queue for this problem.
        // Key points
        // 1. We have to use all tickets
        // 2. each ticket has to be used only once
        // 3.
        //     a -> b b->a a->c
        // We can't take path a->c. Because it only uses one ticket (edge). For the above graph, we can choose path a->b->a->c
        // 4. each edge has to be used
        // 5. Graph has cycles
        // 6. Graph can even have some duplicate edges(we might have multiple flights with the same origin and destination)
        // 7. we have to always start from "JFK"
        // in the example above, notice that valid path has length 4 when there are 3 tickets. So a valid itinerary will always be of length totalTickets +1
        // Step 1: Create a graph: For each source there can be multiple destinations so we can use Map of String, List
        // as we observe from problem statement, we have to return the path smallest in lexical order
        // So instead of list, we can use priority Queue
        Map<String, PriorityQueue<Destination>> graph = new HashMap<>();
        for(List<String> ticket: tickets) {
            String source = ticket.get(0);
            String destination = ticket.get(1);
            PriorityQueue<Destination> pq = graph.getOrDefault(source, new PriorityQueue<>((d1,d2) -> d1.name.compareTo(d2.name)));
            pq.offer(new Destination(destination, false));
            graph.put(source, pq);
        }
        List<String> path = new ArrayList<>(); // it is given that the solution always exist, so we will always get a valid path
        path.add("JFK");
        backtrack(path, graph, tickets.size());
        return path;
    }

    private boolean backtrack(List<String> path, Map<String, PriorityQueue<Destination>> graph, int noOfTickets) {
        if(path.size()==noOfTickets +1) {
            return true;
        }
        String from = path.get(path.size()-1);// we have to find ticket from where we are currently, we are at the end of the path
        if(!graph.containsKey(from)) {// no tickets starting from current source, we have to backtrack and choose another destination
            return false;
        }
        // NOTE: priority queue iterator doesn't return items in sort order
//        for(Destination destination: graph.get(from)) {
//
//        }
        PriorityQueue<Destination> pq = graph.get(from);
        while (!pq.isEmpty()) {
            Destination destination = pq.poll();
            path.add(destination.name);
            boolean result = backtrack(path, graph, noOfTickets);// how do we know we found a path and don't need to check further? We can return a boolean instead of void
            if(result) {
                return true;
            }
            pq.offer(destination);
            path.remove(path.size()-1);
        }

        //if we checked all possible destinations for current source and couldn't find a path, return false, so the caller can backtrack and try other destinations
        // Noe that for the first call("JFK") it will never reach here because the problem is guaranteed to have a solution
        return false;
    }

    public List<String> findItinerary2(List<List<String>> tickets) {
        //TLE 80 / 81 testcases passed
        // recursion and backtracking
        //TLE
        // Key points
        // 1. We have to use all tickets
        // 2. each ticket has to be used only once
        // 3.
        //     a -> b b->a a->c
        // We can't take path a->c. Because it only uses one ticket (edge). For the above graph, we can choose path a->b->a->c
        // 4. each edge has to be used
        // 5. Graph has cycles
        // 6. Graph can even have some duplicate edges(we might have multiple flights with the same origin and destination)
        // 7. we have to always start from "JFK"
        // in the example above, notice that valid path has length 4 when there are 3 tickets. So a valid itinerary will always be of length totalTickets +1
        // Step 1: Create a graph: For each source there can be multiple destinations so we can use Map of String, List
        // as we observe from problem statement, we have to return the path smallest in lexical order
        // So instead of list, we can use priority Queue
        Map<String, List<Destination>> graph = new HashMap<>();
        for(List<String> ticket: tickets) {
            String source = ticket.get(0);
            String destination = ticket.get(1);
            List<Destination> destinations = graph.getOrDefault(source, new ArrayList<>());
            destinations.add(new Destination(destination, false));
            graph.put(source, destinations);
        }
        for(Map.Entry<String, List<Destination>> entry : graph.entrySet()) {
            entry.getValue().sort((d1,d2) -> d1.name.compareTo(d2.name));
        }
        List<String> path = new ArrayList<>(); // it is given that the solution always exist, so we will always get a valid path
        path.add("JFK");
        backtrack2(path, graph, tickets.size());
        return path;
    }

    private boolean backtrack2(List<String> path, Map<String, List<Destination>> graph, int noOfTickets) {
        if(path.size()==noOfTickets +1) {
            return true;
        }
        String from = path.get(path.size()-1);// we have to find ticket from where we are currently, we are at the end of the path
        if(!graph.containsKey(from)) {// no tickets starting from current source, we have to backtrack and choose another destination
            return false;
        }
        // NOTE: priority queue iterator doesn't return items in sort order
//        for(Destination destination: graph.get(from)) {
//
//        }
        for(Destination destination: graph.get(from)) {
            if(destination.visited) {
                continue;
            }
            destination.visited=true;
            path.add(destination.name);
            boolean result = backtrack2(path, graph, noOfTickets);// how do we know we found a path and don't need to check further? We can return a boolean instead of void
            if(result) {
                return true;
            }
            destination.visited=false;
            path.remove(path.size()-1);
        }
        //if we checked all possible destinations for current source and couldn't find a path, return false, so the caller can backtrack and try other destinations
        // Noe that for the first call("JFK") it will never reach here because the problem is guaranteed to have a solution
        return false;
    }

    public List<String> findItinerary3(List<List<String>> tickets) {
        //Eulerian path
        // Hierzolher's algorithm
        //https://algorithms.discrete.ma.tum.de/graph-algorithms/hierholzer/index_en.html

        // dry run for possible path that contains all edges:
        // result:
        // recursion: JFK->SFO->ATL->SFO- stuck at SFO
        // remaining edges JFK->ATL->JFK

        //  We add SFO to result because it doesn't have more edges. This will be the last node we will actually visit.
        // the idea is that the node that has no remaining edges should be visited last
        // result: SFO
        //recursion: JFK->SFO->ATL (SFO removed from recursion stack)
        // remaining edges JFK->ATL->JFK

        // ATL has an unvisited edge ATL->JFK
        // result: SFO
        // recursion: JFK->SFO->ATL->JFK
        // remaining edges JFK->ATL

        // we are at JFK, JFK has an unvisited edge: JFK-> ATL So add ATL to recursion
        // result: SFO
        // recursion: JFK->SFO->ATL->JFK-> ATL
        // remaining edges: none

        // no more unvisited edges. Add all nodes one by one to path starting from top of stack (ATL)
        // we will append the top to stack to head of linked list
        // so, ATL gets added to head first:
        // result: ATL, SFO
        // recursion: JFK->SFO->ATL->JFK

        // keep on adding top of stack to head of the list
        // result: JFK,SFO,ATL,JFK, ATL, SFO

        // Step1: create the graph
        Map<String, LinkedList<String>> graph = new HashMap<>();// we will be polling first element from sorted list, so used linkedlist instead of arraylist
        for(List<String> ticket: tickets) {
            String source = ticket.get(0);
            String destination = ticket.get(1);
            LinkedList<String> destinations = graph.getOrDefault(source, new LinkedList<>());
            destinations.add(destination);
            graph.put(source, destinations);
        }
        // Step 2: sort the destinations for each source
        graph.forEach((key, value) -> Collections.sort(value));

        // Step 3: visit the graph starting from JFK
        List<String> result = new LinkedList<>(); // use linked list because we will be inserting at the head always, arraylist will be time-consuming
        dfs("JFK", graph,result);
        return result;

    }

    private void dfs(String source, Map<String, LinkedList<String>> graph, List<String> result) {
        if(graph.containsKey(source)) {
            while (!graph.get(source).isEmpty()) {
                String nextDestination = graph.get(source).pollFirst();
                dfs(nextDestination, graph, result);
            }
        }
        result.addFirst(source);
    }


    private static class Destination {
        String name;
        boolean visited;

        public Destination(String airport, boolean visited) {
            this.name = airport;
            this.visited = visited;
        }
    }
}
