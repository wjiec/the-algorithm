package problem.p962maximumwidthramp;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 962. Maximum Width Ramp
 *
 * https://leetcode.cn/problems/maximum-width-ramp/
 *
 * A ramp in an integer array nums is a pair (i, j) for which i < j
 * and nums[i] <= nums[j]. The width of such a ramp is j - i.
 *
 * Given an integer array nums, return the maximum width of a ramp in nums.
 * If there is no ramp in nums, return 0.
 */

public class Solution {

    public int maxWidthRamp(int[] nums) {
        int[][] sorted = new int[nums.length][1];
        for (int i = 0; i < nums.length; i++) sorted[i][0] = i;
        Arrays.sort(sorted, Comparator.comparingInt(v -> nums[v[0]]));

        int ans = 0, max = nums.length;
        for (int[] idx : sorted) {
            ans = Math.max(ans, idx[0] - max);
            max = Math.min(max, idx[0]);
        }

        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().maxWidthRamp(new int[]{6,0,8,2,1,5}) == 4;
        assert new Solution().maxWidthRamp(new int[]{9,8,1,0,1,9,4,0,4,1}) == 7;
    }

}
