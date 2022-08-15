package problem.p1493longestsubarrayof1safterdeletingoneelement;

/**
 * 1493. Longest Subarray of 1's After Deleting One Element
 *
 * https://leetcode.cn/problems/longest-subarray-of-1s-after-deleting-one-element/
 *
 * Given a binary array nums, you should delete one element from it.
 *
 * Return the size of the longest non-empty subarray containing only 1's in the resulting array.
 *
 * Return 0 if there is no such subarray.
 */

public class Solution {

    public int longestSubarray(int[] nums) {
        int ans = 0, n = nums.length, zero = 0;
        for (int l = 0, r = 0; r < n; r++) {
            if (nums[r] == 0) zero++;
            while (zero > 1) zero -= nums[l++] == 0 ? 1 : 0;
            ans = Math.max(ans, r - l + 1 - zero);
        }
        return ans == n ? n - 1 : ans;
    }

    public static void main(String[] args) {
        assert new Solution().longestSubarray(new int[]{1,1,0,1}) == 3;
        assert new Solution().longestSubarray(new int[]{0,1,1,1,0,1,1,0,1}) == 5;
        assert new Solution().longestSubarray(new int[]{1,1,1}) == 2;
    }

}
