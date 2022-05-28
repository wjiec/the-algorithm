package problem.p910smallestrangeii;

import java.util.Arrays;

/**
 * 910. Smallest Range II
 *
 * https://leetcode.cn/problems/smallest-range-ii/
 *
 * You are given an integer array nums and an integer k.
 *
 * For each index i where 0 <= i < nums.length, change nums[i] to be either nums[i] + k or nums[i] - k.
 *
 * The score of nums is the difference between the maximum and minimum elements in nums.
 *
 * Return the minimum score of nums after changing the values at each index.
 */

public class Solution {

    public int smallestRangeII(int[] nums, int k) {
        Arrays.sort(nums);

        int max = nums[nums.length - 1], min = nums[0], ans = max - min;
        for (int i = 0, x = nums.length - 1; i < x; i++) {
            int hi = Math.max(max - k, nums[i] + k);
            int lo = Math.min(min + k, nums[i + 1] - k);
            ans = Math.min(ans, hi - lo);
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().smallestRangeII(new int[]{1}, 0) == 1;
        assert new Solution().smallestRangeII(new int[]{0,10}, 2) == 6;
        assert new Solution().smallestRangeII(new int[]{1,3,6}, 3) == 3;
    }

}
