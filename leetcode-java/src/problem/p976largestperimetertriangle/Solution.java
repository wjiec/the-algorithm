package problem.p976largestperimetertriangle;

import java.util.Arrays;

/**
 * 976. Largest Perimeter Triangle
 *
 * https://leetcode-cn.com/problems/largest-perimeter-triangle/
 *
 * Given an integer array nums, return the largest perimeter of a triangle with a non-zero area,
 * formed from three of these lengths.
 *
 * If it is impossible to form any triangle of a non-zero area, return 0.
 */

public class Solution {

    public int largestPerimeter(int[] nums) {
        Arrays.sort(nums);
        for (int i = nums.length - 1; i >= 2; i--) {
            if (nums[i - 2] + nums[i - 1] > nums[i]) {
                return nums[i] + nums[i - 1] + nums[i - 2];
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        assert new Solution().largestPerimeter(new int[]{2,1,2}) == 5;
        assert new Solution().largestPerimeter(new int[]{1,2,1}) == 0;
        assert new Solution().largestPerimeter(new int[]{3,2,3,4}) == 10;
        assert new Solution().largestPerimeter(new int[]{3,6,2,3}) == 8;
    }

}
