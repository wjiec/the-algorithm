package weekly.bw127.A;

/**
 * 3095. Shortest Subarray With OR at Least K I
 *
 * https://leetcode.cn/contest/biweekly-contest-127/problems/shortest-subarray-with-or-at-least-k-i/
 *
 * You are given an array nums of non-negative integers and an integer k.
 *
 * An array is called special if the bitwise OR of all of its elements is at least k.
 *
 * Return the length of the shortest special non-empty subarray of nums, or return -1 if no special subarray exists.
 */

public class Solution {

    public int minimumSubarrayLength(int[] nums, int k) {
        int ans = Integer.MAX_VALUE, n = nums.length;
        for (int i = 0; i < n; i++) {
            int curr = 0;
            for (int j = i; j < n; j++) {
                curr |= nums[j];
                if (curr >= k) ans = Math.min(ans, j - i + 1);
            }
        }
        return ans > n ? -1 : ans;
    }

    public static void main(String[] args) {
    }

}
