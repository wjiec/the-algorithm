package weekly.bw149.D;

/**
 * 3441. Minimum Cost Good Caption
 *
 * https://leetcode.cn/contest/biweekly-contest-149/problems/minimum-cost-good-caption/
 *
 * You are given a string caption of length n. A good caption is a string
 * where every character appears in groups of at least 3 consecutive occurrences.
 *
 * For example:
 *
 * "aaabbb" and "aaaaccc" are good captions.
 * "aabbb" and "ccccd" are not good captions.
 * You can perform the following operation any number of times:
 *
 * Choose an index i (where 0 <= i < n) and change the character at that index to either:
 *
 * The character immediately before it in the alphabet (if caption[i] != 'a').
 * The character immediately after it in the alphabet (if caption[i] != 'z').
 * Your task is to convert the given caption into a good caption using the minimum number of
 * operations, and return it. If there are multiple possible good captions, return the
 * lexicographically smallest one among them. If it is impossible to create a good caption,
 * return an empty string "".
 */

public class Solution {

    public String minCostGoodCaption(String caption) {
        int n = caption.length();
        if (n < 3) return "";

        int[] chars = new int[caption.length()];
        for (int i = 0; i < caption.length(); i++) chars[i] = caption.charAt(i) - 'a';

        // dp[i][j] 表示当第 i 位选择使用 j 时的最小操作数
        int[][] dp = new int[n + 1][26];
        // minChar[i] 表示在 i 处最少操作次数所使用的字母
        int[] minChar = new int[n + 1];
        // best[i][j] 表示当第 i 位选择使用 j 时最优的来源
        int[][] best = new int[n + 1][26];
        for (int i = n - 1; i >= 0; i--) {
            int curr = Integer.MAX_VALUE;
            for (char j = 0; j < 26; j++) {
                // 把当前字母变成 j 的操作数
                int currOp = Math.abs(chars[i] - j);

                // 下一个位置也使用 j 的情况
                int ans1 = dp[i + 1][j] + currOp;
                // 也可以包括当前位置之后连续 3 个都相同
                int ans2 = i <= n - 6 ? (dp[i + 3][minChar[i + 3]] + currOp + Math.abs(chars[i + 1] - j) + Math.abs(chars[i + 2] - j)) : Integer.MAX_VALUE;

                // 保存最佳方案来源
                dp[i][j] = ans1;
                if (ans2 < dp[i][j] || (ans2 == dp[i][j] && minChar[i + 3] < j)) {
                    dp[i][j] = ans2; best[i][j] = minChar[i + 3];
                } else best[i][j] = j;

                // 更新当前位置的最佳字符
                if (dp[i][j] < curr) { curr = dp[i][j]; minChar[i] = j; }
            }
        }

        char[] ans = new char[n];
        for (int i = 0, j = minChar[0]; i < n; ) {
            ans[i] = (char) ('a' + j);
            if (best[i][j] != j) {
                ans[i + 1] = ans[i + 2] = ans[i];
                j = best[i][j]; i += 3;
            } else i++;
        }

        return new String(ans);
    }

    public static void main(String[] args) {
        assert new Solution().minCostGoodCaption("owsjeo").equals("sssjjj");
    }

}
