package problem.p396rotatefunction;

/**
 * 396. Rotate Function
 *
 * https://leetcode-cn.com/problems/rotate-function/
 *
 * You are given an integer array nums of length n.
 *
 * Assume arrk to be an array obtained by rotating nums by k positions clock-wise.
 * We define the rotation function F on nums as follow:
 *
 * F(k) = 0 * arrk[0] + 1 * arrk[1] + ... + (n - 1) * arrk[n - 1].
 * Return the maximum value of F(0), F(1), ..., F(n-1).
 *
 * The test cases are generated so that the answer fits in a 32-bit integer.
 */

public class Solution {

    public int maxRotateFunction(int[] nums) {
        int sum = 0, curr = 0, n = nums.length;
        for (int i = 0; i < n; i++) {
            sum += nums[i];
            curr += nums[i] * i;
        }

        int ans = curr;
        for (int i = 1; i < n; i++) {
            curr = curr + sum - nums[n - i] * n;
            if (curr > ans) ans = curr;
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().maxRotateFunction(new int[]{4,3,2,6}) == 26;
        assert new Solution().maxRotateFunction(new int[]{100}) == 0;
    }

}
