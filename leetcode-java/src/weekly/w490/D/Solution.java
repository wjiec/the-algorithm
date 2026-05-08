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

    @SuppressWarnings("unchecked")
    private static class Optimization {
        public int countSequences(int[] nums, long k) {
            // 由于数据范围是 1 <= v <= 6, 考虑质因数分解只有 2 3 5 (1 不对结果产生影响)
            //  - 所以所有的可以凑成的整数都是 2 ^ e1 * 3 ^ e2 * 5 ^ e3
            //  - 因子为 2 最多只有 2 * n 个, 3 和 5 最多只有 n 个, 所以总数量就是 n^3 个
            //
            // 直接记忆化搜索即可
            return count(nums, nums.length - 1, 1, 1, k);
        }

        private final Map<Long, Map<Long, Integer>>[] memo = new Map[20];
        { Arrays.setAll(memo, i -> new HashMap<>()); }

        // 枚举当前在 i 位置, 当前值是 p / q 的数量是多少
        private int count(int[] nums, int i, long p, long q, long k) {
            if (i < 0) return p == k && q == 1 ? 1 : 0;
            if (memo[i].containsKey(p) && memo[i].get(p).containsKey(q)) return memo[i].get(p).get(q);

            // 不选
            int ans1 = count(nums, i - 1, p, q, k);
            // 选乘
            long g1 = gcd(p * nums[i], q);
            int ans2 = count(nums, i - 1, p * nums[i] / g1, q / g1, k);
            // 选除
            long g2 = gcd(p, q * nums[i]);
            int ans3 = count(nums, i - 1, p / g2, q * nums[i] / g2, k);

            memo[i].computeIfAbsent(p, key -> new HashMap<>()).put(q, ans1 + ans2 + ans3);
            return ans1 + ans2 + ans3;
        }

        private static long gcd(long a, long b) { return a % b == 0 ? b : gcd(b, a % b); }
    }

    public static void main(String[] args) {
        int[] nums = new int[19]; int k = ThreadLocalRandom.current().nextInt((int) 1e15);
        Arrays.setAll(nums, i -> ThreadLocalRandom.current().nextInt(6) + 1);
        Benchmark.benchmark("Optimization", () -> {
            System.out.println(new Optimization().countSequences(nums, k));
        });
        Benchmark.benchmark("", () -> {
            System.out.println(new Solution().countSequences(nums, k));
        });

        assert new Solution().countSequences(new int[]{1}, 1) == 3;
        assert new Solution().countSequences(new int[]{4,6,3}, 2) == 2;
        assert new Solution().countSequences(new int[]{1,5}, 1) == 3;
    }

}
