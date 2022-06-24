package problem.p1043partitionarrayformaximumsum;

/**
 * 1043. Partition Array for Maximum Sum
 *
 * https://leetcode.cn/problems/partition-array-for-maximum-sum/
 *
 * Given an integer array arr, partition the array into (contiguous) subarrays of length at most k.
 * After partitioning, each subarray has their values changed to become the maximum value of that subarray.
 *
 * Return the largest sum of the given array after partitioning. Test cases are
 * generated so that the answer fits in a 32-bit integer.
 */

public class Solution {

    public int maxSumAfterPartitioning(int[] arr, int k) {
        int[][] dp = new int[arr.length][arr.length];
        for (int i = 0; i < arr.length; i++) {
            int max = 0, end = Math.min(i + k, arr.length);
            for (int j = i; j < end; j++) {
                if (arr[j] > max) max = arr[j];
                dp[i][j] = Math.max(dp[i][j], max * (j - i + 1));
            }
        }

        int[] ans = new int[arr.length];
        System.arraycopy(dp[0], 0, ans, 0, arr.length);
        for (int i = k; i < arr.length; i++) {
            for (int j = i - k; j < i; j++) {
                ans[i] = Math.max(ans[i], ans[j] + dp[j + 1][i]);
            }
        }
        return ans[arr.length - 1];
    }

    public static void main(String[] args) {
        assert new Solution().maxSumAfterPartitioning(new int[]{1,15,7,9,2,5,10}, 3) == 84;
        assert new Solution().maxSumAfterPartitioning(new int[]{1,4,1,5,7,3,6,1,9,9,3}, 4) == 83;
        assert new Solution().maxSumAfterPartitioning(new int[]{1}, 1) == 1;
    }

}
