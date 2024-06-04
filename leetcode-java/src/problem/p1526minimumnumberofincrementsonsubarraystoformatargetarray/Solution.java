package problem.p1526minimumnumberofincrementsonsubarraystoformatargetarray;

import ability.Benchmark;

import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeMap;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 1526. Minimum Number of Increments on Subarrays to Form a Target Array
 *
 * https://leetcode.cn/problems/minimum-number-of-increments-on-subarrays-to-form-a-target-array/
 *
 * You are given an integer array target. You have an integer array initial of the
 * same size as target with all elements initially zeros.
 *
 * In one operation you can choose any subarray from initial and increment each value by one.
 *
 * Return the minimum number of operations to form a target array from initial.
 *
 * The test cases are generated so that the answer fits in a 32-bit integer.
 */

public class Solution {

    public int minNumberOperations(int[] target) {
        // {k, v} 记录区间 [k, v)
        TreeMap<Integer, Integer> map = new TreeMap<>();
        Integer[] sorted = new Integer[target.length];
        for (int i = 0; i < sorted.length; i++) sorted[i] = i;
        Arrays.sort(sorted, Comparator.comparingInt(i -> target[i]));
        map.put(0, target.length);

        int ans = 0, prev = 0;
        for (int i = 0; i < sorted.length; ) {
            int curr = target[sorted[i]];

            ans += (curr - prev) * map.size();
            prev = curr;

            while (i < sorted.length && target[sorted[i]] == curr) {
                int idx = sorted[i++]; // 需要拆分的区间分割点
                var interval = map.floorEntry(idx); // 需要拆分的区间的起点

                if (interval.getKey() == idx) map.remove(interval.getKey()); // 移除起点
                else map.put(interval.getKey(), idx); // 移除中间点

                if (idx + 1 != interval.getValue()) map.put(idx + 1, interval.getValue());
            }
        }

        return ans;
    }

    private static class DivideAndConquer {
        public int minNumberOperations(int[] target) {
            tree = new int[target.length * 4];
            build(target, 0, target.length - 1, 1);

            return minOperations(target, 0, target.length, 0);
        }

        private int[] tree = new int[0];

        private void build(int[] target, int l, int r, int p) {
            if (l == r) {
                tree[p] = target[l];
                return;
            }

            int mid = l + (r - l) / 2;
            build(target, l, mid, p * 2);
            build(target, mid + 1, r, p * 2 + 1);
            tree[p] = Math.min(tree[p * 2], tree[p * 2 + 1]);
        }

        private int query(int l, int r, int s, int t, int p) {
            // [l, r] 为查询区间, [s, t] 为当前节点包含的区间, p 为当前节点的编号
            if (l <= s && t <= r) return tree[p];

            int m = s + (t - s) / 2, mi = Integer.MAX_VALUE;
            if (l <= m) mi = Math.min(mi, query(l, r, s, m, p * 2));
            if (r > m) mi = Math.min(mi, query(l, r, m + 1, t, p * 2 + 1));
            return mi;
        }

        private int minOperations(int[] target, int l, int r, int base) {
            if (l >= r) return 0;

            int mi = query(l, r - 1, 0, target.length - 1, 1);
            int ans = mi - base;
            for (int s = l - 1, i = l; i <= r; i++) {
                if (i == r || target[i] == mi) {
                    ans += minOperations(target, s + 1, i, mi);
                    s = i;
                }
            }

            return ans;
        }
    }

    private static class MonotoneStack {
        public int minNumberOperations(int[] target) {
            int ans = target[0];
            for (int i = 1; i < target.length; i++) {
                ans += Math.max(0, target[i] - target[i - 1]);
            }
            return ans;
        }
    }

    public static void main(String[] args) {
        int[] testdata = new int[100_000];
        for (int i = 0; i < testdata.length; i++) testdata[i] = ThreadLocalRandom.current().nextInt(100_000);
        int ans = new Solution().minNumberOperations(testdata);

        Benchmark.benchmark("split", () -> {
            assert new Solution().minNumberOperations(new int[]{1,2,3,2,1}) == 3;
            assert new Solution().minNumberOperations(new int[]{3,1,1,2}) == 4;
            assert new Solution().minNumberOperations(new int[]{3,1,5,4,2}) == 7;
            assert new Solution().minNumberOperations(new int[]{1,1,1,1}) == 1;
            assert new Solution().minNumberOperations(testdata) == ans;
        });

        Benchmark.benchmark("dac", () -> {
            assert new DivideAndConquer().minNumberOperations(new int[]{1,2,3,2,1}) == 3;
            assert new DivideAndConquer().minNumberOperations(new int[]{3,1,1,2}) == 4;
            assert new DivideAndConquer().minNumberOperations(new int[]{3,1,5,4,2}) == 7;
            assert new DivideAndConquer().minNumberOperations(new int[]{1,1,1,1}) == 1;
            assert new DivideAndConquer().minNumberOperations(testdata) == ans;
        });

        Benchmark.benchmark("dac", () -> {
            assert new MonotoneStack().minNumberOperations(new int[]{1,2,3,2,1}) == 3;
            assert new MonotoneStack().minNumberOperations(new int[]{3,1,1,2}) == 4;
            assert new MonotoneStack().minNumberOperations(new int[]{3,1,5,4,2}) == 7;
            assert new MonotoneStack().minNumberOperations(new int[]{1,1,1,1}) == 1;
            assert new MonotoneStack().minNumberOperations(testdata) == ans;
        });
    }

}
