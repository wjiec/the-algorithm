package weekly.bw89.C;

/**
 * 6210. Minimize Maximum of Array
 *
 * https://leetcode.cn/contest/biweekly-contest-89/problems/minimize-maximum-of-array/
 *
 * You are given a 0-indexed array nums comprising of n non-negative integers.
 *
 * In one operation, you must:
 *
 * Choose an integer i such that 1 <= i < n and nums[i] > 0.
 * Decrease nums[i] by 1.
 * Increase nums[i - 1] by 1.
 *
 * Return the minimum possible value of the maximum integer of nums after
 * performing any number of operations.
 */

public class Solution {

    public int minimizeArrayValue(int[] nums) {
        long l = 0, r = 0, ans = 0;
        for (var n : nums) r = Math.max(r, n);

        while (l <= r) {
            long mid = l + (r - l) / 2;
            if (check(nums, mid)) {
                ans = mid;
                r = mid - 1;
            } else l = mid + 1;
        }
        return (int) ans;
    }

    private boolean check(int[] nums, long target) {
        long carry = 0;
        for (int i = nums.length - 1; i > 0; i--) {
            if (nums[i] + carry > target) {
                carry = nums[i] + carry - target;
            } else carry = 0;
        }
        return nums[0] + carry <= target;
    }

    public static void main(String[] args) {
        assert new Solution().minimizeArrayValue(new int[]{3,7,1,6}) == 5;
    }

}
