package lcci.s8.p14booleanevaluationlcci;

/**
 * 面试题 08.14. 布尔运算
 *
 * https://leetcode.cn/problems/boolean-evaluation-lcci/
 *
 * 给定一个布尔表达式和一个期望的布尔结果 result，布尔表达式由 0 (false)、1 (true)、& (AND)、 | (OR) 和 ^ (XOR) 符号组成。
 * 实现一个函数，算出有几种可使该表达式得出 result 值的括号方法。
 */

public class Solution {

    public int countEval(String s, int result) {
        if (s.length() == 0) return 0;
        if (s.length() == 1) return s.charAt(0) - '0' == result ? 1 : 0;

        int n = s.length();
        char[] chars = s.toCharArray();
        int[][][] dp = new int[n][n][2];
        for (int i = 0; i < n; i += 2) {
            dp[i][i][chars[i] - '0'] = 1;
        }

        for (int len = 3; len <= n; len += 2) {
            for (int b = 0; b < n; b += 2) {
                int e = b + len - 1;
                if (e >= n) break;

                for (int sym = b + 1; sym < e; sym += 2) {
                    int[] left = dp[b][sym - 1];
                    int[] right = dp[sym + 1][e];

                    switch (chars[sym]) {
                        case '|' -> {
                            dp[b][e][0] += left[0] * right[0];
                            dp[b][e][1] += left[1] * right[1] + left[0] * right[1] + left[1] * right[0];
                        }
                        case '&' -> {
                            dp[b][e][0] += left[0] * right[0] + left[1] * right[0] + left[0] * right[1];
                            dp[b][e][1] += left[1] * right[1];
                        }
                        case '^' -> {
                            dp[b][e][0] += left[0] * right[0] + left[1] * right[1];
                            dp[b][e][1] += left[0] * right[1] + left[1] * right[0];
                        }
                    }
                }
            }
        }
        return dp[0][n - 1][result];
    }

    public static void main(String[] args) {
        assert new Solution().countEval("1", 0) == 0;

        assert new Solution().countEval("1^0|0|1", 0) == 2;
        assert new Solution().countEval("0&0&0&1^1|0", 1) == 10;
    }

}
