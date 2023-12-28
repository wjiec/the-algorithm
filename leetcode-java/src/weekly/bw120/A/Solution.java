package weekly.bw120.A;

/**
 * 2970. Count the Number of Incremovable Subarrays I
 *
 * https://leetcode.cn/contest/biweekly-contest-120/problems/count-the-number-of-incremovable-subarrays-i/
 *
 * You are given a 0-indexed array of positive integers nums.
 *
 * A subarray of nums is called incremovable if nums becomes strictly increasing on removing the subarray.
 *
 * For example, the subarray [3, 4] is an incremovable subarray of [5, 3, 4, 6, 7] because removing
 * this subarray changes the array [5, 3, 4, 6, 7] to [5, 6, 7] which is strictly increasing.
 *
 * Return the total number of incremovable subarrays of nums.
 *
 * Note that an empty array is considered strictly increasing.
 *
 * A subarray is a contiguous non-empty sequence of elements within an array.
 */

public class Solution {

    public int incremovableSubarrayCount(int[] nums) {
        int ans = 0, n = nums.length;
        for (int l = 0; l < n; l++) {
            for (int r = l; r < n; r++) {
                if (check(nums, l, r)) ans++;
            }
        }
        return ans;
    }

    private boolean check(int[] nums, int l, int r) {
        int prev = -1, n = nums.length;
        for (int i = 0; i < n; i++) {
            if (i >= l && i <= r) continue;
            if (nums[i] <= prev) return false;
            prev = nums[i];
        }
        return true;
    }

    public static void main(String[] args) {
    }

}
