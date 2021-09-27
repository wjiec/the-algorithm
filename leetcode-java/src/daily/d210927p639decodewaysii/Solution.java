package daily.d210927p639decodewaysii;

/**
 * 639. Decode Ways II
 *
 * https://leetcode-cn.com/problems/decode-ways-ii/
 *
 * A message containing letters from A-Z can be encoded into numbers using the following mapping:
 *
 * 'A' -> "1"
 * 'B' -> "2"
 * ...
 * 'Z' -> "26"
 *
 * To decode an encoded message, all the digits must be grouped then mapped back into letters
 * using the reverse of the mapping above (there may be multiple ways). For example, "11106" can be mapped into:
 *
 * "AAJF" with the grouping (1 1 10 6)
 * "KJF" with the grouping (11 10 6)
 *
 * Note that the grouping (1 11 06) is invalid because "06"
 * cannot be mapped into 'F' since "6" is different from "06".
 *
 * In addition to the mapping above, an encoded message may contain the '*' character,
 * which can represent any digit from '1' to '9' ('0' is excluded).
 *
 * For example, the encoded message "1*" may represent any of the encoded messages
 * "11", "12", "13", "14", "15", "16", "17", "18", or "19".
 * Decoding "1*" is equivalent to decoding any of the encoded messages it can represent.
 *
 * Given a string s consisting of digits and '*' characters, return the number of ways to decode it.
 *
 * Since the answer may be very large, return it modulo 109 + 7.
 */

public class Solution {

    private static final int MOD = 1000000007;

    public int numDecodings(String s) {
        long a = 0, b = 1, c = 0;
        for (int i = 1, l = s.length(); i <= l; i++) {
            c = b * check1digit(s.charAt(i - 1)) % MOD;
            if (i > 1) {
                c = (c + a * check2digit(s.charAt(i - 2), s.charAt(i - 1))) % MOD;
            }

            a = b;
            b = c;
        }
        return (int) c;
    }

    private int check1digit(char c) {
        return c == '0' ? 0 : (c == '*' ? 9 : 1);
    }

    private int check2digit(char a, char b) {
        if (a == '*' && b == '*') return 15;
        if (a == '*') return b <= '6' ? 2 : 1;
        if (b == '*') {
            if (a == '1') return 9;
            if (a == '2') return 6;
            return 0;
        }
        return (a != '0' && ((a - '0') * 10 + (b - '0') <= 26)) ? 1 : 0;
    }

    public static void main(String[] args) {
        assert new Solution().numDecodings("*") == 9;
        assert new Solution().numDecodings("1*") == 18;
        assert new Solution().numDecodings("2*") == 15;
    }

}
