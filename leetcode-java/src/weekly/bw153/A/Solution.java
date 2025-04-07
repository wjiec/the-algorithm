package weekly.bw153.A;

/**
 * 3498. Reverse Degree of a String
 *
 * https://leetcode.cn/contest/biweekly-contest-153/problems/reverse-degree-of-a-string/
 *
 * Given a string s, calculate its reverse degree.
 *
 * The reverse degree is calculated as follows:
 *
 * For each character, multiply its position in the reversed
 * alphabet ('a' = 26, 'b' = 25, ..., 'z' = 1) with its position in the string (1-indexed).
 *
 * Sum these products for all characters in the string.
 *
 * Return the reverse degree of s.
 */

public class Solution {

    public int reverseDegree(String s) {
        int ans = 0;
        for (int i = 0; i < s.length(); i++) {
            ans += (i + 1) * (Math.abs(s.charAt(i) - 'z') + 1);
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
