package weekly.bw105.B;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 2707. Extra Characters in a String
 *
 * https://leetcode.cn/contest/biweekly-contest-105/problems/extra-characters-in-a-string/
 *
 * You are given a 0-indexed string s and a dictionary of words dictionary.
 * You have to break s into one or more non-overlapping substrings such that each
 * substring is present in dictionary. There may be some extra characters in s which are not
 * present in any of the substrings.
 *
 * Return the minimum number of extra characters left over if you break up s optimally.
 */

public class Solution {

    public int minExtraChar(String s, String[] dictionary) {
        Set<String> words = new HashSet<>(Arrays.asList(dictionary));
        int[] dp = new int[s.length() + 1];
        for (int i = 1; i <= s.length(); i++) {
            dp[i] = dp[i - 1] + 1;
            for (int j = 0; j < i; j++) {
                if (words.contains(s.substring(j, i))) {
                    dp[i] = Math.min(dp[i], dp[j]);
                }
            }
        }
        return dp[s.length()];
    }

    public static void main(String[] args) {
        assert new Solution().minExtraChar("leetscode", new String[]{"leet","code","leetcode"}) == 1;
        assert new Solution().minExtraChar("sayhelloworld", new String[]{"hello","world"}) == 3;
    }

}
