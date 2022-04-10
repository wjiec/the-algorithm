package problem.p522longestuncommonsubsequenceii;

import java.util.Arrays;

/**
 * 522. Longest Uncommon Subsequence II
 *
 * https://leetcode-cn.com/problems/longest-uncommon-subsequence-ii/
 *
 * Given an array of strings strs, return the length of the longest uncommon subsequence between them.
 * If the longest uncommon subsequence does not exist, return -1.
 *
 * An uncommon subsequence between an array of strings is a string that is a subsequence
 * of one string but not the others.
 *
 * A subsequence of a string s is a string that can be obtained after deleting any number of characters from s.
 *
 * For example, "abc" is a subsequence of "aebdc" because you can delete the underlined characters in "aebdc"
 * to get "abc". Other subsequences of "aebdc" include "aebdc", "aeb", and "" (empty string).
 */

public class Solution {

    public int findLUSlength(String[] strs) {
        Arrays.sort(strs, (a, b) -> b.length() - a.length());

        for (int i = 0; i < strs.length; i++) {
            boolean unique = true;
            for (int j = 0; j < strs.length; j++) {
                if (i == j) continue;

                if (isSubString(strs[i], strs[j])) {
                    unique = false; break;
                }
            }

            if (unique) return strs[i].length();
        }
        return -1;
    }

    private boolean isSubString(String a, String b) {
        int i = 0;
        for (int j = 0; i < a.length() && j < b.length(); j++) {
            if (a.charAt(i) == b.charAt(j)) i++;
        }
        return i == a.length();
    }

    public static void main(String[] args) {
        assert new Solution().findLUSlength(new String[]{"aba","cdc","eae"}) == 3;
        assert new Solution().findLUSlength(new String[]{"aaa","aaa","aa"}) == -1;
    }

}
