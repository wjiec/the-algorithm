package weekly.bw173.A;

/**
 * Q1. Reverse String Prefix
 *
 * https://leetcode.cn/contest/biweekly-contest-173/problems/reverse-string-prefix/
 *
 * You are given a string s and an integer k.
 *
 * Reverse the first k characters of s and return the resulting string.
 */

public class Solution {

    public String reversePrefix(String s, int k) {
        char[] chars = s.toCharArray();
        for (int l = 0, r = k - 1; l < r; l++, r--) {
            char c = chars[l]; chars[l] = chars[r]; chars[r] = c;
        }
        return new String(chars);
    }

    public static void main(String[] args) {
    }

}
