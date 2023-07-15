package problem.p1537getthemaximumscore;

/**
 * 1537. Get the Maximum Score
 *
 * https://leetcode.cn/problems/get-the-maximum-score/
 *
 * You are given two sorted arrays of distinct integers nums1 and nums2.
 *
 * A valid path is defined as follows:
 *
 * Choose array nums1 or nums2 to traverse (from index-0).
 *
 * Traverse the current array from left to right.
 *
 * If you are reading any value that is present in nums1 and nums2 you are allowed to change
 * your path to the other array. (Only one repeated value is considered in the valid path).
 *
 * The score is defined as the sum of uniques values in a valid path.
 *
 * Return the maximum score you can obtain of all possible valid paths. Since the answer
 * may be too large, return it modulo 109 + 7.
 */

@SuppressWarnings("DuplicatedCode")
public class Solution {

    public int maxSum(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;
        long best1 = 0, best2 = 0;
        for (int i = 0, j = 0; i < m || j < n; ) {
            if (i < m && j < n) {
                if (nums1[i] < nums2[j]) best1 += nums1[i++];
                else if (nums1[i] > nums2[j]) best2 += nums2[j++];
                else {
                    long best = Math.max(best1, best2) + nums1[i];
                    best1 = best2 = best; ++i; ++j;
                }
            }
            else if (i < m) best1 += nums1[i++];
            else best2 += nums2[j++];
        }
        return (int) (Math.max(best1, best2) % 1_000_000_007);
    }

    public static void main(String[] args) {
        assert new Solution().maxSum(new int[]{2,4,5,8,10}, new int[]{4,6,8,9}) == 30;
        assert new Solution().maxSum(new int[]{1,3,5,7,9}, new int[]{3,5,100}) == 109;
        assert new Solution().maxSum(new int[]{1,2,3,4,5}, new int[]{6,7,8,9,10}) == 40;
        assert new Solution().maxSum(new int[]{1,4,5,8,9,11,19}, new int[]{2,3,4,11,12}) == 61;
    }

}
