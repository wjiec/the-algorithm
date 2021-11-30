package problem.p1984minimumdifferencebetweenhighestandlowestofkscores;

import java.util.Arrays;

/**
 * 1984. Minimum Difference Between Highest and Lowest of K Scores
 *
 * https://leetcode-cn.com/problems/minimum-difference-between-highest-and-lowest-of-k-scores/
 *
 * You are given a 0-indexed integer array nums, where nums[i] represents the score of the ith student.
 *
 * You are also given an integer k.
 *
 * Pick the scores of any k students from the array so that the difference between
 * the highest and the lowest of the k scores is minimized.
 *
 * Return the minimum possible difference.
 */

public class Solution {

    public int minimumDifference(int[] nums, int k) {
        if (k == 1) return 0;

        Arrays.sort(nums);

        int ans = Integer.MAX_VALUE;
        for (int i = 0, e = nums.length - k; i <= e; i++) {
            ans = Math.min(ans, nums[i + k - 1] - nums[i]);
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().minimumDifference(new int[]{90}, 1) == 0;
        assert new Solution().minimumDifference(new int[]{9,4,1,7}, 2) == 2;
    }

}
