package offer2.p94;

import java.util.Arrays;

/**
 * 剑指 Offer II 094. 最少回文分割
 *
 * https://leetcode.cn/problems/omKAoA
 *
 * 给定一个字符串 s，请将 s 分割成一些子串，使每个子串都是回文串。
 *
 * 返回符合要求的 最少分割次数 。
 */

@SuppressWarnings("DuplicatedCode")
public class Solution {

    public int minCut(String s) {
        int n = s.length();
        char[] chars = s.toCharArray();

        // dp[i] 表示从 [0 ~ i) 有多少个回文串
        int[] dp = new int[n + 1];
        Arrays.fill(dp, n); dp[0] = 0;
        for (int i = 1; i <= n; i++) {
            dp[i] = Math.min(dp[i], dp[i - 1] + 1); // 无脑直接增加一个

            // 从同一个位置出发开始检查回文串
            for (int l = i - 1, r = i - 1; l >= 0 && r < n; l--, r++) {
                if (chars[l] == chars[r]) {
                    dp[r + 1] = Math.min(dp[r + 1], dp[l] + 1);
                } else break;
            }

            // 从相邻位置出发检查回文串
            for (int l = i - 2, r = i - 1; l >= 0 && r < n; l--, r++) {
                if (chars[l] == chars[r]) {
                    dp[r + 1] = Math.min(dp[r + 1], dp[l] + 1);
                } else break;
            }
        }
        return dp[chars.length] - 1; // n 个回文串需要 n - 1 次分割
    }

    public static void main(String[] args) {
        assert new Solution().minCut("ccaacabacb") == 3;
        assert new Solution().minCut("abcdefghijkllkjiuh") == 10;

        assert new Solution().minCut("aab") == 1;
        assert new Solution().minCut("a") == 0;
        assert new Solution().minCut("ab") == 1;
    }

}
