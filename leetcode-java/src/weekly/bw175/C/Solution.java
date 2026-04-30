package weekly.bw175.C;

import common.Template;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Q3. Longest Strictly Increasing Subsequence With Non-Zero Bitwise AND
 *
 * https://leetcode.cn/contest/biweekly-contest-175/problems/longest-strictly-increasing-subsequence-with-non-zero-bitwise-and/
 *
 * You are given an integer array nums.
 *
 * Return the length of the longest strictly increasing subsequence in nums whose bitwise AND is non-zero.
 * If no such subsequence exists, return 0.
 */

@SuppressWarnings("DuplicatedCode")
public class Solution {

    public int longestSubsequence(int[] nums) {
        int mx = 0;
        for (var v : nums) mx = Math.max(mx, v);

        int ans = 0;
        // 也就是至少有一位都置位的情况下的最长严格递增子序列
        for (int i = 32 - Integer.numberOfLeadingZeros(mx); i >= 0; i--) {
            int mask = 1 << i;
            List<Integer> curr = new ArrayList<>();
            for (var v : nums) if ((v & mask) != 0) curr.add(v);
            if (curr.size() > ans) {
                ans = Math.max(ans, longestSubsequence(curr));
            }
        }
        return ans;
    }

    @Template({"最长递增子序列", "LIS"})
    private int longestSubsequence(List<Integer> nums) {
        int[] lis = new int[nums.size()]; int ans = 0;
        for (var v : nums) {
            var idx = Arrays.binarySearch(lis, 0, ans, v);
            if (idx < 0) idx = ~idx;
            if (idx < ans) lis[idx] = v; else lis[ans++] = v;
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
