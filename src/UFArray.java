
/**
 *
 * @author hkhoi
 */
public class UFArray implements UnionFind {

    private final int[] root;
    private final int[] size;

    public UFArray(int n) {
        root = new int[n];
        size = new int[n];

        for (int i = 0; i < n; ++i) {
            root[i] = i;
            size[i] = 1;
        }
    }

    @Override
    public boolean find(int a, int b) {
        return findRoot(a) == findRoot(b);
    }

    @Override
    public void union(int a, int b) {
        int rootA = findRoot(a);
        int rootB = findRoot(b);

        if (rootA != rootB) {
            if (size[rootA] > size[rootB]) {
                root[rootB] = rootA;
                size[rootA] += size[rootB];
            } else {
                root[rootA] = rootB;
                size[rootB] += size[rootA];
            }
        }
    }

    private int findRoot(int a) {
        while (root[a] != a) {
            a = root[a];
        }

        return a;
    }
}
