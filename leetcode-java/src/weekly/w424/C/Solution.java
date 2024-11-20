package weekly.w424.C;

/**
 * 3356. Zero Array Transformation II
 *
 * https://leetcode.cn/contest/weekly-contest-424/problems/zero-array-transformation-ii/
 *
 * You are given an integer array nums of length n and a 2D array queries where queries[i] = [li, ri, vali].
 *
 * Each queries[i] represents the following action on nums:
 *
 * Decrement the value at each index in the range [li, ri] in nums by at most vali.
 * The amount by which each value is decremented can be chosen independently for each index.
 * A Zero Array is an array with all its elements equal to 0.
 *
 * Return the minimum possible non-negative value of k, such that after processing
 * the first k queries in sequence, nums becomes a Zero Array. If no such k exists, return -1.
 */

public class Solution {

    public int minZeroArray(int[] nums, int[][] queries) {
        int l = 0, r = queries.length + 1, ans = -1;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (canZeroArray(nums, queries, mid)) {
                ans = mid; r = mid;
            } else l = mid + 1;
        }
        return ans;
    }

    private boolean canZeroArray(int[] nums, int[][] queries, int n) {
        long[] diff = new long[nums.length + 1];
        for (int i = 0; i < n; i++) {
            diff[queries[i][0]] += queries[i][2];
            diff[queries[i][1] + 1] -= queries[i][2];
        }

        for (int i = 0; i < nums.length; i++) {
            if (i > 0) diff[i] += diff[i - 1];
            if (diff[i] < nums[i]) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        assert new Solution().minZeroArray(new int[]{2,0,2}, new int[][]{{0,2,1},{0,2,1},{1,1,3}}) == 2;
        assert new Solution().minZeroArray(new int[]{4,3,2,1}, new int[][]{{1,3,2},{0,2,1}}) == -1;
    }

}
