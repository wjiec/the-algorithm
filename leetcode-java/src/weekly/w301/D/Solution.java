package weekly.w301.D;

/**
 * 6115. Count the Number of Ideal Arrays
 *
 * https://leetcode.cn/contest/weekly-contest-301/problems/count-the-number-of-ideal-arrays/
 *
 * You are given two integers n and maxValue, which are used to describe an ideal array.
 *
 * A 0-indexed integer array arr of length n is considered ideal if the following conditions hold:
 *
 * Every arr[i] is a value from 1 to maxValue, for 0 <= i < n.
 * Every arr[i] is divisible by arr[i - 1], for 0 < i < n.
 * Return the number of distinct ideal arrays of length n. Since the answer may be very large, return it modulo 109 + 7.
 */

public class Solution {

    // TLE
    public int idealArrays(int n, int maxValue) {
        final int MOD = 1_000_000_007;
        int[] curr = new int[maxValue + 1];
        for (int i = 1; i <= maxValue; i++) curr[i] = 1;

        for (int i = 1; i < n; i++) {
            for (int j = maxValue; j >= 1; j--) {
                for (int k = 2; j * k <= maxValue; k++) {
                    curr[j * k] = (curr[j * k] + curr[j]) % MOD;
                }
            }
        }

        int ans = 0;
        for (int i = 0; i <= maxValue; i++) {
            ans = (ans + curr[i]) % MOD;
        }

        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().idealArrays(5, 9) == 111;

        assert new Solution().idealArrays(2, 5) == 10;
        assert new Solution().idealArrays(5, 3) == 11;
    }

}
