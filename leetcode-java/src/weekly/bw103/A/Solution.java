package weekly.bw103.A;

/**
 * 2656. Maximum Sum With Exactly K Elements
 *
 * https://leetcode.cn/contest/biweekly-contest-103/problems/maximum-sum-with-exactly-k-elements/
 *
 * You are given a 0-indexed integer array nums and an integer k. Your task is to perform
 * the following operation exactly k times in order to maximize your score:
 *
 * Select an element m from nums.
 * Remove the selected element m from the array.
 * Add a new element with a value of m + 1 to the array.
 * Increase your score by m.
 * Return the maximum score you can achieve after performing the operation exactly k times.
 */

public class Solution {

    public int maximizeSum(int[] nums, int k) {
        int max = 0;
        for (var v : nums) max = Math.max(max, v);
        return k * (max + max + k - 1) / 2;
    }

}
