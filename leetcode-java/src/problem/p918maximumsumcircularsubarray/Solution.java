package problem.p918maximumsumcircularsubarray;

import common.TODO;

/**
 * 918. Maximum Sum Circular Subarray
 *
 * https://leetcode.cn/problems/maximum-sum-circular-subarray/
 *
 * Given a circular integer array nums of length n,
 * return the maximum possible sum of a non-empty subarray of nums.
 *
 * A circular array means the end of the array connects to the beginning of the array.
 * Formally, the next element of nums[i] is nums[(i + 1) % n] and
 * the previous element of nums[i] is nums[(i - 1 + n) % n].
 *
 * A subarray may only include each element of the fixed buffer nums at most once.
 * Formally, for a subarray nums[i], nums[i + 1], ..., nums[j],
 * there does not exist i <= k1, k2 <= j with k1 % n == k2 % n.
 */

public class Solution {

    @TODO
    public int maxSubarraySumCircular(int[] nums) {
        int ans = nums[0], curr = nums[0];
        for (int i = 1; i < nums.length; i++) {
            curr = nums[i] + Math.max(curr, 0);
            if (curr > ans) ans = curr;
        }

        int[] rightSum = new int[nums.length];
        rightSum[nums.length - 1] = nums[nums.length - 1];
        for (int i = nums.length - 2; i >= 0; i--) {
            rightSum[i] = rightSum[i + 1] + nums[i];
        }

        int[] maxRights = new int[nums.length];
        maxRights[nums.length - 1] = nums[nums.length - 1];
        for (int i = nums.length - 2; i >= 0; i--) {
            maxRights[i] = Math.max(maxRights[i + 1], rightSum[i]);
        }

        int leftSum = 0;
        for (int i = 0, n = nums.length - 2; i < n; i++) {
            leftSum += nums[i];
            ans = Math.max(ans, leftSum + maxRights[i + 2]);
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().maxSubarraySumCircular(new int[]{1,-2,3,-2}) == 3;
        assert new Solution().maxSubarraySumCircular(new int[]{5,-3,5}) == 10;
        assert new Solution().maxSubarraySumCircular(new int[]{-3,-2,-3}) == -2;
    }

}
