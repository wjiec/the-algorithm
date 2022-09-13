package problem.p1839longestsubstringofallvowelsinorder;

/**
 * 1839. Longest Substring Of All Vowels in Order
 *
 * https://leetcode.cn/problems/longest-substring-of-all-vowels-in-order/
 *
 * A string is considered beautiful if it satisfies the following conditions:
 *
 * Each of the 5 English vowels ('a', 'e', 'i', 'o', 'u') must appear at least once in it.
 * The letters must be sorted in alphabetical order (i.e. all 'a's before 'e's, all 'e's before 'i's, etc.).
 * For example, strings "aeiou" and "aaaaaaeiiiioou" are considered beautiful, but "uaeio", "aeoiu", and
 * "aaaeeeooo" are not beautiful.
 *
 * Given a string word consisting of English vowels, return the length of the longest beautiful substring of word.
 * If no such substring exists, return 0.
 *
 * A substring is a contiguous sequence of characters in a string.
 */

public class Solution {

    public int longestBeautifulSubstring(String word) {
        int MASK = 1 | (1 << 4) | (1 << 8) | (1 << 14)| (1 << 20);

        int ans = 0, n = word.length();
        char[] chars = word.toCharArray();
        for (int j = 0; j < n; ) {
            char prev = 'a' - 1;
            int curr = 0, state = 0;
            while (j < n && chars[j] >= prev) {
                curr++;
                state |= 1 << (chars[j] - 'a');
                prev = chars[j++];
            }
            if ((state & MASK) == MASK) ans = Math.max(ans, curr);
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().longestBeautifulSubstring("aeiaaioaaaaeiiiiouuuooaauuaeiu") == 13;
        assert new Solution().longestBeautifulSubstring("aeeeiiiioooauuuaeiou") == 5;
        assert new Solution().longestBeautifulSubstring("a") == 0;
    }

}
