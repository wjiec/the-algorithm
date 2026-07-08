package weekly.bw179.D;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Q4. Count Non Decreasing Arrays With Given Digit Sums
 *
 * https://leetcode.cn/contest/biweekly-contest-179/problems/count-non-decreasing-arrays-with-given-digit-sums/
 *
 * You are given an integer array digitSum of length n.
 *
 * An array arr of length n is considered valid if:
 *
 * 0 <= arr[i] <= 5000
 * it is non-decreasing.
 * the sum of the digits of arr[i] equals digitSum[i].
 *
 * Return an integer denoting the number of distinct valid arrays.
 * Since the answer may be large, return it modulo 1e9 + 7.
 *
 * An array is said to be non-decreasing if each element is greater than or
 * equal to the previous element, if it exists.
 */

public class Solution {

    private static final int MAX_N = 5001;
    private static final int MOD = 1_000_000_007;
    private static final Set<Integer>[] m = new Set[51];
    static {
        Arrays.setAll(m, i -> new HashSet<>());
        for (int i = 0; i < MAX_N; i++) {
            int sum = 0;
            for (var v = i; v != 0; v /= 10) sum += v % 10;
            m[sum].add(i);
        }
    }

    // 找到满足非递减数组的 arr[i] 的数位和等于 digitSum[i] 的数组数量
    public int countArrays(int[] digitSum) {
        // 如果第一位可以选择 a, b, c, ..., 那么第二位可以怎么选?
        //  - 第二位的数字要求不比前一位的数字小
        // 那么枚举每一位选择 v 的可能性
        int[][] dp = new int[2][MAX_N];
        for (var v : m[digitSum[0]]) dp[0][v] = 1;

        // 从第二位开始, 枚举如果这一位选择使用 v, 那么可以使用多少种方案
        for (int i = 1; i < digitSum.length; i++) {
            int curr = i & 1, prev = curr ^ 1;
            for (int j = 1; j < MAX_N; j++) dp[prev][j] = (dp[prev][j] + dp[prev][j - 1]) % MOD;
            if (dp[prev][MAX_N - 1] == 0) return 0; // 如果没有答案就可以直接跳过了

            // 如果这一位选择使用 v, 那么前一位只能比 v 小, 也就是 dp[v] = sum(dp[0...v])
            Arrays.fill(dp[curr], 0);
            for (var v : m[digitSum[i]]) dp[curr][v] = dp[prev][v];
        }

        int ans = 0;
        for (var v : dp[(digitSum.length & 1) ^ 1]) ans = (ans + v) % MOD;
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().countArrays(new int[]{0, 11}) == 268;
    }

}
