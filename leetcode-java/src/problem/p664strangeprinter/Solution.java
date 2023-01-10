package problem.p664strangeprinter;

/**
 * 664. Strange Printer
 *
 * https://leetcode.cn/problems/strange-printer/
 *
 * There is a strange printer with the following two special properties:
 *
 * The printer can only print a sequence of the same character each time.
 *
 * At each turn, the printer can print new characters starting from and ending at
 * any place and will cover the original existing characters.
 *
 * Given a string s, return the minimum number of turns the printer needed to print it.
 */

public class Solution {

    private char[] chars = null;

    public int strangePrinter(String s) {
        chars = s.toCharArray();
        return dfs(0, s.length() - 1);
    }

    private final int[][] memo = new int[101][101];

    private int dfs(int l, int r) {
        if (l >= r) return l == r ? 1 : 0;
        if (chars[l] == chars[r]) return dfs(l + 1, r);
        if (memo[l][r] == 0) {
            int ans = r - l + 1;
            for (int i = l; i < r; i++) {
                ans = Math.min(ans, dfs(l, i) + dfs(i + 1, r));
            }
            memo[l][r] = ans;
        }
        return memo[l][r];
    }

    private static class DynamicProgramming {
        public int strangePrinter(String s) {
            int n = s.length();
            int[][] dp = new int[n][n];

            char[] chars = s.toCharArray();
            for (int l = n - 1; l >= 0; l--) {
                dp[l][l] = 1;

                for (int r = l + 1; r < n; r++) {
                    if (chars[l] == chars[r]) {
                        dp[l][r] = dp[l + 1][r];
                    } else {
                        dp[l][r] = r - l + 1;
                        for (int mid = l; mid < r; mid++) {
                            dp[l][r] = Math.min(dp[l][r], dp[l][mid] + dp[mid + 1][r]);
                        }
                    }
                }
            }

            return dp[0][n - 1];
        }
    }

    public static void main(String[] args) {
        assert new Solution().strangePrinter("tbgtgb") == 4;
        assert new Solution().strangePrinter("baacdddaaddaaaaccbddbcabdaabdbbcdcbbbacbddcabcaaa") == 19;
        assert new Solution().strangePrinter("abcabc") == 5;

        assert new Solution().strangePrinter("aaabbb") == 2;
        assert new Solution().strangePrinter("aba") == 2;
        assert new Solution().strangePrinter("abcdeffedcba") == 6;
        assert new Solution().strangePrinter("abcdefedcba") == 6;

        assert new DynamicProgramming().strangePrinter("tbgtgb") == 4;
        assert new DynamicProgramming().strangePrinter("baacdddaaddaaaaccbddbcabdaabdbbcdcbbbacbddcabcaaa") == 19;
        assert new DynamicProgramming().strangePrinter("abcabc") == 5;
        assert new DynamicProgramming().strangePrinter("aaabbb") == 2;
        assert new DynamicProgramming().strangePrinter("aba") == 2;
        assert new DynamicProgramming().strangePrinter("abcdeffedcba") == 6;
        assert new DynamicProgramming().strangePrinter("abcdefedcba") == 6;
    }

}
