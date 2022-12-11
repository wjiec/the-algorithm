package problem.p44wildcardmatching;

import java.util.HashMap;
import java.util.Map;

/**
 * 44. Wildcard Matching
 *
 * https://leetcode.cn/problems/wildcard-matching/
 *
 * Given an input string (s) and a pattern (p), implement wildcard
 * pattern matching with support for '?' and '*' where:
 *
 * '?' Matches any single character.
 * '*' Matches any sequence of characters (including the empty sequence).
 *
 * The matching should cover the entire input string (not partial).
 */

public class Solution {

    public boolean isMatch(String s, String p) {
        char[] chars1 = s.toCharArray();
        char[] chars2 = p.toCharArray();
        return dfs(chars1, 0, chars2, 0);
    }

    private final Map<Integer, Boolean> memo = new HashMap<>();

    private boolean dfs(char[] s1, int a, char[] s2, int b) {
        if (a == s1.length || b == s2.length) {
            while (b < s2.length && s2[b] == '*') b++;
            return a == s1.length && b == s2.length;
        }

        int id = (a << 16) | b;
        boolean ans = false;
        if (memo.containsKey(id)) return memo.get(id);

        if (s2[b] == '?' || s1[a] == s2[b]) {
            ans = dfs(s1, a + 1, s2, b + 1);
        } else if (s2[b] == '*') {
            for (int c = a; c <= s1.length; c++) {
                if (dfs(s1, c, s2, b + 1)) {
                    ans = true;
                    break;
                }
            }
        }

        memo.put(id, ans);
        return ans;
    }

    private static class DynamicProgramming {
        public boolean isMatch(String s, String p) {
            char[] scs = s.toCharArray();
            char[] pcs = p.toCharArray();

            int sl = s.length(), pl = p.length();
            // dp[i][j] 表示使用 s[0 ~ i) 与 p[0 ~ j) 是否能够匹配
            boolean[][] dp = new boolean[sl + 1][pl + 1];

            dp[0][0] = true;
            for (int j = 1; j <= pl; j++) {
                if (pcs[j - 1] == '*') dp[0][j] = true;
                else break;
            }

            for (int i = 1; i <= sl; i++) {
                for (int j = 1; j <= pl; j++) {
                    if (pcs[j - 1] == '?') {
                        // 必须匹配一个字符
                        dp[i][j] = dp[i - 1][j - 1];
                    } else if (pcs[j - 1] == '*') {
                        // 匹配任意个字符
                        dp[i][j] = dp[i - 1][j - 1] || dp[i - 1][j] || dp[i][j - 1];
                    } else if (pcs[j - 1] == scs[i - 1]) {
                        dp[i][j] = dp[i - 1][j - 1];
                    }
                }
            }

            return dp[sl][pl];
        }
    }

    public static void main(String[] args) {
        assert new Solution().isMatch("", "*****");

        assert !new Solution().isMatch("aa", "a");
        assert new Solution().isMatch("aa", "*");
        assert !new Solution().isMatch("cb", "?a");
        assert new Solution().isMatch("adceb", "*a*b");
        assert !new Solution().isMatch("acdcb", "a*c?b");

        assert new DynamicProgramming().isMatch("", "*****");
        assert !new DynamicProgramming().isMatch("abc", "");
        assert !new DynamicProgramming().isMatch("aa", "a");
        assert new DynamicProgramming().isMatch("aa", "*");
        assert !new DynamicProgramming().isMatch("cb", "?a");
        assert new DynamicProgramming().isMatch("adceb", "*a*b");
        assert !new DynamicProgramming().isMatch("acdcb", "a*c?b");
    }

}
