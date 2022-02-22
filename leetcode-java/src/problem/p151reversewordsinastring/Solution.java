package problem.p151reversewordsinastring;

/**
 * 151. Reverse Words in a String
 *
 * https://leetcode-cn.com/problems/reverse-words-in-a-string/
 *
 * Given an input string s, reverse the order of the words.
 *
 * A word is defined as a sequence of non-space characters. The words in s will be separated by at least one space.
 *
 * Return a string of the words in reverse order concatenated by a single space.
 *
 * Note that s may contain leading or trailing spaces or multiple spaces between two words.
 *
 * The returned string should only have a single space separating the words. Do not include any extra spaces.
 */

public class Solution {

    public String reverseWords(String s) {
        char[] chars = s.toCharArray();
        for (int l = 0, r = chars.length - 1; l < r; l++, r--) swap(chars, l, r);

        int e = 0, n = chars.length;
        for (int r = 0; r < n; ) {
            while (r < n && chars[r] == ' ') r++;
            if (e != 0 && r < n) chars[e++] = ' ';
            while (r < n && chars[r] != ' ') chars[e++] = chars[r++];
        }

        for (int l = 0, i = 0; i < e;) {
            while (i < e && chars[i] != ' ') i++;
            for (int r = i - 1; l < r; l++, r--) swap(chars, l, r);
            l = ++i;
        }

        return new String(chars, 0, e);
    }

    private void swap(char[] chars, int l, int r) {
        char temp = chars[l];
        chars[l] = chars[r];
        chars[r] = temp;
    }

    public static void main(String[] args) {
        assert new Solution().reverseWords("the sky is blue").equals("blue is sky the");
        assert new Solution().reverseWords("  hello world  ").equals("world hello");
        assert new Solution().reverseWords("a good   example").equals("example good a");
        assert new Solution().reverseWords("  Bob    Loves  Alice   ").equals("Alice Loves Bob");
        assert new Solution().reverseWords("Alice does not even like bob").equals("bob like even not does Alice");

        assert new Solution().reverseWords("hello world").equals("world hello");
        assert new Solution().reverseWords("hhh").equals("hhh");
        assert new Solution().reverseWords("fast").equals("fast");

    }

}
