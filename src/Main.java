
import java.util.Arrays;

/**
 * Competitive Java Library
 *
 * @author hkhoi
 */
public class Main {

    /* ArrayDisjointSet */
    public static class ArrayDisjointSet {

        private final int[] root;
        private final int[] size;

        public ArrayDisjointSet(int n) {
            root = new int[n];
            size = new int[n];

            for (int i = 0; i < n; ++i) {
                root[i] = i;
                size[i] = 1;
            }
        }

        public boolean find(int a, int b) {
            return findRoot(a) == findRoot(b);
        }

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

    /* Segment Tree */
    /**
     * ***********************************************************************
     */
    public static class SumSegmentTree {

        private int[] values;
        private int[] results;

        public SumSegmentTree(int[] values) {
            this.values = values;
            results = new int[values.length * 2 - 1];
            build(0, 0, values.length - 1);
        }

        public SumSegmentTree(int size) {
            values = new int[size];
            results = new int[size * 2 + 1];
        }

        private void build(int index, int left, int right) {
            if (left == right) {
                results[index] = values[left];
            } else {
                int mid = (left + right) / 2;
                int leftIndex = index * 2 + 1;
                int rightIndex = leftIndex + 1;
                build(leftIndex, left, mid);
                build(rightIndex, mid + 1, right);

                results[index] = results[leftIndex] + results[rightIndex];
            }
        }

        public void update(int pos, int dif) {
            if (pos <= values.length - 1 && pos >= 0) {
                values[pos] += dif;
                update(0, pos, dif, 0, values.length - 1);
            }
        }

        private void update(int index, int pos, int dif, int left, int right) {
            results[index] += dif;
            
            if (left != right) {
                int mid = (left + right) / 2;

                int leftIndex = index * 2 + 1;
                int rightIndex = leftIndex + 1;

                if (pos <= mid) {
                    update(leftIndex, pos, dif, left, mid);
                } else {
                    update(rightIndex, pos, dif, mid + 1, right);
                }
            }
        }

        public int query(int left, int right) {
            return 0;
        }

        public void test() {
            System.out.println(Arrays.toString(values));
            System.out.println(Arrays.toString(results));
        }
    }

    /* Test driver */
    /**
     * ***********************************************************************
     * @param args
     */
    public static void main(String[] args) {
        int[] a = {1, 2, 3, 4, 5};
        SumSegmentTree st = new SumSegmentTree(a);
        st.test();
        st.update(0, -1);
        st.test();
    }
}
