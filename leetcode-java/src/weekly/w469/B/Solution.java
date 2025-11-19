package weekly.w469.B;

/**
 * Q2. Split Array With Minimum Difference
 *
 * https://leetcode.cn/contest/weekly-contest-469/problems/split-array-with-minimum-difference/
 *
 * You are given an integer array nums.
 *
 * Split the array into exactly two subarrays, left and right, such that left is strictly
 * increasing and right is strictly decreasing.
 *
 * Return the minimum possible absolute difference between the sums of left and right.
 * If no valid split exists, return -1.
 */

public class Solution {

    public long splitArray(int[] nums) {
        if (nums.length == 2) return Math.abs(nums[0] - nums[1]);

        int l = 1, r = nums.length - 2;
        while (l < nums.length && nums[l] > nums[l - 1]) l++;
        while (r >= 0 && nums[r] > nums[r + 1]) r--;

        // 此时左侧最长递增数组是 [0, l), 右侧最长的递减数组是 (r, n)
        //  - 如果 l <= r 那么中间还剩几个元素无法分配, 所以不存在有效的分割方案
        //  - 在 [r, l] 中选择一个位置进行分割
        if (l <= r) return -1;

        long total = 0, left = 0;
        for (int i = 0; i < nums.length; i++) {
            total += nums[i];
            if (i < r) left += nums[i];
        }

        long ans = total;
        for (int i = Math.max(r, 0); i < l; i++) {
            left += nums[i];
            ans = Math.min(ans, Math.abs(total - left - left));
        }

        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().splitArray(new int[]{9,5,4,2}) == 2;
        assert new Solution().splitArray(new int[]{29,30,28,27,21,18}) == 35;

        assert new Solution().splitArray(new int[]{1,1}) == 0;
        assert new Solution().splitArray(new int[]{1,1,1}) == -1;
        assert new Solution().splitArray(new int[]{1,2,3,2,2,1}) == -1;
        assert new Solution().splitArray(new int[]{1,3,2}) == 2;
        assert new Solution().splitArray(new int[]{1,2,4,3}) == 4;
    }

}
