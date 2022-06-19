package weekly.w298.C;

import java.math.BigInteger;

/**
 * 6099. Longest Binary Subsequence Less Than or Equal to K
 *
 * https://leetcode.cn/contest/weekly-contest-298/problems/longest-binary-subsequence-less-than-or-equal-to-k/
 *
 * You are given a binary string s and a positive integer k.
 *
 * Return the length of the longest subsequence of s that makes up a binary number less than or equal to k.
 *
 * Note:
 *
 * The subsequence can contain leading zeroes.
 * The empty string is considered to be equal to 0.
 * A subsequence is a string that can be derived from another string by deleting
 * some or no characters without changing the order of the remaining characters.
 */

public class Solution {

    public int longestSubsequence(String s, int k) {
        StringBuilder sb = new StringBuilder();
        BigInteger curr = new BigInteger(String.valueOf(k));
        for (int i = s.length() - 1; i >= 0; i--) {
            sb.insert(0, s.charAt(i));
            BigInteger next = new BigInteger(sb.toString(),2);
            if(next.compareTo(curr) > 0) {
                sb.deleteCharAt(0);
            }
        }
        return sb.length();
    }

    public static void main(String[] args) {
        assert new Solution().longestSubsequence("1001010", 5) == 5;
        assert new Solution().longestSubsequence("00101001", 1) == 6;
        assert new Solution().longestSubsequence("001010101011010100010101101010010", 93951055) == 6;
    }

}
