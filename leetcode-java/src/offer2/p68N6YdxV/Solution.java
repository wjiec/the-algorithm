package offer2.p68N6YdxV;

/**
 * 剑指 Offer II 068. 查找插入位置
 *
 * https://leetcode-cn.com/problems/N6YdxV/
 *
 * 给定一个排序的整数数组 nums 和一个整数目标值 target ，请在数组中找到 target ，并返回其下标。
 *
 * 如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
 *
 * 请必须使用时间复杂度为 O(log n) 的算法。
 */

public class Solution {

    public int searchInsert(int[] nums, int target) {
        int l = 0, r = nums.length;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] < target) l = mid + 1;
            else if (nums[mid] > target) r = mid;
            else return mid;
        }
        return l;
    }

    public static void main(String[] args) {
        assert new Solution().searchInsert(new int[]{1,3,5,6}, 5) == 2;
        assert new Solution().searchInsert(new int[]{1,3,5,6}, 2) == 1;
        assert new Solution().searchInsert(new int[]{1,3,5,6}, 7) == 4;
        assert new Solution().searchInsert(new int[]{1,3,5,6}, 0) == 0;
        assert new Solution().searchInsert(new int[]{1}, 0) == 0;
    }

}
