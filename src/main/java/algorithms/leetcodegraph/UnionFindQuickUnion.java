package algorithms.leetcodegraph;

public class UnionFindQuickUnion {
    int[] parent;
    public UnionFindQuickUnion(int size) {
        parent = new int[size];
        for(int i=0; i< size; i++) {
            parent[i] =i;
        }
    }

    // find the root
    public int find(int n) {
        while (n!=parent[n]) {
            n=parent[n];
        }
        return n;
    }

    public void union(int x, int y) {
        // 1, 0 -> parent[0] ==1
        // 2, 0 -> find[2] = 2, find[0] = 1. parent[1] = 2
        int rootX = find(x); //1
        int rootY = find(y); //0.
        if(rootX !=rootY) {// if they are equal, it means x and y are already connected
            parent[rootY] = rootX;
        }
    }

    public boolean connected(int x, int y) {
        return find(x) ==find(y);
    }

    public static void main(String[] args) throws Exception {
        UnionFindQuickUnion uf = new UnionFindQuickUnion(10);
        // 1-2-5-6-7    3-8-9    4
        uf.union(1, 2);
        uf.union(2, 5);
        uf.union(5, 6);
        uf.union(6, 7);
        uf.union(3, 8);
        uf.union(8, 9);
        System.out.println(uf.connected(1, 5)); // true
        System.out.println(uf.connected(5, 7)); // true
        System.out.println(uf.connected(4, 9)); // false
        // 1-2-5-6-7    3-8-9-4
        uf.union(9, 4);
        System.out.println(uf.connected(4, 9)); // true


        // the following sequence will create a linear structure
        uf.union(1, 0); // the first parameter x always becomes the parent in our implementation
        uf.union(2, 0);// 2 becomes root
        uf.union(3, 0);// 3 becomes root
        uf.union(4, 0);// 4 becomes root of previous root
    }

}
