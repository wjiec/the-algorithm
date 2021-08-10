package daily.d210810parithmeticslices;

/**
 * 413. Arithmetic Slices
 *
 * https://leetcode-cn.com/problems/arithmetic-slices/
 *
 * An integer array is called arithmetic if it consists of at least three elements and
 * if the difference between any two consecutive elements is the same.
 *
 * For example, [1,3,5,7,9], [7,7,7,7], and [3,-1,-5,-9] are arithmetic sequences.
 * Given an integer array nums, return the number of arithmetic subarrays of nums.
 *
 * A subarray is a contiguous subsequence of the array.
 */

public class Solution {

    public int numberOfArithmeticSlices(int[] nums) {
        if (nums.length == 1) return 0;

        int d = nums[0] - nums[1], t = 0, ans = 0;
        for (int i = 2, l = nums.length; i < l; i++) {
            if (nums[i - 1] - nums[i] == d) ++t;
            else {
                d = nums[i - 1] - nums[i];
                t = 0;
            }

            ans += t;
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().numberOfArithmeticSlices(new int[]{1,2,3,4}) == 3;
        assert new Solution().numberOfArithmeticSlices(new int[]{1}) == 0;
    }

}
