package algorithms.leetcode.graph;

// You are given an integer n, which indicates that there are n courses labeled from 1 to n. You are also given an array relations where relations[i] = [prevCoursei, nextCoursei], representing a prerequisite relationship between course prevCoursei and course nextCoursei: course prevCoursei has to be taken before course nextCoursei.
//
//In one semester, you can take any number of courses as long as you have taken all the prerequisites in the previous semester for the courses you are taking.
//
//Return the minimum number of semesters needed to take all courses. If there is no way to take all the courses, return -1.


// Input: n = 3, relations = [[1,3],[2,3]]
//Output: 2
//Explanation: The figure above represents the given graph.
//In the first semester, you can take courses 1 and 2.
//In the second semester, you can take course 3.

// Input: n = 3, relations = [[1,2],[2,3],[3,1]]
//Output: -1
//Explanation: No course can be studied because they are prerequisites of each other.

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ParallelCourses {

    public int minimumSemesters(int n, int[][] relations) {
        // Topological Sort
        // which courses to take first?
        // the ones having indegree=0;
        int minSemesters =0;
        int[] indegree = new int[n+1];
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i< (n +1) ; i++) {
            graph.add(new ArrayList<>());
        }
        for(int[] relation: relations) {
            graph.get(relation[0]).add(relation[1]);
            indegree[relation[1]]++;
        }
        // find out the nodes having indegree =0
        Queue<Integer> q = new LinkedList<>();
        for(int i =1; i< n+1; i++) {
            System.out.println("indegree [" + i + "] = " + indegree[i]);
            if(indegree[i]==0) {
                q.offer(i);
            }
        }
        int coursesRemaining = n;
        System.out.println(graph);
        while (!q.isEmpty() ) {
            minSemesters++;
            int size = q.size();
            System.out.println("q size = " + q.size()) ;
            coursesRemaining-=size;
            System.out.println("coursesRemaining = " + coursesRemaining);
            for(int i = 0; i< size; i++) {
                int node = q.poll();
                for(int neighbour : graph.get(node)) {
                    indegree[neighbour]--;
                    if(indegree[neighbour] ==0) {
                        System.out.println("neighbour = " + neighbour);
                        q.offer(neighbour);
                    }
                }
            }
        }
        if(coursesRemaining ==0) {
            return minSemesters;
        }
        return -1;
    }

}
