package problem.p1413minimumvaluetogetpositivestepbystepsum;

/**
 * 1413. Minimum Value to Get Positive Step by Step Sum
 *
 * https://leetcode-cn.com/problems/minimum-value-to-get-positive-step-by-step-sum/
 *
 * Given an array of integers nums, you start with an initial positive value startValue.
 *
 * In each iteration, you calculate the step by step sum of startValue plus elements in nums (from left to right).
 *
 * Return the minimum positive value of startValue such that the step by step sum is never less than 1.
 */

public class Solution {

    public int minStartValue(int[] nums) {
        int sum = 0, ans = 0;
        for (var n : nums) {
            sum += n;
            if (sum < 1) {
                int diff = 1 - sum;
                sum += diff;
                ans += diff;
            }
        }
        return ans == 0 ? 1 : ans;
    }

    public static void main(String[] args) {
        assert new Solution().minStartValue(new int[]{-3,2,-3,4,2}) == 5;
        assert new Solution().minStartValue(new int[]{1,2}) == 1;
        assert new Solution().minStartValue(new int[]{1,-2,-3}) == 5;
    }

}
