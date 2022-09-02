package problem.p1749maximumabsolutesumofanysubarray;

/**
 * 1749. Maximum Absolute Sum of Any Subarray
 *
 * https://leetcode.cn/problems/maximum-absolute-sum-of-any-subarray/
 *
 * You are given an integer array nums. The absolute sum of a
 * subarray [numsl, numsl+1, ..., numsr-1, numsr] is
 * abs(numsl + numsl+1 + ... + numsr-1 + numsr).
 *
 * Return the maximum absolute sum of any (possibly empty) subarray of nums.
 *
 * Note that abs(x) is defined as follows:
 *
 * If x is a negative integer, then abs(x) = -x.
 * If x is a non-negative integer, then abs(x) = x.
 */

public class Solution {

    public int maxAbsoluteSum(int[] nums) {
        int ans = 0, positive = 0, negative = 0;
        for (int val : nums) {
            positive = Math.max(positive + val, val);
            negative = Math.max(negative - val, -val);
            ans = Math.max(ans, Math.max(positive, negative));
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().maxAbsoluteSum(new int[]{1,-3,2,3,-4}) == 5;
        assert new Solution().maxAbsoluteSum(new int[]{2,-5,1,-4,3,-2}) == 8;
    }

}
