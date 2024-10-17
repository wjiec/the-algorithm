package problem.p1639numberofwaystoformatargetstringgivenadictionary;

import java.util.Arrays;
import java.util.TreeMap;

/**
 * 1639. Number of Ways to Form a Target String Given a Dictionary
 *
 * https://leetcode.cn/problems/number-of-ways-to-form-a-target-string-given-a-dictionary/
 *
 * You are given a list of strings of the same length words and a string target.
 *
 * Your task is to form target using the given words under the following rules:
 *
 * target should be formed from left to right.
 * To form the ith character (0-indexed) of target, you can choose the
 * kth character of the jth string in words if target[i] = words[j][k].
 *
 * Once you use the kth character of the jth string of words, you can no
 * longer use the xth character of any string in words where x <= k.
 *
 * In other words, all characters to the left of or at index k become unusuable for every string.
 *
 * Repeat the process until you form the string target.
 *
 * Notice that you can use multiple characters from the same string in words
 * provided the conditions above are met.
 *
 * Return the number of ways to form target from words. Since the answer may be
 * too large, return it modulo 109 + 7.
 * @noinspection unchecked
 */

public class Solution {

    public int numWays(String[] words, String target) {
        // 只和位置有关, 和在哪个字符串没关系
        TreeMap<Integer, Integer>[] g = new TreeMap[128];
        Arrays.setAll(g, i -> new TreeMap<>());
        for (var word : words) {
            char[] chars = word.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                g[chars[i]].merge(i, 1, Integer::sum);
            }
        }

        int n = words[0].length();
        memo = new long[target.length()][n + 1];
        for (var row : memo) Arrays.fill(row, -1);
        return (int) dfs(target.toCharArray(), 0, -1, n, g);
    }

    private long[][] memo;

    private long dfs(char[] chars, int i, int j, int n, TreeMap<Integer, Integer>[] g) {
        if (i == chars.length) return 1;
        if (memo[i][j + 1] != -1) return memo[i][j + 1];

        long ans = 0;
        var group = g[chars[i]];
        for (var e = group.higherEntry(j); e != null && n - e.getKey() >= chars.length - i; ) {
            ans += e.getValue() * dfs(chars, i + 1, e.getKey(), n, g);
            e = group.higherEntry(e.getKey());
        }

        return memo[i][j + 1] = (ans % 1_000_000_007);
    }

    // 超时, 很奇怪
    private static class Optimization {
        public int numWays(String[] words, String target) {
            final int MOD = 1_000_000_007;
            int wl = words[0].length(), tl = target.length();

            // dp[i][j] 表示 使用 words[0:i) 的字符, 组装成 target[0:j) 的方案数
            long[][] dp = new long[wl + 1][tl + 1]; dp[0][0] = 1;
            // 枚举使用第 i 个字符进行转移
            for (int i = 1; i <= wl; i++) {
                int[] count = new int[128];
                for (var word : words) count[word.charAt(i - 1)]++;

                // 枚举可以转移到字符串 target 的哪一个位置
                for (int j = 1; j <= i && j <= tl; j++) {
                    char c = target.charAt(j - 1);
                    if (count[c] == 0) continue;

                    // 我们最少也需要 j - 1 个字符才能组装得到 target[0:j - 1]
                    for (int k = i - 1; k >= j - 1; k--) {
                        dp[i][j] = (dp[i][j] + count[c] * dp[k][j - 1]) % MOD;
                    }
                }
            }

            long ans = 0;
            for (int i = tl; i <= wl; i++) {
                ans = (ans + dp[i][tl]) % MOD;
            }

            return (int) ans;
        }
    }

    // 状态压缩
    private static class Optimization1 {
        public int numWays(String[] words, String target) {
            final int MOD = 1_000_000_007;
            int wl = words[0].length(), tl = target.length();

            long[] dp = new long[tl + 1]; dp[0] = 1;
            // 枚举使用第 i 个字符进行转移
            for (int i = 1; i <= wl; i++) {
                int[] count = new int[128];
                for (var word : words) count[word.charAt(i - 1)]++;

                // 枚举可以转移到字符串 target 的哪一个位置
                for (int j = Math.min(i, tl); j > 0; j--) {
                    char c = target.charAt(j - 1);
                    if (count[c] == 0) continue;

                    dp[j] = (dp[j] + count[c] * dp[j - 1]) % MOD;
                }
            }

            return (int) dp[tl];
        }
    }

    public static void main(String[] args) {
        assert new Solution().numWays(new String[]{"acca","bbbb","caca"}, "aba") == 6;
        assert new Solution().numWays(new String[]{"abba","baab"}, "bab") == 4;
        assert new Solution().numWays(new String[]{"abcd"}, "abcd") == 1;
        assert new Solution().numWays(new String[]{"abab","baba","abba","baab"}, "abba") == 16;

        assert new Optimization().numWays(new String[]{"acca","bbbb","caca"}, "aba") == 6;
        assert new Optimization().numWays(new String[]{"abba","baab"}, "bab") == 4;
        assert new Optimization().numWays(new String[]{"abcd"}, "abcd") == 1;
        assert new Optimization().numWays(new String[]{"abab","baba","abba","baab"}, "abba") == 16;

        assert new Optimization1().numWays(new String[]{"acca","bbbb","caca"}, "aba") == 6;
        assert new Optimization1().numWays(new String[]{"abba","baab"}, "bab") == 4;
        assert new Optimization1().numWays(new String[]{"abcd"}, "abcd") == 1;
        assert new Optimization1().numWays(new String[]{"abab","baba","abba","baab"}, "abba") == 16;
    }

}
