package problem.p1906minimumabsolutedifferencequeries;

import common.Checker;

/**
 * 1906. Minimum Absolute Difference Queries
 *
 * https://leetcode.cn/problems/minimum-absolute-difference-queries/
 *
 * The minimum absolute difference of an array a is defined as the minimum
 * value of |a[i] - a[j]|, where 0 <= i < j < a.length and a[i] != a[j].
 * If all elements of a are the same, the minimum absolute difference is -1.
 *
 * For example, the minimum absolute difference of the array [5,2,3,7,2] is |2 - 3| = 1.
 * Note that it is not 0 because a[i] and a[j] must be different.
 * You are given an integer array nums and the array queries where queries[i] = [li, ri]. For
 * each query i, compute the minimum absolute difference of the subarray nums[li...ri] containing
 * the elements of nums between the 0-based indices li and ri (inclusive).
 *
 * Return an array ans where ans[i] is the answer to the ith query.
 *
 * A subarray is a contiguous sequence of elements in an array.
 *
 * The value of |x| is defined as:
 *
 * x if x >= 0.
 * -x if x < 0.
 */

public class Solution {

    public int[] minDifference(int[] nums, int[][] queries) {
        int[][] sum = new int[nums.length + 1][101];
        for (int i = 1; i <= nums.length; i++) {
            System.arraycopy(sum[i - 1], 0, sum[i], 0, 101);
            sum[i][nums[i - 1]]++;
        }

        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int l = queries[i][0], r = queries[i][1];
            int last = 0, curr = Integer.MAX_VALUE;
            for (int j = 1; j < 101; j++) {
                if (sum[l][j] != sum[r + 1][j]) {
                    if (last != 0) curr = Math.min(curr, j - last);
                    last = j;
                }
            }

            ans[i] = curr == Integer.MAX_VALUE ? -1 : curr;
        }
        return ans;
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().minDifference(new int[]{1,3,4,8}, new int[][]{{0,1},{1,2},{2,3},{0,3}}), new int[]{2,1,4,1});
        assert Checker.check(new Solution().minDifference(new int[]{4,5,2,2,7,10}, new int[][]{{2,3},{0,2},{0,5},{3,5}}), new int[]{-1,1,1,3});
    }

}
