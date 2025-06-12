package weekly.bw157.B;

import java.util.Arrays;
import java.util.TreeSet;

/**
 * Q2. Find Maximum Number of Non Intersecting Substrings
 *
 * https://leetcode.cn/contest/biweekly-contest-157/problems/find-maximum-number-of-non-intersecting-substrings/
 *
 * You are given a string word.
 *
 * Return the maximum number of non-intersecting substrings of word that are at
 * least four characters long and start and end with the same letter.
 */

public class Solution {

    /** @noinspection unchecked*/
    public int maxSubstrings(String word) {
        TreeSet<Integer>[] g = new TreeSet[128];
        Arrays.setAll(g, i -> new TreeSet<>());

        // dp[i] 表示从 [0, i) 的长度至少为 4 的不相交子字符串的数量
        int[] dp = new int[word.length() + 1];
        for (int i = 1; i <= word.length(); i++) {
            dp[i] = dp[i - 1];
            char c = word.charAt(i - 1);
            Integer p = g[c].floor(i - 3);
            if (i >= 4 && p != null) dp[i] = Math.max(dp[i], dp[p - 1] + 1);
            g[c].add(i);
        }

        return dp[word.length()];
    }

    public static void main(String[] args) {
        assert new Solution().maxSubstrings("abcceaddba") == 1;

        assert new Solution().maxSubstrings("abcdeafdef") == 2;
        assert new Solution().maxSubstrings("bcdaaaab") == 1;

        assert new Solution().maxSubstrings("aaaa") == 1;
        assert new Solution().maxSubstrings("aaab") == 0;
        assert new Solution().maxSubstrings("aaaaaaaa") == 2;
    }

}
