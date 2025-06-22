package weekly.w452.D;

import common.Checker;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

/**
 * Q4. Maximize Count of Distinct Primes After Split
 *
 * https://leetcode.cn/contest/weekly-contest-452/problems/maximize-count-of-distinct-primes-after-split
 *
 * You are given an integer array nums having length n and a 2D integer array queries where queries[i] = [idx, val].
 *
 * For each query:
 *
 * Update nums[idx] = val.
 * Choose an integer k with 1 <= k < n to split the array into the non-empty
 * prefix nums[0..k-1] and suffix nums[k..n-1] such that the sum of the
 * counts of distinct prime values in each part is maximum.
 *
 * Note: The changes made to the array in one query persist into the next query.
 *
 * Return an array containing the result for each query, in the order they are given.
 */

public class Solution {

    private static final int MAX_N = 100_100;
    private static final boolean[] primes = new boolean[MAX_N];
    static {
        Arrays.fill(primes, true);
        primes[0] = primes[1] = false;
        for (int i = 2; i < MAX_N; i++) {
            if (primes[i]) for (int j = i + i; j < MAX_N; j += i) primes[j] = false;
        }
    }

    @SuppressWarnings("DuplicatedCode")
    private static class LazySegmentTree {
        private final static int NO_LAZY = 0;

        public final int sz;
        private final int[] tree, lazy;
        public LazySegmentTree(int n) {
            sz = n; tree = new int[4 * n]; lazy = new int[4 * n];
        }

        public int query(int ql, int qr) { return query(1, 0, sz - 1, ql, qr); }
        private int query(int node, int l, int r, int ql, int qr) {
            if (ql <= l && r <= qr) return tree[node]; // ql <= [l, r] <= qr

            spread(node, l, r);
            int mid = l + (r - l) / 2;
            if (qr <= mid) return query(node * 2, l, mid, ql, qr); // [ql, qr] |
            if (mid < ql) return query(node * 2 + 1, mid + 1, r, ql, qr); // | [ql, qr]

            int lv = query(node * 2, l, mid, ql, qr);
            int rv = query(node * 2 + 1, mid + 1, r, ql, qr);
            return mergeTree(lv, rv);
        }

        public void update(int ql, int qr, int v) { update(1, 0, sz - 1, ql, qr, v); }
        private void update(int node, int l, int r, int ql, int qr, int v) {
            if (ql <= l && r <= qr) { apply(node, l, r, v); return; } // ql <= [l, r] <= qr

            spread(node, l, r);
            int mid = l + (r - l) / 2;
            if (ql <= mid) update(node * 2, l, mid, ql, qr, v); // [ql, |
            if (mid < qr) update(node * 2 + 1, mid + 1, r, ql, qr, v); // |, qr]
            maintain(node);
        }

        // 将当前节点的懒标记传递给子节点
        private void spread(int node, int l, int r) {
            if (lazy[node] == NO_LAZY) return;

            int mid = l + (r - l) / 2;
            apply(node * 2, l, mid, lazy[node]);
            apply(node * 2 + 1, mid + 1, r, lazy[node]);
            lazy[node] = NO_LAZY;
        }

        private void apply(int node, int l, int r, int v) {
            tree[node] += v; lazy[node] = mergeLazy(lazy[node], v);
        }

        private void maintain(int node) { tree[node] = mergeTree(tree[node * 2], tree[node * 2 + 1]); }
        private int mergeTree(int a, int b) { return Math.max(a, b); }
        private int mergeLazy(int a, int b) { return a + b; }
    }

    public int[] maximumCount(int[] nums, int[][] queries) {
        // 将数组分为前后两个部分, 使得每个部分中的*不同质数*的数量之和最大
        //  - 对于一段数组 nums = 2, 3, 5, 3, 2, 5, 2
        //  - 我们先计算整个数组中的不同质数个数, 然后枚举切分的位置可以给这个值带来的增量
        //      - 例如我们切在 2, 3, 5 | 3, 2, 5, 2 就会带来 3 的增量(因为我们将本来重复的 2, 3, 5 分开了)
        //  - 例如一个序列 p ... p 我们只需要在每个 p 的最前和最后一个位置之中随便切一刀就可以额外多 1 的增量
        Map<Integer, TreeSet<Integer>> g = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (primes[nums[i]]) {
                g.computeIfAbsent(nums[i], k -> new TreeSet<>()).add(i);
            }
        }

        LazySegmentTree lst = new LazySegmentTree(nums.length);
        for (var v : g.values()) if (v.size() > 1) lst.update(v.first(), v.last(), 1);

        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int idx = queries[i][0], val = queries[i][1], old = nums[idx]; nums[idx] = val;

            // 处理旧的值
            if (primes[old]) {
                var curr = g.get(old);
                if (curr.size() > 1) lst.update(curr.first(), curr.last(), -1); // 撤销之前的 +1

                curr.remove(idx);
                if (curr.isEmpty()) g.remove(old);
                else if (curr.size() > 1) lst.update(curr.first(), curr.last(), 1); // 新区间 +1
            }

            // 处理新修改的值
            if (primes[val]) {
                var curr = g.computeIfAbsent(val, k -> new TreeSet<>());
                if (curr.size() > 1) lst.update(curr.first(), curr.last(), -1); // 撤销之前的 +1

                curr.add(idx);
                if (curr.size() > 1) lst.update(curr.first(), curr.last(), 1); // 新区间 +1
            }

            ans[i] = g.size() + lst.query(0, nums.length - 1);
        }

        return ans;
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().maximumCount(new int[]{6, 68}, new int[][]{{0,2},{0,33}}), new int[]{1,0});

        assert Checker.check(new Solution().maximumCount(new int[]{2,1,3,1,2}, new int[][]{{1,2},{3,3}}), new int[]{3,4});
        assert Checker.check(new Solution().maximumCount(new int[]{2,1,4}, new int[][]{{0,1}}), new int[]{0});
    }

}
