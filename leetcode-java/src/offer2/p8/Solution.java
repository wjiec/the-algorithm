package offer2.p8;

/**
 * 剑指 Offer II 008. 和大于等于 target 的最短子数组
 *
 * https://leetcode.cn/problems/2VG8Kg/
 *
 * 给定一个含有 n 个正整数的数组和一个正整数 target 。
 *
 * 找出该数组中满足其和 ≥ target 的长度最小的 连续子数组 [numsl, numsl+1, ..., numsr-1, numsr] ，并返回其长度。
 * 如果不存在符合条件的子数组，返回 0 。
 */

@SuppressWarnings("DuplicatedCode")
public class Solution {

    public int minSubArrayLen(int target, int[] nums) {
        int ans = Integer.MAX_VALUE, n = nums.length;
        for (int l = 0, r = 0, s = 0; r < n; r++) {
            s += nums[r];
            while (s - nums[l] >= target) s -= nums[l++];
            if (s >= target) ans = Math.min(ans, r - l + 1);
        }
        return ans == Integer.MAX_VALUE ? 0 : ans;
    }

    public static void main(String[] args) {
        assert new Solution().minSubArrayLen(7, new int[]{2,3,1,2,4,3}) == 2;
        assert new Solution().minSubArrayLen(4, new int[]{1,4,4}) == 1;
        assert new Solution().minSubArrayLen(11, new int[]{1,1,1,1,1,1,1,1}) == 0;
    }

}
