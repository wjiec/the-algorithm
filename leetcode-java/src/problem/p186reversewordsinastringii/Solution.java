package problem.p186reversewordsinastringii;

import common.PrettyPrinter;

/**
 * 186. Reverse Words in a String II
 *
 * https://leetcode-cn.com/problems/reverse-words-in-a-string-ii/
 *
 * Given a character array s, reverse the order of the words.
 *
 * A word is defined as a sequence of non-space characters.
 * The words in s will be separated by a single space.
 *
 * Your code must solve the problem in-place, i.e. without allocating extra space.
 */

public class Solution {

    public void reverseWords(char[] s) {
        swap(s, 0, s.length - 1);
        for (int l = 0, r = 0; r <= s.length; r++) {
            if (r == s.length || s[r] == ' ') {
                swap(s, l, r - 1);
                l = r + 1;
            }
        }
    }

    private void swap(char[] s, int l, int r) {
        for (; l < r; l++, r--) {
            char t = s[l]; s[l] = s[r]; s[r] = t;
        }
    }

    public static void main(String[] args) {
        char[] cs1 = new char[]{'t','h','e',' ','s','k','y',' ','i','s',' ','b','l','u','e'};
        new Solution().reverseWords(cs1);
        PrettyPrinter.println(cs1);

        char[] cs2 = new char[]{'a'};
        new Solution().reverseWords(cs2);
        PrettyPrinter.println(cs2);
    }

}
