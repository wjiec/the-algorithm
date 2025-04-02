package weekly.w441.D;

import ability.Benchmark;

import java.util.HashMap;
import java.util.Map;

/**
 * 3490. Count Beautiful Numbers
 *
 * https://leetcode.cn/contest/weekly-contest-441/problems/count-beautiful-numbers/
 *
 * You are given two positive integers, l and r. A positive integer is called beautiful
 * if the product of its digits is divisible by the sum of its digits.
 *
 * Return the count of beautiful numbers between l and r, inclusive.
 */

public class Solution {

    // 数位的乘积可以被数位之和整除的整数数量
    public int beautifulNumbers(int l, int r) {
        // 如果数位之积为 0, 则后续所有数字都是可以的
        // 简化为 beautiful(r) - beautiful(l - 1)
        return beautiful(r) - beautiful(l - 1);
    }

    private int beautiful(int v) {
        int[] digits = new int[String.valueOf(v).length()];
        for (int i = digits.length - 1; i >= 0; i--, v /= 10) digits[i] = v % 10;

        memo.clear();
        // 使用数位 dp 进行求解
        return beautiful(digits, 0, 1, 0, true, false);
    }

    private final Map<Long, Integer> memo = new HashMap<>();

    private int beautiful(int[] digits, int i, long mul, long sum, boolean limited, boolean valid) {
        if (i == digits.length) return valid && mul % sum == 0 ? 1 : 0;

        long key = (mul << 32) | (sum << 16) | i;
        if (!limited && memo.containsKey(key)) return memo.get(key);

        int ans = 0;
        if (!valid) ans += beautiful(digits, i + 1, mul, sum, false, false);

        int upper = limited ? digits[i] : 9;
        for (int j = valid ? 0 : 1; j <= upper; j++) {
            ans += beautiful(digits, i + 1, mul * j, sum + j, limited && j == upper, true);
        }

        memo.put(key, ans);
        return ans;
    }

    public static void main(String[] args) {
        Benchmark.benchmark("", () -> {
            assert new Solution().beautifulNumbers(951, 955) == 2;

            assert new Solution().beautifulNumbers(1, 999999999) == 670349658;
            assert new Solution().beautifulNumbers(1, 509) == 167;
            assert new Solution().beautifulNumbers(571, 581) == 5;

            assert new Solution().beautifulNumbers(10, 20) == 2;
            assert new Solution().beautifulNumbers(1, 15) == 10;
        });
    }

}
