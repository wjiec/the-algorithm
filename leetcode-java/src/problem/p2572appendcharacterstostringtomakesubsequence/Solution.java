package problem.p2572appendcharacterstostringtomakesubsequence;

/**
 * 6246. Append Characters to String to Make Subsequence
 *
 * https://leetcode.cn/problems/append-characters-to-string-to-make-subsequence/
 *
 * You are given two strings s and t consisting of only lowercase English letters.
 *
 * Return the minimum number of characters that need to be appended to the
 * end of s so that t becomes a subsequence of s.
 *
 * A subsequence is a string that can be derived from another string by deleting
 * some or no characters without changing the order of the remaining characters.
 */

public class Solution {

    public int appendCharacters(String s, String t) {
        char[] sc = s.toCharArray(), tc = t.toCharArray();
        int si = 0, sl = sc.length, ti = 0, tl = tc.length;
        while (si < sl && ti < tl) {
            if (sc[si] == tc[ti]) ti++;
            si++;
        }
        return tl - ti;
    }

    public static void main(String[] args) {
        assert new Solution().appendCharacters("coaching", "coding") == 4;
        assert new Solution().appendCharacters("abcde", "a") == 0;
        assert new Solution().appendCharacters("z", "abcde") == 5;
    }

}
