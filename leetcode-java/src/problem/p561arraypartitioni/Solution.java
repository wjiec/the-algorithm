package problem.p561arraypartitioni;

import java.util.Arrays;

/**
 * 561. Array Partition I
 *
 * https://leetcode-cn.com/problems/array-partition-i/
 *
 * Given an integer array nums of 2n integers, group these integers into
 * n pairs (a1, b1), (a2, b2), ..., (an, bn) such that the sum of min(ai, bi)
 * for all i is maximized. Return the maximized sum.
 */

public class Solution {

    public int arrayPairSum(int[] nums) {
        Arrays.sort(nums);
        int sz = nums.length, sum = 0;
        for (int i = 0; i < sz; i += 2) {
            sum += nums[i];
        }
        return sum;
    }

    public static void main(String[] args) {
        assert new Solution().arrayPairSum(new int[]{1,4,3,2}) == 4;
        assert new Solution().arrayPairSum(new int[]{6,2,6,5,1,2}) == 9;
    }

}
