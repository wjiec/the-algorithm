package weekly.bw170.D;

import ability.Benchmark;

import java.util.Arrays;

/**
 * Q4. Total Waviness of Numbers in Range II
 *
 * https://leetcode.cn/contest/biweekly-contest-170/problems/total-waviness-of-numbers-in-range-ii/
 *
 * You are given two integers num1 and num2 representing an inclusive range [num1, num2].
 *
 * The waviness of a number is defined as the total count of its peaks and valleys:
 *
 * A digit is a peak if it is strictly greater than both of its immediate neighbors.
 * A digit is a valley if it is strictly less than both of its immediate neighbors.
 * The first and last digits of a number cannot be peaks or valleys.
 * Any number with fewer than 3 digits has a waviness of 0.
 *
 * Return the total sum of waviness for all numbers in the range [num1, num2].
 */

public class Solution {

    private static final long[] pow10 = new long[18];
    static { pow10[0] = 1; }
    static { for (int i = 1; i < pow10.length; i++) pow10[i] = pow10[i - 1] * 10L; }

    public long totalWaviness(long num1, long num2) {
        char[] chars1 = String.valueOf(num1 - 1).toCharArray();
        int[] digits1 = new int[chars1.length];
        for (int i = 0; i < chars1.length; i++) digits1[i] = chars1[i] - '0';

        char[] chars2 = String.valueOf(num2).toCharArray();
        int[] digits2 = new int[chars2.length];
        for (int i = 0; i < chars2.length; i++) digits2[i] = chars2[i] - '0';

        for (var m : memo) for (var r : m) Arrays.fill(r, -1);
        long ans1 = dfs(digits1, 0, true, -1, -1);

        for (var m : memo) for (var r : m) Arrays.fill(r, -1);
        long ans2 = dfs(digits2, 0, true, -1, -1);
        return ans2 - ans1;
    }

    private final long[][][] memo = new long[18][11][11];
    { for (var m : memo) for (var r : m) Arrays.fill(r, -1); }

    // 当前需要选择第 i 位的数字, 前一位选择的是 pre, 再前一位是 pp, 求该情况下的波动值是多少
    private long dfs(int[] digits, int i, boolean limited, int pp, int pre) {
        if (i >= digits.length) return 0;
        if (!limited && memo[i][pre + 1][pp + 1] != -1) return memo[i][pre + 1][pp + 1];

        // 如果前一位没有选, 那么当前位也可以不选, 直接跳过
        long ans = (pre != -1) ? 0 : dfs(digits, i + 1, false, -1, -1);
        // 枚举当前位可以填的数字
        for (int v = pre != -1 ? 0 : 1, upper = limited ? digits[i] : 9; v <= upper; v++) {
            // 如果当前位选择 v 且 pre 能构成峰或者谷的话, 也就是后续的数字不管怎么选择这都是满足条件的
            //  - 我们需要计算 pre 和 v 之间的关系
            //      - 如果 pre > pp == pre > v, 那么 pre 的位置就是一个峰或者谷
            //  - 也就是 ... pre v [...] 后面的数位可以随意选择
            //  - 后面的长度为 l = digits.length - i - 1
            //  - 也就是在当前位填写 v 后 pre 可以贡献 10^l 个波动值
            // 如果当前位已经到达上限了, 那么后面就不能无脑填 [0-9] 了
            if (pre != -1 && pp != -1 && pre != pp && pre != v && pre > pp == pre > v) {
                if (limited && v == upper) {
                    long after = 0;
                    for (int j = i + 1; j < digits.length; j++) after = after * 10 + digits[j];
                    ans += after + 1; // 后面可以全 0
                } else ans += pow10[digits.length - i - 1];
            }

            // 继续枚举后面的值
            ans += dfs(digits, i + 1, limited && v == upper, pre, v);
        }

        if (!limited) memo[i][pre + 1][pp + 1] = ans;
        return ans;
    }

    public static void main(String[] args) {
        Benchmark.benchmark("", () -> {
            assert new Solution().totalWaviness(120, 130) == 3;
            assert new Solution().totalWaviness(198, 202) == 3;
            assert new Solution().totalWaviness(4848, 4848) == 2;
            assert new Solution().totalWaviness(12345678912345L, 92345678912345L) == 550245678912348L;
        });
    }

}
