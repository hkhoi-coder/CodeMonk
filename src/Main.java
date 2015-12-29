
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

    /* Segment Tree - Generic version */
    /**
     * ***********************************************************************
     */
    public static abstract class SegmentTree<T> {

        private final T[] values;
        private final T[] results;

        public SegmentTree(T[] values) {
            this.values = values;
            results = (T[]) new Object[values.length * 4];
        }

        public SegmentTree(int size) {
            values = (T[]) new Object[size];
            results = (T[]) new Object[values.length * 4];
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

        public final void updateValue(int pos, T value) {
            values[pos] = value;
        }

        public final T query(int left, int right) {
            return query(left, right, 0, 0, values.length - 1);
        }

        private T query(int queryLeft, int queryRight, int index, int left, int right) {
            if (left > queryRight || right < queryLeft) {
                return outRangeValue(); // Current segment is out of query range;
            } else if (left >= queryLeft && right <= queryRight) {
                return results[index];  // Current segment is in query range;
            } else {
                int mid = (left + right) / 2;
                int leftIndex = index * 2 + 1;
                int rightIndex = leftIndex + 1;

                T leftResult = query(queryLeft, queryRight, leftIndex, left, mid);
                T rightResult = query(queryLeft, queryRight, rightIndex, mid + 1, right);

                if (leftResult.equals(outRangeValue())) {
                    return rightResult;
                }
                if (rightResult.equals(outRangeValue())) {
                    return leftResult;
                }
                return computation(leftResult, rightResult);
            }
        }

        public abstract T computation(T arg0, T arg1);

        public abstract T outRangeValue();
    }
        /* Test driver */
        /**
         * ***********************************************************************
         * @param args
         */
        public static void main(String[] args) {
        }
    }

