package problem.p1785minimumelementstoaddtoformagivensum;

/**
 * 1785. Minimum Elements to Add to Form a Given Sum
 *
 * https://leetcode.cn/problems/minimum-elements-to-add-to-form-a-given-sum/
 *
 * You are given an integer array nums and two integers limit and goal.
 * The array nums has an interesting property that abs(nums[i]) <= limit.
 *
 * Return the minimum number of elements you need to add to make the sum of the
 * array equal to goal. The array must maintain its property that abs(nums[i]) <= limit.
 *
 * Note that abs(x) equals x if x >= 0, and -x otherwise.
 */

public class Solution {

    public int minElements(int[] nums, int limit, int goal) {
        long g = goal;
        for (var n : nums) g -= n;
        return (int) ((Math.abs(g) + limit - 1) / limit);
    }

    public static void main(String[] args) {
        assert new Solution().minElements(new int[]{1,-1,1}, 3, -4) == 2;
        assert new Solution().minElements(new int[]{1,-10,9,1}, 100, 0) == 1;
    }

}
