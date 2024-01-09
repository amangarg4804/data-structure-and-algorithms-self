package algorithms.leetcodegraph;

//Optimized “disjoint set” with Path Compression and Union by Rank
//This implementation of the “disjoint set” is optimized with both “path compression” and “union by rank”.
public class OptimizedDisjointSet {
    int[] root;
    int[] rank;

    OptimizedDisjointSet(int size) {
        root = new int[size];
        rank = new int[size];
        for(int i=0; i< size; i++) {
            root[i] = i;
            rank[i] = 1;
        }
    }

    public int find(int n) {
        if(root[n]==n) {
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
    //Time: O(N) time for constructor
    // O(α(N)) for find, union and connected. �
    //α refers to the Inverse Ackermann function. In practice, we assume it's a constant. In other words, O(α(N)) is regarded as O(1) on average.
    //Space: We need O(N) space to store the array of size N.
}
