package weekly.w392.B;

/**
 * 3106. Lexicographically Smallest String After Operations With Constraint
 *
 * https://leetcode.cn/contest/weekly-contest-392/problems/lexicographically-smallest-string-after-operations-with-constraint/
 *
 * You are given a string s and an integer k.
 *
 * Define a function distance(s1, s2) between two strings s1 and s2 of the same length n as:
 *
 * The sum of the minimum distance between s1[i] and s2[i] when the characters from 'a' to 'z' are placed
 * in a cyclic order, for all i in the range [0, n - 1].
 *
 * For example, distance("ab", "cd") == 4, and distance("a", "z") == 1.
 *
 * You can change any letter of s to any other lowercase English letter, any number of times.
 *
 * Return a string denoting the lexicographically smallest string t you can get after some
 * changes, such that distance(s, t) <= k.
 */

public class Solution {

    public String getSmallestString(String s, int k) {
        StringBuilder sb = new StringBuilder();
        for (var c : s.toCharArray()) {
            if (k != 0) {
                int toA = Math.min(c - 'a', 'a' + 26 - c);
                if (k >= toA) {
                    sb.append('a');
                    k -= toA;
                } else {
                    sb.append((char) (c - k));
                    k = 0;
                }
            } else sb.append(c);
        }

        return sb.toString();
    }

    public static void main(String[] args) {
    }

}
