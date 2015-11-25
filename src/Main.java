/**
 *
 * @author hkhoi
 */
public class Main {
    public static void main(String[] args) {
        UnionFind uf = new UFArray(5);
        uf.union(2, 3);
        System.out.println(uf.find(3, 2));
    }
}
