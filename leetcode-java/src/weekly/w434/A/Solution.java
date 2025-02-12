package weekly.w434.A;

/**
 * 3432. Count Partitions with Even Sum Difference
 *
 * https://leetcode.cn/contest/weekly-contest-434/problems/count-partitions-with-even-sum-difference/
 *
 * You are given an integer array nums of length n.
 *
 * A partition is defined as an index i where 0 <= i < n - 1, splitting the array into
 * two non-empty subarrays such that:
 *
 * Left subarray contains indices [0, i].
 * Right subarray contains indices [i + 1, n - 1].
 *
 * Return the number of partitions where the difference between
 * the sum of the left and right subarrays is even.
 */

public class Solution {

    public int countPartitions(int[] nums) {
        int sum = 0;
        for (var v : nums) sum += v;

        int ans = 0, curr = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            curr += nums[i]; sum -= nums[i];
            if ((curr - sum) % 2 == 0) ans++;
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
