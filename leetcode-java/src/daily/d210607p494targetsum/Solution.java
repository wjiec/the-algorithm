package daily.d210607p494targetsum;

/**
 * 494. Target Sum
 *
 * https://leetcode-cn.com/problems/target-sum/
 *
 * You are given an integer array nums and an integer target.
 *
 * You want to build an expression out of nums by adding one of the symbols '+' and '-'
 * before each integer in nums and then concatenate all the integers.
 *
 * For example, if nums = [2, 1], you can add a '+' before 2 and a '-'
 * before 1 and concatenate them to build the expression "+2-1".
 *
 * Return the number of different expressions that you can build, which evaluates to target.
 */

public class Solution {

    // @TODO
    public int findTargetSumWays(int[] nums, int target) {
        return match(nums, 0, nums.length, target);
    }

    private int match(int[] nums, int start, int n, int target) {
        if (start < n - 1) {
            return match(nums, start + 1, n, target - nums[start]) + match(nums, start + 1, n, target + nums[start]);
        }

        int ans = 0;
        ans += target == nums[start] ? 1 : 0;
        ans += target == -nums[start] ? 1 : 0;

        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().findTargetSumWays(new int[]{1000}, -1000) == 1;
        assert new Solution().findTargetSumWays(new int[]{1,1,1,1,1}, 3) == 5;
        assert new Solution().findTargetSumWays(new int[]{1}, 1) == 1;
    }

}
