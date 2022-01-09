package problem.p2099findsubsequenceoflengthkwiththelargestsum;

import common.Checker;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 2099. Find Subsequence of Length K With the Largest Sum
 *
 * https://leetcode-cn.com/problems/find-subsequence-of-length-k-with-the-largest-sum/
 *
 * You are given an integer array nums and an integer k. You want to find a subsequence of
 * nums of length k that has the largest sum.
 *
 * Return any such subsequence as an integer array of length k.
 *
 * A subsequence is an array that can be derived from another array by deleting
 * some or no elements without changing the order of the remaining elements.
 */

public class Solution {

    public int[] maxSubsequence(int[] nums, int k) {
        int[][] map = new int[nums.length][2];
        for (int i = 0; i < nums.length; i++) {
            map[i][0] = i; map[i][1] = nums[i];
        }

        Arrays.sort(map, (a, b) -> b[1] - a[1]);
        Arrays.sort(map, 0, k, Comparator.comparingInt(v -> v[0]));

        int[] ans = new int[k];
        for (int i = 0; i < k; i++) ans[i] = map[i][1];
        return ans;
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().maxSubsequence(new int[]{2,1,3,3}, 2), new int[]{3,3});
        assert Checker.check(new Solution().maxSubsequence(new int[]{-1,-2,3,4}, 3), new int[]{-1,3,4});
        assert Checker.check(new Solution().maxSubsequence(new int[]{3,4,3,3}, 2), new int[]{3,4});
    }

}
