package weekly.bw170.A;

/**
 * Q1. Minimum Number of Flips to Reverse Binary String
 *
 * https://leetcode.cn/contest/biweekly-contest-170/problems/minimum-number-of-flips-to-reverse-binary-string/
 *
 * You are given a positive integer n.
 *
 * Let s be the binary representation of n without leading zeros.
 *
 * The reverse of a binary string s is obtained by writing the characters of s in the opposite order.
 *
 * You may flip any bit in s (change 0 → 1 or 1 → 0). Each flip affects exactly one bit.
 *
 * Return the minimum number of flips required to make s equal to the reverse of its original form.
 */

public class Solution {

    public int minimumFlips(int n) {
        var s = Integer.toBinaryString(n);
        var r = new StringBuilder(s).reverse().toString();

        int ans = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != r.charAt(i)) {
                ans++;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
