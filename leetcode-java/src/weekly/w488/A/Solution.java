package weekly.w488.A;

/**
 * Q1. Count Dominant Indices
 *
 * https://leetcode.cn/contest/weekly-contest-488/problems/count-dominant-indices/
 *
 * You are given an integer array nums of length n.
 *
 * An element at index i is called dominant if: nums[i] > average(nums[i + 1], nums[i + 2], ..., nums[n - 1])
 *
 * Your task is to count the number of indices i that are dominant.
 *
 * The average of a set of numbers is the value obtained by adding all the numbers together and dividing
 * the sum by the total number of numbers.
 *
 * Note: The rightmost element of any array is not dominant.
 */

public class Solution {

    public int dominantIndices(int[] nums) {
        int sum = 0;
        for (var v : nums) sum += v;

        int ans = 0, n = nums.length;
        for (int i = 0; i < n - 1; i++) {
            sum -= nums[i];
            if (nums[i] > sum / (n - i - 1)) ans++;
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
