package weekly.w415.C;

import ability.Ability.AlphaTrie;

/**
 * 3291. Minimum Number of Valid Strings to Form Target I
 *
 * https://leetcode.cn/contest/weekly-contest-415/problems/minimum-number-of-valid-strings-to-form-target-i/
 *
 * You are given an array of strings words and a string target.
 *
 * A string x is called valid if x is a prefix of any string in words.
 *
 * Return the minimum number of valid strings that can be concatenated to form target.
 *
 * If it is not possible to form target, return -1.
 */

public class Solution {

    private static final int INF = Integer.MAX_VALUE / 2;

    public int minValidStrings(String[] words, String target) {
        AlphaTrie trie = new AlphaTrie();
        for (var word : words) trie.set(word).asLeaf();

        int n = target.length();
        int[] dp = new int[n + 1];

        char[] chars = target.toCharArray();
        for (var i = n - 1; i >= 0; i--) {
            AlphaTrie curr = trie; dp[i] = INF;
            for (int j = i; j < n && ((curr = curr.get(chars[j])) != null); j++) {
                dp[i] = Math.min(dp[i], dp[j + 1] + 1);
            }
        }

        return dp[0] >= INF ? -1 : dp[0];
    }

    public static void main(String[] args) {
        assert new Solution().minValidStrings(new String[]{"abc","aaaaa","bcdef"}, "aabcdabc") == 3;
    }

}
