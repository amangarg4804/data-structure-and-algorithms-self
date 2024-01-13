package algorithms.leetcodegraph;

//https://leetcode.com/problems/number-of-provinces/description/
//There are n cities. Some of them are connected, while some are not. If city a is connected directly with city b, and city b is connected directly with city c, then city a is connected indirectly with city c.
//A province is a group of directly or indirectly connected cities and no other cities outside of the group.
//You are given an n x n matrix isConnected where isConnected[i][j] = 1 if the ith city and the jth city are directly connected, and isConnected[i][j] = 0 otherwise.
//Return the total number of provinces.

//Input: isConnected = [[1,1,0],[1,1,0],[0,0,1]]
//Output: 2

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

//Input: isConnected = [[1,0,0],[0,1,0],[0,0,1]]
//Output: 3
//
//
//Constraints:
//
//1 <= n <= 200
//n == isConnected.length
//n == isConnected[i].length
//isConnected[i][j] is 1 or 0.
//isConnected[i][i] == 1
//isConnected[i][j] == isConnected[j][i]
public class NumberOfProvinces {

    // the problem looks similar to counting no of islands. Each island is a collection of 1's in all 4 directions.
    //[[1,1,0],
    // [1,1,0],
    // [0,0,1]]

    // but the above assumption is wrong. Check this example:
    // [[1,0,0,1], // 0->3
    //  [0,1,1,0], // 0->3 1->2
    //  [0,1,1,1], // (2->1) (2->3) -> 1->2->3->0
    //  [1,0,1,1]] // (3,0),(3,2),(3,3)-> 1->2->3->0
    // As per above example, all vertex are connected, so the answer is 1. But if we count islands(using 4 directions), they are 4.
    // We want to calculate no of connected components here. No of connected components is different from no of islands.

    // Let's solve by Union find path compression
    public int findCircleNum(int[][] isConnected) {

        // Union Find using set
        UnionFindPathCompression unionFindPathCompression = new UnionFindPathCompression(isConnected.length);
        for(int row = 0; row < isConnected.length; row++) {
            for(int col=0; col < isConnected[row].length; col++) {
                if(isConnected[row][col] ==1) {
                    unionFindPathCompression.union(row, col);
                }
            }
        }
        Set<Integer> roots = new HashSet<>();
        for(int i=0; i< isConnected.length; i++) {
            roots.add(unionFindPathCompression.find(i));
        }

        return roots.size();
    }

    public int findCircleNum2(int[][] isConnected) {
        /// union find without set
        UnionFindPathCompression unionFindPathCompression = new UnionFindPathCompression(isConnected.length);
        int totalComponents = isConnected.length; // count each vertex as separate component initially. As we keep finding connections between two nodes. Union(merge) them and decrement totalComponents
        // e.g. let's say we have two nodes 0 and 1. So initially totalComponents are 2. If we find a connection between these nodes, then we merge them and decremenet total components by 1. So Answer in this case is 2-1 =1.

        for(int row = 0; row < isConnected.length; row++) {
            for(int col=row+1; col < isConnected[row].length; col++) {
                // we start from row+1 because for isConnected matrix isConnected[row][col] == isConnected[col][row]
                // so if we have already merged row 0 col 1. we should not check for col 1, row 0, otherwise we would be merging these vertices twice
                if(isConnected[row][col] ==1 &&  unionFindPathCompression.find(row) != unionFindPathCompression.find(col)) {
                    // we have to check both conditions
                    // isConnected[row][col] ==1 checks for direct connectivity as per given matrix
                    //unionFindPathCompression.find(row) != unionFindPathCompression.find(col) checks whether these vertex were already connected indirectly
                    // if we don't check that, it fails for [[1,1,1],[1,1,1],[1,1,1]]. answer should be 1, but it results in 0
                    // 0 connects to 1 and 2 in first row. 1-0-2. When we start iterating row 1, 1 and 2 are already connected. So we shouldn't decrement
                    unionFindPathCompression.union(row, col);
                    totalComponents--;
                }
            }
        }
        return totalComponents;
    }

    private static class UnionFindPathCompression {
        int[] root;
        int[] rank;

        UnionFindPathCompression(int size) {
            root = new int[size];
            rank = new int[size];
            for(int i=0; i< size; i++) {
                root[i] =i;
                rank[i] =1;
            }
        }

        public int find(int n) {// find root
            if(n==root[n]) {
                return n;
            }
            root[n] = find(root[n]);
            return root[n];
        }

        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if(rootX != rootY) {
                if(rank[rootX] > rank[rootY]) {
                    root[rootY] = rootX;
                } else if(rank[rootY] > rank[rootX]) {
                    root[rootX] = rootY;
                } else {
                    root[rootY] = rootX;
                    rank[rootX] +=1;
                }
            }
        }

