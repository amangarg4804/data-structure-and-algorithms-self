package algorithms.leetcodegraph;

public class UnionFindUnionByRank {
    int[] root; // actually it is parent in case of quick union
    int[] rank; // stores height of each node

    UnionFindUnionByRank(int size) {
        root = new int[size];
        rank = new int[size];
        for(int i=0; i< size; i++) {
            root[i] = i;
            rank[i] =1;// initially each node has height of 1
        }
    }

    public int find(int n) { //finds the root
        while (n!=root[n]) {
            n = root[n];
        }
        return n;
    }

    public void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        if(rootX != rootY) {// if equal, means already connected, nothing to do
            if(rank[rootX] > rank[rootY]) { // if height of x is greater than height of y, then x should be the root of merged tree.
                // Note that in this case, we don't have to increment heigh, e.g. if a tree T1 of height 3 is merged with tree T2 of height 2
                // and root of T1 is chosen as tree of merged tree, the height of merged tree remains the same as height of T1.
                // In this example, 3
                root[rootY] = rootX;
            } else if(rank[rootY] > rank[rootX]) {
                root[rootX] = rootY;
            } else {
                // both trees have same height, anyone can be chosen as root.
                root[rootY]= rootX;
                rank[rootX] += 1; //we should increment height of root node chosen. In this case, rootX was chosen as root of merged tree
            }
        }
        // time: O(log N)- height of tree
        // space: O(N) for arrays
    }

    public boolean connected(int x, int y) {
        return find(x) == find(y);
    }
    public static void main(String[] args) throws Exception {
        UnionFindUnionByRank uf = new UnionFindUnionByRank(10);
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
