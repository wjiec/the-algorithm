package problem.p880decodedstringatindex;

import common.TODO;

/**
 * 880. Decoded String at Index
 *
 * https://leetcode.cn/problems/decoded-string-at-index/
 *
 * You are given an encoded string s. To decode the string to a tape, the encoded
 * string is read one character at a time and the following steps are taken:
 *
 * If the character read is a letter, that letter is written onto the tape.
 * If the character read is a digit d, the entire current tape is repeatedly written d - 1 more times in total.
 * Given an integer k, return the kth letter (1-indexed) in the decoded string.
 */

public class Solution {

    @TODO
    public String decodeAtIndex(String s, int k) {
        long len = 0;
        for (var c : s.toCharArray()) {
            if (!Character.isDigit(c)) len++;
            else len *= c - '0';
        }

        for (int i = s.length() - 1; i >= 0; i--) {
            char c = s.charAt(i); k %= len;
            if (k == 0 && Character.isLetter(c)) {
                return Character.toString(c);
            }

            if (!Character.isDigit(c)) len--;
            else len /= c - '0';
        }

        return " ";
    }

    public static void main(String[] args) {
        assert new Solution().decodeAtIndex("leet2code3", 10).equals("o");
        assert new Solution().decodeAtIndex("ha22", 5).equals("h");
        assert new Solution().decodeAtIndex("a2345678999999999999999", 1).equals("a");
    }

}
