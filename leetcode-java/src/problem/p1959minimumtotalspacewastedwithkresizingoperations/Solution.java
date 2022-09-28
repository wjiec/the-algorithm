package problem.p1959minimumtotalspacewastedwithkresizingoperations;

import common.TODO;
import common.Tag;

import java.util.Arrays;

/**
 * 1959. Minimum Total Space Wasted With K Resizing Operations
 *
 * https://leetcode.cn/problems/minimum-total-space-wasted-with-k-resizing-operations/
 *
 * You are currently designing a dynamic array. You are given a 0-indexed integer array nums, where
 * nums[i] is the number of elements that will be in the array at time i.
 * In addition, you are given an integer k, the maximum number of times you can resize the array (to any size).
 *
 * The size of the array at time t, sizet, must be at least nums[t] because there needs to be enough
 * space in the array to hold all the elements. The space wasted at time t is defined as sizet - nums[t], and
 * the total space wasted is the sum of the space wasted across every time t where 0 <= t < nums.length.
 *
 * Return the minimum total space wasted if you can resize the array at most k times.
 *
 * Note: The array can have any size at the start and does not count towards the number of resizing operations.
 */

public class Solution {

    @TODO
    @Tag({"把数组分为N段", "最小化权重值"})
    public int minSpaceWastedKResizing(int[] nums, int k) {
        int n = nums.length;
        int[][] w = new int[n][n];
        for (int i = 0; i < n; i++) {
            int max = 0, sum = 0;
            for (int j = i; j < n; j++) {
                sum += nums[j];
                max = Math.max(max, nums[j]);
                w[i][j] = max * (j - i + 1) - sum;
            }
        }

        int[][] dp = new int[n][k + 2];
        for (var x : dp) Arrays.fill(x, Integer.MAX_VALUE / 2);
        for (int i = 0; i < n; i++) {
            for (int j = 1; j <= k + 1; j++) {
                for (int x = 0; x <= i; x++) {
                    dp[i][j] = Math.min(dp[i][j], x == 0 ? w[x][i] : (dp[x - 1][j - 1] + w[x][i]));
                }
            }
        }
        return dp[n - 1][k + 1];
    }

    public static void main(String[] args) {
        assert new Solution().minSpaceWastedKResizing(new int[]{10,20}, 0) == 10;
        assert new Solution().minSpaceWastedKResizing(new int[]{10,20,30}, 1) == 10;
        assert new Solution().minSpaceWastedKResizing(new int[]{10,20,15,30,20}, 2) == 15;
    }

}
