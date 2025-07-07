package weekly.w453.A;

/**
 * Q1. Transform Array to All Equal Elements
 *
 * https://leetcode.cn/contest/weekly-contest-453/problems/transform-array-to-all-equal-elements
 *
 * You are given an integer array nums of size n containing only 1 and -1, and an integer k.
 *
 * You can perform the following operation at most k times:
 *
 * Choose an index i (0 <= i < n - 1), and multiply both nums[i] and nums[i + 1] by -1.
 *
 * Note that you can choose the same index i more than once in different operations.
 *
 * Return true if it is possible to make all elements of the array equal after
 * at most k operations, and false otherwise.
 */

public class Solution {

    public boolean canMakeEqual(int[] nums, int k) {
        int[] nums1 = new int[nums.length];
        System.arraycopy(nums, 0, nums1, 0, nums.length);
        return canMakeEqual(nums1, k, 1) || canMakeEqual(nums, k, -1);
    }

    private boolean canMakeEqual(int[] nums, int k, int v) {
        int n = nums.length;
        for (int i = 0; i < n - 1; i++) {
            if (nums[i] != v) {
                if (--k < 0) return false;

                nums[i] *= -1;
                nums[i + 1] *= -1;
            }
        }
        return nums[n - 1] == v;
    }

    public static void main(String[] args) {
        assert !new Solution().canMakeEqual(new int[]{1, -1, 1, 1, -1, -1, -1, 1, -1}, 4);
    }

}
