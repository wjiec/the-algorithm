package weekly.bw108.A;

/**
 * 6913. Longest Alternating Subarray
 *
 * https://leetcode.cn/contest/biweekly-contest-108/problems/longest-alternating-subarray/
 *
 * You are given a 0-indexed integer array nums. A subarray s of length m is called alternating if:
 *
 * m is greater than 1.
 * s1 = s0 + 1.
 * The 0-indexed subarray s looks like [s0, s1, s0, s1,...,s(m-1) % 2].
 * In other words, s1 - s0 = 1, s2 - s1 = -1, s3 - s2 = 1, s4 - s3 = -1,
 * and so on up to s[m - 1] - s[m - 2] = (-1)m.
 *
 * Return the maximum length of all alternating subarrays present in nums or -1 if no such subarray exists.
 *
 * A subarray is a contiguous non-empty sequence of elements within an array.
 */

public class Solution {

    public int alternatingSubarray(int[] nums) {
        int ans = 0, n = nums.length;
        for (int i = 1; i < n; i++) {
            if (nums[i - 1] + 1 == nums[i]) {
                int c = 2;
                for (int j = i + 1; j < n; j++) {
                    if (nums[j] == nums[j - 2]) c++;
                    else break;
                }
                ans = Math.max(ans, c);
            }
        }
        return ans == 0 ? -1 : ans;
    }

    public static void main(String[] args) {
    }

}
