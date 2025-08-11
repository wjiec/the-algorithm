package weekly.bw160.A;

/**
 * Q1. Hexadecimal and Hexatrigesimal Conversion©leetcode
 *
 * https://leetcode.cn/contest/biweekly-contest-160/problems/hexadecimal-and-hexatrigesimal-conversion/
 *
 * You are given an integer n.
 *
 * Return the concatenation of the hexadecimal representation of n2
 * and the hexatrigesimal representation of n3.
 *
 * A hexadecimal number is defined as a base-16 numeral system that
 * uses the digits 0 – 9 and the uppercase letters A - F to represent
 * values from 0 to 15.
 *
 * A hexatrigesimal number is defined as a base-36 numeral system that uses
 * the digits 0 – 9 and the uppercase letters A - Z to represent values from 0 to 35.
 */

public class Solution {

    public String concatHex36(int n) {
        StringBuilder hex = new StringBuilder();
        for (int v = n * n; v != 0; v /= 16) {
            int mod = v % 16;
            hex.append((char) (mod < 10 ? ('0' + mod) : ('A' + mod - 10)));
        }

        StringBuilder base36 = new StringBuilder();
        for (int v = n * n * n; v != 0; v /= 36) {
            int mod = v % 36;
            base36.append((char) (mod < 10 ? ('0' + mod) : ('A' + mod - 10)));
        }
        return hex.reverse().toString() + base36.reverse();
    }

    public static void main(String[] args) {
    }

}
