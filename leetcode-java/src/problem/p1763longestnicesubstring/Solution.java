package problem.p1763longestnicesubstring;

/**
 * 1763. Longest Nice Substring
 *
 * https://leetcode-cn.com/problems/longest-nice-substring/
 *
 * A string s is nice if, for every letter of the alphabet that s contains,
 * it appears both in uppercase and lowercase.
 *
 * For example, "abABB" is nice because 'A' and 'a' appear, and 'B' and 'b' appear.
 *
 * However, "abA" is not because 'b' appears, but 'B' does not.
 *
 * Given a string s, return the longest substring of s that is nice.
 *
 * If there are multiple, return the substring of the earliest occurrence.
 *
 * If there are none, return an empty string.
 */

public class Solution {

    public String longestNiceSubstring(String s) {
        String ans = "";
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length - ans.length(); i++) {
            int lower = 0, upper = 0;
            for (int j = i; j < chars.length; j++) {
                if ('a' <= chars[j] && chars[j] <= 'z') {
                    lower |= 1 << (chars[j] - 'a');
                } else {
                    upper |= 1 << (chars[j] - 'A');
                }

                if (lower == upper && (j - i + 1) > ans.length()) {
                    ans = s.substring(i, j + 1);
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().longestNiceSubstring("YazaAay").equals("aAa");
        assert new Solution().longestNiceSubstring("Bb").equals("Bb");
        assert new Solution().longestNiceSubstring("c").equals("");
        assert new Solution().longestNiceSubstring("dDzeE").equals("dD");
    }

}
