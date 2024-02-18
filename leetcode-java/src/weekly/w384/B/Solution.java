package weekly.w384.B;

/**
 * 3034. Number of Subarrays That Match a Pattern I
 *
 * https://leetcode.cn/contest/weekly-contest-384/problems/number-of-subarrays-that-match-a-pattern-i/
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
        int ans = 0, n = nums.length, m = pattern.length;
        for (int i = 1; i <= n - m; i++) {
            boolean matched = true;

            next:
            for (int j = 0, k = i; j < m; j++, k++) {
                switch (pattern[j]) {
                    case 1:
                        if (nums[k] <= nums[k - 1]) {
                            matched = false;
                            break next;
                        }
                        break;
                    case 0:
                        if (nums[k] != nums[k - 1]) {
                            matched = false;
                            break next;
                        }
                        break;
                    case -1:
                        if (nums[k] >= nums[k - 1]) {
                            matched = false;
                            break next;
                        }
                        break;
                }
            }

            if (matched) ans++;
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().countMatchingSubarrays(new int[]{1,2,3,4,5,6}, new int[]{1,1}) == 4;
    }

}
