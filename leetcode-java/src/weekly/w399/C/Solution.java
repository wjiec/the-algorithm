package weekly.w399.C;

import ability.Array;
import ability.Benchmark;

import java.util.HashMap;
import java.util.Map;

/**
 * 3164. Find the Number of Good Pairs II
 *
 * https://leetcode.cn/contest/weekly-contest-399/problems/find-the-number-of-good-pairs-ii/
 *
 * You are given 2 integer arrays nums1 and nums2 of lengths n and m respectively.
 *
 * You are also given a positive integer k.
 *
 * A pair (i, j) is called good if nums1[i] is divisible by nums2[j] * k (0 <= i <= n - 1, 0 <= j <= m - 1).
 *
 * Return the total number of good pairs.
 */

public class Solution {

    // a / (k * b) = n
    // a = n * k * b
    // a / b = n * k
    // a / k = n * b
    public long numberOfPairs(int[] nums1, int[] nums2, int k) {
        Map<Integer, Integer> m = new HashMap<>();
        for (var a : nums1) {
            if (a % k != 0) continue;
            for (int d = 1, x = a / k; d * d <= x; d++) {
                if (x % d != 0) continue;
                m.merge(d, 1, Integer::sum);
                if (d * d < x) m.merge(x / d, 1, Integer::sum);
            }
        }

        long ans = 0;
        for (var v : nums2) ans += m.getOrDefault(v, 0);
        return ans;
    }

    public static void main(String[] args) {
        Benchmark.benchmark("Solution", () -> {
            assert new Solution().numberOfPairs(new int[]{1,3,4}, new int[]{1,3,4}, 1) == 5;
            assert new Solution().numberOfPairs(new int[]{1}, new int[]{9}, 2) == 0;
            assert new Solution().numberOfPairs(Array.make(100_000, 1_000_000), Array.make(100_000, 1), 1) == 100_000_00_000L;
        });
    }

}
