package weekly.w312.B;

/**
 * 6189. Longest Subarray With Maximum Bitwise AND
 *
 * https://leetcode.cn/contest/weekly-contest-312/problems/longest-subarray-with-maximum-bitwise-and/
 *
 * You are given an integer array nums of size n.
 *
 * Consider a non-empty subarray from nums that has the maximum possible bitwise AND.
 *
 * In other words, let k be the maximum value of the bitwise AND of any subarray of nums.
 * Then, only subarrays with a bitwise AND equal to k should be considered.
 *
 * Return the length of the longest such subarray.
 *
 * The bitwise AND of an array is the bitwise AND of all the numbers in it.
 * A subarray is a contiguous sequence of elements within an array.
 */

public class Solution {

    public int longestSubarray(int[] nums) {
        int max = 0, n = nums.length;
        for (var v : nums) max = Math.max(max, v);

        int ans = 0;
        for (int i = 0; i < n; ) {
            if (nums[i] == max) {
                int v = nums[i], curr = 0;
                while (i < n && (nums[i] & v) == v) { i++; curr++; }
                ans = Math.max(ans, curr);
            } else i++;
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().longestSubarray(new int[]{1,2,3,3,2,2}) == 2;
        assert new Solution().longestSubarray(new int[]{1,2,3,4}) == 4;
    }

}
