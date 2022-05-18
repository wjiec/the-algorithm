package problem.p833findandreplaceinstring;

import java.util.Arrays;

/**
 * 833. Find And Replace in String
 *
 * https://leetcode.cn/problems/find-and-replace-in-string/
 *
 * You are given a 0-indexed string s that you must perform k replacement operations on. The replacement operations are given as three 0-indexed parallel arrays, indices, sources, and targets, all of length k.
 *
 * To complete the ith replacement operation:
 *
 * Check if the substring sources[i] occurs at index indices[i] in the original string s.
 * If it does not occur, do nothing.
 * Otherwise if it does occur, replace that substring with targets[i].
 * For example, if s = "abcd", indices[i] = 0, sources[i] = "ab", and targets[i] = "eee",
 * then the result of this replacement will be "eeecd".
 *
 * All replacement operations must occur simultaneously, meaning the replacement operations
 * should not affect the indexing of each other. The testcases will be generated
 * such that the replacements will not overlap.
 *
 * For example, a testcase with s = "abc", indices = [0, 1], and sources = ["ab","bc"]
 * will not be generated because the "ab" and "bc" replacements overlap.
 *
 * Return the resulting string after performing all replacement operations on s.
 * A substring is a contiguous sequence of characters in a string.
 */

public class Solution {

    public String findReplaceString(String s, int[] indices, String[] sources, String[] targets) {
        int[] matches = new int[s.length()];
        Arrays.fill(matches, -1);

        for (int i = 0; i < indices.length; i++) {
            if (s.startsWith(sources[i], indices[i])) {
                matches[indices[i]] = i;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); ) {
            if (matches[i] >= 0) {
                sb.append(targets[matches[i]]);
                i += sources[matches[i]].length();
            } else sb.append(s.charAt(i++));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        assert new Solution().findReplaceString("abcd", new int[]{0, 2},
            new String[]{"a", "cd"}, new String[]{"eee", "ffff"}).equals("eeebffff");
        assert new Solution().findReplaceString("abcd", new int[]{0, 2},
            new String[]{"ab","ec"}, new String[]{"eee","ffff"}).equals("eeecd");
    }

}
