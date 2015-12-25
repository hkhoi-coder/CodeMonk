
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
    public static abstract class SegmentTree {

        private final int[] values;
        private final int[] results;

        public SegmentTree(int[] values) {
            this.values = values;
            results = new int[values.length * 2 + 1];
        }

        public SegmentTree(int size) {
            values = new int[size];
            results = new int[size * 2 + 1];
        }

        public final void updateResults() {
            updateResult(0, 0, values.length - 1);
        }

        private void updateResult(int index, int left, int right) {
            if (left == right) {
                results[index] = values[left];
            } else {
                int mid = (left + right) / 2;
                int leftIndex = index * 2 + 1;
                int rightIndex = leftIndex + 1;
                updateResult(leftIndex, left, mid);
                updateResult(rightIndex, mid + 1, right);

                results[index] = computation(results[leftIndex], results[rightIndex]);
            }
        }

        public final void updateValue(int pos, int value) {
            values[pos] = value;
        }

        public final int query(int left, int right) {
            return query(left, right, 0, 0, values.length - 1);
        }

        private int query(int queryLeft, int queryRight, int index, int left, int right) {
            if (queryLeft == left && queryRight == right) {
                return results[index];
            } else {
                int mid = (left + right) / 2;

                int leftIndex = index * 2 + 1;
                int rightIndex = leftIndex + 1;

                int leftQuery = query(queryLeft, mid, leftIndex, left, mid);
                int rightQuery = query(mid + 1, queryRight, rightIndex, mid, right);

                return computation(leftQuery, rightQuery);
            }
        }

        public abstract int computation(int arg0, int arg1);

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
        SegmentTree st = new SegmentTree(3) {
            @Override
            public int computation(int arg0, int arg1) {
                return arg0 + arg1;
            }
        };
        st.test();
    }
}
