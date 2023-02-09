package weekly.w331.C;

import java.util.Arrays;

/**
 * 2560. House Robber IV
 *
 * https://leetcode.cn/problems/house-robber-iv/
 *
 * There are several consecutive houses along a street, each of which has some money inside.
 * There is also a robber, who wants to steal money from the homes, but he refuses to steal
 * from adjacent homes.
 *
 * The capability of the robber is the maximum amount of money he steals from
 * one house of all the houses he robbed.
 *
 * You are given an integer array nums representing how much money is stashed in each house.
 * More formally, the ith house from the left has nums[i] dollars.
 *
 * You are also given an integer k, representing the minimum number of houses the robber
 * will steal from. It is always possible to steal at least k houses.
 *
 * Return the minimum capability of the robber out of all the possible ways to steal
 * at least k houses.
 */

public class Solution {

    public int minCapability(int[] nums, int k) {
        int[] dp1 = new int[nums.length + 1];
        int[] dp2 = new int[nums.length + 1];

        int[] sorted = new int[nums.length];
        System.arraycopy(nums, 0, sorted, 0, nums.length);
        Arrays.sort(sorted);

        int l = 0, r = sorted.length, ans = 0;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (check(nums, dp1, dp2, k, sorted[mid])) {
                ans = sorted[mid]; r = mid;
            } else l = mid + 1;
        }
        return ans;
    }

    private boolean check(int[] nums, int[] dp1, int[] dp2, int k, int max) {
        int n = nums.length, cap = 0;
        for (int i = 1; i <= n; i++) {
            if (nums[i - 1] <= max) {
                dp1[i] = dp2[i - 1] + 1; // 偷当前的
            } else dp1[i] = 0;

            dp2[i] = Math.max(dp1[i - 1], dp2[i - 1]); // 不偷当前的
            cap = Math.max(cap, Math.max(dp1[i], dp2[i]));
        }
        return cap >= k;
    }

    public static void main(String[] args) {
        assert new Solution().minCapability(new int[]{2,3,5,9}, 2) == 5;
        assert new Solution().minCapability(new int[]{2,7,9,3,1}, 2) == 2;
    }

}
