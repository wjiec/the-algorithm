package problem.p2057smallestindexwithequalvalue;

/**
 * 2057. Smallest Index With Equal Value
 *
 * https://leetcode-cn.com/problems/smallest-index-with-equal-value/
 *
 * Given a 0-indexed integer array nums, return the smallest index i of nums such that i mod 10 == nums[i],
 * or -1 if such index does not exist.
 *
 * x mod y denotes the remainder when x is divided by y.
 */

public class Solution {

    public int smallestEqual(int[] nums) {
        for (int i = 0, l = nums.length; i < l; i++) {
            if (i % 10 < l && i % 10 == nums[i]) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        assert new Solution().smallestEqual(new int[]{0,1,2}) == 0;
        assert new Solution().smallestEqual(new int[]{4,3,2,1}) == 2;
        assert new Solution().smallestEqual(new int[]{1,2,3,4,5,6,7,8,9,0}) == -1;
        assert new Solution().smallestEqual(new int[]{2,1,3,5,2}) == 1;
    }

}
