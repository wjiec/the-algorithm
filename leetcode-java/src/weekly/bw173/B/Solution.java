package weekly.bw173.B;

import java.util.HashMap;
import java.util.Map;

/**
 * Q2. Minimum Subarray Length With Distinct Sum At Least K
 *
 * https://leetcode.cn/contest/biweekly-contest-173/problems/minimum-subarray-length-with-distinct-sum-at-least-k/
 *
 * You are given an integer array nums and an integer k.
 *
 * Return the minimum length of a subarray whose sum of the distinct values present
 * in that subarray (each value counted once) is at least k.
 *
 * If no such subarray exists, return -1.
 */

public class Solution {

    public int minLength(int[] nums, int k) {
        int ans = nums.length + 1;
        Map<Integer, Integer> m = new HashMap<>();
        for (int l = 0, r = 0, s = 0; r < nums.length; r++) {
            if (m.merge(nums[r], 1, Integer::sum) == 1) s += nums[r];
            while ((m.get(nums[l]) > 1 && s >= k) || s - nums[l] >= k) {
                Integer newCount = m.merge(nums[l], -1, (a, b) -> a + b == 0 ? null : a + b);
                if (newCount == null) s -= nums[l];
                l++;
            }
            if (s >= k) ans = Math.min(ans, r - l + 1);
        }
        return ans > nums.length ? -1 : ans;
    }

    public static void main(String[] args) {
        assert new Solution().minLength(new int[]{1,12}, 7) == 1;
    }

}
