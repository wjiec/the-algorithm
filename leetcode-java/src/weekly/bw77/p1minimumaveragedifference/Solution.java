package weekly.bw77.p1minimumaveragedifference;

/**
 * 6052. Minimum Average Difference
 *
 * https://leetcode-cn.com/contest/biweekly-contest-77/problems/minimum-average-difference/
 *
 * You are given a 0-indexed integer array nums of length n.
 *
 * The average difference of the index i is the absolute difference between the average of the
 * first i + 1 elements of nums and the average of the last n - i - 1 elements.
 *
 * Both averages should be rounded down to the nearest integer.
 *
 * Return the index with the minimum average difference. If there are multiple such indices, return the smallest one.
 *
 * Note:
 * The absolute difference of two numbers is the absolute value of their difference.
 * The average of n elements is the sum of the n elements divided (integer division) by n.
 * The average of 0 elements is considered to be 0.
 */

public class Solution {

    public int minimumAverageDifference(int[] nums) {
        long sum = 0; int n = nums.length;
        long[] prefix = new long[nums.length];
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            prefix[i] = sum;
        }

        long min = Integer.MAX_VALUE, ans = 0;
        for (int i = 0; i < n; i++) {
            long a = prefix[i] / (i + 1), b = (n - i - 1) == 0 ? 0 : (sum - prefix[i]) / (n - i - 1);
            long avg = Math.abs(a - b);
            if (avg < min) { min = avg; ans = i; }
        }

        return (int) ans;
    }

    public static void main(String[] args) {
        assert new Solution().minimumAverageDifference(new int[]{4,2,0}) == 2;
        assert new Solution().minimumAverageDifference(new int[]{2,5,3,9,5,3}) == 3;
    }

}
