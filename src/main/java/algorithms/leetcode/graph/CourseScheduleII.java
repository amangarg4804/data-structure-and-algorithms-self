package algorithms.leetcode.graph;

// There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1. You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course bi first if you want to take course ai.
//
//For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
//Return the ordering of courses you should take to finish all courses. If there are many valid answers, return any of them. If it is impossible to finish all courses, return an empty array.
//
//
//
//Example 1:
//Input: numCourses = 2, prerequisites = [[1,0]]
//Output: [0,1]
//Explanation: There are a total of 2 courses to take. To take course 1 you should have finished course 0. So the correct course order is [0,1].

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

//Example 2:
//Input: numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]
//Output: [0,2,1,3]
//Explanation: There are a total of 4 courses to take. To take course 3 you should have finished both courses 1 and 2. Both courses 1 and 2 should be taken after you finished course 0.
//So one correct course order is [0,1,2,3]. Another correct ordering is [0,2,1,3].
//
//
// Example 3:
//Input: numCourses = 1, prerequisites = []
//Output: [0]
//
//
//Constraints:
//
//1 <= numCourses <= 2000
//0 <= prerequisites.length <= numCourses * (numCourses - 1)
//prerequisites[i].length == 2
//0 <= ai, bi < numCourses
//ai != bi
//All the pairs [ai, bi] are distinct.
public class CourseScheduleII {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        // Topological sort
        // 4 data structures needd
        // 1. graph or adjacency list
        // 2. inDegree array
        // 3. Queue to push nodes having Indegree = 0
        // 4. Result list
        int[] inDegree = new int[numCourses];
        List<List<Integer>> graph = new ArrayList<>();

        for(int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }

        for(int[] prerequisite: prerequisites) {
            // prerequisites[i] = [ai, bi] indicates that you must take course bi first if you want to take course ai.
            // So the edges are in reverse order
            graph.get(prerequisite[1]).add(prerequisite[0]);
            inDegree[prerequisite[0]]++;
        }
        List<Integer> result = new ArrayList<>();
        Queue<Integer> q= new LinkedList<>();
        for(int i =0 ; i< inDegree.length; i++) {
            if(inDegree[i] == 0) {
                q.offer(i);
            }
        }
        while (!q.isEmpty()) {
            int currentNode = q.poll();
            result.add(currentNode);
            for(int neighbor: graph.get(currentNode)) {
                inDegree[neighbor]--;
                if(inDegree[neighbor] == 0) {
                    q.offer(neighbor);
                }
            }
        }
        if(result.size() != numCourses) {
            return new int[0];
        }
        return result.stream().mapToInt(Integer::intValue).toArray();
    }
}
