package weekly.w356.D;

import java.util.Arrays;

/**
 * 2801. Count Stepping Numbers in Range
 *
 * https://leetcode.cn/contest/weekly-contest-356/problems/count-stepping-numbers-in-range/
 *
 * Given two positive integers low and high represented as strings, find the
 * count of stepping numbers in the inclusive range [low, high].
 *
 * A stepping number is an integer such that all of its adjacent digits have an absolute difference of exactly 1.
 *
 * Return an integer denoting the count of stepping numbers in the inclusive range [low, high].
 *
 * Since the answer may be very large, return it modulo 109 + 7.
 *
 * Note: A stepping number should not have a leading zero.
 */

public class Solution {

    private final int MOD = 1_000_000_007;
    private final int[][] memo = new int[101][10];

    public int countSteppingNumbers(String low, String high) {
        for (var row : memo) Arrays.fill(row, -1);
        int h = dfs(high.toCharArray(), 0, true, false, 0);

        low = minus1(low);
        if (low.isEmpty()) return h;

        for (var row : memo) Arrays.fill(row, -1);
        long ans = h + MOD - dfs(low.toCharArray(), 0, true, false, 0);
        return (int) (ans % MOD);
    }

    private int dfs(char[] digits, int i, boolean limited, boolean valid, int prev) {
        if (i == digits.length) return valid ? 1 : 0;
        if (!limited && valid && memo[i][prev] != -1) return memo[i][prev];

        long ans = 0;
        if (!valid) ans += dfs(digits, i + 1, false, false, prev);

        int lower = valid ? 0 : 1;
        int upper = limited ? (digits[i] - '0') : 9;
        for (int j = lower; j <= upper; j++) {
            if (!valid || Math.abs(prev - j) == 1) {
                ans += dfs(digits, i + 1, limited && j == upper, true, j);
            }
        }
        if (!limited && valid) memo[i][prev] = (int) (ans % MOD);

        return (int) (ans % MOD);
    }

    private String minus1(String number) {
        char[] digits = number.toCharArray();
        for (int i = digits.length - 1; i >= 0; i--) {
            if (digits[i] != '0') {
                digits[i] -= 1; break;
            } else digits[i] = '9';
        }

        int start = 0;
        while (start < digits.length && digits[start] == '0') start++;
        return new String(digits).substring(start);
    }

    public static void main(String[] args) {
        assert new Solution().countSteppingNumbers("2", "40") == 14;

        assert new Solution().countSteppingNumbers("1", "11") == 10;
        assert new Solution().countSteppingNumbers("90", "101") == 2;
    }

}
