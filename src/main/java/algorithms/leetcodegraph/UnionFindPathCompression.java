package algorithms.leetcodegraph;

//Path Compression Optimization - Disjoint Sets
//In the previous implementation of the “disjoint set”, notice that to find the root node, we need to traverse the parent nodes sequentially
// until we reach the root node. If we search the root node of the same element again, we repeat the same operations.
// Is there any way to optimize this process?
//
//The answer is yes! After finding the root node, we can update the parent node of all traversed elements to their root node.
// When we search for the root node of the same element again, we only need to traverse two elements to find its root node,
// which is highly efficient. So, how could we efficiently update the parent nodes of all traversed elements to the root node?
// The answer is to use “recursion”.
// This optimization is called “path compression”, which optimizes the find function.
public class UnionFindPathCompression {
    int[] root;

    UnionFindPathCompression(int size) {
        root = new int[size];
        for(int i=0; i< size; i++) {
            root[i] = i;
        }
    }

    public int find(int n) {// returns the root of current node
        if(root[n]==n) {
            return n;
        }
        root[n] = find(root[n]); // update root of current node to the root of entire tree
        return root[n];
    }

    public void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        if(rootX != rootY) {// if equal, it means nodes are already connected
            root[rootY] = rootX;
        }
    }
    public boolean connected(int x, int y) {
        return find(x) == find(y);
    }

    public static void main(String[] args) throws Exception {
        UnionFindPathCompression uf = new UnionFindPathCompression(10);
        // 1-2-5-6-7 3-8-9 4
        uf.union(1, 2);
        uf.union(2, 5);
        uf.union(5, 6);
        uf.union(6, 7);
        uf.union(3, 8);
        uf.union(8, 9);
        System.out.println(uf.connected(1, 5)); // true
        System.out.println(uf.connected(5, 7)); // true
        System.out.println(uf.connected(4, 9)); // false
        // 1-2-5-6-7 3-8-9-4
        uf.union(9, 4);
        System.out.println(uf.connected(4, 9)); // true
    }

}
