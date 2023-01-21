package offer2.p97;

import java.util.ArrayList;
import java.util.List;

/**
 * 剑指 Offer II 097. 子序列的数目
 *
 * https://leetcode.cn/problems/21dk04/
 *
 * 给定一个字符串 s 和一个字符串 t ，计算在 s 的子序列中 t 出现的个数。
 *
 * 字符串的一个 子序列 是指，通过删除一些（也可以不删除）字符且不干扰剩余字符相对位置所组成的新字符串。
 *
 * （例如，"ACE" 是 "ABCDE" 的一个子序列，而 "AEC" 不是）
 *
 * 题目数据保证答案符合 32 位带符号整数范围。
 */

public class Solution {

    public int numDistinct(String s, String t) {
        if (s.length() < t.length()) return 0;

        List<Integer>[] moves = new List[128];

        for (int i = 0; i < moves.length; i++) {
            moves[i] = new ArrayList<>();
        }

        char[] chars = t.toCharArray();
        for (int i = chars.length - 1; i >= 0; i--) {
            moves[chars[i]].add(i);
        }

        int[] states = new int[t.length() + 1];
        states[0] = 1;

        for (var c : s.toCharArray()) {
            for (var next : moves[c]) {
                states[next + 1] += states[next];
            }
        }
        return states[chars.length];
    }

    private static class DynamicProgramming {
        public int numDistinct(String s, String t) {
            int m = s.length(), n = t.length();
            if (m < n) return 0;

            int[][] dp = new int[m + 1][n + 1];
            // 当 t 为空字符串时, 是所有其他任何字符串的子串
            for (int i = 0; i <= m; i++) dp[i][n] = 1;

            char[] chars = t.toCharArray();
            for (int i = m - 1; i >= 0; i--) {
                char curr = s.charAt(i);
                for (int j = n - 1; j >= 0; j--) {
                    if (curr == chars[j]) {
                        dp[i][j] = dp[i + 1][j + 1] + dp[i + 1][j];
                    } else dp[i][j] = dp[i + 1][j];
                }
            }

            return dp[0][0];
        }
    }

    public static void main(String[] args) {
        assert new Solution().numDistinct("rabbbit", "rabbit") == 3;
        assert new Solution().numDistinct("babgbag", "bag") == 5;

        assert new DynamicProgramming().numDistinct("rabbbit", "rabbit") == 3;
        assert new DynamicProgramming().numDistinct("babgbag", "bag") == 5;
    }

}
