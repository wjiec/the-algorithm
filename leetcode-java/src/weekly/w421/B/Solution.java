package weekly.w421.B;

/**
 * 3335. Total Characters in String After Transformations I
 *
 * https://leetcode.cn/contest/weekly-contest-421/problems/total-characters-in-string-after-transformations-i/
 *
 * You are given a string s and an integer t, representing the number of transformations to perform.
 *
 * In one transformation, every character in s is replaced according to the following rules:
 *
 * If the character is 'z', replace it with the string "ab".
 *
 * Otherwise, replace it with the next character in the alphabet. For example, 'a' is replaced
 * with 'b', 'b' is replaced with 'c', and so on.
 *
 * Return the length of the resulting string after exactly t transformations.
 *
 * Since the answer may be very large, return it modulo 109 + 7.
 */

public class Solution {

    private final int Zi = 25, Ai = 0, Bi = 1;

    public int lengthAfterTransformations(String s, int t) {
        long[] curr = new long[26];
        for (var c : s.toCharArray()) curr[c - 'a']++;

        for (int i = 0; i < t; i++) {
            long[] next = new long[26];
            next[Ai] = next[Bi] = curr[Zi];
            for (char c = 'a'; c < 'z'; c++) {
                next[c - 'a' + 1] = (next[c - 'a' + 1] + curr[c - 'a']) % 1_000_000_007;
            }

            curr = next;
        }

        long ans = 0;
        for (var v : curr) ans = (ans + v) % 1_000_000_007;

        return (int) ans;
    }

    public static void main(String[] args) {
    }

}
