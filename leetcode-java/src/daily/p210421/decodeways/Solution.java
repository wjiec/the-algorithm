package daily.p210421.decodeways;

import java.util.HashMap;
import java.util.Map;

/**
 * 91. Decode Ways
 *
 * https://leetcode-cn.com/problems/decode-ways/
 *
 * A message containing letters from A-Z can be encoded into numbers using the following mapping:
 *
 * 'A' -> "1"
 * 'B' -> "2"
 * ...
 * 'Z' -> "26"
 * To decode an encoded message, all the digits must be grouped then mapped back into letters using the reverse
 * of the mapping above (there may be multiple ways). For example, "11106" can be mapped into:
 *
 * "AAJF" with the grouping (1 1 10 6)
 * "KJF" with the grouping (11 10 6)
 * Note that the grouping (1 11 06) is invalid because "06" cannot be mapped into 'F' since "6" is different from "06".
 *
 * Given a string s containing only digits, return the number of ways to decode it.
 *
 * The answer is guaranteed to fit in a 32-bit integer.
 */

class Solution {

    public int numDecodings(String s) {
        if (s == null || s.isBlank() || s.startsWith("0")) {
            return 0;
        }

        if (s.length() == 1) {
            return 1;
        }

        Map<String, Character> possibles = new HashMap<>();
        for (var c = 'A'; c < 'Z'; c++) {
            possibles.put(String.valueOf(c - 'A' + 1), c);
        }

        return 0;
    }

    public static void main(String[] args) {
        assert new Solution().numDecodings("6") == 1;
        assert new Solution().numDecodings("06") == 0;
        assert new Solution().numDecodings("12") == 2; // 12 1,2
        assert new Solution().numDecodings("10") == 1; // 10
        assert new Solution().numDecodings("101") == 1; // 10,1
        assert new Solution().numDecodings("1011") == 1; // 10,11 10,1,1
    }

}