        public boolean connected(int x, int y) {
            return find(x) == find(y);
        }
    }

    // We can also use DFS to find number of connected components
    public int findCircleNum3(int[][] isConnected) {
        // dfs to find no of connected components
        int n= isConnected.length; // no of vertex
        // start from each non-visited vertex. For each vertex, visit all its connected(direct or indirect) vertices
        int count =0;
        boolean[] visited = new boolean[isConnected.length];
        for(int i=0; i< n; i++) {
            if(!visited[i]) {
                count++;
                visitDfs(i, isConnected, visited);
            }
        }
        return count;
    }

    // [[1,0,0,1], // 0->3
    //  [0,1,1,0], // 0->3 1->2
    //  [0,1,1,1], // (2->1) (2->3) -> 1->2->3->0
    //  [1,0,1,1]] // (3,0),(3,2),(3,3)-> 1->2->3->0
    // As per above example, all vertex are connected
    private void visitDfs(int i, int[][] isConnected, boolean[] visited) {
        visited[i]=true;
        // for each vertex, visit it's neighbour if neighbour is connected.
        // we can't start from i+1
        // for a given vertex we have to visit all connected nodes. So that we don't count this connected component twice in method findCircleNum3. For example:
        // if visit method is called for 0th node, and we find that 0 and 3 are connected. We should visit 3 and check for all nodes connecting 3.
        // It is possible that 3 and 1 are connected directly but 0 and 1 are not connected directly.
        // so we should mark node 1 as visited too. Otherwise, we will be counting the connected component 0-3-1 twice
        for(int j=0; j< isConnected.length; j++) {
            if(isConnected[i][j]==1 && !visited[j]) {
                visitDfs(j, isConnected, visited);
            }
        }
        //Time complexity: O(n^2)
        //
        //Initializing the visit array takes O(n) time.
        //The dfs function visits each node once, which takes O(n) time because there are nodes in total. From each node, we iterate over all possible edges using isConnected[node] which takes O(n) time for each visited node. As a result, it takes a total of O(n^2) time to visit all the nodes and iterate over its edges.
        //Space complexity: O(n).
        //The visit array takes O(n) space.
        //The recursion call stack used by dfs can have no more than n elements in the worst-case scenario. It would take up O(n)) space in that case.
    }

    public int findCircleNum4(int[][] isConnected) {
        // using BFS
        // For each node, if it is not visited, perform BFS on it and mark each of it's connectd vertices as visited
        int count =0;
        boolean[] visited = new boolean[isConnected.length];
        for(int i=0; i< isConnected.length; i++) {
            if(!visited[i]) {
                count++;
                visitBfs(i, isConnected, visited);
            }
        }
        return count;
    }

    private void visitBfs(int i, int[][] isConnected, boolean[] visited) {
        Queue<Integer> q = new LinkedList<>();
        q.offer(i);
        while (!q.isEmpty()) {
            int current = q.poll();
            visited[current] = true;
            for(int k = 0; k< isConnected.length; k++) {
                if(isConnected[current][k] == 1 && !visited[k]) {
                    q.offer(k);
                }
            }

        }
        //Time complexity: O(n^2)
        //Initializing the visit array takes O(n) time.
        //Each queue operation in the BFS algorithm takes O(1) time, and a single node can only be pushed once, leading to O(n) operations for n nodes.
        // we iterate over all possible edges using isConnected[node] which takes O(n) time for each visited node, resulting in O(n^2) operations in total
        // in the worst-case scenario while visiting all nodes.
        //Space complexity: O(n)
        //The BFS queue takes O(n) because each node is added, and in the worst-case scenario you could have a linear amount of nodes in the queue at once.
        //The visit array takes O(n) space as well.
    }

    // we can also do BFS another way. This time we check queue size
    private void visitBfs2(int i, int[][] isConnected, boolean[] visited) {
        Queue<Integer> q = new LinkedList<>();
        q.offer(i);
        while (!q.isEmpty()) {
            int size = q.size();
            for(int j=0; j< size; j++) {
                int current = q.poll();
                visited[current] = true;
                for(int k = 0; k< isConnected.length; k++) {
                    if(isConnected[current][k] == 1 && !visited[k]) {
                        q.offer(k);
                    }
                }
            }
        }
        //Time complexity: O(n^2)
        //Initializing the visit array takes O(n) time.
        //Each queue operation in the BFS algorithm takes O(1) time, and a single node can only be pushed once, leading to O(n) operations for n nodes.
        // we iterate over all possible edges using isConnected[node] which takes O(n) time for each visited node, resulting in O(n^2) operations in total
        // in the worst-case scenario while visiting all nodes.
        //Space complexity: O(n)
        //The BFS queue takes O(n) because each node is added, and in the worst-case scenario you could have a linear amount of nodes in the queue at once.
        //The visit array takes O(n) space as well.
    }

}
