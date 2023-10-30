package weekly.bw116.C;

import java.util.ArrayList;
import java.util.List;

/**
 * 100042. Length of the Longest Subsequence That Sums to Target
 *
 * https://leetcode.cn/contest/biweekly-contest-116/problems/length-of-the-longest-subsequence-that-sums-to-target/
 *
 * You are given a 0-indexed array of integers nums, and an integer target.
 *
 * Return the length of the longest subsequence of nums that sums up to target.
 * If no such subsequence exists, return -1.
 *
 * A subsequence is an array that can be derived from another array by deleting
 * some or no elements without changing the order of the remaining elements.
 */

public class Solution {

    public int lengthOfLongestSubsequence(List<Integer> nums, int target) {
        int[] dp = new int[1001];
        nums.sort(Integer::compare);
        dp[nums.get(0)]++;

        for (int i = 1; i < nums.size() && nums.get(i) <= target; i++) {
            int v = nums.get(i);
            for (int j = dp.length - v - 1; j >= 0; j--) {
                if (dp[j] != 0 || j == 0) dp[v + j] = Math.max(dp[v + j], dp[j] + 1);
            }
        }

        return dp[target] == 0 ? -1 : dp[target];
    }

    public static void main(String[] args) {
        assert new Solution().lengthOfLongestSubsequence(new ArrayList<>(List.of(2,3)), 3) == 1;

        assert new Solution().lengthOfLongestSubsequence(new ArrayList<>(List.of(1,2,3,4,5)), 9) == 3;
        assert new Solution().lengthOfLongestSubsequence(new ArrayList<>(List.of(1,2,3,4,5)), 9) == 3;
        assert new Solution().lengthOfLongestSubsequence(new ArrayList<>(List.of(4,1,3,2,1,5)), 7) == 4;
        assert new Solution().lengthOfLongestSubsequence(new ArrayList<>(List.of(1,1,5,4,5)), 3) == -1;
    }

}
