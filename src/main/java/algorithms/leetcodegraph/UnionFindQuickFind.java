package algorithms.leetcodegraph;

//  Quick Find - Disjoint Set
public class UnionFindQuickFind {
    private int[] root;

    UnionFindQuickFind(int size) {
        root = new int[size];
        for(int i=0; i< size; i++) {
            root[i] =i;
        }
    }
    // returns root of the node
    int find(int n) {
        return root[n];
    }

    // connects two nodes
    void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        if(rootX != rootY) {
            for(int i=0; i< root.length; i++) {
                if(root[i] == rootY) {
                    root[i] = rootX;
                }
            }
        }
    }

    boolean isConnected(int x, int y) {
        return find(x) == find(y);
    }

    public static void main(String[] args) {
        UnionFindQuickFind unionFindQuickFind = new UnionFindQuickFind(5);
        // 0-1-2
        // 3-4
        unionFindQuickFind.union(0,1);
        unionFindQuickFind.union(1,2);
        unionFindQuickFind.union(3,4);
        System.out.println(unionFindQuickFind.find(0));
        System.out.println(unionFindQuickFind.find(1));
        System.out.println(unionFindQuickFind.find(2));
        System.out.println(unionFindQuickFind.find(3));
        System.out.println(unionFindQuickFind.find(4));

        System.out.println(unionFindQuickFind.isConnected(0,1));
        System.out.println(unionFindQuickFind.isConnected(1,2));
        System.out.println(unionFindQuickFind.isConnected(2,3));
        System.out.println(unionFindQuickFind.isConnected(3,4));
        System.out.println(unionFindQuickFind.isConnected(4,0));
    }
}
