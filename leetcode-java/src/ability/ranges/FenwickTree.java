package ability.ranges;

public class FenwickTree {

    // 保存前缀和数据
    private final long[] tree;
    public FenwickTree(int n) { tree = new long[n + 1]; }

    // 在下标 i 处增加 v (1 <= i <= n)
    public void update(int i, int v) {
        for (; i < tree.length; i += i & -i) {
            tree[i] += v;
        }
    }

    // 查询 [1, i] 的前缀和 (1 <= i <= n)
    public long query(int i) {
        long ans = 0;
        for (; i > 0; i -= i & -i) ans += tree[i];
        return ans;
    }

    // 查询区间 [l, r] 的区间和 (1 <= l <= r <= n)
    public long query(int l, int r) { return query(r) - query(l - 1); }

}
