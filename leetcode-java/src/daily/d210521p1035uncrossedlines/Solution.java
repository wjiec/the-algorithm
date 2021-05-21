package daily.d210521p1035uncrossedlines;

/**
 * 1035. Uncrossed Lines
 *
 * https://leetcode-cn.com/problems/uncrossed-lines/
 *
 * We write the integers of nums1 and nums2 (in the order they are given) on two separate horizontal lines.
 *
 * Now, we may draw connecting lines: a straight line connecting two numbers nums1[i] and nums2[j] such that:
 *
 * nums1[i] == nums2[j];
 * The line we draw does not intersect any other connecting (non-horizontal) line.
 * Note that a connecting lines cannot intersect even at the endpoints: each number can only belong to one connecting line.
 *
 * Return the maximum number of connecting lines we can draw in this way.
 */

public class Solution {

    // @TODO dp
    public int maxUncrossedLines(int[] nums1, int[] nums2) {
        int sz1 = nums1.length, sz2 = nums2.length;
        int[][] dp = new int[sz1 + 1][sz2 + 1];
        for (int i = 1; i <= sz1; i++) {
            for (int j = 1; j <= sz2; j++) {
                if (nums1[i - 1] == nums2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[sz1][sz2];
    }

    public static void main(String[] args) {
        assert new Solution().maxUncrossedLines(new int[]{1,4,2}, new int[]{1,2,4}) == 2;
        assert new Solution().maxUncrossedLines(new int[]{2,5,1,2,5}, new int[]{10,5,2,1,5,2}) == 3;
        assert new Solution().maxUncrossedLines(new int[]{1,3,7,1,7,5}, new int[]{1,9,2,5,1}) == 2;
        assert new Solution().maxUncrossedLines(new int[]{1}, new int[]{3}) == 0;
        assert new Solution().maxUncrossedLines(new int[]{1}, new int[]{1,3}) == 1;
        assert new Solution().maxUncrossedLines(new int[]{2,1}, new int[]{1,2,1,3,3,2}) == 2;
    }

}
