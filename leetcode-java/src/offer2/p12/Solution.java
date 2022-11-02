package offer2.p12;

/**
 * 剑指 Offer II 012. 左右两边子数组的和相等
 *
 * https://leetcode-cn.com/problems/tvdfij/
 *
 * 给你一个整数数组 nums ，请计算数组的 中心下标 。
 *
 * 数组 中心下标 是数组的一个下标，其左侧所有元素相加的和等于右侧所有元素相加的和。
 *
 * 如果中心下标位于数组最左端，那么左侧数之和视为 0 ，因为在下标的左侧不存在元素。这一点对于中心下标位于数组最右端同样适用。
 *
 * 如果数组有多个中心下标，应该返回 最靠近左边 的那一个。如果数组不存在中心下标，返回 -1 。
 */

public class Solution {

    public int pivotIndex(int[] nums) {
        int l = nums.length, right = 0;
        for (var n : nums) right += n;

        for (int i = 0, left = 0; i < l; i++) {
            right -= nums[i];
            if (left == right) return i;
            left += nums[i];
        }
        return -1;
    }

    public static void main(String[] args) {
        assert new Solution().pivotIndex(new int[]{1,7,3,6,5,6}) == 3;
        assert new Solution().pivotIndex(new int[]{1, 2, 3}) == -1;
        assert new Solution().pivotIndex(new int[]{2, 1, -1}) == 0;
    }

}
