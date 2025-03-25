package weekly.w440.C;

import ability.Benchmark;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 3479. Fruits Into Baskets III
 *
 * https://leetcode.cn/contest/weekly-contest-440/problems/fruits-into-baskets-iii/
 *
 * You are given two arrays of integers, fruits and baskets, each of length n, where fruits[i]
 * represents the quantity of the ith type of fruit, and baskets[j] represents the capacity of the jth basket.
 *
 * From left to right, place the fruits according to these rules:
 *
 * Each fruit type must be placed in the leftmost available basket with a capacity greater than or
 * equal to the quantity of that fruit type.
 * Each basket can hold only one type of fruit.
 * If a fruit type cannot be placed in any basket, it remains unplaced.
 *
 * Return the number of fruit types that remain unplaced after all possible allocations are made.
 */

public class Solution {

    private static class MinSegmentTree {
        private final int INF = Integer.MAX_VALUE;
        private final int n;
        private final int[] tree, lazy, data;
        public MinSegmentTree(int[] data) {
            this.n = data.length; this.data = data;
            tree = new int[data.length * 4];
            lazy = new int[data.length * 4];
            Arrays.fill(lazy, INF);

            build(1, 0, data.length - 1);
        }

        public int query(int ql, int qr) { return query(1, 0, n - 1, ql, qr); }
        public void update(int ql, int qr, int v) { update(1, 0, n - 1, ql, qr, v); }

        private void build(int p, int l, int r) {
            if (l == r) { tree[p] = data[l]; return; }

            int mid = l + (r - l) / 2;
            build(p * 2, l, mid);
            build(p * 2 + 1, mid + 1, r);
            tree[p] = Math.min(tree[p * 2], tree[p * 2 + 1]);
        }

        // p 为当前节点的编号, [l, r] 为当前节点包含的区间, [ql, qr] 为查询区间
        private int query(int p, int l, int r, int ql, int qr) {
            // 超出范围的直接返回无效值
            if (ql > r || qr < l) return INF;
            // 当前区间为询问区间的子集时直接返回当前区间的和
            if (ql <= l && r <= qr) return tree[p];

            spread(p);
            int ans = INF, mid = l + (r - l) / 2;
            ans = Math.min(ans, query(p * 2, l, mid, ql, qr));
            ans = Math.min(ans, query(p * 2 + 1, mid + 1, r, ql, qr));
            return ans;
        }

        private void update(int p, int l, int r, int ql, int qr, int v) {
            if (ql > r || qr < l) return;
            if (ql <= l && r <= qr) { tree[p] = lazy[p] = v; return; }

            spread(p);
            int mid = l + (r - l) / 2;
            update(p * 2, l, mid, ql, qr, v);
            update(p * 2 + 1, mid + 1, r, ql, qr, v);
            tree[p] = Math.min(tree[p * 2], tree[p * 2 + 1]);
        }

        private void spread(int p) {
            if (lazy[p] == Integer.MAX_VALUE) return;

            tree[p * 2] = Math.min(tree[p * 2], lazy[p]);
            tree[p * 2 + 1] = Math.min(tree[p * 2 + 1], lazy[p]);

            lazy[p * 2] = lazy[p];
            lazy[p * 2 + 1] = lazy[p];

            lazy[p] = Integer.MAX_VALUE;
        }
    }

