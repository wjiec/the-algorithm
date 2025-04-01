package weekly.w441.D;

import ability.Benchmark;

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

        // 使用数位 dp 进行求解, 需要减去 0
        return beautiful(digits, 0, 1, 0, true);
    }

    private int beautiful(int[] digits, int i, long mul, long sum, boolean limited) {
        if (i == digits.length) return sum != 0 && mul % sum == 0 ? 1 : 0;
        // 如果 mul == 0, 那么剩下的所有数都是合法的
        //  - 这种情况只会发生在 x---0--- 的情况, 也就是不含前导 0 且中间出现了个 0
        if (mul == 0) {
            // 如果当前位受到限制, 那么最多只能有 int(digits[i:]) 个数
            if (limited) {
                int ans = 0;
                for (; i < digits.length; i ++) ans = ans * 10 + digits[i];
                return ans;
            }

            // 否则剩下的所有位都可以随便输入
            return (int) Math.pow(10, digits.length - i);
        }

        int ans = 0, upper = limited ? digits[i] : 9;
        for (int j = 0; j <= upper; j++) {
            // 如果有前导 0 的话, 那么 mul 应该保持原样, 否则应该要乘进去的
            ans += beautiful(digits, i + 1, (sum == 0 && j == 0) ? mul : (mul * j), sum + j, limited && j == upper);
        }
        return ans;
    }

    public static void main(String[] args) {
        Benchmark.benchmark("", () -> {
            assert new Solution().beautifulNumbers(1, 999999999) == 670349658;
            assert new Solution().beautifulNumbers(1, 509) == 167;
            assert new Solution().beautifulNumbers(571, 581) == 5;

            assert new Solution().beautifulNumbers(10, 20) == 2;
            assert new Solution().beautifulNumbers(1, 15) == 10;
        });
    }

}
