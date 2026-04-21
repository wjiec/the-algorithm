package weekly.w483.A;

/**
 * Q1. Largest Even Number
 *
 * https://leetcode.cn/contest/weekly-contest-483/problems/largest-even-number/
 *
 * You are given a string s consisting only of the characters '1' and '2'.
 *
 * You may delete any number of characters from s without changing the order of the remaining characters.
 *
 * Return the largest possible resultant string that represents an even integer.
 *
 * If there is no such string, return the empty string "".
 */

public class Solution {

    public String largestEven(String s) {
        char[] chars = s.toCharArray();

        int n = chars.length - 1;
        while (n >= 0 && (chars[n] & 1) == 1) n--;
        if (n < 0) return "";
        return new String(chars, 0, n + 1);
    }

    public static void main(String[] args) {
    }

}
