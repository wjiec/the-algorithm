package weekly.w436.C;

import java.util.Arrays;

/**
 * 3448. Count Substrings Divisible By Last Digit
 *
 * https://leetcode.cn/contest/weekly-contest-436/problems/count-substrings-divisible-by-last-digit/
 *
 * You are given a string s consisting of digits.
 *
 * Return the number of substrings of s divisible by their non-zero last digit.
 *
 * Note: A substring may contain leading zeros.
 */

/** @noinspection DuplicatedCode */
public class Solution {

    public long countSubstrings(String s) {
        // 对于一个以 s_i 结尾的数 v_i 来说, 对于每一个前缀都有:
        //  v_i = v_{i-1} * 10 + s_i
        //
        // 对于每一个模数 m 有以下转换表达式:
        //  v_i % m = (v_{i-1} * 10 + s_i) % m
        //          = (v_{i-1} % m * 10 + s_i) % m
        //
        // 由此, 我们可以建立递推关系:
        //  i - 1 : (v_{i-1} % m * 10 + s_{i-1}) % m = rem_{i-1}
        //      -> i : (rem_{i-1} * 10 + s_i) = rem_i
        //
        // 使用 dp[i + 1][j][k] 表示使用以 s_{i-1} 结尾的数字对 j 取模余数为 k 的方案数
        //
        // 我们需要求的是 v_i % s_i == 0 的数(也就是子字符串)的数量:
        //  ans += dp[i + 1][s_i][0]

        long ans = 0;
        // 可以对数 v_i 取 [1, 9] 的模, 余数为 [0, 8]
        long[][][] dp = new long[s.length() + 1][10][9];
        for (int i = 1; i <= s.length(); i++) {
            int curr = s.charAt(i - 1) - '0';
            // 根据以上递推关系进行转移, 枚举每一个模数 m
            for (int m = 1; m <= 9; m++) {
                // 需要加上当前数作为一个单独数字的项
                dp[i][m][curr % m] += 1;

                // 枚举前一个位置的所有余数 rem
                for (int rem = 0; rem < m; rem++) {
                    dp[i][m][(rem * 10 + curr) % m] += dp[i - 1][m][rem];
                }
            }

            // 叠加答案
            ans += dp[i][curr][0];
        }

        return ans;
    }

    private static class RollingOptimization {
        public long countSubstrings(String s) {
            long ans = 0;
            // 可以对数 v_i 取 [1, 9] 的模, 余数为 [0, 8]
            long[][][] dp = new long[2][10][9];
            for (int i = 0; i < s.length(); i++) {
                int curr = s.charAt(i) - '0';
                // 根据以上递推关系进行转移, 枚举每一个模数 m
                for (int m = 1; m <= 9; m++) {
                    Arrays.fill(dp[i & 1][m], 0);

                    // 需要加上当前数作为一个单独数字的项
                    dp[i & 1][m][curr % m] += 1;

                    // 枚举前一个位置的所有余数 rem
                    for (int rem = 0; rem < m; rem++) {
                        dp[i & 1][m][(rem * 10 + curr) % m] += dp[(i & 1) ^ 1][m][rem];
                    }
                }

                // 叠加答案
                ans += dp[i & 1][curr][0];
            }

            return ans;
        }
    }

    public static void main(String[] args) {
        assert new RollingOptimization().countSubstrings("12936") == 11;
        assert new RollingOptimization().countSubstrings("5701283") == 18;
        assert new RollingOptimization().countSubstrings("1010101010") == 25;

        assert new Solution().countSubstrings("12936") == 11;
        assert new Solution().countSubstrings("5701283") == 18;
        assert new Solution().countSubstrings("1010101010") == 25;
    }

}
