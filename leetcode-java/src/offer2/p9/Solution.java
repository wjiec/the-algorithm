package offer2.p9;

/**
 * 剑指 Offer II 009. 乘积小于 K 的子数组
 *
 * https://leetcode.cn/problems/ZVAVXX/
 *
 * 给定一个正整数数组 nums和整数 k ，请找出该数组内乘积小于 k 的连续的子数组的个数。
 */

@SuppressWarnings("DuplicatedCode")
public class Solution {

    public int numSubarrayProductLessThanK(int[] nums, int k) {
        int ans = 0, n = nums.length;
        for (int l = 0, r = 0, s = 1; r < n; r++) {
            s *= nums[r];
            while (l <= r && s >= k) {
                s /= nums[l++];
            }
            ans += r - l + 1;
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().numSubarrayProductLessThanK(new int[]{10,5,2,6}, 100) == 8;
        assert new Solution().numSubarrayProductLessThanK(new int[]{1,2,3}, 0) == 0;
    }

}
