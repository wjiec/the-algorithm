package problem.p1814countnicepairsinanarray;

import java.util.Arrays;

/**
 * 1814. Count Nice Pairs in an Array
 *
 * https://leetcode.cn/problems/count-nice-pairs-in-an-array/
 *
 * You are given an array nums that consists of non-negative integers. Let us define rev(x) as the
 * reverse of the non-negative integer x. For example, rev(123) = 321, and rev(120) = 21.
 * A pair of indices (i, j) is nice if it satisfies all of the following conditions:
 *
 * 0 <= i < j < nums.length
 * nums[i] + rev(nums[j]) == nums[j] + rev(nums[i])
 * Return the number of nice pairs of indices. Since that number can be too
 * large, return it modulo 109 + 7.
 */

public class Solution {

    public int countNicePairs(int[] nums) {
        long ans = 0, n = nums.length, MOD = 1_000_000_007;
        // nums[i] + rev(nums[j]) == nums[j] + rev(nums[i])
        // nums[i] - rev(nums[i]) == nums[j] - rev(nums[j])
        for (int i = 0; i < n; i++) nums[i] -= reverse(nums[i]);

        Arrays.sort(nums);
        for (int i = 0; i < n; ) {
            int j = i + 1; long c = 1;
            while (j < n && nums[j] == nums[i]) { j++; c++; }
            ans = (ans + c * (c - 1) / 2) % MOD;
            i = j;
        }
        return (int) ans;
    }

    private int reverse(int n) {
        int ans = 0;
        for (; n != 0; n /= 10) ans = ans * 10 + n % 10;
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().countNicePairs(new int[]{42,11,1,97}) == 2;
        assert new Solution().countNicePairs(new int[]{13,10,35,24,76}) == 4;
    }

}
