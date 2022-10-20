package problem.p2217findpalindromewithfixedlength;

import common.Checker;

import java.util.Arrays;

/**
 * 2217. Find Palindrome With Fixed Length
 *
 * https://leetcode.cn/problems/find-palindrome-with-fixed-length/
 *
 * Given an integer array queries and a positive integer intLength, return an array answer
 * where answer[i] is either the queries[i]th smallest positive palindrome of length intLength
 * or -1 if no such palindrome exists.
 *
 * A palindrome is a number that reads the same backwards and forwards.
 *
 * Palindromes cannot have leading zeros.
 */

public class Solution {

    public long[] kthPalindrome(int[] queries, int intLength) {
        int leftLen = (intLength + 1) / 2;
        int start = (int) (Math.pow(10, leftLen - 1) - 1);
        int limit = (int) (Math.pow(10, leftLen) - 1);

        long[] ans = new long[queries.length];
        for (int i = 0; i < queries.length; i++) {
            if (start + queries[i] > limit) ans[i] = -1;
            else ans[i] = recover(intLength, start + queries[i]);
        }

        return ans;
    }

    private long recover(int len, int v) {
        StringBuilder sb = new StringBuilder();
        sb.append(v);

        int leftLen = (len + 1) / 2;
        if (len % 2 == 0) {
            for (int i = leftLen - 1; i >= 0; i--) {
                sb.append(sb.charAt(i));
            }
        } else {
            for (int i = leftLen - 2; i >= 0; i--) {
                sb.append(sb.charAt(i));
            }
        }
        return Long.parseLong(sb.toString());
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().kthPalindrome(new int[]{1,2,3,4,5,90}, 3), new long[]{101,111,121,131,141,999});
        assert Checker.check(new Solution().kthPalindrome(new int[]{2,4,6}, 4), new long[]{1111,1331,1551});
    }

}
