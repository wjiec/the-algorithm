package problem.p795numberofsubarrayswithboundedmaximum;

import common.TODO;

/**
 * 795. Number of Subarrays with Bounded Maximum
 *
 * https://leetcode.cn/problems/number-of-subarrays-with-bounded-maximum/
 *
 * Given an integer array nums and two integers left and right, return the number of contiguous
 * non-empty subarrays such that the value of the maximum array element in that subarray
 * is in the range [left, right].
 *
 * The test cases are generated so that the answer will fit in a 32-bit integer.
 */

public class Solution {

    @TODO
    public int numSubarrayBoundedMax(int[] nums, int left, int right) {
        return count(nums, right) - count(nums, left - 1);
    }

    private int count(int[] nums, int bound) {
        int ans = 0, curr = 0;
        for (int v : nums) {
            curr = v <= bound ? curr + 1 : 0;
            ans += curr;
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().numSubarrayBoundedMax(new int[]{2,1,4,3}, 2, 3) == 3;
        // 2 1    2 5 6 -> 6
        assert new Solution().numSubarrayBoundedMax(new int[]{2,9,2,5,6}, 2, 8) == 7;
    }

}
