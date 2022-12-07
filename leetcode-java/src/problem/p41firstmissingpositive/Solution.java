package problem.p41firstmissingpositive;

/**
 * 41. First Missing Positive
 *
 * https://leetcode.cn/problems/first-missing-positive/
 *
 * Given an unsorted integer array nums, return the smallest missing positive integer.
 *
 * You must implement an algorithm that runs in O(n) time and uses constant extra space.
 */

public class Solution {

    public int firstMissingPositive(int[] nums) {
        int n = nums.length;
        // 最小为 1 , 最大为 n, 小于等于 0 和 大于 n 的可以不用管
        for (int i = 0; i < n; i++) {
            // 把数组中的所有数都变成正数
            if (nums[i] <= 0) nums[i] = n + 1;
        }

        for (int i = 0; i < nums.length; i++) {
            int v = Math.abs(nums[i]);
            if (v > 0 && v <= n && nums[v - 1] > 0) {
                nums[v - 1] = -nums[v - 1];
            }
        }

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) return i + 1;
        }
        return n + 1;
    }

    public static void main(String[] args) {
        assert new Solution().firstMissingPositive(new int[]{1,1}) == 2;

        assert new Solution().firstMissingPositive(new int[]{1,2,0}) == 3;
        assert new Solution().firstMissingPositive(new int[]{3,4,-1,1}) == 2;
        assert new Solution().firstMissingPositive(new int[]{7,8,9,11,12}) == 1;
        assert new Solution().firstMissingPositive(new int[]{4,3,2}) == 1;
        assert new Solution().firstMissingPositive(new int[]{4,3,2,1}) == 5;
        assert new Solution().firstMissingPositive(new int[]{1,2,3,4}) == 5;
    }

}
