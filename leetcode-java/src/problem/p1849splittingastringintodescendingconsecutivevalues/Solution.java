package problem.p1849splittingastringintodescendingconsecutivevalues;

import java.math.BigInteger;

/**
 * 1849. Splitting a String Into Descending Consecutive Values
 *
 * https://leetcode.cn/problems/splitting-a-string-into-descending-consecutive-values/
 *
 * You are given a string s that consists of only digits.
 *
 * Check if we can split s into two or more non-empty substrings such that the numerical values of the
 * substrings are in descending order and the difference between numerical values of every
 * two adjacent substrings is equal to 1.
 *
 * For example, the string s = "0090089" can be split into ["0090", "089"] with numerical values [90,89].
 * The values are in descending order and adjacent values differ by 1, so this way is valid.
 * Another example, the string s = "001" can be split into ["0", "01"], ["00", "1"], or ["0", "0", "1"].
 * However, all the ways are invalid because they have numerical values [0,1], [0,1], and [0,0,1]
 * respectively, all of which are not in descending order.
 * Return true if it is possible to split s as described above, or false otherwise.
 *
 * A substring is a contiguous sequence of characters in a string.
 */

public class Solution {

    public boolean splitString(String s) {
        for (int i = 1; i < s.length(); i++) {
            BigInteger bi = new BigInteger(s.substring(0, i), 10);
            if (dfs(s, i, bi)) {
                return true;
            }
        }
        return false;
    }

    private boolean dfs(String s, int start, BigInteger prev) {
        if (start == s.length()) return true;
        if (prev.equals(BigInteger.ONE)) {
            while (start < s.length() && s.charAt(start) == '0') start++;
            return start == s.length();
        }

        BigInteger curr = BigInteger.ZERO;
        for (; start < s.length(); start++) {
            curr = curr.multiply(BigInteger.valueOf(10))
                .add(BigInteger.valueOf(s.charAt(start) - '0'));
            if (curr.equals(prev.subtract(BigInteger.ONE))) {
                return dfs(s, start + 1, curr);
            } else if (curr.compareTo(prev) >= 0) break;
        }
        return false;
    }

    public static void main(String[] args) {
        assert new Solution().splitString("200100");
        assert !new Solution().splitString("94650723337775781477");

        assert !new Solution().splitString("1234");
        assert new Solution().splitString("050043");
        assert !new Solution().splitString("9080701");
        assert new Solution().splitString("10009998");
        assert new Solution().splitString("1009998");
    }

}
