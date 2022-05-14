package weekly.bw78.p1numberofwaystosplitarray;

/**
 * 6067. Number of Ways to Split Array
 *
 * https://leetcode.cn/contest/biweekly-contest-78/problems/number-of-ways-to-split-array/
 *
 * You are given a 0-indexed integer array nums of length n.
 *
 * nums contains a valid split at index i if the following are true:
 *
 * The sum of the first i + 1 elements is greater than or equal to the sum of the last n - i - 1 elements.
 * There is at least one element to the right of i. That is, 0 <= i < n - 1.
 * Return the number of valid splits in nums.
 */

public class Solution {

    public int waysToSplitArray(int[] nums) {
        long[] sum = new long[nums.length];
        for (int i = 0; i < nums.length; i++) {
            sum[i] = nums[i];
            if (i != 0) sum[i] += sum[i - 1];
        }

        int ans = 0;
        for (int i = 0; i < sum.length - 1; i++) {
            if (sum[i] >= sum[sum.length - 1] - sum[i]) ans++;
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
