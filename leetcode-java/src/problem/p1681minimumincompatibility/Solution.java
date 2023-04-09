package problem.p1681minimumincompatibility;

import java.util.Arrays;

/**
 * 1681. Minimum Incompatibility
 *
 * https://leetcode.cn/problems/minimum-incompatibility/
 *
 * You are given an integer array nums and an integer k. You are asked to distribute this array
 * into k subsets of equal size such that there are no two equal elements in the same subset.
 *
 * A subset's incompatibility is the difference between the maximum and minimum elements in that array.
 *
 * Return the minimum possible sum of incompatibilities of the k subsets after distributing
 * the array optimally, or return -1 if it is not possible.
 *
 * A subset is a group integers that appear in the array with no particular order.
 */

public class Solution {

    public int minimumIncompatibility(int[] nums, int k) {
        int n = nums.length, gc = n / k;
        int[] values = new int[1 << nums.length];
        Arrays.fill(values, -1);
        for (int i = 0; i < values.length; i++) {
            if (Integer.bitCount(i) == gc) {
                int hash = 0, mx = 0, mi = 20;
                boolean ok = true;
                for (int j = 0; j < n; j++) {
                    if ((i & (1 << j)) != 0) {
                        if ((hash & (1 << nums[j])) != 0) {
                            ok = false; break;
                        }

                        hash |= 1 << nums[j];
                        mx = Math.max(mx, nums[j]);
                        mi = Math.min(mi, nums[j]);
                    }
                }
                if (ok) values[i] = mx - mi;
            }
        }

        int[] dp = new int[1 << nums.length];
        Arrays.fill(dp, -1); dp[0] = 0;
        for (int mask = 1; mask < dp.length; mask++) {
            if (Integer.bitCount(mask) % gc == 0) {
                for (var sub = mask; sub != 0; sub = (sub - 1) & mask) {
                    if (values[sub] != -1 && dp[mask ^ sub] != -1) {
                        int next = dp[mask ^ sub] + values[sub];
                        if (dp[mask] == -1) dp[mask] = next;
                        else dp[mask] = Math.min(dp[mask], next);
                    }
                }
            }
        }
        return dp[dp.length - 1];
    }

    public static void main(String[] args) {
        assert new Solution().minimumIncompatibility(new int[]{7,3,16,15,1,13,1,2,14,5,3,10,6,2,7,15}, 8) == 12;

        assert new Solution().minimumIncompatibility(new int[]{1,2,1,4}, 2) == 4;
        assert new Solution().minimumIncompatibility(new int[]{6,3,8,1,3,1,2,2}, 4) == 6;
    }

}
