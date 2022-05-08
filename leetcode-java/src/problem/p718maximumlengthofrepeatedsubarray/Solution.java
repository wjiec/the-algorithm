package problem.p718maximumlengthofrepeatedsubarray;

import common.TODO;

/**
 * 718. Maximum Length of Repeated Subarray
 *
 * https://leetcode-cn.com/problems/maximum-length-of-repeated-subarray/
 *
 * Given two integer arrays nums1 and nums2, return the maximum length of a subarray that appears in both arrays.
 */

public class Solution {

    @TODO(url = "https://leetcode-cn.com/problems/maximum-length-of-repeated-subarray/solution/zui-chang-zhong-fu-zi-shu-zu-by-leetcode-solution/")
    public int findLength(int[] nums1, int[] nums2) {
        int ans = 0, m = nums1.length, n = nums2.length;
        int[][] dp = new int[m + 1][n + 1];
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                dp[i][j] = nums1[i] == nums2[j] ? (dp[i + 1][j + 1] + 1) : 0;
                if (dp[i][j] > ans) ans = dp[i][j];
            }
        }
        return ans;
    }

    private static class SlideWindow {
        public int findLength(int[] nums1, int[] nums2) {
            int ans = 0;
            for (int i = 0; i < nums1.length - ans; i++) {
                ans = Math.max(ans, maxLength(nums1, nums2, i, 0));
            }
            for (int i = 0; i < nums2.length - ans; i++) {
                ans = Math.max(ans, maxLength(nums1, nums2, 0, i));
            }
            return ans;
        }

        public int maxLength(int[] nums1, int[] nums2, int a, int b) {
            int ans = 0, curr = 0;
            for (; a < nums1.length && b < nums2.length; a++, b++) {
                if (nums1[a] == nums2[b]) curr++;
                else curr = 0;
                if (curr > ans) ans = curr;
            }
            return ans;
        }
    }

    public static void main(String[] args) {
        assert new Solution().findLength(new int[]{1,2,3,2,1}, new int[]{3,2,1,4,7}) == 3;
        assert new Solution().findLength(new int[]{0,0,0,0,0}, new int[]{0,0,0,0,0}) == 5;

        assert new SlideWindow().findLength(new int[]{1,2,3,2,1}, new int[]{3,2,1,4,7}) == 3;
        assert new SlideWindow().findLength(new int[]{0,0,0,0,0}, new int[]{0,0,0,0,0}) == 5;
        assert new SlideWindow().findLength(new int[]{0,0,0,0,1}, new int[]{1,0,0,0,0}) == 4;
    }

}
