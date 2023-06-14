package weekly.w349.B;

/**
 * 2734. Lexicographically Smallest String After Substring Operation
 *
 * https://leetcode.cn/contest/weekly-contest-349/problems/lexicographically-smallest-string-after-substring-operation/
 *
 * You are given a string s consisting of only lowercase English letters. In one operation, you can do the following:
 *
 * Select any non-empty substring of s, possibly the entire string, then replace each one of its characters
 * with the previous character of the English alphabet.
 *
 * For example, 'b' is converted to 'a', and 'a' is converted to 'z'.
 *
 * Return the lexicographically smallest string you can obtain after performing the above operation exactly once.
 *
 * A substring is a contiguous sequence of characters in a string.
 *
 * A string x is lexicographically smaller than a string y of the same length if x[i] comes
 * before y[i] in alphabetic order for the first position i such that x[i] != y[i].
 */

public class Solution {

    public String smallestString(String s) {
        int idx = 0, n = s.length();
        char[] chars = s.toCharArray();
        StringBuilder sb = new StringBuilder();
        while (idx < n && chars[idx] == 'a') sb.append(chars[idx++]);
        if (idx == n) sb.setCharAt(n - 1, 'z');

        while (idx < n && chars[idx] != 'a') sb.append((char) (chars[idx++] - 1));
        while (idx < n) sb.append(chars[idx++]);
        return sb.toString();
    }

    public static void main(String[] args) {
    }

}
