package problem.p908smallestrangei;

/**
 * 908. Smallest Range I
 *
 * https://leetcode-cn.com/problems/smallest-range-i/
 *
 * Given an array nums of integers, for each integer nums[i]
 * we may choose any x with -k <= x <= k, and add x to nums[i].
 *
 * After this process, we have some array result.
 *
 * Return the smallest possible difference between
 * the maximum value of result and the minimum value of result.
 */

public class Solution {

    public int smallestRangeI(int[] nums, int k) {
        if (nums.length == 1) return 0;
        int max = 0, min = 10001;
        for (int num : nums) {
            if (num > max) max = num;
            if (num < min) min = num;
        }
        return Math.max(max - min - 2 * k, 0);
    }

    public static void main(String[] args) {
        assert new Solution().smallestRangeI(new int[]{1}, 0) == 0;
        assert new Solution().smallestRangeI(new int[]{0, 10}, 2) == 6;
        assert new Solution().smallestRangeI(new int[]{1, 3, 6}, 3) == 0;
    }

}
