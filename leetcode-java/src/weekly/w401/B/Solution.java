package weekly.w401.B;

import java.util.Arrays;

/**
 * 3179. Find the N-th Value After K Seconds
 *
 * https://leetcode.cn/contest/weekly-contest-401/problems/find-the-n-th-value-after-k-seconds/
 *
 * You are given two integers n and k.
 *
 * Initially, you start with an array a of n integers where a[i] = 1 for all 0 <= i <= n - 1.
 *
 * After each second, you simultaneously update each element to be the sum of all its preceding
 * elements plus the element itself. For example, after one second, a[0] remains the same, a[1]
 * becomes a[0] + a[1], a[2] becomes a[0] + a[1] + a[2], and so on.
 *
 * Return the value of a[n - 1] after k seconds.
 *
 * Since the answer may be very large, return it modulo 109 + 7.
 */

public class Solution {

    public int valueAfterKSeconds(int n, int k) {
        int[] ans = new int[n];
        Arrays.fill(ans, 1);

        int MOD = 1_000_000_007;
        for (int i = 0; i < k; i++) {
            for (int j = 1; j < n; j++) {
                ans[j] = (ans[j - 1] + ans[j]) % MOD;
            }
        }

        return ans[n - 1];
    }

    public static void main(String[] args) {
    }

}
