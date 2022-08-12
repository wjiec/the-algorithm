package problem.p1477findtwononoverlappingsubarrayseachwithtargetsum;

import java.util.Arrays;

/**
 * 1477. Find Two Non-overlapping Sub-arrays Each With Target Sum
 *
 * https://leetcode.cn/problems/find-two-non-overlapping-sub-arrays-each-with-target-sum/
 *
 * You are given an array of integers arr and an integer target.
 *
 * You have to find two non-overlapping sub-arrays of arr each with a sum equal target.
 * There can be multiple answers so you have to find an answer where the sum of
 * the lengths of the two sub-arrays is minimum.
 *
 * Return the minimum sum of the lengths of the two required sub-arrays, or
 * return -1 if you cannot find such two sub-arrays.
 */

public class Solution {

    public int minSumOfLengths(int[] arr, int target) {
        int INF = 1_000_000_000;

        int[] dp = new int[arr.length + 1];
        Arrays.fill(dp, INF);

        int ans = INF, sum = 0;
        for (int l = 0, r = 1; r < dp.length; r++) {
            sum += arr[r - 1];
            while (sum > target) sum -= arr[l++];
            if (sum == target) {
                dp[r] = Math.min(dp[r - 1], r - l);
                ans = Math.min(ans, dp[l] + r - l);
            } else dp[r] = dp[r - 1];
        }
        return ans == INF ? -1 : ans;
    }

    public static void main(String[] args) {
        assert new Solution().minSumOfLengths(new int[]{1,6,1}, 7) == -1;
        assert new Solution().minSumOfLengths(new int[]{2,1,3,3,2,3,1}, 6) == 5;

        assert new Solution().minSumOfLengths(new int[]{3,2,2,4,3}, 3) == 2;
        assert new Solution().minSumOfLengths(new int[]{7,3,4,7}, 7) == 2;
        assert new Solution().minSumOfLengths(new int[]{4,3,2,6,2,3,4}, 6) == -1;
        assert new Solution().minSumOfLengths(new int[]{5,5,4,4,5}, 3) == -1;
        assert new Solution().minSumOfLengths(new int[]{3,1,1,1,5,1,2,1}, 3) == 3;
    }

}
