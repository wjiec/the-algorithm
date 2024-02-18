package weekly.w384.D;

/**
 * 3034. Number of Subarrays That Match a Pattern II
 *
 * https://leetcode.cn/contest/weekly-contest-384/problems/number-of-subarrays-that-match-a-pattern-ii/
 *
 * You are given a 0-indexed integer array nums of size n, and a 0-indexed integer array
 * pattern of size m consisting of integers -1, 0, and 1.
 *
 * A subarray nums[i..j] of size m + 1 is said to match the pattern if the following
 * conditions hold for each element pattern[k]:
 *
 * nums[i + k + 1] > nums[i + k] if pattern[k] == 1.
 * nums[i + k + 1] == nums[i + k] if pattern[k] == 0.
 * nums[i + k + 1] < nums[i + k] if pattern[k] == -1.
 *
 * Return the count of subarrays in nums that match the pattern.
 */

public class Solution {

    public int countMatchingSubarrays(int[] nums, int[] pattern) {
        int[] next = new int[pattern.length];
        for (int i = 1, j = 0; i < pattern.length; i++) {
            while (j > 0 && pattern[j] != pattern[i]) j = next[j - 1];

            if (pattern[j] == pattern[i]) j++;
            next[i] = j;
        }

        int ans = 0;
        for (int i = 1, j = 0; i < nums.length; i++) {
            int v = Integer.compare(nums[i], nums[i - 1]);
            while (j > 0 && pattern[j] != v) j = next[j - 1];

            if (v == pattern[j]) j++;
            if (j == pattern.length) {
                ans++;
                j = next[j - 1];
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().countMatchingSubarrays(new int[]{1,2,3,4,5,6}, new int[]{1,1}) == 4;
    }

}
