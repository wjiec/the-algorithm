package weekly.w490.D;

import ability.Benchmark;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Q4. Count Sequences to K
 *
 * https://leetcode.cn/contest/weekly-contest-490/problems/count-sequences-to-k/
 *
 * You are given an integer array nums, and an integer k.
 *
 * Start with an initial value val = 1 and process nums from left to right. At each index i,
 * you must choose exactly one of the following actions:
 *
 * Multiply val by nums[i].
 * Divide val by nums[i].
 * Leave val unchanged.
 * After processing all elements, val is considered equal to k only if its final rational value exactly equals k.
 *
 * Return the count of distinct sequences of choices that result in val == k.
 *
 * Note: Division is rational (exact), not integer division. For example, 2 / 4 = 1 / 2.
 */

public class Solution {

    record Fraction(long numerator, long denominator) {
        public Fraction mul(long v) { return new Fraction(numerator * v, denominator); }
        public Fraction div(long v) { return new Fraction(numerator, denominator * v); }
        public int compare(long v) { return Long.compare(numerator, v * denominator); }
        public boolean equals(long v) { return numerator % denominator == 0 && (numerator / denominator == v); }
        public Fraction reduction() {
            long g = gcd(numerator, denominator);
            return new Fraction(numerator / g, denominator / g);
        }
        private static long gcd(long a, long b) { return a % b == 0 ? b : gcd(b, a % b); }
    }

    public int countSequences(int[] nums, long k) {
        // 如果全乘起来也达不到 k 的话, 直接提前返回, 分数的约分处理
        int n = nums.length;
        long[] suf = new long[n + 1]; suf[n] = 1;
        for (int i = n - 1; i >= 0; i--) suf[i] = suf[i + 1] * nums[i];
        if (suf[0] < k) return 0;

        // 一共 19 个位置, 每个位置可以选择 选乘, 选除, 不选 3 种可能
        //  - 3^19 = 1162261467 ~= 1e10, 会超时
        Map<Fraction, Integer> curr = new HashMap<>();
        curr.put(new Fraction(1, 1), 1);
        for (int i = 0; i < nums.length; i++) {
            int v = nums[i]; long s = suf[i + 1]; // (i, n) 之后所有数的乘积
            Map<Fraction, Integer> next = new HashMap<>();
            for (var entry : curr.entrySet()) {
                var frac = entry.getKey(); int cnt = entry.getValue();
                next.merge(frac, cnt, Integer::sum); // 不选
//                // 如果当前选择了乘法之后, 后面所有数都选除都无法降低到 k 则跳过
//                var mul = frac.mul(v).reduction();
//                if (mul.div(s).compare(k) <= 0) next.merge(mul, cnt, Integer::sum);
                next.merge(frac.mul(v).reduction(), cnt, Integer::sum); // 选乘
                // 如果当前选除法之后, 后面所有数都选乘都无法达到 k 则跳过
                var div = frac.div(v).reduction();
                if (div.mul(s).compare(k) >= 0) next.merge(div, cnt, Integer::sum);
            }
            curr = next;
        }

        int ans = 0;
        for (var e : curr.entrySet()) if (e.getKey().equals(k)) ans += e.getValue();
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = new int[19]; int k = ThreadLocalRandom.current().nextInt((int) 1e15);
        Arrays.setAll(nums, i -> ThreadLocalRandom.current().nextInt(6) + 1);
        Benchmark.benchmark("", () -> {
            System.out.println(new Solution().countSequences(nums, k));
        });

        assert new Solution().countSequences(new int[]{1}, 1) == 3;
        assert new Solution().countSequences(new int[]{4,6,3}, 2) == 2;
        assert new Solution().countSequences(new int[]{1,5}, 1) == 3;
    }

}
