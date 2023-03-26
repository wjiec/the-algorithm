package weekly.bw100.C;

import java.util.Arrays;

/**
 * 2593. Find Score of an Array After Marking All Elements
 *
 * https://leetcode.cn/contest/biweekly-contest-100/problems/find-score-of-an-array-after-marking-all-elements/
 *
 * You are given an array nums consisting of positive integers.
 *
 * Starting with score = 0, apply the following algorithm:
 *
 * Choose the smallest integer of the array that is not marked.
 * If there is a tie, choose the one with the smallest index.
 *
 * Add the value of the chosen integer to score.
 * Mark the chosen element and its two adjacent elements if they exist.
 * Repeat until all the array elements are marked.
 * Return the score you get after applying the above algorithm.
 */

public class Solution {

    public long findScore(int[] nums) {
        int n = nums.length;
        Integer[] sorted = new Integer[n];
        for (int i = 0; i < n; i++) sorted[i] = i;
        Arrays.sort(sorted, (a, b) -> nums[a] == nums[b] ? a - b : nums[a] - nums[b]);

        long ans = 0;
        boolean[] marked = new boolean[n];
        for (var idx : sorted) {
            if (!marked[idx]) {
                ans += nums[idx];
                marked[idx] = true;
                if (idx - 1 >= 0) marked[idx - 1] = true;
                if (idx + 1 < n) marked[idx + 1] = true;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
