package weekly.bw112.A;

/**
 * 2839. Check if Strings Can be Made Equal With Operations I
 *
 * https://leetcode.cn/contest/biweekly-contest-112/problems/check-if-strings-can-be-made-equal-with-operations-i/
 *
 * You are given two strings s1 and s2, both of length 4, consisting of lowercase English letters.
 *
 * You can apply the following operation on any of the two strings any number of times:
 *
 * Choose any two indices i and j such that j - i = 2, then swap the two characters at those indices in the string.
 * Return true if you can make the strings s1 and s2 equal, and false otherwise.
 */

public class Solution {

    public boolean canBeEqual(String s1, String s2) {
        char[] cs1 = s1.toCharArray();
        char[] cs2 = s2.toCharArray();
        return s1.equals(s2) ||
            equals(cs1[2], cs1[1], cs1[0], cs1[3], cs2[0], cs2[1], cs2[2], cs2[3]) ||
            equals(cs1[0], cs1[3], cs1[2], cs1[1], cs2[0], cs2[1], cs2[2], cs2[3]) ||
            equals(cs1[2], cs1[3], cs1[0], cs1[1], cs2[0], cs2[1], cs2[2], cs2[3]) ||
            equals(cs1[0], cs1[1], cs1[2], cs1[3], cs2[2], cs2[1], cs2[0], cs2[3]) ||
            equals(cs1[0], cs1[1], cs1[2], cs1[3], cs2[0], cs2[3], cs2[2], cs2[1]) ||
            equals(cs1[0], cs1[1], cs1[2], cs1[3], cs2[2], cs2[3], cs2[0], cs2[1]);
    }

    private boolean equals(char a, char b, char c, char d, char w, char x, char y, char z) {
        return a == w && b == x && c == y && d == z;
    }

    public static void main(String[] args) {
    }

}
