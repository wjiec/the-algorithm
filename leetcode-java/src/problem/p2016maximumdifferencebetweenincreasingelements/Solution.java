package problem.p2016maximumdifferencebetweenincreasingelements;

/**
 * 2016. Maximum Difference Between Increasing Elements
 *
 * https://leetcode-cn.com/problems/maximum-difference-between-increasing-elements/
 *
 * Given a 0-indexed integer array nums of size n, find the maximum difference between nums[i] and nums[j]
 * (i.e., nums[j] - nums[i]), such that 0 <= i < j < n and nums[i] < nums[j].
 *
 * Return the maximum difference. If no such i and j exists, return -1.
 */

public class Solution {

    public int maximumDifference(int[] nums) {
        int ans = 0, l = nums.length;
        for (int i = 0; i < l; i++) {
            for (int j = i + 1; j < l; j++) {
                ans = Math.max(ans, nums[j] - nums[i]);
            }
        }
        return ans == 0 ? -1 : ans;
    }

    public static void main(String[] args) {
        assert new Solution().maximumDifference(new int[]{7,1,5,4}) == 4;
        assert new Solution().maximumDifference(new int[]{9,4,3,2}) == -1;
        assert new Solution().maximumDifference(new int[]{1,5,2,10}) == 9;
    }

}