    public int numOfUnplacedFruits(int[] fruits, int[] baskets) {
        int n = fruits.length;
        // 找到 baskets 大于等于 target 的第一个元素
        //  - 必须要在 O(1) 或者 O(logN) 的复杂度下找到元素的位置

        // 将 baskets 进行排序后作为一个数组 A
        // 将数组 A 的索引作为 key, tuple.1 作为值加入到最小值线段树中
        // 找到最小值, 并进行单点修改
        List<int[]> data = new ArrayList<>();
        for (int i = 0; i < n; i++) data.add(new int[]{baskets[i], i});
        data.sort(Comparator.comparingInt(p -> p[0]));

        // 保存单独的索引
        int[] index = new int[n];
        for (int i = 0; i < n; i++) index[i] = data.get(i)[1];

        // 映射 baskets 的索引到 index 的索引
        int[] mapping = new int[n];
        for (int i = 0; i < n; i++) mapping[index[i]] = i;

        // 构造线段树
        int ans = 0;
        MinSegmentTree st = new MinSegmentTree(index);
        // 逐个检查 fruits 是否能找到对应的篮子
        for (var fruit : fruits) {
            // 找到起始可以取的位置
            int start = binarySearch(data, fruit);
            // 这里找到的 idx 实际上是 baskets 数组的索引
            int idx = st.query(start, n - 1);
            if (idx >= n) ans++;
            // 我们需要把 baskets 的索引转换为 index 的索引
            else st.update(mapping[idx], mapping[idx], n);
        }

        return ans;
    }

    // 在 data 中找到第一个大于等于 target 的位置
    private int binarySearch(List<int[]> data, int target) {
        int l = -1, r = data.size();
        while (l + 1 < r) {
            int mid = l + (r - l) / 2;
            if (data.get(mid)[0] < target) l = mid;
            else r = mid;
        }
        return r;
    }

    @SuppressWarnings("DuplicatedCode")
    private static class Optimization {
        private static class MaxSegmentTree {
            private final int[] tree;
            public MaxSegmentTree(int[] data) {
                tree = new int[data.length * 4];
                build(data, 1, 0, data.length - 1);
            }
            private void build(int[] data, int p, int l, int r) {
                if (l == r) { tree[p] = data[l]; return; }

                int mid = l + (r - l) / 2;
                build(data, p * 2, l, mid);
                build(data, p * 2 + 1, mid + 1, r);
                tree[p] = Math.max(tree[p * 2], tree[p * 2 + 1]);
            }
            public int query(int p, int l, int r, int v) {
                // 在当前树中找不到大于等于 v 的数则直接返回错误
                if (tree[p] < v) return -1;
                // 如果 l == r 则说明我们找到了一个叶子结点且大于等于 v
                if (l == r) { tree[p] = 0; return l; }

                // 否则我们优先找左侧的树, 之后再找右侧的树
                int mid = l + (r - l) / 2;
                int ans = query(p * 2, l, mid, v);
                if (ans < 0) ans = query(p * 2 + 1, mid + 1, r, v);
                // 如果我们找到了一个数, 那么就需要进行更新操作
                tree[p] = Math.max(tree[p * 2], tree[p * 2 + 1]);

                return ans;
            }
        }

        public int numOfUnplacedFruits(int[] fruits, int[] baskets) {
            // 直接使用最大值线段树找到大于等于 target 的第一个位置
            MaxSegmentTree st = new MaxSegmentTree(baskets);

            int ans = 0, n = baskets.length;
            for (var fruit : fruits) {
                if (st.query(1, 0, n - 1, fruit) < 0) ans++;
            }
            return ans;
        }
    }

    public static void main(String[] args) {
        Benchmark.benchmark("Optimization", () -> {
            assert new Optimization().numOfUnplacedFruits(new int[]{31}, new int[]{6}) == 1;
            assert new Optimization().numOfUnplacedFruits(new int[]{3,6,1}, new int[]{6,4,7}) == 0;
            assert new Optimization().numOfUnplacedFruits(new int[]{4,2,5}, new int[]{3,5,4}) == 1;
        });

        Benchmark.benchmark("", () -> {
            assert new Solution().numOfUnplacedFruits(new int[]{31}, new int[]{6}) == 1;
            assert new Solution().numOfUnplacedFruits(new int[]{3,6,1}, new int[]{6,4,7}) == 0;
            assert new Solution().numOfUnplacedFruits(new int[]{4,2,5}, new int[]{3,5,4}) == 1;
        });
    }

}
