package weekly.bw137.B;

/**
 * 3255. Find the Power of K-Size Subarrays II
 *
 * https://leetcode.cn/contest/biweekly-contest-137/problems/find-the-power-of-k-size-subarrays-ii/
 *
 * You are given an array of integers nums of length n and a positive integer k.
 *
 * The power of an array is defined as:
 *
 * Its maximum element if all of its elements are consecutive and sorted in ascending order.
 * -1 otherwise.
 * You need to find the power of all subarrays of nums of size k.
 *
 * Return an integer array results of size n - k + 1, where results[i] is the power of nums[i..(i + k - 1)].
 */

/** @noinspection DuplicatedCode*/
public class Solution {

    public int[] resultsArray(int[] nums, int k) {
        if (nums.length == 1 || k == 1) return nums;

        int[] ans = new int[nums.length - k + 1];
        for (int i = 1, d1 = 0; i < nums.length; i++) {
            if (nums[i] - nums[i - 1] == 1) d1++;
            if (i >= k - 1) ans[i - k + 1] = (d1 == k - 1) ? nums[i] : -1;
            if (i >= k - 1 && nums[i - k + 2] - nums[i - k + 1] == 1) d1--;
        }

        return ans;
    }

    public static void main(String[] args) {
    }

}
