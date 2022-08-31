package problem.p1696jumpgamevi;

import java.util.Arrays;

/**
 * 1696. Jump Game VI
 *
 * https://leetcode.cn/problems/jump-game-vi/
 *
 * You are given a 0-indexed integer array nums and an integer k.
 *
 * You are initially standing at index 0. In one move, you can jump at most k steps forward
 * without going outside the boundaries of the array. That is, you can jump from index i to
 * any index in the range [i + 1, min(n - 1, i + k)] inclusive.
 *
 * You want to reach the last index of the array (index n - 1). Your score is the sum of
 * all nums[j] for each index j you visited in the array.
 *
 * Return the maximum score you can get.
 */

public class Solution {

    public int maxResult(int[] nums, int k) {
        int n = nums.length;
        long[] dp = new long[n];
        Arrays.fill(dp, Integer.MIN_VALUE); dp[0] = nums[0];

        for (int i = 0; i < n; i++) {
            for (int j = i + 1, x = 0; x < k && j < n; x++, j++) {
                dp[j] = Math.max(dp[j], dp[i] + nums[j]);
                // 当dp[j] >= dp[i] 时, 后续的所有下标j+n的位置都不会选择i
                // 所以这里直接退出就行
                if (dp[j] >= dp[i]) break;
            }
        }
        return (int) dp[n - 1];
    }

    public static void main(String[] args) {
        assert new Solution().maxResult(new int[]{1,-1,-2,4,-7,3}, 2) == 7;
        assert new Solution().maxResult(new int[]{10,-5,-2,4,0,3}, 3) == 17;
        assert new Solution().maxResult(new int[]{1,-5,-20,4,-1,3,-6,-3}, 2) == 0;
    }

}
