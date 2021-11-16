package problem.p1827minimumoperationstomakethearrayincreasing;

/**
 * 1827. Minimum Operations to Make the Array Increasing
 *
 * https://leetcode-cn.com/problems/minimum-operations-to-make-the-array-increasing/
 *
 * You are given an integer array nums (0-indexed). In one operation,
 * you can choose an element of the array and increment it by 1.
 *
 * For example, if nums = [1,2,3], you can choose to increment nums[1] to make nums = [1,3,3].
 *
 * Return the minimum number of operations needed to make nums strictly increasing.
 *
 * An array nums is strictly increasing if nums[i] < nums[i+1] for all 0 <= i < nums.length - 1.
 *
 * An array of length 1 is trivially strictly increasing.
 */

public class Solution {

    public int minOperations(int[] nums) {
        int ans = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] <= nums[i - 1]) {
                ans += nums[i - 1] - nums[i] + 1;
                nums[i] = nums[i - 1] + 1;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().minOperations(new int[]{1,1,1}) == 3;
        assert new Solution().minOperations(new int[]{1,5,2,4,1}) == 14;
        assert new Solution().minOperations(new int[]{8}) == 0;
    }

}
