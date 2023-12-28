package weekly.bw120.C;

/**
 * 2972. Count the Number of Incremovable Subarrays II
 *
 * https://leetcode.cn/contest/biweekly-contest-120/problems/count-the-number-of-incremovable-subarrays-ii/
 *
 * You are given a 0-indexed array of positive integers nums.
 *
 * A subarray of nums is called incremovable if nums becomes strictly increasing on removing the subarray.
 *
 * For example, the subarray [3, 4] is an incremovable subarray of [5, 3, 4, 6, 7] because removing this
 * subarray changes the array [5, 3, 4, 6, 7] to [5, 6, 7] which is strictly increasing.
 *
 * Return the total number of incremovable subarrays of nums.
 *
 * Note that an empty array is considered strictly increasing.
 *
 * A subarray is a contiguous non-empty sequence of elements within an array.
 */

public class Solution {

    public long incremovableSubarrayCount(int[] nums) {
        int n = nums.length, i = 0;
        while (i < n - 1 && nums[i] < nums[i + 1]) i++;
        if (i == n - 1) return (long) n * (n + 1) / 2;

        long ans = i + 2;
        for (int j = n - 1; (j == n - 1) || (nums[j] < nums[j + 1]); j--) {
            while (i >= 0 && nums[i] >= nums[j]) i--;
            ans += i + 2;
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
