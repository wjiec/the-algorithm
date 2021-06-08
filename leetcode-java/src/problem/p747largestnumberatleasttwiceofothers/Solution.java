package problem.p747largestnumberatleasttwiceofothers;

/**
 * 747. Largest Number At Least Twice of Others
 *
 * https://leetcode-cn.com/problems/largest-number-at-least-twice-of-others/
 *
 * You are given an integer array nums where the largest integer is unique.
 *
 * Determine whether the largest element in the array is at least twice as much as every other number in the array.
 * If it is, return the index of the largest element, or return -1 otherwise.
 */

public class Solution {

    public int dominantIndex(int[] nums) {
        int n = nums.length, max = 0, index = 0, remain = 0;
        if (n == 1) return index;
        for (int i = 0; i < n; i++) {
            if (nums[i] > max) {
                remain = max;
                max = nums[i];
                index = i;
            } else if (nums[i] > remain) {
                remain = nums[i];
            }
        }

        return remain * 2 <= max ? index : -1;
    }

    public static void main(String[] args) {
        assert new Solution().dominantIndex(new int[]{3,6,1,0}) == 1;
        assert new Solution().dominantIndex(new int[]{1,2,3,4}) == -1;
        assert new Solution().dominantIndex(new int[]{1}) == 0;
        assert new Solution().dominantIndex(new int[]{6,3,1,0,100}) == 4;
        assert new Solution().dominantIndex(new int[]{7,4,1,0,2}) == -1;
    }

}
