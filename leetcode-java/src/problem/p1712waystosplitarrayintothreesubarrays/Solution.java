package problem.p1712waystosplitarrayintothreesubarrays;

import common.TODO;

/**
 * 1712. Ways to Split Array Into Three Subarrays
 *
 * https://leetcode.cn/problems/ways-to-split-array-into-three-subarrays/
 *
 * A split of an integer array is good if:
 *
 * The array is split into three non-empty contiguous subarrays - named left, mid, right
 * respectively from left to right.
 *
 * The sum of the elements in left is less than or equal to the sum of the elements in mid, and
 * the sum of the elements in mid is less than or equal to the sum of the elements in right.
 *
 * Given nums, an array of non-negative integers, return the number of good ways to split nums.
 * As the number may be too large, return it modulo 109 + 7.
 */

public class Solution {

    @TODO
    public int waysToSplit(int[] nums) {
        int n = nums.length;
        int[] sum = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            sum[i] = sum[i - 1] + nums[i - 1];
        }

        int ans = 0, MOD = 1_000_000_007, lMax = sum[n] / 3;
        for (int i = 1, st = 2, ed = 2; i <= n - 2 && sum[i] <= lMax; i++) {
            st = Math.max(st, i + 1);
            int midMin = sum[i] * 2, midMax = sum[i] + (sum[n] - sum[i]) / 2;
            while (st < n && sum[st] < midMin) st++;

            ed = Math.max(ed, st - 1);
            while (ed < n - 1 && sum[ed + 1] <= midMax) ed++;

            ans = (ans + (ed - st + 1)) % MOD;
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().waysToSplit(new int[]{1,1,1}) == 1;
        assert new Solution().waysToSplit(new int[]{1,2,2,2,5,0}) == 3;
        assert new Solution().waysToSplit(new int[]{3,2,1}) == 0;
    }

}
