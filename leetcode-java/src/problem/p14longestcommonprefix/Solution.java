package problem.p14longestcommonprefix;

import java.util.HashMap;

/**
 * 14. Longest Common Prefix
 *
 * https://leetcode-cn.com/problems/longest-common-prefix/
 *
 * Write a function to find the longest common prefix string amongst an array of strings.
 *
 * If there is no common prefix, return an empty string "".
 */

public class Solution {

    public String longestCommonPrefix(String[] strs) {
        String prefix = null;
        for (int i = 0; i < strs.length; i++) {
            if (strs[i].length() == 0 || (prefix != null && prefix.charAt(0) != strs[i].charAt(0))) {
                return "";
            }

            if (prefix == null) {
                prefix = strs[i];
                continue;
            }

            int j = 0;
            while (j < prefix.length() && j < strs[i].length()) {
                if (prefix.charAt(j) != strs[i].charAt(j)) {
                    break;
                }
                j++;
            }

            if (j == 0) {
                return "";
            } else {
                prefix = prefix.substring(0, j);
            }
        }
        return prefix == null ? "" : prefix;
    }

    public static void main(String[] args) {
        assert new Solution().longestCommonPrefix(new String[]{}).equals("");
        assert new Solution().longestCommonPrefix(new String[]{"abc", ""}).equals("");
        assert new Solution().longestCommonPrefix(new String[]{"abc", "abc"}).equals("abc");
        assert new Solution().longestCommonPrefix(new String[]{"flower","flow","flight"}).equals("fl");
        assert new Solution().longestCommonPrefix(new String[]{"dog","racecar","car"}).equals("");
        assert new Solution().longestCommonPrefix(new String[]{"ab","a"}).equals("a");
    }

}
